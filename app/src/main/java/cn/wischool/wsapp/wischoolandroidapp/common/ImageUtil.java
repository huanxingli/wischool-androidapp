package cn.wischool.wsapp.wischoolandroidapp.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/12.
 */
public class ImageUtil {
    //获取图片
    @SuppressWarnings("ResourceType")
    public static Bitmap getBitmap(Context context,int id){
        //获取图片资源
        InputStream is = null;
        try {
            is = context.getResources().openRawResource(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 将drawable转化为Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable){
        if (drawable instanceof BitmapDrawable){
            BitmapDrawable bd = (BitmapDrawable)drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,w,h);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 将一个Bitmap保存到SD卡中
     * @param bm
     * @return file类型的Uri
     */
    public static Uri saveBitmap(Bitmap bm){
        File tpDir = new File(Environment.getExternalStorageDirectory() + "/wischool");
        if (!tpDir.exists()){
            tpDir.mkdir();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String str = sdf.format(new Date());
        File img = new File(tpDir.getAbsolutePath() + "/" + str + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG,85,fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将content类型的Uri转化为File类型的Uri
     * @param uri
     * @return
     */
    public static Uri conertUri(Context context,Uri uri){
        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return saveBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources resources,int resId,int reqWidth,int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources,resId,options);
        //计算采样率
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);
        //根据采样率加载Bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources,resId,options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSamleSize = 1;
        if (height>reqHeight || width>reqWidth){
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while((halfHeight/inSamleSize)>=reqHeight && (halfWidth/inSamleSize)>=reqWidth){
                inSamleSize *= 2;
            }
        }
        return inSamleSize;
    }
}
