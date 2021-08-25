package com.example.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.ToDoDao
import com.example.todoapp.data.models.Todo

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<Todo>> = toDoDao.getAllData()
    val sortByHighPriority: LiveData<List<Todo>> = toDoDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<Todo>> = toDoDao.sortByLowPriority()

    suspend fun insertData(toDoData: Todo){
        toDoDao.insertData(toDoData)
    }

    suspend fun updateData(toDoData: Todo){
        toDoDao.updateData(toDoData)
    }

    suspend fun deleteItem(toDoData: Todo){
        toDoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){
        toDoDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Todo>> {
        return toDoDao.searchDatabase(searchQuery)
    }

}