package com.weraremobilefirst.animations.splash

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.animation.AnticipateOvershootInterpolator
import com.weraremobilefirst.animations.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private var animated = false

    private val constraintSet = ConstraintSet()
    private val constraintReset = ConstraintSet()
    private val transition = ChangeBounds()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        constraintSet.clone(rootConstraint)
        constraintReset.clone(rootConstraint)

        initializeTransition()

        animateButton.setOnClickListener {

            animated = !animated

            TransitionManager.beginDelayedTransition(rootConstraint, transition)
            constraintSet.connect(R.id.logoImage, ConstraintSet.BOTTOM, R.id.guideline, ConstraintSet.TOP)
            constraintSet.connect(R.id.bottomBox, ConstraintSet.TOP, R.id.guideline, ConstraintSet.TOP)

            if(!animated){
                constraintReset.applyTo(rootConstraint)
            }else{
                constraintSet.applyTo(rootConstraint)
            }


        }



    }

    private fun initializeTransition() {
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1000
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(p0: Transition?) {
                if (animated) {
                    companyName.animate().alpha(1f)
                    email.animate().alpha(1f)
                    password.animate().alpha(1f)
                }
            }

            override fun onTransitionStart(p0: Transition?) {
                if (!animated) {
                    companyName.animate().alpha(0f)
                    email.animate().alpha(0f)
                    password.animate().alpha(0f)
                }
            }

            override fun onTransitionResume(p0: Transition?) {}
            override fun onTransitionPause(p0: Transition?) {}
            override fun onTransitionCancel(p0: Transition?) {}


        })
    }
}
