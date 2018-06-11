package com.cheng.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.cheng.app.dialog.ActionSheetDialog.*;
import com.cheng.app.dialog.ActionSheetDialog;
import com.cheng.app.dialog.ChooseDialog;
import com.cheng.app.dialog.CustomDialog;
import com.cheng.app.dialog.MyAlertDialog;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;

import java.util.ArrayList;
import java.util.List;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private String headImagPath = Environment.getExternalStorageDirectory().toString() + File.separator + "usericon.jpg";

    WheelView wheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.alert_dialog_btn).setOnClickListener(this);
        findViewById(R.id.eit_dialog_btn).setOnClickListener(this);

        findViewById(R.id.custom_dialog_btn).setOnClickListener(this);
        findViewById(R.id.sheet_dialog_btn).setOnClickListener(this);

        wheelView = findViewById(R.id.wheelview);
        wheelView.setCyclic(false);

        final List<String> mOptionsItems = new ArrayList<>();
        mOptionsItems.add("item0");
        mOptionsItems.add("item1");
        mOptionsItems.add("item2");

        wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                Toast.makeText(MainActivity.this, "" + mOptionsItems.get(index), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_dialog_btn:
                CustomDialog customDialog = new CustomDialog(this)
                        .setTitle("这是一条消息")
                        .setMessage("你确定删除这个联系人吗？")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .setCancelButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                customDialog.show();
                break;
            case R.id.eit_dialog_btn:
                new ChooseDialog(this).show();
                break;
            case R.id.alert_dialog_btn:
                MyAlertDialog myAlertDialog = new MyAlertDialog(this)
                        .builder().setTitle("提醒")
                        .setMsg("这是一条消息！")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();
                break;

            case R.id.sheet_dialog_btn:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        // 判断存储卡是否可以用，可用进行存储
                                        if (hasSdcard()) {
                                            File f = new File(headImagPath);
                                            if(f.exists()){
                                                f.delete();
                                            }
                                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                                    Uri.fromFile(new File(headImagPath)));
                                            cameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                                            startActivityForResult(cameraIntent, PHOTO_REQUEST_CAMERA);
                                        } else {
                                            Toast.makeText(MainActivity.this, "未找到SD卡，无法进行存储！",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                        .addSheetItem("从手机相册选择", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Intent intent = new Intent(Intent.ACTION_PICK);
                                        intent.setType("image/*");
                                        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                                    }
                                }).show();
                break;
        }
    }


    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
}
