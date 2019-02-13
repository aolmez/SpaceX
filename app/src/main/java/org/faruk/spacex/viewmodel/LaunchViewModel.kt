package org.faruk.spacex.viewmodel

import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import org.faruk.spacex.model.Launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_launch.*
import org.faruk.spacex.adapter.LaunchListAdapter
import org.faruk.spacex.common.Util
import org.faruk.spacex.model.AppException
import org.faruk.spacex.model.Resource
import org.faruk.spacex.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.LinkedHashSet


class LaunchViewModel : ViewModel() {

    val launches: MutableLiveData<Resource<MutableList<Launch>?>> = MutableLiveData()

    companion object {
        fun create(activity: FragmentActivity) = ViewModelProviders.of(activity).get(LaunchViewModel::class.java)
    }

    init {
           getLaunches()
    }

    fun getLaunches() {

        if (!Util.isNetworkAvailable()) {
            launches.value = Resource.error(AppException(Throwable("Check Your Internet Connection!", null)))
        }

        launches.value = Resource.loading()
        ApiService.instance.getLaunches().enqueue(object : Callback<List<Launch>?> {

            override fun onResponse(call: Call<List<Launch>?>, response: Response<List<Launch>?>) {
                launches.value = Resource.success(response.body()?.toMutableList())
            }

            override fun onFailure(call: Call<List<Launch>?>, t: Throwable) {
                val exception = AppException(t)
                launches.value = Resource.error(exception)
            }

        })
    }


}