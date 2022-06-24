package com.harunkor.motionmonitorapp.presentation.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable

class CricketBallDrawable(color: Int) : Drawable() {
    private var mPaint: Paint? = null
    private val mRadius = 0

   init {
       mPaint = Paint(Paint.ANTI_ALIAS_FLAG);
       mPaint?.color = color
   }

    override fun draw(canvas: Canvas) {
       val bounds:Rect = getBounds()
        mPaint?.let { paint -> paint
            canvas.drawCircle(bounds.centerX().toFloat(),bounds.centerY().toFloat(),
                mRadius.toFloat(), paint
            )
        }

    }

    override fun setAlpha(alpha: Int) {
        mPaint?.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
       mPaint?.setColorFilter(colorFilter)
    }

    override fun getOpacity(): Int {
        return  PixelFormat.TRANSLUCENT
    }


}