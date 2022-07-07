package com.dicoding.mysubmission1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    var username: String,
    var name : String,
    var location : String,
    var avatar : Int,
    var company : String,
    var followers : String,
    var following : String,
    var repository : String
) : Parcelable