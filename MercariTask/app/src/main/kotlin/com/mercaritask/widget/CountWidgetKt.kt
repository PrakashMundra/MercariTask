package com.mercaritask.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.mercaritask.R

class CountWidgetKt : BaseWidget {
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?) : super(context)

    override fun getLayoutId(): Int {
        return R.layout.widget_count
    }

    override fun processAttributes(context: Context, attrs: AttributeSet, defStyle: Int) {
        super.processAttributes(context, attrs, defStyle)
        val a = context.obtainStyledAttributes(attrs, R.styleable.CountWidget)
        if (a != null) {
            val iconId = a.getResourceId(R.styleable.CountWidget_icon, -1)
            if (iconId > -1)
                (inflatedView.findViewById(R.id.icon) as ImageView).setImageResource(iconId)
            a.recycle()
        }
    }

    fun setCount(count: String) {
        (inflatedView.findViewById(R.id.count) as TextView).text = count
    }
}