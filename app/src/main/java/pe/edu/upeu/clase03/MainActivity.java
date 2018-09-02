package pe.edu.upeu.clase03;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtname, edtprecio, edtcant;
    Button btncrear, btnmostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtname = (EditText) findViewById(R.id.edtname);
        edtprecio = (EditText) findViewById(R.id.edtprecio);
        edtcant = (EditText) findViewById(R.id.edtcant);

        btncrear = (Button) findViewById(R.id.btncrear);
        btnmostrar = (Button) findViewById(R.id.btnmostrar);
        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(edtname.getText().toString(), edtprecio.getText().toString(), edtcant.getText().toString());
            }
        });
        btnmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Listado.class));
            }
        });
    }

    private void guardar(String Nombre, String Precio, String Cantidad) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put("NOMBRE", Nombre);
            c.put("PRECIO", Precio);
            c.put("CANTIDAD", Cantidad);
            db.insert("PRODUCTOS", null, c);
            db.close();
            Toast.makeText(this, "Registro Insertado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
