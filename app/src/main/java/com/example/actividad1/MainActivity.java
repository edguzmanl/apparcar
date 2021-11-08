package com.example.actividad1;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText txtidUsuario, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtidUsuario = findViewById(R.id.txtidUsuario);
        txtPassword = findViewById(R.id.txtPassword);


    }
    public void LogUsuario (View view){
        AdminSQLite2 admin1 = new AdminSQLite2(this,"Administrador",null,1);
        SQLiteDatabase db2 = admin1.getWritableDatabase();

        int cod = Integer.parseInt(txtidUsuario.getText().toString());

        Cursor fila = db2.rawQuery("select * from usuarios where idusuario="+cod, null);

        if (fila.moveToFirst()){

            String msj = fila.getString(3);
            String compara = txtPassword.getText().toString();

            String valor1 = msj.trim();
            String valor2 = compara.trim();

            if (valor1.equals(valor2)){
                txtidUsuario.setText("");
                txtPassword.setText("");

                String msj2 =  fila.getString(1);
                Toast.makeText(this, "Bienvenido a la Aplicación"+msj2, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, SQLiteOpenBD.class);
                startActivity(intent);
                finish();


            }else{
                Toast.makeText(this, "El Usuario no existe o contraseña erronea", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "El Usuario no existe o contraseña erronea", Toast.LENGTH_SHORT).show();
        }
        db2.close();
    }

}