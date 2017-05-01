package cn.wischool.wsapp.wischoolandroidapp.ui.tab_fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.common.SharedPreferencesHelper;
import cn.wischool.wsapp.wischoolandroidapp.common.SharedPreferencesLifecycle;
import cn.wischool.wsapp.wischoolandroidapp.ui.departmentSelect.ProvinceSlectActivity;

/**
 * Created by xiaoxingxing on 2016/5/11.
 */
public class CarFragment extends Fragment {

    private Button btnDepartment;
    private Button btnRepLogin;
    private Button btnMakeEncode;
    private ImageView imageView;
    private EditText editText;
    private final static int QR_WIDTH = 400;
    private final static int QR_HEIGHT = 400;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, null);
        btnDepartment = (Button) view.findViewById(R.id.department_btn);
        btnRepLogin = (Button) view.findViewById(R.id.repeatlogin);
        btnMakeEncode = (Button) view.findViewById(R.id.makeEncode);
        imageView = (ImageView) view.findViewById(R.id.encodeImg);
        editText = (EditText) view.findViewById(R.id.encodeEdit);
        setListener();
        return view;
    }

    private void setListener() {
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
        btnMakeEncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText.getText().toString();
                Bitmap bitmap = createImage(string);
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    private Bitmap createImage(String text) {
        try {

            if (TextUtils.isEmpty(text)) {
                return null;
            }
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix martix = writer.encode(text, BarcodeFormat.QR_CODE,
                    QR_WIDTH, QR_HEIGHT);

            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
                    BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
                    Bitmap.Config.ARGB_8888);

            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }

    }
}
