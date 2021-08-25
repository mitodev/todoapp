package com.example.todoapp.data

import android.content.Context
import androidx.room.*
import com.example.todoapp.data.models.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class database : RoomDatabase() {
    abstract fun ToDoDao(): ToDoDao

    companion object{
        @Volatile
        private var INSTANCE: database?=null

        fun getDatabase(context: Context):database{

            val tempinstence = INSTANCE

            if (tempinstence != null){
                return tempinstence
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    database :: class.java,
                    "todo_database").build()
                INSTANCE=instance
                return  instance
            }


        }
    }
}