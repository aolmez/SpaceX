package org.faruk.spacex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Payload(
    @SerializedName("payload_id") val payloadId: String?,
    @SerializedName("nationality") val nationality: String?,
    @SerializedName("manufacturer") val manufacturer: String?,
    @SerializedName("payload_type") val payloadType: String?
) : Parcelable