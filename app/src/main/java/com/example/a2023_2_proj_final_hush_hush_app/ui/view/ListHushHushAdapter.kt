package com.example.a2023_2_proj_final_hush_hush_app.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2023_2_proj_final_hush_hush_app.databinding.CardHushHushBinding
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.ShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.ui.viewHolder.ListHushHushViewHolder

class ListHushHushAdapter :  RecyclerView.Adapter<ListHushHushViewHolder>() {
    private var hushHushList: List<ShowResponse> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHushHushViewHolder {
        val item = CardHushHushBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHushHushViewHolder(item)
    }

    override fun getItemCount(): Int {
        return hushHushList.count()
    }

    override fun onBindViewHolder(holder: ListHushHushViewHolder, position: Int) {
        holder.bindVH(hushHushList[position])
    }

    fun updateHushHushList(list: List<ShowResponse>) {
        hushHushList = list
        notifyDataSetChanged()
    }
}