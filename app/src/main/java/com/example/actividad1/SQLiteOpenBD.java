package com.example.actividad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SQLiteOpenBD extends AppCompatActivity {

   EditText txtIdproducto, txtDescripcion, txtPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_open_bd);

        txtIdproducto = findViewById(R.id.txtIdproducto);
        txtDescripcion = findViewById(R.id.txtDescrpcion);
        txtPrecio = findViewById(R.id.txtPrecio);
    }

    public void Registrar(View view) {
        AdminSQLite admin = new AdminSQLite(this,"Administrador",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        int cod = Integer.parseInt(txtIdproducto.getText().toString());
        String des = txtDescripcion.getText().toString();
        int valor = Integer.parseInt(txtPrecio.getText().toString());

        ContentValues datos = new ContentValues();
        datos.put("codigo",cod);
        datos.put("descripcion",des);
        datos.put("precio",valor);

        db.insert("almacen", null,datos);
        db.close();

        txtIdproducto.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");

        Toast.makeText(this, "Producto registrado con éxito", Toast.LENGTH_SHORT).show();
    }

    public void Consultar(View view){
        AdminSQLite admin = new AdminSQLite(this,"Administrador",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        int cod = Integer.parseInt(txtIdproducto.getText().toString());

        Cursor fila = db.rawQuery("select * from almacen where codigo="+cod, null);

        if (fila.moveToFirst()){
            txtDescripcion.setText(fila.getString(1));
            txtPrecio.setText(fila.getString(2));
        }else{
            Toast.makeText(this, "El producto no existe", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void Eliminar (View view){
        AdminSQLite admin = new AdminSQLite(this,"Administrador",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        int cod = Integer.parseInt(txtIdproducto.getText().toString());

        int val = db.delete("almacen","codigo="+txtIdproducto,null);
        db.close();

        txtIdproducto.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");

        if (val==1){
            Toast.makeText(this, "usuario Eliminado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void Actualizar (View view){
        AdminSQLite admin = new AdminSQLite(this,"Administrador",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        int cod = Integer.parseInt(txtIdproducto.getText().toString());
        String des = txtDescripcion.getText().toString();
        int valor = Integer.parseInt(txtPrecio.getText().toString());

        ContentValues datos = new ContentValues();
        datos.put("codigo",cod);
        datos.put("descripcion",des);
        datos.put("precio",valor);

        int val = db.update("almacen",datos,"codigo="+cod, null);
        db.close();

        if (val==1){
            Toast.makeText(this, "usuario Actualizado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error Actualizando usuario", Toast.LENGTH_SHORT).show();
        }




    }
}