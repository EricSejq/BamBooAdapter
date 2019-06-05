package com.example.eric.bambooadapter.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.eric.bambooadapter.R;

import androidx.core.content.ContextCompat;

/**
 * Description: 滑动按钮
 * Data：2019/5/13-0:01
 *
 * @author: eric
 */
public class SlideButton extends View {

    private Paint mCirclePaint;
    private Paint mBackPaint;

    private int mCircleRadius = 0;
    private int mX;
    private int mStartX;
    private int mEndX;
    private boolean isWorking = false;
    private boolean isAnimation = false;

    public SlideButton(Context context) {
        this(context, null);
    }

    public SlideButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

        mBackPaint = new Paint();
        mBackPaint.setAntiAlias(true);
        mBackPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));

        this.setOnClickListener(view -> onCheck());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //initValue
        if (mCircleRadius == 0) {
            mCircleRadius = getHeight() / 2 - 4;
            mStartX = 0 + mCircleRadius + 2;
            mEndX = getWidth() - mCircleRadius - 2;
            mX = mStartX;
        }

        if (getWidth() > 2 * mX) {
            mBackPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        } else {
            mBackPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        }

        //drawback
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), getHeight() / 2, getHeight() / 2, mBackPaint);

        //slide
        canvas.drawCircle(mX, getHeight() / 2, mCircleRadius, mCirclePaint);
    }

    public void onCheck() {
        if (isAnimation) {
            return;
        }
        ValueAnimator animator;
        if (isWorking) {
            animator = ValueAnimator.ofInt(mEndX, mStartX);
        } else {
            animator = ValueAnimator.ofInt(mStartX, mEndX);
        }
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(300);
        animator.addUpdateListener(valueAnimator -> {
            mX = (int) valueAnimator.getAnimatedValue();
            invalidate();
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimation = false;
                isWorking = !isWorking;
                if (null != mSlideChangeListener) {
                    mSlideChangeListener.changed(isWorking);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

    public void setmSlideChangeListener(SlideChangeListener mSlideChangeListener) {
        this.mSlideChangeListener = mSlideChangeListener;
    }

    private SlideChangeListener mSlideChangeListener;

    public interface SlideChangeListener {
        /**
         * 滑动结束时回调
         *
         * @param isWorking 滑动结束的状态
         */
        void changed(boolean isWorking);
    }
}
