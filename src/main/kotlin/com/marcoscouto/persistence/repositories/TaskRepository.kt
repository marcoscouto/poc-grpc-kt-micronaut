package com.marcoscouto.persistence.repositories

import com.marcoscouto.persistence.entities.TaskEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface TaskRepository : CrudRepository<TaskEntity, String>