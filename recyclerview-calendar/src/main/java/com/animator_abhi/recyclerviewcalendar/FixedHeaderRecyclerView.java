package com.animator_abhi.recyclerviewcalendar;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Fixed header of recyclerView.
 */
class FixedHeaderRecyclerView extends RecyclerView {
    private View mFixedHeaderView;

    private int mFixedHeaderViewWidth;
    private int mFixedHeaderViewHeight;

    private boolean mFixedHeaderViewVisible;

    private FixedHeaderAdapter mFixedHeaderAdapter;

    private TextView headerText;
    private View fixedDivider;

    public FixedHeaderRecyclerView(Context context) {
        super(context);

        init();
    }

    public FixedHeaderRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public FixedHeaderRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        setFadingEdgeLength(0);
    }

    /**
     * Setup fixedHeaderView.
     *
     * @param layoutResId if 0 than fixedHeaderView is null.
     */
    public void setFixedHeaderView(int layoutResId) {
        if (layoutResId == 0) {
            mFixedHeaderView = null;
        } else {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            mFixedHeaderView = layoutInflater.inflate(layoutResId, this, false);
            headerText = (TextView) mFixedHeaderView.findViewById(R.id.month);
            headerText.setTextSize(Util.getInstance().monthTextSize);
            fixedDivider = mFixedHeaderView.findViewById(R.id.divider);
            if (Util.getInstance().isDividerColorChangeAt[0]) {
                fixedDivider.setBackgroundColor(Util.getInstance().dividerColor);
            }

           /* if (Util.getInstance().isDividerVisible)
            {   fixedDivider.setVisibility(View.VISIBLE);
            }
            else
            {  fixedDivider.setVisibility(View.INVISIBLE);}*/
        }

        requestLayout();
    }


    public View getFixedHeaderView() {
        return mFixedHeaderView;
    }

    public void setFixedHeaderColor(int color) {
        headerText.setBackgroundColor(color);
    }


    public TextView getFixedHeader() {
        return headerText;
    }


    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        mFixedHeaderAdapter = (FixedHeaderAdapter) adapter;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mFixedHeaderView != null) {
            measureChild(mFixedHeaderView, widthMeasureSpec, heightMeasureSpec);

            mFixedHeaderViewWidth = mFixedHeaderView.getMeasuredWidth();
            mFixedHeaderViewHeight = mFixedHeaderView.getMeasuredHeight();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (mFixedHeaderView != null) {
            mFixedHeaderView.layout(0, 0, mFixedHeaderViewWidth, mFixedHeaderViewHeight);

            invalidateFixedHeaderView();
        }
    }

    private void invalidateFixedHeaderView() {
        if (mFixedHeaderView == null || mFixedHeaderAdapter == null) {
            return;
        }

        int position = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        if (position == -1) {
            return;
        }

        int state = mFixedHeaderAdapter.getFixedHeaderState(position);

        switch (state) {
            case FixedHeaderAdapter.STATE_GONE: {
                mFixedHeaderViewVisible = false;

                break;
            }
            case FixedHeaderAdapter.STATE_VISIBLE: {
                mFixedHeaderViewVisible = true;

                if (mFixedHeaderView.getTop() != 0) {
                    mFixedHeaderView.layout(0, 0, mFixedHeaderViewWidth, mFixedHeaderViewHeight);
                }

                mFixedHeaderAdapter.configureFixedHeader(mFixedHeaderView, position);

                break;
            }
            case FixedHeaderAdapter.STATE_PUSHABLE: {
                mFixedHeaderViewVisible = true;

                int firstViewBottom = getChildAt(0).getBottom();
                int y = mFixedHeaderViewHeight < firstViewBottom ? 0 : firstViewBottom - mFixedHeaderViewHeight;
                if (mFixedHeaderView.getTop() != y) {
                    mFixedHeaderView.layout(0, y, mFixedHeaderViewWidth, mFixedHeaderViewHeight + y);
                }

                mFixedHeaderAdapter.configureFixedHeader(mFixedHeaderView, position);

                break;
            }
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (mFixedHeaderViewVisible && mFixedHeaderView != null) {
            drawChild(canvas, mFixedHeaderView, getDrawingTime());
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        invalidateFixedHeaderView();
    }

    /**
     *  adapter of RecyclerView Must implement this interface.
     */
    interface FixedHeaderAdapter {
        /**
         * Always Hide.
         */
        int STATE_GONE = 0;
        /**
         *Always show.
         */
        int STATE_VISIBLE = 1;
        /**
         * May Push.
         */
        int STATE_PUSHABLE = 2;

        /**
         * The return value corresponding to the determined position for fixedHeaderView status.
         *
         * @return STATE_GONE, STATE_VISIBLE 或 STATE_PUSHABLE.
         */
        int getFixedHeaderState(int position);

        /**
         * Configuration corresponds to the position of fixedHeaderView.
         */
        void configureFixedHeader(View fixedHeaderView, int position);
    }

  /*  public void setHeaderSize(float size)
    {
        headerText.setTextSize(size);
        requestLayout();
    }*/

}
