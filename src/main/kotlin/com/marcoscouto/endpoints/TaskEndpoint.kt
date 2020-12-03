package com.marcoscouto.endpoints

import com.marcoscouto.Task
import com.marcoscouto.TaskRequest
import com.marcoscouto.TaskServiceGrpcKt
import java.util.*
import javax.inject.Singleton

@Singleton
class TaskEndpoint: TaskServiceGrpcKt.TaskServiceCoroutineImplBase() {

    override suspend fun save(request: TaskRequest): Task {
        return Task
                .newBuilder()
                .setId(UUID.randomUUID().toString())
                .setTitle(request.title)
                .setMessage(request.message)
                .build()
    }
}