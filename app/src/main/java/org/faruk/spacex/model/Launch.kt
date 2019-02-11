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