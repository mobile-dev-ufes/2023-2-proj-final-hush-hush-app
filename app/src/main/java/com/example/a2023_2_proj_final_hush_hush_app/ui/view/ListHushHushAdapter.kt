package com.example.a2023_2_proj_final_hush_hush_app.ui.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2023_2_proj_final_hush_hush_app.databinding.CardHushHushBinding
import com.example.a2023_2_proj_final_hush_hush_app.interfaces.CardViewHolderClickListener
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.ShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.ui.viewHolder.ListHushHushViewHolder

class ListHushHushAdapter(private val onClickListener: CardViewHolderClickListener) :  RecyclerView.Adapter<ListHushHushViewHolder>() {
    private var hushHushList: Array<ShowResponse> = arrayOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHushHushViewHolder {
        val item = CardHushHushBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHushHushViewHolder(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return hushHushList.count()
    }

    override fun onBindViewHolder(holder: ListHushHushViewHolder, position: Int) {
        holder.bindVH(hushHushList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateHushHushList(list: Array<ShowResponse>) {
        hushHushList = list
        notifyDataSetChanged()
    }

    fun getHushHushByIndex(index: Int): ShowResponse {
        return hushHushList[index]
    }
}