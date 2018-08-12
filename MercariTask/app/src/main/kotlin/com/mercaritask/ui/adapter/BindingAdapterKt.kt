package com.mercaritask.ui.adapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.mercaritask.widget.CountWidgetKt
import com.squareup.picasso.Picasso

object BindingAdapterKt {
    @JvmStatic
    @BindingAdapter("bind:imageUrlKt")
    fun loadImageKt(imageView: ImageView, name: String?) {
        if (name != null && !name.isNullOrEmpty()) {
            Picasso.with(imageView.context)
                    .load("https://dummyimage.com/100x100/000/D50000.png&text=$name")
                    .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:countKt")
    fun setCountKt(widget: CountWidgetKt, count: String) {
        widget.setCount(count)
    }
}