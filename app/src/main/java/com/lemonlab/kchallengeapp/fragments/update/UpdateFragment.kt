package com.lemonlab.kchallengeapp.fragments.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lemonlab.kchallengeapp.R
import com.lemonlab.kchallengeapp.model.Question
import com.lemonlab.kchallengeapp.viewmodel.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var questionViewModel: QuestionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        view.update_question_text_et.setText(args.currentQuestion.questionText)
        view.update_answer_1_et.setText(args.currentQuestion.answerOne)
        view.update_answer_2_et.setText(args.currentQuestion.answerTwo)
        view.update_answer_3_et.setText(args.currentQuestion.answerThree)
        view.update_answer_4_et.setText(args.currentQuestion.answerFour)
//        view.update_category_spin

        view.update_btn.setOnClickListener {
            updateItem()
        }
        return view
    }

    private fun updateItem() {
        val questionText = update_question_text_et.text.toString()
        val answerOne = update_answer_1_et.text.toString()
        val answerTwo = update_answer_2_et.text.toString()
        val answerThree = update_answer_3_et.text.toString()
        val answerFour = update_answer_4_et.text.toString()
        val category = update_category_spin.selectedItem.toString()
        val correctAnswer = update_correct_answer_spin.selectedItem.toString()

        if (inputCheck(questionText, answerOne, answerTwo)) {
            val updatedQuestion = Question(
                args.currentQuestion.id,
                category,
                questionText,
                answerOne,
                answerTwo,
                answerThree,
                answerFour,
                Integer.parseInt(correctAnswer)
            )
            questionViewModel.updateQuestion(updatedQuestion)

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
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