package com.example.mvvmkotlinexample.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvmkotlinexample.model.IssueResponseModel
import com.example.mvvmkotlinexample.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object IssueListRepository {

    val issueListLiveData = MutableLiveData<List<IssueResponseModel>>()

    fun getServicesApiCall(): MutableLiveData<List<IssueResponseModel>> {

        val call = RetrofitClient.apiInterface.getIssueList()

        call.enqueue(object : Callback<List<IssueResponseModel>> {
            override fun onFailure(call: Call<List<IssueResponseModel>>, t: Throwable) {
                Log.e("onFailure: ", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<IssueResponseModel>>,
                responseModel: Response<List<IssueResponseModel>>
            ) {
                issueListLiveData.value = responseModel.body();
            }
        })

        return issueListLiveData
    }
}