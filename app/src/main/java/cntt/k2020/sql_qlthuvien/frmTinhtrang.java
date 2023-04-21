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

class xuattt{
    String matt;
    String tentt;
    public xuattt(String matt, String tentt)
    {
        this.matt=matt;
        this.tentt=tentt;
    }
    public String toString()
    {
        String kq="";
        kq+="Mã thể loại: "+this.matt+"\n";
        kq+="Tên thể loại: "+this.tentt+"\n";
        return kq;
    }
}


public class frmTinhtrang extends AppCompatActivity {

    Button themtt,suatt,xoatt, canclett;
    EditText matt,tentt;
    ListView hthitt;
    ArrayList listtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_tinhtrang);

        themtt=(Button) findViewById(R.id.themtt);
        suatt=(Button) findViewById(R.id.suatt);
        xoatt=(Button) findViewById(R.id.xoatt);
        matt=(EditText) findViewById(R.id.matt);
        tentt=(EditText) findViewById(R.id.tentt);
        hthitt=(ListView)findViewById(R.id.hthitt);

        canclett=(Button) findViewById(R.id.canclett);

        canclett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        themtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();

            }

        });
        suatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sua();
            }
        });
        xoatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa();
            }
        });
        hthitt.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xuattt xuatTT=(xuattt) listtt.get(i);
                matt.setText(xuatTT.matt);
                tentt.setText(xuatTT.tentt);
                return false;
            }
        });
        hthi();

    }
    public void them()
    {
        SQLiteDatabase database=null;
        String MATT=matt.getText().toString();
        String TENTT=tentt.getText().toString();
        String sql="INSERT INTO TINHTRANG(MATT,TENTT) VALUES ('"+MATT+"','"+TENTT+"')";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"THÊM thành công",Toast.LENGTH_LONG).show();
            matt.setText("");
            tentt.setText("");
            matt.findFocus();
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
        String sql="SELECT * FROM TINHTRANG ORDER BY TENTT";
        listtt= new ArrayList();
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,listtt);
        hthitt.setAdapter(adapter);
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            Cursor cursor=database.rawQuery(sql,null);
            if(cursor.moveToFirst())
                do{
                    listtt.add(new xuattt(cursor.getString(0),cursor.getString(1)));
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
        String MATT=matt.getText().toString();
        String TENTT=tentt.getText().toString();
        String sql="UPDATE  TINHTRANG SET TENTT='"+TENTT+"' WHERE MATT='"+MATT+"'";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"SỬA thành công",Toast.LENGTH_LONG).show();
            matt.setText("");
            tentt.setText("");
            matt.findFocus();
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
        String MATT=matt.getText().toString();
        String sql= "DELETE FROM TINHTRANG WHERE MATT='"+MATT+"'";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"XÓA thành công",Toast.LENGTH_LONG).show();
            matt.setText("");
            tentt.setText("");
            matt.findFocus();
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