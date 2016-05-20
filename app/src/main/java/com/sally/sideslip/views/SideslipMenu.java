package com.sally.sideslip.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.sally.sideslip.R;

/**
 * Created by sally on 16/5/20.
 */
public class SideSlipMenu extends HorizontalScrollView {
    private static final String TAG = "SideslipMenu";

    private LinearLayout mWrapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    /**
     * 屏幕的宽度
     */
    private int mScreenWidth;
    /**
     * menu菜单的右边距
     */
    private int mMenuRightPadding = 50;
    /**
     * menu菜单的宽度
     */
    private int mMenuWidth;

    /**
     * 确保onMeasure()方法只执行一次，防止多次测量
     */
    private boolean onMeasureOnce;
    /**
     * menu菜单是否打开
     */
    private boolean isOpen;
    /**
     * 自定义属性
     */
    private int menuRightPadding;


    /**
     * 在代码中初始化组件时，调用该构造方法
     * @param context
     */
    public SideSlipMenu(Context context) {
        this(context, null);
    }

    /**
     * 在布局文件中，不使用自定义属性，调用该构造方法
     * @param context
     * @param attrs
     */
    public SideSlipMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 在布局文件中，使用了自定义属性，调用该构造方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SideSlipMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SideSlipMenu, defStyleAttr, 0);
        menuRightPadding = (int) typedArray.getDimension(R.styleable.SideSlipMenu_rightPadding, mMenuRightPadding);
        typedArray.recycle();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = wm.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);

        mScreenWidth = outMetrics.widthPixels;
        // 将dp转换为px
        mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
    }

    /**
     * 设置子view的宽高，以及自身的宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!onMeasureOnce) {
            mWrapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWrapper.getChildAt(0);
            mContent = (ViewGroup) mWrapper.getChildAt(1);

            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - menuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;

            onMeasureOnce = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 决定子view放置的位置
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {
            this.scrollTo(mMenuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if(scrollX >= mMenuWidth/2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    private void open() {
        this.smoothScrollTo(0, 0);
    }

    private void off() {
        this.smoothScrollTo(mMenuWidth, 0);
    }

    public void menuToggle() {
        if(isOpen) {
            off();
            isOpen = false;
        } else {
            open();
            isOpen = true;
        }
    }
}
