package cn.wischool.wsapp.wischoolandroidapp.ui.community;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.adapter.HotAdapter;
import cn.wischool.wsapp.wischoolandroidapp.infobean.HotItemData;

/**
 * Created by Administrator on 2017/2/24.
 */

public class HotFragment extends Fragment {

    private RecyclerView mRecycleView;
    private List<HotItemData> datas;
    private HotAdapter hotAdapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hot,container,false);

        mRecycleView = (RecyclerView) view.findViewById(R.id.recycleView);

        //初始化测试数据
        initData();
        hotAdapter = new HotAdapter(getActivity(),datas);
        mRecycleView.setAdapter(hotAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        mRecycleView.setLayoutManager(layoutManager);



        return view;
    }

    private void initData() {
        datas = new ArrayList<>();
        List<Integer> photos1 = new ArrayList<>();
        photos1.add(R.drawable.hot1);
        HotItemData hotItem1 = new HotItemData(R.drawable.head1,"小李子007",
                "#元旦倒数##夜骑花城广场##夜游珠江##夜骑装备",
                "新的一年新的愿望，2017你好！",
                1,photos1, false,12,"10分钟前");

        List<Integer> photos2 = new ArrayList<>();
        photos2.add(R.drawable.hot2);
        HotItemData hotItem2 = new HotItemData(R.drawable.head2,"小魔魔",
                "#景色##晨曦##希望的开始",
                "这里的景色这是美！",
                1,photos2, false,8,"10分钟前");

        List<Integer> photos3 = new ArrayList<>();
        photos3.add(R.drawable.hot3);
        HotItemData hotItem3 = new HotItemData(R.drawable.head3,"对方正在输入...",
                "#景色##夕阳西下##辛苦的一天",
                "辛苦了一天，终于可以休息了！",
                1,photos3, false,8,"40分钟前");

        List<Integer> photos4 = new ArrayList<>();
        photos4.add(R.drawable.hot4);
        HotItemData hotItem4 = new HotItemData(R.drawable.head4,"扫寺僧",
                "#景色##夕阳西下##辛苦的一天",
                "辛苦了一天，哈哈哈！",
                1,photos4, false,8,"32分钟前");
        datas.add(hotItem1);
        datas.add(hotItem2);
        datas.add(hotItem3);
        datas.add(hotItem4);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
