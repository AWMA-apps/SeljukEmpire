package com.awma.seljukempire.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class Events(
    val eid: String?=null,
    val imageUrl: String?=null,
    val webUrl: String?=null,
    val importance: Int?=null,
    val titleText: Map<String, String>?=null,
    val text: Map<String, String>?=null
): Parcelable


