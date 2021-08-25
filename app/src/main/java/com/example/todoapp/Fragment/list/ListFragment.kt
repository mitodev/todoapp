package com.example.todoapp.Fragment.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todoapp.R
import com.example.todoapp.data.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private val adapter : ListAdapter by lazy { ListAdapter() }

    private val mToDoViewModel :ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ? Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // ? when clicking on the + btn we go to the next fragment(ADD)
        view.floatingActionButton3.setOnClickListener{
                findNavController().navigate(R.id.action_listFragment_to_addFragment2)
            }

        // ? setting the recycler view with the adapter and getting all the data from the vm class
        val recyclerView = view.recyclerView
        recyclerView.adapter= adapter
        recyclerView.layoutManager= GridLayoutManager(requireActivity(),2)
        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer
        { data->
            adapter.setData(data)
            setvisibility()})

        // ? setting the menu to have options that we make on the menu xml folder
        setHasOptionsMenu(true)

        setvisibility()

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }
    // ? buttons on the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_delete_all->DeleteAll()

        }
        return true
    }

    private fun DeleteAll() {

        mToDoViewModel.deleteAll()

        Toast.makeText(requireContext(),"you ve deleted EVERYTHING mf", Toast.LENGTH_SHORT).show()

    }
    fun DataBaseIsEmpty():Boolean
    {
        return mToDoViewModel.getAllData.value?.size==0
    }
    fun setvisibility(){
        if(DataBaseIsEmpty())
        {
            view?.imageView?.visibility = View.VISIBLE
            view?.textView?.visibility = View.VISIBLE
        }else
        {
            view?.imageView?.visibility = View.INVISIBLE
            view?.textView?.visibility = View.INVISIBLE}
    }

}