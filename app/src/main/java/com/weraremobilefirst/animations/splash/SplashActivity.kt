package com.weraremobilefirst.animations.splash

import android.animation.LayoutTransition
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.transition.*
import android.view.View
import android.view.ViewGroup
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
            transition.addListener(object : Transition.TransitionListener{
                override fun onTransitionEnd(p0: Transition?) {
                    if(animated){
                        companyName.animate().alpha(1f)
                        email.animate().alpha(1f)
                        password.animate().alpha(1f)
                    }
                }
                override fun onTransitionResume(p0: Transition?) {}
                override fun onTransitionPause(p0: Transition?) {}
                override fun onTransitionCancel(p0: Transition?) {}
                override fun onTransitionStart(p0: Transition?) {
                    if(!animated){
                        companyName.animate().alpha(0f)
                        email.animate().alpha(0f)
                        password.animate().alpha(0f)
                    }
                }

            })

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
}
