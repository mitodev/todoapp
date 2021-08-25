package com.example.todoapp.data

import androidx.room.TypeConverter
import com.example.todoapp.data.models.prio

class Converter {

    @TypeConverter
    fun fromprio(Prio: prio): String{
        return Prio.name
    }
    @TypeConverter
    fun toprio(Prio :String): prio {
        return prio.valueOf(Prio)
    }
}