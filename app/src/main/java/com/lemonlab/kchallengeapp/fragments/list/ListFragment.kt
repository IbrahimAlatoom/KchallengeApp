package com.lemonlab.kchallengeapp.fragments.list

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson

import com.lemonlab.kchallengeapp.R
import com.lemonlab.kchallengeapp.viewmodel.QuestionViewModel

import kotlinx.android.synthetic.main.fragment_list.view.*
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.OutputStreamWriter


class ListFragment : Fragment() {

    private lateinit var questionViewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)


//        RV
        val adapter = QuestionAdapter()
        val recyclerView = view.rv
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

//        Question ViewModel
        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        questionViewModel.readAllData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }




        view.floatingActionButton.setOnClickListener {
            navigateToAdd()
        }

        return view
    }

    private fun navigateToAdd() {
        findNavController().navigate(R.id.action_listFragment_to_addFragment)
    }

    private fun ToJson() {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val myFile = File(path,"data.json")
        val fileOut = FileOutputStream(myFile,true)
        val writer = OutputStreamWriter(fileOut)
        val gson = Gson()
        questionViewModel.readAllData.observe(viewLifecycleOwner){
            var json = gson.toJson(it,writer)
        }

    }

}