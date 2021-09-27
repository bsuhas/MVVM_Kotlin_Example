package com.example.mvvmkotlinexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinexample.model.IssueResponseModel
import com.example.mvvmkotlinexample.repository.CommentListRepository
import com.example.mvvmkotlinexample.repository.IssueListRepository

class IssueDetailsViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<List<IssueResponseModel>>? = null

    fun getCommentList(issueId: String): LiveData<List<IssueResponseModel>>? {
        servicesLiveData = CommentListRepository.getServicesApiCall(issueId)
        return servicesLiveData;
    }

}