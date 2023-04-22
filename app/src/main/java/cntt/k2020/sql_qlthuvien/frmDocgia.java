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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import android.os.Bundle;

class xuatdg {
    String madg;
    String tendg;
    String lop;
    String mkdg;

    public xuatdg(String madg, String tendg, String lop, String mkdg) {
        this.madg = madg;
        this.tendg = tendg;
        this.lop = lop;
        this.mkdg = mkdg;
    }

    public String toString() {
        String kq = "";
        kq += "Mã đọc giả: " + this.madg + " | ";
        kq += "Tên đọc giả: " + this.tendg + "\n";
        kq += "Lớp: " + this.lop + "\n";
        kq += "Mật khẩu dg: " + this.mkdg + "\n";
        return kq;
    }
}

public class frmDocgia extends AppCompatActivity {

    Button themdg,suadg,xoadg, cancledg;
    EditText madg,tendg,lop,mkdg,khoa;
    RadioButton nam , nu;
    RadioGroup phai;
    ListView hthidg;
    ArrayList listdg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_docgia);


        themdg=(Button) findViewById(R.id.themdg);
        suadg=(Button) findViewById(R.id.suadg);
        xoadg=(Button) findViewById(R.id.xoadg);
        madg=(EditText) findViewById(R.id.madg);
        tendg=(EditText) findViewById(R.id.tendg);
        mkdg=(EditText) findViewById(R.id.mkdg);
        lop=(EditText) findViewById(R.id.lop);
        khoa=(EditText) findViewById(R.id.khoa);
        nam=(RadioButton) findViewById(R.id.nam);
        nu=(RadioButton) findViewById(R.id.nu);
        phai=(RadioGroup) findViewById(R.id.phai);
        hthidg=(ListView)findViewById(R.id.hthidg);


        cancledg=(Button) findViewById(R.id.cancledg);
        hthi();


        themdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();
            }
        });
        suadg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaSV();

            }
        });
        xoadg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa();

            }
        });

        hthidg.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xuatdg xuatDG=(xuatdg) listdg.get(i);
                madg.setText(xuatDG.madg);
                tendg.setText(xuatDG.tendg);
                lop.setText(xuatDG.lop);
                mkdg.setText(xuatDG.mkdg);
                return false;
            }
        });





    }

    public void them()
    {
        SQLiteDatabase database=null;
        String maDG=madg.getText().toString();
        String tenDG=tendg.getText().toString();
        String Khoa=khoa.getText().toString();
        String Lop=lop.getText().toString();
        String mkDG=mkdg.getText().toString();
        String gt="";

        if(nam.isChecked()==true) {
            gt=nam.getText().toString();
        }

        if(nu.isChecked()==true)
            gt=nu.getText().toString();

        String sql="INSERT INTO DOCGIA (MADG ,TENDG ,PHAI ,LOP , KHOA ,MATKHAUDG )VALUES ('"+maDG+"','"+tenDG+"','"+gt+"','"+Lop+"','"+Khoa+"','"+mkDG+"' )";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"THÊM thành công",Toast.LENGTH_LONG).show();
            madg.setText("");
            tendg.setText("");
            khoa.setText("");
            lop.setText("");
            mkdg.setText("");
            madg.findFocus();
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
        String sql="SELECT MADG,TENDG,LOP,MATKHAUDG FROM DOCGIA ORDER BY TENDG";
        listdg= new ArrayList();
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,listdg);
        hthidg.setAdapter(adapter);
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            Cursor cursor=database.rawQuery(sql,null);
            if(cursor.moveToFirst())
                do{
                    listdg.add(new xuatdg(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
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
        String maDG=madg.getText().toString();
        String tenDG=tendg.getText().toString();
        String Khoa=khoa.getText().toString();
        String Lop=lop.getText().toString();
        String mkDG=mkdg.getText().toString();
        String gt="";

        if(nam.isChecked()==true) {
            gt=nam.getText().toString();
        }

        if(nu.isChecked()==true)
            gt=nu.getText().toString();

        String sql="UPDATE DOCGIA SET TENDG='"+tenDG+"',PHAI='"+gt+"',LOP='"+Lop+"',KHOA='"+Khoa+"',MATKHAUDG='"+mkDG+"'" +
                " WHERE MADG='"+maDG+"'";

        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"SỬA thành công",Toast.LENGTH_LONG).show();
            madg.setText("");
            tendg.setText("");
            lop.setText("");
            khoa.setText("");
            mkdg.setText("");
            madg.findFocus();
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
        String maDG=madg.getText().toString();
        String sql= "DELETE FROM DOCGIA WHERE MADG='"+maDG+"'";
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


