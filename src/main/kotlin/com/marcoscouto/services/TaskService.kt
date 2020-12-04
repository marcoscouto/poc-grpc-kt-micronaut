package com.marcoscouto.services

import com.marcoscouto.persistence.entities.TaskEntity

interface TaskService {
    fun save(taskEntity: TaskEntity): TaskEntity
}