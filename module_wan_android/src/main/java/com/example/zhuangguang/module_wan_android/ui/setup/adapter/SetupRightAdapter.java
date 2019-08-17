package com.example.zhuangguang.module_wan_android.ui.setup.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.zhuangguang.common.base.adapter.BaseListAdapter;
import com.example.zhuangguang.common.listener.OnListItemClickListener;
import com.example.zhuangguang.module_wan_android.R;
import com.example.zhuangguang.module_wan_android.bean.ClassificationChild;

import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  16:09
 * @desc ${TODD}
 */
public class SetupRightAdapter extends BaseListAdapter<ClassificationChild> {
    private int selectIndex;

    public SetupRightAdapter(Context context, List<ClassificationChild> items, int layoutResId, OnListItemClickListener listener) {
        super(context, items, layoutResId, listener);
    }

    @Override
    protected void onBindData(SuperViewHolder holder, int viewType, int layoutPosition, ClassificationChild data) {
        TextView textView = holder.findViewById(R.id.tv_text);
        textView.setText(data.getName());
        if (layoutPosition == selectIndex) {
            textView.setTextColor(getContext().getResources().getColor(R.color.white));
            textView.setBackgroundColor(getContext().getResources().getColor(R.color.orange));
        } else {
            textView.setTextColor(getContext().getResources().getColor(R.color.black));
            textView.setBackgroundColor(getContext().getResources().getColor(R.color.transparent));
        }
    }

    public void notifyData(int selectIndex) {
        this.selectIndex = selectIndex;
        notifyDataSetChanged();
    }
}
