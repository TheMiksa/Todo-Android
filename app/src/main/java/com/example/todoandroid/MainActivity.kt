package com.example.todoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val todoList = TodoManager.items
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TodoListAdapter(todoList)
        binding.rvTodoList.adapter = adapter
        binding.btnAddItem.setOnClickListener {
            TodoManager.setItem(TodoItem("Todo: ${Id.getId()}", "main_todo_list"))
            println("${todoList.size}${todoList}")
            adapter.notifyItemInserted(todoList.size - 1)
        }
    }
}