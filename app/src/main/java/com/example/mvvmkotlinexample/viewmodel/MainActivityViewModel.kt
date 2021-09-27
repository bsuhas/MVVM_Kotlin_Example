package com.example.mvvmkotlinexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinexample.model.IssueResponseModel
import com.example.mvvmkotlinexample.repository.IssueListRepository

class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<List<IssueResponseModel>>? = null

    fun getIssueList() : LiveData<List<IssueResponseModel>>? {
        servicesLiveData = IssueListRepository.getServicesApiCall()
        return servicesLiveData;
    }

}