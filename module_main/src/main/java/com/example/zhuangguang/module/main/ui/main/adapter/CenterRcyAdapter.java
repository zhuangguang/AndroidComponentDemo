package com.example.zhuangguang.module.main.ui.main.adapter;

import android.content.Context;

import com.example.zhuangguang.common.base.adapter.BaseListAdapter;
import com.example.zhuangguang.common.bean.Template;
import com.example.zhuangguang.common.listener.OnListItemClickListener;
import com.example.zhuangguang.module.main.R;

import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/2/14  22:10
 * @desc ${TODD}
 */
public class CenterRcyAdapter extends BaseListAdapter<Template> {
    public CenterRcyAdapter(Context context, List<Template> items, int layoutResId, OnListItemClickListener listener) {
        super(context, items, layoutResId, listener);
    }

    @Override
    protected void onBindData(SuperViewHolder holder, int viewType, int layoutPosition, Template data) {
        holder.setText(R.id.tv_title, data.getTitle());
        holder.setText(R.id.tv_describe, data.getDescribe());
        holder.setBackgroundResource(R.id.iv_icon, data.getRes());
    }
}
