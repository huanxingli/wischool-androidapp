package cn.wischool.wsapp.wischoolandroidapp.ui.taskshow_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.widget.tasksearch.DataInput;
import cn.wischool.wsapp.wischoolandroidapp.widget.tasksearch.TaskCellData;
import cn.wischool.wsapp.wischoolandroidapp.widget.tasksearch.TaskListViewAdapter;

/**
 * Created by Administrator on 2016/1/6.
 */
public class TaskListFragment extends Fragment {

    private ListView taskList;

    private TaskListViewAdapter adapter = null;

    private List<TaskCellData> list = new ArrayList<TaskCellData>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_task,container,false);
        taskList = (ListView) v.findViewById(R.id.searchListView);
        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList();
    }

    private void initList() {
        for (int i = 0; i< DataInput.data.length; i++){
            list.add(DataInput.data[i]);
        }
        adapter = new TaskListViewAdapter(getActivity(),list);

        taskList.setAdapter(adapter);

    }
}
