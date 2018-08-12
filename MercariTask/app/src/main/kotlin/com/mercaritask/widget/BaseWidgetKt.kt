package com.mercaritask.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout

abstract class BaseWidgetKt constructor(context: Context,
                                        attrs: AttributeSet,
                                        defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {
    lateinit var inflatedView: View

    init {
        preInitAttributes(context, attrs, -1)
        init()
        processAttributes(context, attrs, -1)
    }

    fun preInitAttributes(context: Context, attrs: AttributeSet, defStyle: Int) {

    }

    fun processAttributes(context: Context, attrs: AttributeSet, defStyle: Int) {

    }

    protected fun init() {
        val context = context ?: return
        inflatedView = LayoutInflater.from(context).inflate(getLayoutId(), this)
    }

    protected abstract fun getLayoutId(): Int
}