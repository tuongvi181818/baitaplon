package cntt.k2020.myapp;



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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
class xuattl{
    String maTl;
    String tenTl;
    public xuattl(String maTL, String tenTL)
    {
        this.maTl=maTL;
        this.tenTl=tenTL;
    }
    public String toString()
    {
        String kq="";
        kq+="Mã thể loại: "+this.maTl+"\n";
        kq+="Tên thể loại: "+this.tenTl+"\n";
        return kq;
    }
}

public class nhapTheloai extends AppCompatActivity {

    Button themtl,suatl,xoatl, cancle;
    EditText matl,tentl;
    ListView hthi;
    ArrayList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_theloai);
        themtl=(Button) findViewById(R.id.themtl);
        suatl=(Button) findViewById(R.id.suatl);
        xoatl=(Button) findViewById(R.id.xoatl);
        matl=(EditText) findViewById(R.id.matl);
        tentl=(EditText) findViewById(R.id.tentl);
        hthi=(ListView)findViewById(R.id.hthi);

        cancle=(Button) findViewById(R.id.cancle);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        themtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();

            }

        });
        suatl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sua();
            }
        });
        xoatl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa();
            }
        });
      hthi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
              xuattl xuat=(xuattl) list.get(i);
              matl.setText(xuat.maTl);
              tentl.setText(xuat.tenTl);
              return false;
          }
      });
        hthi();

    }
    public void them()
    {
        SQLiteDatabase database=null;
        String MATL=matl.getText().toString();
        String TENTL=tentl.getText().toString();
        String sql="INSERT INTO THELOAI(MATL,TENTL) VALUES ('"+MATL+"','"+TENTL+"')";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"THÊM thành công",Toast.LENGTH_LONG).show();
            matl.setText("");
            tentl.setText("");
            matl.findFocus();
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
        String sql="SELECT * FROM THELOAI ORDER BY tentl";
       list= new ArrayList();
         ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        hthi.setAdapter(adapter);
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            Cursor cursor=database.rawQuery(sql,null);
            if(cursor.moveToFirst())
            do{
               list.add(new xuattl(cursor.getString(0),cursor.getString(1)));
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
        String MATL=matl.getText().toString();
        String TENTL=tentl.getText().toString();
        String sql="UPDATE  THELOAI  SET TENTL='"+TENTL+"' WHERE MATL='"+MATL+"'";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"SỬA thành công",Toast.LENGTH_LONG).show();
            matl.setText("");
            tentl.setText("");
            matl.findFocus();
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
        String MATL=matl.getText().toString();
        String sql= "DELETE FROM THELOAI WHERE MATL='"+MATL+"'";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"XÓA thành công",Toast.LENGTH_LONG).show();
            matl.setText("");
            tentl.setText("");
            matl.findFocus();
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