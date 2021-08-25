package com.example.todoapp.Fragment.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.data.SharedViewModel
import com.example.todoapp.data.models.Todo
import com.example.todoapp.data.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class updateFragment : Fragment() {


    private val args by navArgs<updateFragmentArgs>()
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        // ? this changes the color of the prio by the SHAREDVIEWMODEL class
        view.current_prio_spinner.onItemSelectedListener=mSharedViewModel.listener

        //menu
        setHasOptionsMenu(true)

        // setting the args values from the list view to the current values
        view.current_title_et.setText(args.currentitem.title)
        view.current_description_et.setText(args.currentitem.description)
        view.current_prio_spinner.setSelection(prio())

        return view

    }

    fun prio() : Int
    {
        var x = 0
        args.currentitem.Prio.name
        when(args.currentitem.Prio.name)
        {
            "High"-> x=0
            "Mid"-> x=1
            "Low"-> x=2
        }
        return x
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            R.id.menu_save-> saveChanges()
            R.id.menu_delete-> deleteCurrentItem()

        }
        return true
    }

    private fun deleteCurrentItem() {

        val Id = args.currentitem.id
        val Title= current_title_et.text.toString()
        val Prio= mSharedViewModel.presePrio(current_prio_spinner.selectedItem.toString())
        val Description=current_description_et.text.toString()

        val newData= Todo(
            Id,
            Title,
            Prio,
            Description
        )
        mToDoViewModel.deleteItem(newData)

        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        
        Toast.makeText(requireContext(),"you ve deleted it mf", Toast.LENGTH_SHORT).show()
    }

    //saving the changes
    private fun saveChanges()
    {
        val mId = args.currentitem.id
        val mTitle= current_title_et.text.toString()
        val mPrio= current_prio_spinner.selectedItem.toString()
        val mDescription=current_description_et.text.toString()

        val priority= mSharedViewModel.presePrio(mPrio)

        if(mSharedViewModel.verify(mTitle,mDescription)) {
            //update data to database
            val newData = Todo(
                mId,
                mTitle,
                priority,
                mDescription
            )
            //the stuff you do when the fun ends(updating)
            mToDoViewModel.updateData(newData)
        }

        findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        Toast.makeText(requireContext(),"you did it mf", Toast.LENGTH_SHORT).show()
    }


}