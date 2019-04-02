package com.igor.scrumassistant.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;

public class CheckableAutofitHeightConstraintLayout extends ConstraintLayout implements Checkable {
    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};

    private boolean mIsChecked;
    private boolean mIsCheckable;

    public CheckableAutofitHeightConstraintLayout(Context context)
    {
        super(context);
    }

    public CheckableAutofitHeightConstraintLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CheckableAutofitHeightConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public CheckableAutofitHeightConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
//    {
//        super(context, attrs, defStyleAttr);
//    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace)
    {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked())
        {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    public boolean isCheckable()
    {
        return mIsCheckable;
    }


    public void setCheckable(boolean isCheckable)
    {
        boolean wasCheckable = isCheckable();
        mIsCheckable = isCheckable;
        if (!isCheckable && isChecked())
        {
            setChecked(false);
        }
        else if (wasCheckable ^ mIsCheckable)
        {
            refreshDrawableState();
        }

    }

    @Override
    public void setChecked(boolean isChecked)
    {
        boolean wasChecked = isChecked();
        mIsChecked = isCheckable() && isChecked;

        if (wasChecked ^ mIsChecked)
        {
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked()
    {
        return mIsChecked;
    }

    @Override
    public void toggle()
    {
        setChecked(!mIsChecked);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        //noinspection SuspiciousNameCombination
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
