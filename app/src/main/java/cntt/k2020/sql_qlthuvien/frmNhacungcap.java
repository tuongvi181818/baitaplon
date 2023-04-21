package cntt.k2020.sql_qlthuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

class xuatncc{
    String mancc;
    String tenncc;
    public xuatncc(String mancc, String tenncc)
    {
        this.mancc=mancc;
        this.tenncc=tenncc;
    }
    public String toString()
    {
        String kq="";
        kq+="Mã thể loại: "+this.mancc+"\n";
        kq+="Tên thể loại: "+this.tenncc+"\n";
        return kq;
    }
}
public class frmNhacungcap extends AppCompatActivity {

    Button themncc,suancc,xoancc, canclencc;
    EditText mancc,tenncc;
    ListView hthincc;
    ArrayList listncc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_nhacungcap);

        themncc=(Button) findViewById(R.id.themncc);
        suancc=(Button) findViewById(R.id.suancc);
        xoancc=(Button) findViewById(R.id.xoancc);
        mancc=(EditText) findViewById(R.id.mancc);
        tenncc=(EditText) findViewById(R.id.tenncc);
        hthincc=(ListView)findViewById(R.id.hthincc);

        canclencc=(Button) findViewById(R.id.canclencc);

        canclencc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        themncc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();

            }

        });
        suancc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sua();
            }
        });
        xoancc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa();
            }
        });
        hthincc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xuatncc xuatNCC=(xuatncc) listncc.get(i);
                mancc.setText(xuatNCC.mancc);
                tenncc.setText(xuatNCC.tenncc);
                return false;
            }
        });
        hthi();

    }
    public void them()
    {
        SQLiteDatabase database=null;
        String MANCC=mancc.getText().toString();
        String TENNCC=tenncc.getText().toString();
        String sql="INSERT INTO NHACUNGCAP(MANCC,TENNCC) VALUES ('"+MANCC+"','"+TENNCC+"')";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"THÊM thành công",Toast.LENGTH_LONG).show();
            mancc.setText("");
            tenncc.setText("");
            mancc.findFocus();
            hthi();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"THÊM  KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }

    }

    public void  hthi()
    {
        SQLiteDatabase database=null;
        String sql="SELECT * FROM NHACUNGCAP ORDER BY TENNCC";
        listncc= new ArrayList();
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,listncc);
        hthincc.setAdapter(adapter);
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            Cursor cursor=database.rawQuery(sql,null);
            if(cursor.moveToFirst())
                do{
                    listncc.add(new xuatncc(cursor.getString(0),cursor.getString(1)));
                }while (cursor.moveToNext());
        }

        catch (Exception EX)
        {
            Toast.makeText(this,"ko tìm thấy dữ liệu",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }
    public void sua()
    {
        SQLiteDatabase database=null;
        String MANCC=mancc.getText().toString();
        String TENNCC=tenncc.getText().toString();
        String sql="UPDATE  NHACUNGCAP  SET TENNCC='"+MANCC+"' WHERE MANCC='"+TENNCC+"'";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"SỬA thành công",Toast.LENGTH_LONG).show();
            mancc.setText("");
            tenncc.setText("");
            mancc.findFocus();
            hthi();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"SỬA  KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }
    public void xoa()
    {
        SQLiteDatabase database=null;
        String MANCC=mancc.getText().toString();
        String sql= "DELETE FROM NHACUNGCAP WHERE MANCC='"+MANCC+"'";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"XÓA thành công",Toast.LENGTH_LONG).show();
            mancc.setText("");
            tenncc.setText("");
            mancc.findFocus();
            hthi();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"XÓA  KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }
    }
}