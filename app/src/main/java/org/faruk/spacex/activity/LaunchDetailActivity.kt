package org.faruk.spacex.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import org.faruk.spacex.R
import org.faruk.spacex.model.Launch

class LaunchDetailActivity : BaseActivity() {

    private lateinit var launch: Launch

    companion object {
        fun start(context: Context, launch: Launch) {
            val intent = Intent(context, LaunchDetailActivity::class.java)
            intent.putExtra("launch", launch)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Picasso.get().load(tweet.user?.profileImageUrl).into(detailUserPhoto)
        //detailUserName.text = tweet.user?.name ?: ""

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