package com.example.todoapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import com.example.todoapp.data.models.prio
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todo_table")
@Parcelize
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var Prio: prio,
    var description:String
) : Parcelable
