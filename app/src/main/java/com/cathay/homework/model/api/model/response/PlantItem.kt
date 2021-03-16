package com.cathay.homework.model.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlantItem(
    @SerializedName("F_Name_Latin")
    val nameLatin: String = "",

    @SerializedName("F_pdf02_ALT")
    val pdf02Alt: String = "",

    @SerializedName("F_Location")
    val location: String = "",

    @SerializedName("F_pdf01_ALT")
    val pdf01ALT: String = "",

    @SerializedName("F_Summary")
    val summary: String = "",

    @SerializedName("F_Pic01_URL")
    val pic01Url: String = "",

    @SerializedName("F_pdf02_URL")
    val pdf02Url: String = "",

    @SerializedName("F_Pic02_URL")
    val pic02Url: String = "",

    @SerializedName("\uFEFFF_Name_Ch")
    val nameCh: String = "",

    @SerializedName("F_Keywords")
    val keywords: String = "",

    @SerializedName("F_Code")
    val code: String = "",

    @SerializedName("F_Geo")
    val geo: String = "",

    @SerializedName("F_Pic03_URL")
    val pic03Url: String = "",

    @SerializedName("F_Voice01_ALT")
    val voice01Alt: String = "",

    @SerializedName("F_AlsoKnown")
    val alsoKnown: String = "",

    @SerializedName("F_Voice02_ALT")
    val voice02Alt: String = "",

    @SerializedName("F_Pic04_ALT")
    val pic04Alt: String = "",

    @SerializedName("F_Name_En")
    val nameEn: String = "",

    @SerializedName("F_Brief")
    val brief: String = "",

    @SerializedName("F_Pic04_URL")
    val pic04Url: String = "",

    @SerializedName("F_Voice01_URL")
    val voice01Url: String = "",

    @SerializedName("F_Feature")
    val feature: String = "",

    @SerializedName("F_Pic02_ALT")
    val pic02Alt: String = "",

    @SerializedName("F_Family")
    val family: String = "",

    @SerializedName("F_Voice03_ALT")
    val voice03Alt: String = "",

    @SerializedName("F_Voice02_URL")
    val voice02Url: String = "",

    @SerializedName("F_Pic03_ALT")
    val pic03Alt: String = "",

    @SerializedName("F_Pic01_ALT")
    val pic01Alt: String = "",

    @SerializedName("F_CID")
    val cid: String = "",

    @SerializedName("F_pdf01_URL")
    val pdf01Url: String = "",

    @SerializedName("F_Vedio_URL")
    val videoUrl: String = "",

    @SerializedName("F_Genus")
    val genus: String = "",

    @SerializedName("F_Function＆Application")
    val functionApplication: String = "",

    @SerializedName("F_Voice03_URL")
    val voice03Url: String = "",

    @SerializedName("F_Update")
    val update: String = "",

    @SerializedName("_id")
    val id: Long = 0,
) : Parcelable