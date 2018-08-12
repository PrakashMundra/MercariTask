package com.mercaritask.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public abstract class BaseWidget extends FrameLayout {
    protected View inflatedView;

    public BaseWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        preInitAttributes(context, attrs, defStyle);
        init();
        processAttributes(context, attrs, defStyle);
    }

    public BaseWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        preInitAttributes(context, attrs, -1);
        init();
        processAttributes(context, attrs, -1);
    }

    public BaseWidget(Context context) {
        super(context);
        init();
    }

    protected void preInitAttributes(Context context, AttributeSet attrs, int defStyle) {

    }

    protected void processAttributes(Context context, AttributeSet attrs, int defStyle) {

    }

    protected void init() {
        Context context = getContext();
        if (context == null) return;
        inflatedView = LayoutInflater.from(context).inflate(getLayoutId(), this);
    }

    protected abstract int getLayoutId();
}
