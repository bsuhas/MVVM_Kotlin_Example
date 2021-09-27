package com.example.mvvmkotlinexample.retrofit

import com.example.mvvmkotlinexample.model.IssueResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("issues")
    fun getIssueList(): Call<List<IssueResponseModel>>


    @GET("issues/{ISSUE_ID}/comments")
    fun getCommentList(@Path("ISSUE_ID") issue_id: String): Call<List<IssueResponseModel>>

}