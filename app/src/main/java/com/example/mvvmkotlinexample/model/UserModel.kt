package com.example.mvvmkotlinexample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    val login: String? = null,
    val avatar_url: String? = null

) : Parcelable {
    override fun toString(): String {
        return "UserModel(login=$login, avatar_url=$avatar_url)"
    }
}