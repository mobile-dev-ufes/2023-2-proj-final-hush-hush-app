package com.example.a2023_2_proj_final_hush_hush_app.responses.dashboards

import com.example.a2023_2_proj_final_hush_hush_app.responses.post.ShowResponse as ShowPostResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.ShowResponse as ShowUserResponse
import com.google.gson.annotations.SerializedName

class DashboardsResponse {
    @SerializedName("total_users")
    var totalUsers: Int = 0;

    @SerializedName("total_posts")
    var totalPosts: Int = 0;

    @SerializedName("total_comments")
    var totalComments: Int = 0;

    @SerializedName("most_commented_posts")
    var mostCommentedPosts: Array<ShowPostResponse> = arrayOf<ShowPostResponse>()

    @SerializedName("users_with_most_posts")
    var usersWithMostPosts: Array<ShowUserResponse> = arrayOf<ShowUserResponse>()

    @SerializedName("users_with_most_comments")
    var usersWithMostComments: Array<ShowUserResponse> = arrayOf<ShowUserResponse>()
}