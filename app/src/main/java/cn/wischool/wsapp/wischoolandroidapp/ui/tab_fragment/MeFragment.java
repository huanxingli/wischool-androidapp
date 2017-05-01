package cn.wischool.wsapp.wischoolandroidapp.ui.tab_fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.common.ImageUtil;
import cn.wischool.wsapp.wischoolandroidapp.infobean.PersonItemData;
import cn.wischool.wsapp.wischoolandroidapp.adapter.MeRecyclerAdapter;

/**
 * Created by xiaoxingxing on 2016/5/11.
 */
public class MeFragment extends Fragment {

    private TextView textName; //用户姓名

    private ImageView imageLogo; //用户头像

    private TextView textBack;

    private TextView textDetails;

    private TextView textFriends;

    private TextView textCredits;

    private RecyclerView listView;

    private MeRecyclerAdapter adapter;
    private PersonItemData datas;
    private List<PersonItemData> lists;
    //private ImageLoader imageLoader;

    private static int CAMERA_REQUEST_CODE = 1;
    private static int GALLERY_REQUEST_CODE = 2;
    private static int CROP_REQUEST_CODE = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,null);
        initView(view);
        initData();
        //imageLoader= ImageLoader.getInstance();
        adapter = new MeRecyclerAdapter(getActivity(),lists);
        listView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        listView.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(new MeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "短点击" + position, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(), "长点击" + position, Toast.LENGTH_LONG).show();
            }
        });

        this.registerForContextMenu(imageLogo);
        return view;
    }

    private void initView(View view) {
        textName = (TextView) view.findViewById(R.id.person_info_name);
        imageLogo = (ImageView) view.findViewById(R.id.person_info_logo);
        textBack = (TextView) view.findViewById(R.id.person_info_back);
        textDetails = (TextView) view.findViewById(R.id.person_info_details);
        textFriends = (TextView) view.findViewById(R.id.person_info_friends);
        textCredits = (TextView) view.findViewById(R.id.person_info_credits);
        listView = (RecyclerView) view.findViewById(R.id.person_info_listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1, 1, 1, "拍照");
        menu.add(1, 2, 1, "从相册里选择");
        menu.add(1, 3, 1, "取消");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST_CODE);
                break;
            case 2:
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");
                startActivityForResult(intent1,GALLERY_REQUEST_CODE);
                break;
            case 3:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE){//从拍照界面返回
            if (data == null){
                return;
            }
            Bundle bundle = data.getExtras();
            Bitmap bitmap = bundle.getParcelable("data");
            Uri uri = ImageUtil.saveBitmap(bitmap);
            startImageZoom(uri);
        }else if (requestCode == GALLERY_REQUEST_CODE){ //从图库界面返回
            if (data != null){
                Uri uri;
                uri = data.getData();
                Uri fileUri = ImageUtil.conertUri(getActivity(),uri);
                startImageZoom(fileUri);
            }
        }else if (requestCode == CROP_REQUEST_CODE){
            if (data == null){
                return;
            }
            Bundle bundle = data.getExtras();
            Bitmap bitmap = bundle.getParcelable("data");
            Uri uri = ImageUtil.saveBitmap(bitmap);
            int logoWith = imageLogo.getWidth();
            int logoHeight = imageLogo.getHeight();
            Picasso.with(getActivity()).load(uri).resize(logoWith,logoHeight).into(imageLogo);
        }
    }

    /**
     * 调用系统裁剪图片
     * @param uri
     */
    private void startImageZoom(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop","true");
        intent.putExtra("aspectX",1);//宽和高都是以1:1缩放
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,CROP_REQUEST_CODE);
    }

    /**
     * 初始化列表数据
     */
    private void initData() {
        lists = new ArrayList<PersonItemData>();
        datas = new PersonItemData(R.drawable.person_info_detail,
                "全部订单","查看全部订单记录");
        lists.add(datas);
        datas = null;
        datas = new PersonItemData(R.drawable.person_info_credit,
                "积分和优惠","使用积分优惠券");
        lists.add(datas);
        datas = null;
        datas = new PersonItemData(R.drawable.person_info_service,
                "用户服务","查看用户服务");
        lists.add(datas);
    }
}
