package org.faruk.spacex.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AlertDialog
import org.faruk.spacex.SpaceXApp

class Util {
    companion object {
        fun isNetworkAvailable(): Boolean {
            val connectivityManager = SpaceXApp.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }

        fun showSingleChoiceAlertDialog(
            context: Context,
            title: String? = "",
            items: Array<String>?,
            checkedItemIndex: Int = -1,
            callback: (checkedIndex: Int) -> Unit
        ) {
            val alertDialogBuilder = AlertDialog.Builder(context)
            //alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert)
            alertDialogBuilder.setTitle(title)
            alertDialogBuilder.setCancelable(true)

            alertDialogBuilder.setSingleChoiceItems(items, checkedItemIndex) { dialog, item ->
                callback(item)
                dialog.dismiss()
            }

            val alert = alertDialogBuilder.create()
            alert.show()
        }
    }
}