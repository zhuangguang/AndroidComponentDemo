package com.example.zhuangguang.module_wan_android.ui.main.adapter;

import android.content.Context;

import com.example.zhuangguang.common.base.adapter.BaseListAdapter;
import com.example.zhuangguang.common.bean.Template;
import com.example.zhuangguang.common.listener.OnListItemClickListener;
import com.example.zhuangguang.module_wan_android.R;

import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/2/23  20:59
 * @desc ${首页适配器}
 */
public class HomeRcyAdapter extends BaseListAdapter<Template>{
    public HomeRcyAdapter(Context context, List<Template> items, int layoutResId, OnListItemClickListener listener) {
        super(context, items, layoutResId, listener);
    }

    @Override
    public void onBindData(SuperViewHolder holder, int viewType, int layoutPosition, Template data) {
        holder.setText(R.id.tv_text, data.getTitle());
        holder.setBackgroundResource(R.id.iv_icon, data.getRes());
    }
}
