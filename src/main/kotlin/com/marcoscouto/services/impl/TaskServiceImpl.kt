package com.marcoscouto.services.impl

import com.marcoscouto.persistence.entities.TaskEntity
import com.marcoscouto.persistence.repositories.TaskRepository
import com.marcoscouto.services.TaskService
import io.micronaut.grpc.annotation.GrpcService
import org.slf4j.LoggerFactory
import java.util.*

@GrpcService
class TaskServiceImpl(val repository: TaskRepository): TaskService {

    val log = LoggerFactory.getLogger(TaskServiceImpl::class.java)

    override fun save(taskEntity: TaskEntity): TaskEntity {
        log.info("[TASK] Saving task $taskEntity")
        val taskSaved = this.repository.save(taskEntity)
        log.info("[TASK] Task saved id = ${taskSaved.id}")
        return taskSaved
    }

    override fun findById(id: String): Optional<TaskEntity> {
        log.info("[TASK] Find task by id $id")
        return this.repository.findById(id)
    }

}