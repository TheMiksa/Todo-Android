package com.example.todoandroid


data class TodoItem(
    val todo: String,
    val groupId: String,
    val id: Int =  Id.getQniqIbyGroup(groupId),
    var isDone: Boolean = false
)
