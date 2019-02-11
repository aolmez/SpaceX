package org.faruk.spacex.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.faruk.spacex.R
import org.faruk.spacex.model.BaseListModel

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun viewHolderFactory(inflater: LayoutInflater, parent: ViewGroup, type: Int): BaseViewHolder {
            return when (type) {
                BaseListModel.TYPE_REGULAR -> RegularViewHolder(
                    inflater.inflate(
                        R.layout.list_item_regular,
                        parent,
                        false
                    )
                )
                else -> {
                    //TODO Empty ViewHolder...
                    RegularViewHolder(inflater.inflate(R.layout.list_item_regular, parent, false))
                }
            }
        }
    }

}