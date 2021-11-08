package com.example.actividad1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLite2 extends SQLiteOpenHelper {
    public AdminSQLite2(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db2) {
        db2.execSQL("create table usuarios(idusuario integer primary key, nomusuario text, aliasusuario text, clave text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int i, int i1) {
        db2.execSQL("drop table if exists usuarios");
        db2.execSQL("create table usuarios(idusuario integer primary key, nomusuario text, aliasusuario text, clave text)");
    }
}
