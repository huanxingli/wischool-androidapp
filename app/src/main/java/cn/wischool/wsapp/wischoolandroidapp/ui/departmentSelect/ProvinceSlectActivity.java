package cn.wischool.wsapp.wischoolandroidapp.ui.departmentSelect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;

import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.adapter.department.ProvinceAdapter;
import cn.wischool.wsapp.wischoolandroidapp.model.db.Province;
import cn.wischool.wsapp.wischoolandroidapp.model.db.business.ProvinceService;
import cn.wischool.wsapp.wischoolandroidapp.ui.BaseFragmentActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Administrator on 2016/2/29.
 *
 * 省份列表
 */

@ContentView(R.layout.activity_province)
public class ProvinceSlectActivity extends BaseFragmentActivity {

    @InjectView(R.id.list_province)
    private ListView listProvinces;

    @InjectView(R.id.province_back)
    private TextView back;

    @Inject
    private ProvinceService ps;


    private ArrayAdapter<String> adapter;
    private List<Province > list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        list = ps.getProvinces();
//        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter = new ProvinceAdapter(this,android.R.layout.simple_list_item_1,list);
        listProvinces.setAdapter(adapter);

        listProvinces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProvinceSlectActivity.this, CollegeSelectActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", (int) id);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishCurrentActivity();
            }
        });
    }
}
