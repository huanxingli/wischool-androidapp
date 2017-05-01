package cn.wischool.wsapp.wischoolandroidapp.ui.departmentSelect;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;


import com.google.inject.Inject;

import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.adapter.department.DepartmentAdapter;
import cn.wischool.wsapp.wischoolandroidapp.common.UiHelper;
import cn.wischool.wsapp.wischoolandroidapp.model.db.Department;
import cn.wischool.wsapp.wischoolandroidapp.model.db.business.DepartmentService;
import cn.wischool.wsapp.wischoolandroidapp.ui.BaseFragmentActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Administrator on 2016/3/6.
 *
 * 学院列表
 */

@ContentView(R.layout.activity_department)
public class DepartmentActivity extends BaseFragmentActivity {

    @InjectView(R.id.department_list)
    private ListView listDepartment;

    @InjectView(R.id.department_back)
    private ImageButton back;

    @Inject
    private DepartmentService dptService;

    private ArrayAdapter<Department> adapter;
    private List<Department> lists;

    private String ColId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle bundle = this.getIntent().getExtras();
        ColId = bundle.getString("ColId");
        lists = dptService.getDepartmentsByColId(ColId);

        adapter = new DepartmentAdapter(this,android.R.layout.simple_list_item_1,lists);
        listDepartment.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishCurrentActivity();
            }
        });

        listDepartment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UiHelper.getInstance().toastMessage(DepartmentActivity.this,
                        "你是" + dptService.getDepartmentById((int)id).getDepName() + "的学生");
            }
        });
    }
}
