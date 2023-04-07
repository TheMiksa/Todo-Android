package com.example.todoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            val newDuty = binding.etNewDuty.text.trim()

            if (newDuty.isNotEmpty()) {
                binding.etNewDuty.setText("")
                TodoManager.setItem(TodoItem("$newDuty", "main_todo_list"))
                println("${todoList.size}${todoList}")
                adapter.notifyItemInserted(todoList.size - 1)
            } else {
                Toast.makeText(
                    this,
                    "Please, add some text for the new duty =)",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }
}