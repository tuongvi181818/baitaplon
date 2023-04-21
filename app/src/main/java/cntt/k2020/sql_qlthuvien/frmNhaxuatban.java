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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import android.os.Bundle;
class xuatnxb{
    String manxb;
    String tennxb;
    public xuatnxb(String manxb, String tennxb)
    {
        this.manxb=manxb;
        this.tennxb=tennxb;
    }
    public String toString()
    {
        String kq="";
        kq+="Mã thể loại: "+this.manxb+"\n";
        kq+="Tên thể loại: "+this.tennxb+"\n";
        return kq;
    }
}
public class frmNhaxuatban extends AppCompatActivity {

    Button themnxb,suanxb,xoanxb, canclenxb;
    EditText manxb,tennxb;
    ListView hthinxb;
    ArrayList listnxb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_nhaxuatban);
        themnxb=(Button) findViewById(R.id.themnxb);
        suanxb=(Button) findViewById(R.id.suanxb);
        xoanxb=(Button) findViewById(R.id.xoanxb);
        manxb=(EditText) findViewById(R.id.manxb);
        tennxb=(EditText) findViewById(R.id.tennxb);
        hthinxb=(ListView)findViewById(R.id.hthinxb);

        canclenxb=(Button) findViewById(R.id.canclenxb);

        canclenxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        themnxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();

            }

        });
        suanxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sua();
            }
        });
        xoanxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa();
            }
        });
        hthinxb.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xuatnxb xuatNXB=(xuatnxb) listnxb.get(i);
                manxb.setText(xuatNXB.manxb);
                tennxb.setText(xuatNXB.tennxb);
                return false;
            }
        });
        hthi();

    }
    public void them()
    {
        SQLiteDatabase database=null;
        String MANXB=manxb.getText().toString();
        String TENNXB=tennxb.getText().toString();
        String sql="INSERT INTO NHAXUATBAN(MANXB,TENNXB) VALUES ('"+MANXB+"','"+TENNXB+"')";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"THÊM thành công",Toast.LENGTH_LONG).show();
            manxb.setText("");
            tennxb.setText("");
            manxb.findFocus();
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
        String sql="SELECT * FROM NHAXUATBAN ORDER BY TENNXB";
        listnxb= new ArrayList();
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,listnxb);
        hthinxb.setAdapter(adapter);
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            Cursor cursor=database.rawQuery(sql,null);
            if(cursor.moveToFirst())
                do{
                    listnxb.add(new xuatnxb(cursor.getString(0),cursor.getString(1)));
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
        String MANXB=manxb.getText().toString();
        String TENNXB=tennxb.getText().toString();
        String sql="UPDATE  NHAXUATBAN  SET TENNXB='"+TENNXB+"' WHERE MANXB='"+MANXB+"'";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"SỬA thành công",Toast.LENGTH_LONG).show();
            manxb.setText("");
            tennxb.setText("");
            manxb.findFocus();
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
        String MANXB=manxb.getText().toString();
        String sql= "DELETE FROM NHAXUATBAN WHERE MANXB='"+MANXB+"'";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"XÓA thành công",Toast.LENGTH_LONG).show();
            manxb.setText("");
            tennxb.setText("");
            manxb.findFocus();
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