package com.example.a2023_2_proj_final_hush_hush_app.ui.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.a2023_2_proj_final_hush_hush_app.databinding.CardHushHushBinding
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.ShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.utils.Utils

class ListHushHushViewHolder(private val binding: CardHushHushBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindVH(hushHush: ShowResponse) {
        binding.username.text = hushHush.user.username
        binding.textViewTitleOfHushHushInCard.text = hushHush.title
        binding.commentsCount.text = hushHush.commentsCount.toString()
        binding.createdAt.text = Utils.formatDateAccordingUserLocale(hushHush.createdAt)
    }
}