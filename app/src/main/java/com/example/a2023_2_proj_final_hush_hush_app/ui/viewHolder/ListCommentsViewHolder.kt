package com.example.a2023_2_proj_final_hush_hush_app.ui.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.databinding.CardCommentBinding
import com.example.a2023_2_proj_final_hush_hush_app.interfaces.ViewHolderClickListener
import com.example.a2023_2_proj_final_hush_hush_app.responses.comment.ShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.utils.Utils

class ListCommentsViewHolder(private val binding: CardCommentBinding, private val onClickListener: ViewHolderClickListener) : RecyclerView.ViewHolder(binding.root) {
    fun bindVH(comment: ShowResponse) {
        binding.username.text = comment.user.username
        binding.createdAt.text = Utils.formatDateAccordingUserLocale(comment.createdAt)
        binding.comment.text = comment.content
        binding.likesCount.text = comment.likesCount.toString()
        binding.dislikesCount.text = comment.dislikesCount.toString()

        if (comment.userEvaluation == "Like") {
            binding.like.setImageResource(R.drawable.like_green_icon)
            binding.dislike.setImageResource(R.drawable.dislike_icon)
        } else if (comment.userEvaluation == "Dislike") {
            binding.like.setImageResource(R.drawable.like_icon)
            binding.dislike.setImageResource(R.drawable.dislike_red_icon)
        } else {
            binding.like.setImageResource(R.drawable.like_icon)
            binding.dislike.setImageResource(R.drawable.dislike_icon)
        }

        binding.like.setOnClickListener {
            onClickListener.onClick(adapterPosition, "Like")
        }

        binding.dislike.setOnClickListener {
            onClickListener.onClick(adapterPosition, "Dislike")
        }
    }

    private fun setIsEnabledLike(isEnabled: Boolean) {
        binding.like.isEnabled = isEnabled
    }

    private fun setIsEnabledDislike(isEnabled: Boolean) {
        binding.dislike.isEnabled = isEnabled
    }

    fun enableEvaluations() {
        this.setIsEnabledLike(true)
        this.setIsEnabledDislike(true)
    }

    fun disableEvaluations() {
        this.setIsEnabledLike(false)
        this.setIsEnabledDislike(false)
    }
}
