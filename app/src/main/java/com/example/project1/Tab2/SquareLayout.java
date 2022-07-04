package com.example.project1.Tab2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.text.AttributedCharacterIterator;

public class SquareLayout extends LinearLayout {
    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareLayout(Context context, AttributeSet attrs, int deffStyleAttr) {
        super(context, attrs, deffStyleAttr);
    }

    @Override
    protected void  onMeasure(int widthMeasureSpec, int heighMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }


}
