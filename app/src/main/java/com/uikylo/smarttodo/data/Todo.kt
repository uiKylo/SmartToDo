package com.uikylo.smarttodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Priority {
    LOW, MEDIUM, HIGH
}

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val note: String = "",
    val priority: Priority = Priority.MEDIUM,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null
)
