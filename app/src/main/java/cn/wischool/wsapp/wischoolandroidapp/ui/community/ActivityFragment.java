package cn.wischool.wsapp.wischoolandroidapp.ui.community;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.adapter.ActivityAdapter;
import cn.wischool.wsapp.wischoolandroidapp.infobean.ActivityItem;
import cn.wischool.wsapp.wischoolandroidapp.widget.MyDecoration;

/**
 * Created by Administrator on 2017/2/24.
 */

public class ActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private ActivityAdapter adapter;
    private List<ActivityItem> datas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.CommunityActivity);
        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View contextView = localInflater.inflate(R.layout.fragment_activity, container, false);

        recyclerView = (RecyclerView) contextView.findViewById(R.id.activityRV);
        initData();
        adapter = new ActivityAdapter(getContext(),datas);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyDecoration(getContext(), MyDecoration.VERTICAL_LIST));

        return contextView;
    }

    private void initData() {
        datas = new ArrayList<>();
        ActivityItem item1 = new ActivityItem("暑假背包行",7,R.drawable.community_activity_item_background);
        ActivityItem item2 = new ActivityItem("瑜伽俱乐部静坐比赛",5,R.drawable.community_activity_item_background2);
        ActivityItem item3 = new ActivityItem("车协元旦清远骑行",6,R.drawable.community_activity_item_background3);
        ActivityItem item4 = new ActivityItem("挑战大胃王比赛",9,R.drawable.community_activity_item_background);
        datas.add(item1);
        datas.add(item2);
        datas.add(item3);
        datas.add(item4);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
