package com.example.a2023_2_proj_final_hush_hush_app.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.FragmentShowHushHushBinding
import com.example.a2023_2_proj_final_hush_hush_app.interfaces.ViewHolderClickListener
import com.example.a2023_2_proj_final_hush_hush_app.responses.comment.IndexResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.comment.ShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.ShowResponse as PostShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.CommentService
import com.example.a2023_2_proj_final_hush_hush_app.services.EvaluationService
import com.example.a2023_2_proj_final_hush_hush_app.services.PostService
import com.example.a2023_2_proj_final_hush_hush_app.ui.view.ListCommentsAdapter
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.ShowHushHushViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class ShowHushHushFragment : Fragment(R.layout.fragment_show_hush_hush), ViewHolderClickListener {
    private lateinit var showHushHushVM: ShowHushHushViewModel
    private var _binding: FragmentShowHushHushBinding? = null
    private lateinit var sp: Preferences
    private lateinit var adapter: ListCommentsAdapter
    private val binding get() = _binding!!
    private var commentService = RetrofitClient.createService(CommentService::class.java)
    private var evaluationService = RetrofitClient.createService(EvaluationService::class.java)
    private var postService = RetrofitClient.createService(PostService::class.java)
    private val postId = 10
//    companion object {
//        fun newInstance(comments: IndexResponse): ShowHushHushFragment {
//            val fragment = ShowHushHushFragment()
//            val args = Bundle()
//            args.getSerializable()
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        showHushHushVM = ViewModelProvider(this)[ShowHushHushViewModel::class.java]
        sp = Preferences(requireContext().applicationContext)
        _binding = FragmentShowHushHushBinding.inflate(inflater, container, false)
        binding.recyclerListComments.layoutManager = LinearLayoutManager(this.context)
        adapter = ListCommentsAdapter(binding.recyclerListComments, this)
        binding.recyclerListComments.adapter = adapter

        this.setObserver()

        this.getHushHush()
        this.getListComments()
        return binding.root
    }

    private fun getHushHush() {
        val currentLocale: Locale = resources.configuration.locales[0]
        val language: String = currentLocale.language
        val token = sp.getToken()

        val call = postService.show(token, language, postId)

        call.enqueue(object:
            Callback<PostShowResponse> {
            override fun onResponse(call: Call<PostShowResponse>, response: Response<PostShowResponse>) {
                if(response.isSuccessful) {
                    val post = response.body()!!
                    showHushHushVM.setHushHush(post)

                }else{
                    showToast("An error has occurred when load Hush-Hush.")
                }
            }

            override fun onFailure(call: Call<PostShowResponse>, t: Throwable) {
                showToast("Internal Server Error!")
            }
        })
    }

    private fun getListComments() {
        val currentLocale: Locale = resources.configuration.locales[0]
        val language: String = currentLocale.language
        val token = sp.getToken()

        val call = commentService.index(token, language, postId)

        call.enqueue(object:
            Callback<IndexResponse> {
            override fun onResponse(call: Call<IndexResponse>, response: Response<IndexResponse>) {
                if(response.isSuccessful) {
                    adapter.updateCommentsList(response.body()!!.data)

                }else{
                    showToast("An error has occurred.")
                }
            }

            override fun onFailure(call: Call<IndexResponse>, t: Throwable) {
                showToast("Internal Server Error!")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showToast(message: String) {
        Toast.makeText(requireContext().applicationContext, message, Toast.LENGTH_LONG).show()
    }

    override fun onClick(index: Int, evaluation: String) {
        val comment = adapter.getCommentByIndex(index)
        this.adapter.disableEvaluationsByIndex(index)

        if (evaluation == "Like") {
            this.handleClickLike(comment)
        } else if (evaluation == "Dislike") {
            this.handleClickDislike(comment)
        }

        this.getListComments()
        this.adapter.enableEvaluationsByIndex(index)
    }

    private fun handleClickLike(comment: ShowResponse) {
        if (comment.userEvaluation == "Like") {
            this.setNone(comment.id)
        } else {
            this.setLike(comment.id)
        }
    }

    private fun handleClickDislike(comment: ShowResponse) {
        if (comment.userEvaluation == "Dislike") {
            this.setNone(comment.id)
        } else {
            this.setDisLike(comment.id)
        }
    }

    private fun setLike(commentId: Int) {
        val currentLocale: Locale = resources.configuration.locales[0]
        val language: String = currentLocale.language
        val token = sp.getToken()

        val call = evaluationService.like(token, language, commentId)

        call.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful) {
                    showToast("Like successful!")
                } else {
                    showToast("An error has occurred.")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                showToast("Internal Server Error!")
            }
        })
    }

    private fun setDisLike(commentId: Int) {
        val currentLocale: Locale = resources.configuration.locales[0]
        val language: String = currentLocale.language
        val token = sp.getToken()

        val call = evaluationService.dislike(token, language, commentId)

        call.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful) {
                    showToast("Dislike successful!")
                } else {
                    showToast("An error has occurred.")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                showToast("Internal Server Error!")
            }
        })
    }

    private fun setNone(commentId: Int) {
        val currentLocale: Locale = resources.configuration.locales[0]
        val language: String = currentLocale.language
        val token = sp.getToken()

        val call = evaluationService.none(token, language, commentId)

        call.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful) {
                    showToast("Success on remove evaluation!")
                } else {
                    showToast("An error has occurred.")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                showToast("Internal Server Error!")
            }
        })
    }

    private fun setObserver() {
        showHushHushVM.hushHush().observe(viewLifecycleOwner) {
            binding.cardHushHushDetails.username.text = it.user.username
            binding.cardHushHushDetails.createdAt.text = it.createdAt
            binding.cardHushHushDetails.title.text = it.title
            binding.cardHushHushDetails.content.text = it.content
            binding.cardHushHushDetails.commentsCount.text = it.commentsCount.toString()
        }
    }
}