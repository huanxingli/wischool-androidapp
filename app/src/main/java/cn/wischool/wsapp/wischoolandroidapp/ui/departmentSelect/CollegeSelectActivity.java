package cn.wischool.wsapp.wischoolandroidapp.ui.departmentSelect;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.inject.Inject;

import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.adapter.department.CollegeSelectAdapter;
import cn.wischool.wsapp.wischoolandroidapp.common.UiHelper;
import cn.wischool.wsapp.wischoolandroidapp.model.db.College;
import cn.wischool.wsapp.wischoolandroidapp.model.db.business.CollegeService;
import cn.wischool.wsapp.wischoolandroidapp.ui.BaseFragmentActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Administrator on 2016/2/29.
 *
 * 学校列表
 */
@ContentView(R.layout.activity_college)
public class CollegeSelectActivity extends BaseFragmentActivity {

    @InjectView(R.id.list_colleges)
    private ListView listColleges;

    @InjectView(R.id.college_back)
    private ImageButton back;

    @InjectView(R.id.college_search)
    private EditText collegeSearch;

    @Inject
    private CollegeService collegeService;

    private int id;

    private ArrayAdapter<String> adapter;
    private List<College> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getInt("id");
        list = collegeService.getCollegesById(id + "");
        setListData(list);

    }

    /**
     * 为listview设置数据
     * @param list
     */
    private void setListData(List<College> list) {
        adapter = new CollegeSelectAdapter(this, android.R.layout.simple_list_item_1, list);
        listColleges.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        listColleges.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                UiHelper.getInstance().toastMessage(CollegeSelectActivity.this,
//                        "你选择了" + collegeService.getCollegeById(id+"").getColName());
                Intent intent = new Intent(CollegeSelectActivity.this,DepartmentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ColId",id+"");
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishCurrentActivity();
            }
        });

        collegeSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                list.clear();
                String info = String.valueOf(collegeSearch.getText());
                list = collegeService.getCollegesByName(info,id+"");
                if (list.size()==0){
                    UiHelper.getInstance().toastMessage(CollegeSelectActivity.this,"null");
                }
                adapter.notifyDataSetChanged();
                setListData(list);
            }
        });
    }


}


