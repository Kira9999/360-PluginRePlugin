package com.optima.plugin.repluginlib.base;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.optima.plugin.repluginlib.Logger;
import com.optima.plugin.repluginlib.VerifyVersionReceiver;
import com.optima.plugin.repluginlib.pluginUtils.P_Constants;
import com.optima.plugin.repluginlib.pluginUtils.P_Context;

/**
 * create by wma
 * on 2020/8/17 0017
 */
public class BaseActivity extends AppCompatActivity {
    public String TAG;
    VerifyVersionReceiver receiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        setTitle(this.getClass().getSimpleName());
        Intent intent = getIntent();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new VerifyVersionReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("Test");
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d(TAG, "onActivityResult: requestCode = " + requestCode);
        if (data != null) {
            Logger.d(TAG, "onActivityResult: data = " + data.getStringExtra(P_Constants.INTENT_KEY));
        }
    }

    /**
     * ????????????
     * ???1?????????????????????
     * ???2???????????????????????????
     * ???3?????????????????????
     *
     * @param intent
     * @param isOuterClass ??????????????????????????????Activity
     */
    public void startActivity(Intent intent, boolean isOuterClass) {
        ComponentName component = intent.getComponent();
        String packageName = component.getPackageName();
        Logger.d(TAG, "startActivity: packageName = " + packageName);
        intent.putExtra(P_Constants.INTENT_KEY, this.getClass().getSimpleName());
        if (isOuterClass) {
            if (P_Constants.HOST_PACKAGE_NAME.equals(packageName)) {// ????????????
                super.startActivity(intent);
            } else {
                P_Context.startPluginActivity(BaseActivity.this, intent);// ????????????
            }
        } else {
            super.startActivity(intent);// ??????????????????
        }
    }

    /**
     * @param intent
     * @param requestCode
     * @param isOuterClass ??????????????????????????????Activity
     */
    public void startActivityForResult(Intent intent, int requestCode, boolean isOuterClass) {
        ComponentName component = intent.getComponent();
        String packageName = component.getPackageName();
        Logger.d(TAG, "startActivityForResult: packageName = " + packageName);
        intent.putExtra(P_Constants.INTENT_KEY, this.getClass().getSimpleName());
        if (isOuterClass) {
            if (P_Constants.HOST_PACKAGE_NAME.equals(packageName)) {// ????????????
                super.startActivityForResult(intent, requestCode);
            } else {
                P_Context.startPluginActivityForResult(BaseActivity.this, intent, requestCode);// ????????????
            }
        } else {
            super.startActivityForResult(intent, requestCode);// ??????????????????
        }
    }

    /**
     * ????????????
     *
     * @param service
     * @param isHostToPlugin ?????????????????????????????????
     * @return
     */
    public ComponentName startService(Intent service, boolean isHostToPlugin) {
        if (isHostToPlugin) {
            return P_Context.startPluginService(BaseActivity.this, service);
        } else {
            return super.startService(service);
        }
    }

    /**
     * ????????????
     *
     * @param name
     * @param isHostToPlugin ?????????????????????????????????
     * @return
     */
    public boolean stopService(Intent name, boolean isHostToPlugin) {
        if (isHostToPlugin) {
            return P_Context.stopPluginService(BaseActivity.this, name);
        } else {
            return super.stopService(name);
        }
    }


    /**
     * ????????????
     *
     * @param service
     * @param conn
     * @param isHostToPlugin ?????????????????????????????????
     * @return
     */
    public boolean bindService(Intent service, ServiceConnection conn, boolean isHostToPlugin) {
        if (isHostToPlugin) {
            return P_Context.bindPluginService(BaseActivity.this, service, conn);
        } else {
            return super.bindService(service, conn, BIND_AUTO_CREATE);
        }
    }


    /**
     * ????????????
     *
     * @param conn
     * @param isHostToPlugin ?????????????????????????????????
     */
    public void unbindService(ServiceConnection conn, boolean isHostToPlugin) {
        if (isHostToPlugin) {
            P_Context.unBindPluginService(BaseActivity.this, conn);
        } else {
            super.unbindService(conn);
        }
    }


    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra(P_Constants.INTENT_KEY, "WMA-OK");
        setResult(RESULT_OK, data);
        super.onBackPressed();
    }



}
