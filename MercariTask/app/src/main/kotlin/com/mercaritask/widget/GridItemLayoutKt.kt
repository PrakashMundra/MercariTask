package com.mercaritask.widget

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

class GridItemLayoutKt : AppCompatImageView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val w = this.measuredWidth
        setMeasuredDimension(w, w)
    }
}