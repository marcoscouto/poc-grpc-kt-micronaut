package com.marcoscouto.services

import com.marcoscouto.persistence.entities.TaskEntity
import java.util.*

interface TaskService {

    fun save(taskEntity: TaskEntity): TaskEntity

    fun findById(id: String): Optional<TaskEntity>

}