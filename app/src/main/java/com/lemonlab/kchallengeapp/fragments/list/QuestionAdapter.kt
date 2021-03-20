package com.lemonlab.kchallengeapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lemonlab.kchallengeapp.R
import com.lemonlab.kchallengeapp.model.Question
import kotlinx.android.synthetic.main.question_item.view.*

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    private var questionList = emptyList<Question>()

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.question_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = questionList[position]
        holder.itemView.id_text.text = currentItem.id.toString()
        holder.itemView.question_content.text = currentItem.questionText

        holder.itemView.row_lay.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    fun setData(question: List<Question>){
        this.questionList = question
        notifyDataSetChanged()
    }

}