package org.faruk.spacex.holder

import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_regular.view.*
import org.faruk.spacex.model.Launch

class RegularViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private val missionImage = itemView.missionImage
    private val missionName = itemView.missionName
    private val manufacturer = itemView.manufacturer
    private val nationality = itemView.nationality
    private val siteName = itemView.siteName

    fun bindViews(launch: Launch?, itemClicked: (launch: Launch?) -> Unit) {

        Picasso.get().load(launch?.links?.missionPatch).into(missionImage)
        missionName.text = launch?.missionName
        manufacturer.text = launch?.rocket?.secondStage?.payloads?.get(0)?.manufacturer
        nationality.text = launch?.rocket?.secondStage?.payloads?.get(0)?.nationality
        siteName.text = launch?.launchSite?.siteName

        itemView.setOnClickListener {
            itemClicked(launch)
        }
    }
}