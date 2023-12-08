package com.example.a2023_2_proj_final_hush_hush_app.ui.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2023_2_proj_final_hush_hush_app.databinding.CardCommentBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.CardHushHushBinding
import com.example.a2023_2_proj_final_hush_hush_app.interfaces.ViewHolderClickListener
import com.example.a2023_2_proj_final_hush_hush_app.responses.comment.ShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.ui.viewHolder.ListCommentsViewHolder
import com.example.a2023_2_proj_final_hush_hush_app.ui.viewHolder.ListHushHushViewHolder

class ListCommentsAdapter(private val recyclerView: RecyclerView, private val onClickListener: ViewHolderClickListener) : RecyclerView.Adapter<ListCommentsViewHolder>() {
    private var commentsList: Array<ShowResponse> = arrayOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCommentsViewHolder {
        val item = CardCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListCommentsViewHolder(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return commentsList.count()
    }

    override fun onBindViewHolder(holder: ListCommentsViewHolder, position: Int) {
        holder.bindVH(commentsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCommentsList(list: Array<ShowResponse>) {
        commentsList = list
        notifyDataSetChanged()
    }

    fun getCommentByIndex(index: Int): ShowResponse {
        return commentsList[index]
    }

    fun enableEvaluationsByIndex(index: Int) {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(index) as? ListCommentsViewHolder
        viewHolder?.enableEvaluations()
    }

    fun disableEvaluationsByIndex(index: Int) {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(index) as? ListCommentsViewHolder
        viewHolder?.disableEvaluations()
    }
}