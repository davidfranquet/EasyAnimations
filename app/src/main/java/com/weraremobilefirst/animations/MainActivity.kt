package com.weraremobilefirst.animations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.ViewCompat
import android.support.v4.app.ActivityOptionsCompat
import android.content.Intent
import android.view.View
import android.support.v4.util.Pair as AndroidPair

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToDetail.setOnClickListener {
            openDetailActivity()
        }
    }

    private fun openDetailActivity() {
        val intent = Intent(this, DetailActivity::class.java)
        val imageViewTransitionPair: AndroidPair<View, String> = AndroidPair(imageView, ViewCompat.getTransitionName(imageView))
        val buttonTransitionPair: AndroidPair<View, String> = AndroidPair(goToDetail, ViewCompat.getTransitionName(goToDetail))
        val options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageViewTransitionPair, buttonTransitionPair)

        startActivity(intent, options.toBundle())
    }
}
