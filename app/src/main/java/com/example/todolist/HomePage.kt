package com.example.todolist

import com.example.todolist.TasksAdapter

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePage : AppCompatActivity() {

    private val tasks = mutableListOf<String>()
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val taskEditText: EditText = findViewById(R.id.taskEditText)
        val floats: FloatingActionButton = findViewById(R.id.floats)
        val tasksRecyclerView: RecyclerView = findViewById(R.id.tasksRecyclerView)

        // Set up RecyclerView
        tasksAdapter = TasksAdapter(tasks) { position ->
            tasksAdapter.removeTask(position)
        }
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        tasksRecyclerView.adapter = tasksAdapter

        // Set up FloatingActionButton
        floats.setOnClickListener {
            val task = taskEditText.text.toString()
            if (task.isNotEmpty()) {
                tasks.add(task)
                tasksAdapter.notifyItemInserted(tasks.size - 1)
                taskEditText.text.clear()
            }
        }
    }
}
