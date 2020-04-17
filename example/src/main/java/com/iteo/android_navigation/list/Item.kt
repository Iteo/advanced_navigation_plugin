package com.iteo.android_navigation.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val id: Int,
    val name: String,
    val color: Int
) : Parcelable
