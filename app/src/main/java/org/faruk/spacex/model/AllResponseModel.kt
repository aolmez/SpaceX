package org.faruk.spacex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Launch(
    @SerializedName("flight_number") val flightNumber: Int?,
    @SerializedName("mission_name") val missionName: String?,
    @SerializedName("launch_year") val launchYear: String?,
    @SerializedName("launch_date_unix") val launchDateUnix: Long?,
    @SerializedName("rocket") val rocket: Rocket?,
    @SerializedName("launch_site") val launchSite: LaunchSite?,
    val links: Links?,
    val details: String?
) : BaseListModel, Parcelable {
    override val type: Int
        get() = BaseListModel.TYPE_REGULAR
}

@Parcelize
data class LaunchSite(
    @SerializedName("site_name") val siteName: String?
) : Parcelable

@Parcelize
data class Links(
    @SerializedName("mission_patch") val missionPatch: String?,
    @SerializedName("video_link") val videoLink: String?,
    @SerializedName("youtube_id") val youtubeId: String?
) : Parcelable

@Parcelize
data class Payload(
    @SerializedName("payload_id") val payloadId: String?,
    @SerializedName("nationality") val nationality: String?,
    @SerializedName("manufacturer") val manufacturer: String?,
    @SerializedName("payload_type") val payloadType: String?
) : Parcelable

@Parcelize
data class Rocket(
    @SerializedName("rocket_id") val rocketId: String?,
    @SerializedName("rocket_name") val rocketName: String?,
    @SerializedName("rocket_type") val rocketType: String?,
    @SerializedName("second_stage") val secondStage: SecondStage?
) : Parcelable

@Parcelize
data class SecondStage(
    @SerializedName("payloads") val payloads: List<Payload>?
) : Parcelable