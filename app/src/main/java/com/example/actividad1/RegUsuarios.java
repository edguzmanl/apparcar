package com.example.actividad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegUsuarios extends AppCompatActivity {

    EditText txtidusuario, txtnomUsuario, txtNombre2, txtPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_usuarios);

        txtidusuario = findViewById(R.id.txtidusuario);
        txtnomUsuario = findViewById(R.id.txtnomUsuario);
        txtNombre2 = findViewById(R.id.txtNombre2);
        txtPassword2 = findViewById(R.id.txtPassword2);

        };

    public void RegUsuario (View view){
        AdminSQLite2 admin1 = new AdminSQLite2(this,"Administrador",null,1);
        SQLiteDatabase db2 = admin1.getWritableDatabase();

        int cod = Integer.parseInt(txtidusuario.getText().toString());
        String nom = txtnomUsuario.getText().toString();
        String alias = txtNombre2.getText().toString();
        String clave = txtPassword2.getText().toString();

        ContentValues datos = new ContentValues();
        datos.put("idusuario",cod);
        datos.put("nomusuario",nom);
        datos.put("aliasusuario",alias);
        datos.put("clave",clave);


        db2.insert("usuarios", null,datos);
        db2.close();

        txtidusuario.setText("");
        txtnomUsuario.setText("");
        txtNombre2.setText("");
        txtPassword2.setText("");

        Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();

    }
}