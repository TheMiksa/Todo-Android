package com.example.todoandroid

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoandroid.databinding.RvTodoItemBinding

class TodoListAdapter (private val todos: MutableList<TodoItem>): RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {
    inner class ViewHolder(private val todoItemBinding: RvTodoItemBinding)
        : RecyclerView.ViewHolder(todoItemBinding.root) {
        fun bind(item: TodoItem) {
            todoItemBinding.root.id = item.id
            todoItemBinding.run {
                tvText.text = item.todo
                cbIsDone.isChecked = item.isDone
                tvText.paintFlags =  if (item.isDone) Paint.STRIKE_THRU_TEXT_FLAG else 0
                btnBin.setOnClickListener {
                    deleteItem(item)
                }
                cbIsDone.setOnCheckedChangeListener { _, _ ->
                    item.isDone = !item.isDone
                    tvText.paintFlags =  if (item.isDone) Paint.STRIKE_THRU_TEXT_FLAG else 0

                }
            }
        }
    }

    fun deleteItem(item: TodoItem)  {
        val position = TodoManager.items.indexOf(item)
        if (position >= 0) {
            TodoManager.removeItem(item)
            notifyItemRemoved(position)
        }
    }
    override fun getItemCount(): Int {
        return todos.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo: TodoItem = todos[position]
        println("Adapter: $todo")
        holder.bind(todo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val todoBinding = RvTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(todoBinding)
    }
}