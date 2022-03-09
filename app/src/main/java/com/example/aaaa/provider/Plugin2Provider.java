package com.example.aaaa.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aaaa.activity.ProviderTestActivity;
import com.optima.plugin.repluginlib.Logger;


/**
 * create by wma
 * on 2020/8/18 0018
 */
public class Plugin2Provider extends ContentProvider {
    String TAG = Plugin2Provider.class.getSimpleName();

    @Override
    public boolean onCreate() {
        Logger.d(TAG, "onCreate: ");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Logger.d(TAG, "query: ");
        Intent intent = new Intent(getContext(), ProviderTestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Logger.d(TAG, "getType: ");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Logger.d(TAG, "insert: uri = " + uri);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Logger.d(TAG, "delete: ");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Logger.d(TAG, "update: ");
        return 0;
    }
}
