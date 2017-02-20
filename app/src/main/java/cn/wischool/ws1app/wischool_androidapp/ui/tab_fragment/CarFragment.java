package cn.wischool.ws1app.wischool_androidapp.ui.tab_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.wischool.ws1app.wischool_androidapp.R;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesHelper;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesLifecycle;
import cn.wischool.ws1app.wischool_androidapp.ui.departmentSelect.ProvinceSlectActivity;

/**
 * Created by xiaoxingxing on 2016/5/11.
 */
public class CarFragment extends Fragment {

    private Button btnDepartment;
    private Button btnRepLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car,null);
        btnDepartment = (Button) view.findViewById(R.id.department_btn);
        btnRepLogin = (Button) view.findViewById(R.id.repeatlogin);
        setListener();
        return view;
    }

    private void setListener(){
        btnDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProvinceSlectActivity.class);
                startActivity(intent);
            }
        });

        btnRepLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SharedPreferencesHelper.putBoolean(getActivity().getApplicationContext(), SharedPreferencesLifecycle.forever, "hasLogin", false);

           }
        });
    }

}
