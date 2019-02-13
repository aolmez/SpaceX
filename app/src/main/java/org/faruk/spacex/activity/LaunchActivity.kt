package org.faruk.spacex.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_launch.*
import org.faruk.spacex.adapter.LaunchListAdapter
import org.faruk.spacex.common.Util
import org.faruk.spacex.model.BaseListModel
import org.faruk.spacex.model.Launch
import org.faruk.spacex.model.Resource
import java.util.LinkedHashSet
import org.faruk.spacex.viewmodel.LaunchViewModel


class LaunchActivity : BaseActivity() {

    private var launchList: MutableList<Launch>? = null
    private var filteredLaunchList: MutableList<BaseListModel>? = null
    private var yearList: MutableList<String>? = null
    private var sortList: Array<String> = arrayOf("Ascend", "Descend")
    private var selectedSortIndex = 0
    private var selectedYearIndex = 0

    private var launchViewModel: LaunchViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(org.faruk.spacex.R.layout.activity_launch)

        if(launchViewModel == null){
            launchViewModel = LaunchViewModel.create(this)
        }

        launchViewModel?.launches?.observe(this, Observer<Resource<MutableList<Launch>?>> { resource ->
            if (resource != null) {
                when (resource.status) {
                    Resource.Status.SUCCESS -> {

                        launchList = resource.data?.toMutableList()
                        filteredLaunchList = resource.data?.toMutableList()

                        launchRecyclerView.addItemDecoration(DividerItemDecoration(this@LaunchActivity, LinearLayout.VERTICAL))
                        launchRecyclerView.adapter =
                            LaunchListAdapter(this@LaunchActivity, filteredLaunchList, launchItemClicked)

                        val tempYearList: LinkedHashSet<String> = linkedSetOf()
                        launchList?.forEach {
                            it.launchYear?.let { it1 -> tempYearList.add(it1) }
                        }
                        yearList = tempYearList.sortedDescending().toMutableList()
                        yearList?.add(0, "All")

                        launchOverlayProgressBar.stop()
                    }
                    Resource.Status.ERROR->{
                        launchOverlayProgressBar.stop()
                        Toast.makeText(this, "Error: "+resource.exception?.message, Toast.LENGTH_LONG).show()
                    }
                    Resource.Status.LOADING->{
                        launchOverlayProgressBar.show()
                    }
                }
            }
        })

        fabMenuItemSort.setOnClickListener(fabMenuItemSortListener)
        fabMenuItemFilter.setOnClickListener(fabMenuItemFilterListener)

    }

    private val launchItemClicked: (launch: Launch?) -> Unit = {
        LaunchDetailActivity.start(this@LaunchActivity, it)
    }

    //Fab Sort Listener
    private val fabMenuItemSortListener: View.OnClickListener = View.OnClickListener {
        Util.showSingleChoiceAlertDialog(this, "Select a Sort", sortList) {
            filteredLaunchList?.sortWith(compareBy { (it as Launch).flightNumber })

            if (it != 0) {
                filteredLaunchList?.reverse()
            }

            selectedSortIndex = it
            launchRecyclerView.adapter?.notifyDataSetChanged()
        }
        fabMenu.close(true)
    }

    //Fab Filter Listener
    private val fabMenuItemFilterListener: View.OnClickListener = View.OnClickListener {
        val listNew: Array<String>? = yearList?.toTypedArray()
        Util.showSingleChoiceAlertDialog(this, "Select a Year", listNew, callback = {

            if (selectedYearIndex != it) {
                if (it == 0) {
                    filteredLaunchList?.clear()
                    launchList?.forEach {
                        filteredLaunchList?.add(it)
                    }
                } else {
                    filteredLaunchList?.clear()
                    launchList?.forEach { launch ->
                        if (launch.launchYear?.equals(yearList?.get(it))!!) {
                            filteredLaunchList?.add(launch)
                        }
                    }
                }

                selectedYearIndex = it
                launchRecyclerView.adapter?.notifyDataSetChanged()
            }

        })

        fabMenu.close(true)
    }

}