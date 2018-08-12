package com.mercaritask.ui.adapter;

import android.widget.ImageView;

import com.mercaritask.widget.CountWidget;
import com.squareup.picasso.Picasso;

public class BindingAdapter {
    @android.databinding.BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String name) {
        if (name != null && !name.trim().isEmpty()) {
            Picasso.with(imageView.getContext())
                    .load("https://dummyimage.com/100x100/000/D50000.png&text=" + name)
                    .into(imageView);
        }
    }

    @android.databinding.BindingAdapter({"bind:count"})
    public static void setCount(CountWidget widget, String count) {
        widget.setCount(count);
    }
}
