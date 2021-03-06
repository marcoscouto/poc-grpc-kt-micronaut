package com.marcoscouto.endpoints

import com.marcoscouto.Task
import com.marcoscouto.TaskFindByIdRequest
import com.marcoscouto.TaskSaveRequest
import com.marcoscouto.TaskServiceGrpcKt
import com.marcoscouto.dto.TaskRequest
import com.marcoscouto.persistence.entities.TaskEntity
import com.marcoscouto.services.TaskService
import io.grpc.Status
import io.micronaut.validation.validator.Validator
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class TaskEndpoint(val service: TaskService, val validator: Validator): TaskServiceGrpcKt.TaskServiceCoroutineImplBase() {

    val log = LoggerFactory.getLogger(TaskEndpoint::class.java)

    override suspend fun save(request: TaskSaveRequest): Task {
        log.info("[TASK] Save task requested")
        val dto = TaskRequest(request)
        val validation = validator.validate(dto)

        if(validation.isNotEmpty()){
            validation.forEach { log.error("[TASK] Task request invalid: ${it.message}") }
            throw Status.INVALID_ARGUMENT
                .withDescription("Erro de validação")
                .asException()
        }

        val entity = TaskEntity(dto)
        val saved = this.service.save(entity)
        return saved.toTask()
    }

    override suspend fun findById(request: TaskFindByIdRequest): Task {
        log.info("[TASK] Find by id requested")
        val searched = this.service.findById(request.id)
        if(searched.isEmpty){
            log.error("[TASK] Task not founded ${request.id}")
            throw Status.NOT_FOUND.asException()
        }
        log.info("[TASK] Task founded ${request.id}")
        return searched.get().toTask()
    }
}