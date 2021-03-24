package com.viatom.myapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat


class TouchView : View {

    interface Ga{
        fun yes(x:Int,y:Int)
    }
    fun setG(g:Ga){
       ga=g
    }
    var ga:Ga?=null
    var canvas: Canvas? = null
    private val wavePaint = Paint()
    var backG: Bitmap? = null
    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        wavePaint.apply {
            color = getColor(R.color.white)
            style = Paint.Style.FILL
            strokeWidth = 2.0f
        }



    }

    var ixn=0;
    var backC: Canvas? = null
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if(ixn==0) {
            ixn= 1;
            backG = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            backC = Canvas(backG!!)
            backC!!.drawARGB(255, 0, 0, 0)
        }
        canvas.drawBitmap(backG!!, Rect(0,0,width,height), Rect(0,0,width,height),wavePaint)
    }


    private fun drawWave(canvas: Canvas) {
        canvas.drawColor(getColor(R.color.black))
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x=event!!.x.toInt()
        val y=event!!.y.toInt()
        ga?.yes(x,y)
        backC?.drawRect(Rect(x-10,y-10,x+10,y+10),wavePaint)
        invalidate()


        return true
    }

    private fun getColor(resource_id: Int): Int {
        return ContextCompat.getColor(context, resource_id)
    }
}