package com.cham.helx.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.ViewCompat
import kotlin.math.roundToInt


/**
 * Hello World
 * Date: 2019/11/6
 * Author: Cham
 */
class TimeLineItemDecoration(var context: Context) : RecyclerView.ItemDecoration() {



    //上下左右的偏移量
    var topOffset : Int = dp2px(context , 5)
    var bottomOffset : Int = 0
    var leftOffset : Int = dp2px(context , 5)
    var rightOffset : Int = dp2px(context , 50)


    //轴线画笔
    var paintLine : Paint? = null

    init {
        paintLine = Paint(Paint.ANTI_ALIAS_FLAG)
        paintLine?.color = Color.GRAY
        paintLine?.strokeCap = Paint.Cap.ROUND
        paintLine?.strokeWidth = dp2px(context , 5).toFloat()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        //outRect就是表示在item的上下左右所撑开的距离,默认值为0
        val childPosition = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter!!.itemCount
        if (childPosition !=  itemCount - 1){
            outRect.set(leftOffset , topOffset , rightOffset , bottomOffset)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        //onDraw 方法却是针对 RecyclerView 本身
        drawHorizontal(c,parent )
    }

    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        // 和drawVertical差不多 left right 与 top和bottom对调一下
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom -dp2px(parent.context,5)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child
                    .layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin +
                    ViewCompat.getTranslationX(child).roundToInt()
            val right = (left + 150) as Int
            if(i !=childCount-1){
                paintLine?.let { c.drawLine(left.toFloat(),bottom.toFloat(), right.toFloat(), bottom.toFloat(), it) }
            }
        }
    }
    private fun dp2px(context: Context , dpValue : Int) : Int{
        val displayMetrics = context.resources.displayMetrics
        return ((displayMetrics.density*dpValue + 0.5f).toInt())
    }

}