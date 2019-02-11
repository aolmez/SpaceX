package org.faruk.spacex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
    @SerializedName("mission_patch") val missionPatch: String?,
    @SerializedName("video_link") val videoLink: String?,
    @SerializedName("youtube_id") val youtubeId: String?
) : Parcelable