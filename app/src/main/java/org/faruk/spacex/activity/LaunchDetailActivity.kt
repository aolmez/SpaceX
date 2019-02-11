package org.faruk.spacex.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_launch_detail.*
import kotlinx.android.synthetic.main.list_item_regular.view.*
import org.faruk.spacex.R
import org.faruk.spacex.model.Launch

class LaunchDetailActivity : BaseActivity() {

    private var launch: Launch? = null

    companion object {
        fun start(context: Context, launch: Launch?) {
            val intent = Intent(context, LaunchDetailActivity::class.java)
            intent.putExtra("launch", launch)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""

        launch = intent.getParcelableExtra("launch")

        Picasso.get().load(launch?.links?.missionPatch).into(missionImage)
        missionName.text = launch?.missionName
        manufacturer.text = launch?.rocket?.secondStage?.payloads?.get(0)?.manufacturer
        nationality.text = launch?.rocket?.secondStage?.payloads?.get(0)?.nationality

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}