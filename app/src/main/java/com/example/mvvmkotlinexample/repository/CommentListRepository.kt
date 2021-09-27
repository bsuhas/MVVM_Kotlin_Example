package com.example.mvvmkotlinexample.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvmkotlinexample.model.IssueResponseModel
import com.example.mvvmkotlinexample.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CommentListRepository {

    val commentListLiveData = MutableLiveData<List<IssueResponseModel>>()

    fun getServicesApiCall(issueID: String): MutableLiveData<List<IssueResponseModel>> {

        val call = RetrofitClient.apiInterface.getCommentList(issueID)

        call.enqueue(object : Callback<List<IssueResponseModel>> {
            override fun onFailure(call: Call<List<IssueResponseModel>>, t: Throwable) {
                Log.e("onFailure: ", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<IssueResponseModel>>,
                responseModel: Response<List<IssueResponseModel>>
            ) {
                commentListLiveData.value = responseModel.body();
            }
        })

        return commentListLiveData
    }
}