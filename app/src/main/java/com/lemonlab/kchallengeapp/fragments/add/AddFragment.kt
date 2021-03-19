package com.lemonlab.kchallengeapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lemonlab.kchallengeapp.R
import com.lemonlab.kchallengeapp.model.Question
import com.lemonlab.kchallengeapp.viewmodel.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private lateinit var questionViewModel: QuestionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val questionText = question_text_et.text.toString()
        val answerOne = answer_1_et.text.toString()
        val answerTwo = answer_2_et.text.toString()
        val answerThree = answer_3_et.text.toString()
        val answerFour = answer_4_et.text.toString()
        val category = category_spin.selectedItem.toString()
        val correctAnswer = correct_answer_spin.selectedItem.toString()


        if (inputCheck(questionText, answerOne, answerTwo)) {
//            Create Question Object
            val question = Question(
                0,
                category,
                questionText,
                answerOne,
                answerTwo,
                answerThree,
                answerFour,
                Integer.parseInt(correctAnswer)
            )
//        Add Data to Database
            questionViewModel.addQuestion(question)

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please Fill Out Required Fields", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(
        questionText: String,
        answerOne: String,
        answerTwo: String,
    ): Boolean {
        return !(TextUtils.isEmpty(questionText) || TextUtils.isEmpty(answerOne)
                && TextUtils.isEmpty(answerTwo))
    }

}