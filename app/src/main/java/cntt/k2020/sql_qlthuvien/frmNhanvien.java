package cntt.k2020.sql_qlthuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import android.os.Bundle;

class xuatnv {
    String manv;
    String tennv;
    String email;
    Integer sdt;
    String mknv;

    public xuatnv(String manv, String tennv, String email, Integer sdt, String mknv) {
        this.manv = manv;
        this.tennv = tennv;
        this.email = email;
        this.sdt = sdt;
        this.mknv = mknv;
    }

    public String toString() {
        String kq = "";
        kq += "Mã đọc giả: " + this.manv + " | ";
        kq += "Tên đọc giả: " + this.tennv + "\n";
        kq += "Lớp: " + this.email + "\n";
        kq += "Lớp: " + this.sdt + "\n";
        kq += "Mật khẩu dg: " + this.mknv + "\n";
        return kq;
    }
}

public class frmNhanvien extends AppCompatActivity {

    Button themnv,suanv,xoanv, canclenv;
    EditText manv,tennv,email,sdt,mknv;

    ListView hthinv;
    ArrayList listnv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_nhanvien);


        themnv=(Button) findViewById(R.id.themnv);
        suanv=(Button) findViewById(R.id.suanv);
        xoanv=(Button) findViewById(R.id.xoanv);
        manv=(EditText) findViewById(R.id.manv);
        tennv=(EditText) findViewById(R.id.tennv);
        email=(EditText) findViewById(R.id.email);
        sdt=(EditText) findViewById(R.id.sdt);
        mknv=(EditText) findViewById(R.id.mknv);

        hthinv=(ListView)findViewById(R.id.hthidg);


        canclenv=(Button) findViewById(R.id.canclenv);
        hthi();


        themnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();
            }
        });
        suanv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaSV();

            }
        });
        xoanv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa();

            }
        });

        hthinv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xuatnv xuatNV=(xuatnv) listnv.get(i);
                manv.setText(xuatNV.manv);
                tennv.setText(xuatNV.tennv);
                email.setText(xuatNV.email);
                sdt.setText(xuatNV.sdt);
                mknv.setText(xuatNV.mknv);
                return false;
            }
        });





    }

    public void them()
    {
        SQLiteDatabase database=null;
        String maNV=manv.getText().toString();
        String tenNV=tennv.getText().toString();
        String Email=email.getText().toString();
        String Sdt=sdt.getText().toString();
        String mkNV=mknv.getText().toString();

        String sql="INSERT INTO NHANVIEN (MANV ,TENNV ,EMAIL ,SODT, MATKHAUNV )VALUES ('"+maNV+"','"+tenNV+"'," +
               ",'"+Email+"','"+Sdt+"','"+mkNV+"' )";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"THÊM thành công",Toast.LENGTH_LONG).show();
            manv.setText("");
            tennv.setText("");
            email.setText("");
            sdt.setText("");
            mknv.setText("");
            manv.findFocus();
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
        String sql="SELECT * FROM NHANVIEN ORDER BY MANV";
        listnv= new ArrayList();
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,listnv);
        hthinv.setAdapter(adapter);
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            Cursor cursor=database.rawQuery(sql,null);
            if(cursor.moveToFirst())
                do{
                    listnv.add(new xuatnv(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4)));
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
    public void suaSV()
    {
        SQLiteDatabase database=null;
        String maNV=manv.getText().toString();
        String tenNV=tennv.getText().toString();
        String Email=email.getText().toString();
        String Sdt=sdt.getText().toString();
        String mkNV=mknv.getText().toString();

        String sql="UPDATE NHANVIEN SET TENNV='"+tenNV+"',EMAIL='"+Email+"',SODT='"+Sdt+"',MATKHAUNV='"+mkNV+"'" +
                " WHERE MANV='"+maNV+"'";

        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"SỬA thành công",Toast.LENGTH_LONG).show();
            manv.setText("");
            tennv.setText("");
            email.setText("");
            sdt.setText("");
            mknv.setText("");
            manv.findFocus();
            hthi();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"SỬA KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }
    public void xoa()
    {
        SQLiteDatabase database=null;
        String maNV=manv.getText().toString();
        String sql= "DELETE FROM NHANVIEN WHERE MANV='"+maNV+"'";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"XÓA thành công",Toast.LENGTH_LONG).show();
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