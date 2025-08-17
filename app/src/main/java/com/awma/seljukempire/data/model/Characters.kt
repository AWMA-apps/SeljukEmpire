package com.awma.seljukempire.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Characters(
    val eid: String?=null,
    val imageUrl: String?=null,
    val charPhotoUrl: String?=null,
    val importance: Int?=null,
    val titleText: Map<String, String>?=null,
    val text: Map<String, String>?=null
): Parcelable
