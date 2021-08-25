package com.example.todoapp.Fragment.add

import android.icu.text.CaseMap
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.data.SharedViewModel
import com.example.todoapp.data.models.Todo
import com.example.todoapp.data.models.prio
import com.example.todoapp.data.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private val mToDoViewModel:ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
         ): View? {

        //set menu
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        // ? this changes the color of the prio by the SHAREDVIEWMODEL class
        view.prio_spinner.onItemSelectedListener=mSharedViewModel.listener

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    //buttons on the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home ->
                findNavController().navigate(R.id.action_addFragment2_to_listFragment)

            R.id.menu_add->
                insertDataToDatabase()
        }
        return true
    }


    private fun insertDataToDatabase() {
        val mTitle= title_et.text.toString()
        val mPrio= prio_spinner.selectedItem.toString()
        val mDescription=description_et.text.toString()

        val priority= mSharedViewModel.presePrio(mPrio)

        if(mSharedViewModel.verify(mTitle,mDescription))
        {
            //insert data to database
            val newData= Todo(
                0,
                mTitle,
                priority,
                mDescription
            )
            //the stuff you do when the fun ends
            mToDoViewModel.insertData(newData)

            Toast.makeText(requireContext(),"you did it mf", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment2_to_listFragment)
        }else{Toast.makeText(requireContext(),"try again plz", Toast.LENGTH_SHORT).show()}
    }






}