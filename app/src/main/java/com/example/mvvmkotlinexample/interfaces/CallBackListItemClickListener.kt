package com.example.mvvmkotlinexample.interfaces

import com.example.mvvmkotlinexample.model.IssueResponseModel

interface CallBackListItemClickListener {
    fun onListItemClickListener(issueResponseModel : IssueResponseModel)
}