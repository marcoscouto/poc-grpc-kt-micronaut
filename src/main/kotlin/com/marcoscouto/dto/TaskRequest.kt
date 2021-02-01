package com.marcoscouto.dto

import com.marcoscouto.TaskSaveRequest
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class TaskRequest(

    @field:[NotBlank(message = "Título é obrigatório") Size(min = 6, message = "Título tem que ter ao menos 6 caracteres")]
    var title: String,

    @field:[NotBlank(message = "Mensagem é obrigatória")]
    var message: String

    ) {

    constructor(taskSaveRequest: TaskSaveRequest):
            this(taskSaveRequest.title, taskSaveRequest.message)

}


