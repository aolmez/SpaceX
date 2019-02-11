package org.faruk.spacex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchSite(
    @SerializedName("site_name") val siteName: String?
) : Parcelable