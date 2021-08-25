package com.example.todoapp.data

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.R
import com.example.todoapp.data.models.prio

class SharedViewModel(application: Application): AndroidViewModel(application) {

    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when(position){
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red)) }
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow)) }
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green)) }
            }
        }
    }

    fun presePrio(priority:String): prio {

        return when(priority)
        {
            "High"->{
                prio.High}
            "Med"->{
                prio.Mid}
            "Low"->{
                prio.Low}
            else -> prio.Low
        }

    }

    fun verify( title: String, Dis: String): Boolean {
        return if(TextUtils.isEmpty(title) && TextUtils.isEmpty(Dis))
        {
            false }
        else !(title.isEmpty()&& Dis.isEmpty())
        { true}
    }
}