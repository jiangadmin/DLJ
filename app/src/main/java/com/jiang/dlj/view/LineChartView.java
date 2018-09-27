package com.jiang.dlj.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.jiang.dlj.R;
import com.jiang.dlj.utils.LogUtil;

/**
 * Created by Administrator on 2016/12/13.
 */

public class LineChartView extends View {
    private static final String TAG = "LineChartView";
    private Paint linePaint;
    private double[] array = new double[7];
    private int mWidth = 0;
    private int mHeight = 0;
    private double max = 0.00;
    private double min = 0.00;
    private int color = getResources().getColor(R.color.gray_3);

    public LineChartView(Context context) {
        super(context);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(3);//宽度
        setData(array, color);
        getMaxValue();
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(3);//宽度
        setData(array, color);
        getMaxValue();
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(3);//宽度
        setData(array, color);
        getMaxValue();
    }

    @SuppressLint("NewApi")
    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(3);//宽度
        setData(array, color);
        getMaxValue();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        /**
         * 自定义控件的宽高必须由调用者自己指定具体的数值
         */
        if (widthSpecMode == MeasureSpec.EXACTLY) {
            mWidth = widthSpecSize;
        } else {
            mWidth = 300;
        }

        if (heightSpecMode == MeasureSpec.EXACTLY) {
            mHeight = heightSpecSize;
        } else {
            mHeight = 150;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 6; i++) {
            if (array[i] == -1.00 || array[i + 1] == -1.00) {
                LogUtil.e(TAG, "不知道这里是干嘛的");
            } else {
                canvas.drawLine(getPaddingLeft() + (float) i * getMeasuredWidth() / 6, getPaddingTop() + (float) (getMeasuredHeight() / (max - min) * (max - array[i])), getPaddingLeft() + (float) (i + 1) * getMeasuredWidth() / 6, getPaddingTop() + (float) (getMeasuredHeight() / (max - min) * (max - array[i + 1])), linePaint);
            }
        }
    }

    public void setData(double[] array1, int color) {
        this.color = color;
        linePaint.setColor(color);
        for (int i = 0; i < 7; i++) {
            array[i] = array1[i];
        }
        getMaxValue();
        invalidate();
    }

    //获取最大值
    private void getMaxValue() {
        max = min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max && array[i] != -1.00) {
                max = array[i];
            }
            if (array[i] < min && array[i] != -1.00) {
                min = array[i];
            }
        }
    }
}
