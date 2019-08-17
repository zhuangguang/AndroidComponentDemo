package com.example.zhuangguang.module_wan_android.ui.wxnumber.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.zhuangguang.common.base.adapter.BaseListAdapter;
import com.example.zhuangguang.common.listener.OnListItemClickListener;
import com.example.zhuangguang.module_wan_android.R;
import com.example.zhuangguang.module_wan_android.bean.Article;

import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  17:13
 * @desc ${TODD}
 */
public class WXArticleAdapter extends BaseListAdapter<Article> {

    public WXArticleAdapter(Context context, List<Article> items, int layoutResId, OnListItemClickListener listener) {
        super(context, items, layoutResId, listener);
    }

    @Override
    public void onBindData(SuperViewHolder holder, int viewType, int layoutPosition, Article data) {
        TextView textView = holder.findViewById(R.id.tv_text);
        if (layoutPosition % 2 == 0) {
            textView.setBackgroundColor(getColor(R.color.light_blue));
        } else {
            textView.setBackgroundColor(getColor(R.color.light_yellow));
        }
        textView.setText(data.getTitle());

    }

    private int getColor(int res) {
        return getContext().getResources().getColor(res);
    }

}

