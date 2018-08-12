package com.mercaritask.widget

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class GridItemDecorationKt(val spanCount: Int, val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        outRect.left = spacing - column * spacing / spanCount
        outRect.right = (column + 1) * spacing / spanCount
        if (position < spanCount)
            outRect.top = spacing
        outRect.bottom = spacing
    }
}