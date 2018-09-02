package pe.edu.upeu.clase03;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.IDN;

public class Modificar extends AppCompatActivity {

    EditText edtname, edtprecio, edtcant;
    Button btnmodificar, btneliminar;
    int id;
    String nombre, precio, cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("Id");
            nombre = bundle.getString("Nombre");
            precio = String.valueOf(bundle.getInt("Precio"));
            cantidad = String.valueOf(bundle.getInt("Cantidad"));
        }

        edtname = (EditText) findViewById(R.id.edtname);
        edtprecio = (EditText) findViewById(R.id.edtprecio);
        edtcant = (EditText) findViewById(R.id.edtcant);

        edtname.setText(nombre);
        edtprecio.setText(precio);
        edtcant.setText(cantidad);

        btnmodificar = (Button) findViewById(R.id.btnmodificar);
        btneliminar = (Button) findViewById(R.id.btneliminar);

        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar(id, edtname.getText().toString(), Integer.parseInt(edtprecio.getText().toString()), Integer.parseInt(edtcant.getText().toString()));
                onBackPressed();
            }
        });
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(id);
                onBackPressed();
            }
        });
    }

    private void Modificar(int Id, String Nombre, int Precio, int Cantidad) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "update PRODUCTOS set nombre='" + Nombre + "',Precio='" + Precio + "',Cantidad='" + Cantidad + "' where Id_productos =" + Id;
        db.execSQL(sql);
        db.close();
    }

    private void Eliminar(int Id) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "delete from PRODUCTOS where Id_productos =" + Id;
        db.execSQL(sql);
        db.close();
    }
}
