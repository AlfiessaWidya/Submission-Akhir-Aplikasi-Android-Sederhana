package com.dicoding.myrecyclervew

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val name: String,
    val desc: String,
    val photo: String,
    val released: String,
    val rating: String,
    val actor: String,
    val link: String = ""
) : Parcelable
