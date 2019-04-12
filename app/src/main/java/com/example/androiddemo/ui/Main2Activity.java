package com.example.androiddemo.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.androiddemo.R;
import com.example.androiddemo.ui.horizontalScroll.HorizontalPageLayoutManager;
import com.example.androiddemo.ui.horizontalScroll.MyAdapter;
import com.example.androiddemo.ui.horizontalScroll.PagingScrollHelper;
import com.example.androiddemo.utils.ToastUtils;

import java.lang.reflect.Method;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView rv_demo;
    private static final int PERMISSION_READ_WRITE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        requestPermission();
//        shutdownOrReboot(false);
        rv_demo = findViewById(R.id.rv_demo);
        rv_demo.setLayoutManager(new HorizontalPageLayoutManager(2, 4));
        List<String> mDataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mDataList.add("item" + i);
        }
        MyAdapter adapter = new MyAdapter(this, mDataList);
        rv_demo.setAdapter(adapter);
        PagingScrollHelper helper = new PagingScrollHelper();
        helper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
            @Override
            public void onPageChange(int index) {
                ToastUtils.showToast("item " + index + "click");
            }
        });
        helper.setUpRecycleView(rv_demo);
        helper.updateLayoutManger();
    }

    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.REBOOT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REBOOT}, PERMISSION_READ_WRITE);
        }
    }

    public static void shutdownOrReboot(final boolean shutdown) {
        try {
            Class<?> ServiceManager = Class.forName("android.os.ServiceManager");
            // 获得ServiceManager的getService方法
            Method getService = ServiceManager.getMethod("getService", java.lang.String.class);
            // 调用getService获取RemoteService
            Object oRemoteService = getService.invoke(null, Context.POWER_SERVICE);
            // 获得IPowerManager.Stub类
            Class<?> cStub = Class.forName("android.os.IPowerManager$Stub");
            // 获得asInterface方法
            Method asInterface = cStub.getMethod("asInterface", android.os.IBinder.class);
            // 调用asInterface方法获取IPowerManager对象
            Object oIPowerManager = asInterface.invoke(null, oRemoteService);
            if (shutdown) {
                // 获得shutdown()方法
                Method shutdownMethod = oIPowerManager.getClass().getMethod(
                        "shutdown", boolean.class, String.class, boolean.class);
                // 调用shutdown()方法
                shutdownMethod.invoke(oIPowerManager, true, null, false);
            } else {
                // 获得reboot()方法
                Method rebootMethod = oIPowerManager.getClass().getMethod("reboot",
                        boolean.class, String.class, boolean.class);
                // 调用reboot()方法
                rebootMethod.invoke(oIPowerManager, true, null, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_READ_WRITE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(Main2Activity.class.getSimpleName(), "permission granted");
                }else{
                    Log.e(Main2Activity.class.getSimpleName(), "permission denied");
                }
            }
        }
    }
}
