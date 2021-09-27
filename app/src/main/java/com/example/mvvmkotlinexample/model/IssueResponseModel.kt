package com.example.mvvmkotlinexample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IssueResponseModel(
    val title: String? = null,
    val updated_at: String? = "",
    val body: String? = null,
    val comments_url: String? = null,
    val user: UserModel? = null,
    val number: String? = null

) : Parcelable {
    override fun toString(): String {
        return "IssueResponseModel(title=$title, updated_at=$updated_at, body=$body, comments_url=$comments_url, user=$user, number=$number)"
    }
}
