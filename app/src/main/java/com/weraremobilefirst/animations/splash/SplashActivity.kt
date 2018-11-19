package com.weraremobilefirst.animations.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AnticipateOvershootInterpolator
import com.weraremobilefirst.animations.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    var animated = false

    val constraintSet = ConstraintSet()
    val constraintReset = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        constraintSet.clone(rootConstraint)
        constraintReset.clone(rootConstraint)

        animateButton.setOnClickListener {

            animated = !animated

            val transition = ChangeBounds()
            transition.interpolator = AnticipateOvershootInterpolator(1.0f)
            transition.duration = 1000


            TransitionManager.beginDelayedTransition(rootConstraint, transition)
            constraintSet.clear(R.id.logoImage, ConstraintSet.BOTTOM)
            constraintSet.connect(R.id.bottomBox, ConstraintSet.TOP, R.id.guideline, ConstraintSet.TOP)

            if(!animated){
                constraintReset.applyTo(rootConstraint)
            }else{
                constraintSet.applyTo(rootConstraint)
            }


        }



    }
}
