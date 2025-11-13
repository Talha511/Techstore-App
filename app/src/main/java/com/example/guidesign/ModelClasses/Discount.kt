package com.example.guidesign.ModelClasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Discount(
    val img: Int ,
    val name: String ,
    val price: String ,
    val dis: String,
    val des: String
): Parcelable

