package com.example.todoandroid

class TodoManager {
    companion object {
        val items = mutableListOf<TodoItem>()
        val setItem: (item: TodoItem) -> Unit = {it -> items.add(it)}
        val removeItem: (item: TodoItem) -> Unit = {item -> items.removeIf { it.id == item.id }}
    }
}