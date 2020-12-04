package com.marcoscouto.endpoints

import com.marcoscouto.Task
import com.marcoscouto.TaskRequest
import com.marcoscouto.TaskServiceGrpcKt
import com.marcoscouto.persistence.entities.TaskEntity
import com.marcoscouto.services.TaskService
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class TaskEndpoint(val service: TaskService): TaskServiceGrpcKt.TaskServiceCoroutineImplBase() {

    val log = LoggerFactory.getLogger(TaskEndpoint::class.java)

    override suspend fun save(request: TaskRequest): Task {
        log.info("[TASK] Save task requested")
        val entity = TaskEntity(request)
        val saved = this.service.save(entity)
        return saved.toTask()
    }
}