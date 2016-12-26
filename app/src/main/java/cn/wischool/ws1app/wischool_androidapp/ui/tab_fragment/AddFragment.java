package cn.wischool.ws1app.wischool_androidapp.ui.tab_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.wischool.ws1app.wischool_androidapp.R;

/**
 * Created by xiaoxingxing on 2016/5/11.
 */
public class AddFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add,null);
        return view;
    }
}
