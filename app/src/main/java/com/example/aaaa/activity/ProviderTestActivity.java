package com.example.aaaa.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.aaaa.R;
import com.optima.plugin.repluginlib.base.BaseActivity;
import com.optima.plugin.repluginlib.pluginUtils.P_Constants;
import com.optima.plugin.repluginlib.pluginUtils.P_Context;

/**
 * create by wma
 * on 2020/8/18 0018
 */
public class ProviderTestActivity extends BaseActivity implements View.OnClickListener{
    Uri uri;
    ContentResolver resolver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_test);
        uri = Uri.parse(P_Constants.HOST_PROVIDER_URI);
        resolver = getContentResolver();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add) {// 增
            P_Context.insertToHost(P_Context.getHostContext(), uri, new ContentValues());
//            resolver.insert(uri,new ContentValues());
        } else if (v.getId() == R.id.btn_delete) {// 删
            P_Context.deleteToHost(P_Context.getHostContext(), uri, null, null);
//            resolver.delete(uri,null,null);
        } else if (v.getId() == R.id.btn_update) {// 改
            P_Context.updateToHost(P_Context.getHostContext(), uri, new ContentValues(), null, null);
//            resolver.update(uri, new ContentValues(), null, null);
        } else if (v.getId() == R.id.btn_query) {// 查
            P_Context.queryToHost(P_Context.getHostContext(),uri,null,null,null,null);
//            resolver.query(uri, null, null, null, null);
        }
    }
}
