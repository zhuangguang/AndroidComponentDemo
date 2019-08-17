package com.example.zhuangguang.common.widget.pulltorefresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhuangguang.common.R;
import com.example.zhuangguang.common.widget.LVCircularRing;

/**
 * @author 庄光
 * @time 2019/1/28  20:50
 * @desc ${刷新控件}
 */
public class RefreshView extends FrameLayout implements IRefreshView{
    private TextView tv;
    private ImageView arrow;
    //    private ProgressBar progressBar;
    private LVCircularRing lvCircularRing;
    private boolean refresh = false;//是否是刷新
    public RefreshView(@NonNull Context context) {
        this(context ,null);
    }

    public RefreshView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View childView = View.inflate(context, R.layout.layout_refresh_view, null);
        addView(childView);
        tv = childView.findViewById(R.id.header_tv);
        arrow = childView.findViewById(R.id.header_arrow);
        lvCircularRing = childView.findViewById(R.id.header_progress);
    }

    @Override
    public void begin() {

    }

    @Override
    public void progress(float progress, float all) {
        float s = progress/all;
        if(s >= 0.9f) {
            arrow.setRotation(180);
        }else {
            arrow.setRotation(0);
        }

        if (progress >= all - 10) {
            tv.setText(refresh ? R.string.pull_loose_refreshing : R.string.pull_loose_load_more);
        } else {
            tv.setText(refresh ? R.string.pull_down_refresh : R.string.pull_up_loading);
        }

    }

    @Override
    public void finishing(float progress, float all) {
        lvCircularRing.stopAnim();
    }

    @Override
    public void loading() {

        arrow.setVisibility(GONE);
        lvCircularRing.setVisibility(VISIBLE);
        lvCircularRing.startAnim();
        tv.setText(R.string.loading);
    }

    @Override
    public void normal() {
        arrow.setVisibility(VISIBLE);
        lvCircularRing.setVisibility(GONE);
        lvCircularRing.stopAnim();
        tv.setText(refresh ? R.string.pull_down_refresh : R.string.pull_up_loading);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setType(boolean refresh) {
        this.refresh = refresh;
    }
}
