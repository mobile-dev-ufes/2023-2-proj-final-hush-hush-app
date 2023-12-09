package com.example.a2023_2_proj_final_hush_hush_app.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.bodies.comment.StoreUpdateBody
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.LoginBody
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.FragmentShowHushHushBinding
import com.example.a2023_2_proj_final_hush_hush_app.interfaces.ViewHolderClickListener
import com.example.a2023_2_proj_final_hush_hush_app.responses.comment.IndexResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.comment.ShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.comment.StoreUpdateResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.StoreLoginResponse
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

class ShowHushHushFragment : Fragment(R.layout.fragment_show_hush_hush), ViewHolderClickListener , OnClickListener{
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

        binding.cardWriteComment.buttonSendComment.setOnClickListener(this)

        binding.cardWriteComment.writeComment.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showHushHushVM.setCommentContent(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

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

    override fun onClick(view: View) {
        if(view.id == R.id.button_send_comment ){
            this.handleClickSendComment()

        }
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
                if(!response.isSuccessful) {
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
                if(!response.isSuccessful) {
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
                if(!response.isSuccessful) {
                    showToast("Success on remove evaluation!")
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

        showHushHushVM.isLoading().observe(viewLifecycleOwner){
            binding.cardWriteComment.buttonSendComment.isEnabled = !it
        }

    }

    private fun handleClickSendComment() {
        this.sendComment()
        this.getListComments()  //to include the new comment in list
        binding.cardWriteComment.writeComment.text.clear();
    }


    private fun sendComment(){
        showHushHushVM.setIsLoading(true)
        val currentLocale: Locale = resources.configuration.locales[0]
        val language: String = currentLocale.language
        val token = sp.getToken()

        val body = StoreUpdateBody()
        body.content = showHushHushVM.commentContent().value.toString()


        val call = commentService.store(token, language, postId, body)

        call.enqueue(object: Callback<StoreUpdateResponse> {
            override fun onResponse(call: Call<StoreUpdateResponse>, response: Response<StoreUpdateResponse>,) {
                if(response.isSuccessful) {
                    showToast("Comment submitted successfully!")
                }else{
                    showToast("Error on submit comment.")
                }

                showHushHushVM.setIsLoading(false)
            }

            override fun onFailure(call: Call<StoreUpdateResponse>, t: Throwable) {
                showHushHushVM.setIsLoading(false)
                showToast("Internal Server Error!")
            }
        })
    }
}