package com.mercaritask.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercaritask.R;

public class CountWidget extends BaseWidget {
    public CountWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CountWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountWidget(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_count;
    }

    @Override
    protected void processAttributes(Context context, AttributeSet attrs, int defStyle) {
        super.processAttributes(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CountWidget);
        if (a != null) {
            int iconId = a.getResourceId(R.styleable.CountWidget_icon, -1);
            if (iconId > -1)
                ((ImageView) inflatedView.findViewById(R.id.icon)).setImageResource(iconId);
            a.recycle();
        }
    }

    public void setCount(String count) {
        ((TextView) inflatedView.findViewById(R.id.count)).setText(count);
    }
}
