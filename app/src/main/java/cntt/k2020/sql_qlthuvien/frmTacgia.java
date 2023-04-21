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

class xuattg {
    String matg;
    String tentg;
    public xuattg(String matg, String tentg)
    {
        this.matg=matg;
        this.tentg=tentg;
    }
    public String toString()
    {
        String kq="";
        kq+="Mã thể loại: "+this.matg+"\n";
        kq+="Tên thể loại: "+this.tentg+"\n";
        return kq;
    }
}

public class frmTacgia extends AppCompatActivity {

    Button themtg,suatg,xoatg, cancletg;
    EditText matg,tentg;
    ListView hthitg ;
    ArrayList listtg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_tacgia);

        themtg=(Button) findViewById(R.id.themtg);
        suatg=(Button) findViewById(R.id.suatg);
        xoatg=(Button) findViewById(R.id.xoatg);
        matg=(EditText) findViewById(R.id.matg);
        tentg=(EditText) findViewById(R.id.tentg);
        hthitg=(ListView)findViewById(R.id.hthitg);

        cancletg=(Button) findViewById(R.id.cancletg);

        cancletg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        themtg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();

            }

        });
        suatg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sua();
            }
        });
        xoatg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa();
            }
        });
        hthitg.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xuattg xuatNCC=(xuattg) listtg.get(i);
                matg.setText(xuatNCC.matg);
                tentg.setText(xuatNCC.tentg);
                return false;
            }
        });
        hthi();

    }
    public void them()
    {
        SQLiteDatabase database=null;
        String MATG=matg.getText().toString();
        String TENTG=tentg.getText().toString();
        String sql="INSERT INTO TACGIA(MATG,TENTG) VALUES ('"+MATG+"','"+TENTG+"')";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"THÊM thành công",Toast.LENGTH_LONG).show();
            matg.setText("");
            tentg.setText("");
            matg.findFocus();
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
        String sql="SELECT * FROM TACGIA ORDER BY TENTG";
        listtg= new ArrayList();
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,listtg);
        hthitg.setAdapter(adapter);
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            Cursor cursor=database.rawQuery(sql,null);
            if(cursor.moveToFirst())
                do{
                    listtg.add(new xuattg(cursor.getString(0),cursor.getString(1)));
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
        String MATG=matg.getText().toString();
        String TENTG=tentg.getText().toString();
        String sql="UPDATE  TACGIA  SET TENTG='"+TENTG+"' WHERE MATG='"+MATG+"'";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"SỬA thành công",Toast.LENGTH_LONG).show();
            matg.setText("");
            tentg.setText("");
            matg.findFocus();
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
        String MATG=matg.getText().toString();
        String sql= "DELETE FROM TACGIA WHERE MATG='"+MATG+"'";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"XÓA thành công",Toast.LENGTH_LONG).show();
            matg.setText("");
            tentg.setText("");
            matg.findFocus();
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