package selimErdinc.appProject.appProject;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.allcodingtutorials.appProjects.R;

public class MainActivity extends AppCompatActivity {
    EditText name, contact, phone;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        phone = findViewById(R.id.phone);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String semtTXT = contact.getText().toString();
                String phoneTXT = phone.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, semtTXT, phoneTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "Müşteri Eklendi", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Müşteri Eklenemedi", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String semtTXT = contact.getText().toString();
                String phoneTXT = phone.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, semtTXT, phoneTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Müşteri Güncellendi", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Müşteri Güncellenemedi", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Girilen Müşteri Silindi", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Girilen Müşteri Silinemedi", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "Kayıt Bulunamadı", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("İsim-Soyisim :"+res.getString(0)+"\n");
                    buffer.append("Semt :"+res.getString(1)+"\n");
                    buffer.append("Telefon Numarası :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Müşteri Kayıtları");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}