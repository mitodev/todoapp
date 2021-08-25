package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.data.models.Todo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData():LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: Todo)

    @Update
    suspend fun updateData(toDoData: Todo)

    @Delete
    suspend fun deleteItem(toDoData: Todo)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN prio LIKE 'H%' THEN 1 WHEN prio LIKE 'M%' THEN 2 WHEN prio LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN prio LIKE 'L%' THEN 1 WHEN prio LIKE 'M%' THEN 2 WHEN prio LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<Todo>>


}