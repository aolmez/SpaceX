package org.faruk.spacex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.faruk.spacex.holder.BaseViewHolder
import org.faruk.spacex.holder.RegularViewHolder
import org.faruk.spacex.model.BaseListModel
import org.faruk.spacex.model.Launch

class LaunchListAdapter(
    context: Context?,
    private val itemList: MutableList<BaseListModel>?,
    private val itemClicked: (launch: Launch?) -> Unit)
    : RecyclerView.Adapter<BaseViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder.viewHolderFactory(inflater, parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (itemList?.get(position)?.type) {
            BaseListModel.TYPE_REGULAR -> (holder as RegularViewHolder).bindViews(itemList[position] as Launch?, itemClicked)
        }
    }

    override fun getItemCount() = itemList?.count() ?: 0

    override fun getItemViewType(position: Int) = itemList?.get(position)?.type ?: BaseListModel.TYPE_EMPTY
}