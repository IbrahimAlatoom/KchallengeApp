package com.lemonlab.kchallengeapp.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.lemonlab.kchallengeapp.R
import com.lemonlab.kchallengeapp.viewmodel.QuestionViewModel

import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var questionViewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
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


}