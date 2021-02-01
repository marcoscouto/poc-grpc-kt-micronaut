package com.marcoscouto.persistence.entities

import com.marcoscouto.Task
import com.marcoscouto.TaskSaveRequest
import com.marcoscouto.dto.TaskRequest
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "task")
data class TaskEntity (@Column(nullable = false) var title: String,
                       @Column(nullable = false) var message: String) {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    lateinit var id: String

    @CreationTimestamp
    lateinit var createdAt: LocalDateTime

    @Deprecated("Constructor used by hibernate")
    constructor() :
            this(String(), String())

    constructor(request: TaskRequest) :
            this(request.title, request.message)

    fun toTask(): Task {
        return Task.newBuilder()
            .setId(this.id)
            .setTitle(this.title)
            .setMessage(this.message)
            .build()
    }

}