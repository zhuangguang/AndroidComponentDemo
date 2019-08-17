package com.example.zhuangguang.module.main.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zhuangguang.common.base.BaseFragment;
import com.example.zhuangguang.common.bean.Template;
import com.example.zhuangguang.common.listener.OnListItemClickListener;
import com.example.zhuangguang.common.utils.ActivityToActivity;
import com.example.zhuangguang.module.main.R;
import com.example.zhuangguang.module.main.R2;
import com.example.zhuangguang.module.main.ui.main.adapter.CenterRcyAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

/**
 * @author 庄光
 * @time 2019/2/14  22:01
 * @desc ${TODD}
 */
public class CenterFragment extends BaseFragment  implements OnListItemClickListener {
    private static final String TAG = CenterFragment.class.getSimpleName();
    @BindView(R2.id.recycle_view)
    RecyclerView recyclerView;

    private List<Template> data = new ArrayList<>();

    public static CenterFragment getInstance(ArrayList<Template> data){
        CenterFragment fragment = new CenterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG,data);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_center;
    }

    @Override
    protected void initView() {
        data.addAll((Collection<? extends Template>)getArguments().getSerializable(TAG));
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerView.setAdapter(new CenterRcyAdapter(mContext, data, R.layout.main_item_of_center_list, this));

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onItemClick(View view, int position) {
        ActivityToActivity.toActivity(mContext, data.get(position));
    }
}
