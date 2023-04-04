package cntt.k2020.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class sqlQLthuvien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_qlthuvien);

        Button taodb= (Button) findViewById(R.id.taodb);
        Button create= (Button) findViewById(R.id.create);
        Button delete= (Button) findViewById(R.id.delete);
        
        taodb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taodb();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoadata();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateTheloai();
                CreateNhacungcap();
                CreateNhaxuatban();
                CreateTacgia();
                CreateSach();
            }
        });

    }
    public void taodb()
    {
        SQLiteDatabase link= openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
    }
    public void xoadata()
    {
        String xoa="";
        if(deleteDatabase("qlthuvien.db")==true)
        {
            xoa=" xóa thành công";
        }
        else
        {
            xoa="xóa không thành công";
        }
        Toast.makeText(this,xoa,Toast.LENGTH_LONG).show();
    }
    public  void CreateTheloai()
    {

        String sql ="CREATE TABLE THELOAI ("+ "MATL  IDENTITY(1,1) PRIMARY KEY,"+ "TENTL TEXT)";
        if (code.Action(sql)==false) {
            Toast.makeText(this,"tao THE LOẠI KHÔNG thành công",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this,"tao THE LOẠI thành công",Toast.LENGTH_LONG).show();
        }

    }
    public  void CreateNhacungcap()
    {

        String sql ="CREATE TABLE NHACUNGCAP ("+ "MANCC IDENTITY(1,1) PRIMARY KEY,"+ "TENNCC TEXT)";
        if (code.Action(sql)==true) {
            Toast.makeText(this,"tao NHA CUNG CAP  thành công",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this,"tao NHA CUNG CAP KHOONG thành công",Toast.LENGTH_LONG).show();
        }

    }
    public  void CreateNhaxuatban()
    {
        SQLiteDatabase database=null;
        String sql ="CREATE TABLE NHAXUATBAN ("+ "MANXB IDENTITY(1,1) PRIMARY KEY,"+ "TENXB TEXT)";
        if (code.Action(sql)==true) {
            Toast.makeText(this,"tao NHA XUAT BAN thành công",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this,"tao NHA XUAT BAN KHOONG thành công",Toast.LENGTH_LONG).show();
        }

    }
    public  void CreateTacgia()
    {

        String sql ="CREATE TABLE TACGIA ("+ "MATG IDENTITY(1,1) PRIMARY KEY,"+ "TENTG TEXT)";
        if (code.Action(sql)==true) {
            Toast.makeText(this,"tao TAC GIA thành công",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this,"tao TAC GIA KHOONG thành công",Toast.LENGTH_LONG).show();
        }
    }
    public  void CreateSach()
    {

        String sql = "CREATE TABLE SACH (MASACH TEXT PRIMARY KEY,TESACH TEXT,SOTRANG INT ,MATL NUMERIC, FOREIGN KEY (MATL) REFERENCES THELOAI(MATL))";
        if (code.Action(sql)==true) {
            Toast.makeText(this,"tao SACH thành công",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();
        }

    }
    public  void CreateTinhTrang()
    {

        String sql ="CREATE TABLE TINHTRANG ("+ "MATT IDENTITY(1,1) PRIMARY KEY,"+ "TENTT TEXT)";
        if (code.Action(sql)==true) {
            Toast.makeText(this,"tao TINH TRANG thành công",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();
        }

    }

    public  void CreateNhanvien()
    {

        String sql ="CREATE TABLE NHANVIEN (MANV TEXT PRIMARY KEY , TENNV TEXT , EMAIL TEXT,SODT INT, MATKHAUNV TEXT )";
        if (code.Action(sql)==true) {
            Toast.makeText(this,"tao NHAN VIEEN thành công",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();
        }

    }
    public  void CreateDocgia()
    {

        String sql ="CREATE TABLE DOCGIA (MADG TEXT PRIMARY KEY , TENDG TEXT , PHAI TEXT,LOP TEXT,KHOA INT, MATKHAUDG TEXT )";
        if (code.Action(sql)==true) {
            Toast.makeText(this,"tao DOCGIA thành công",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();
        }

    }
    public  void CreateNhapsach()
    {

        String sql ="CREATE TABLE NHAPSACH (SOLUONGNHAP INT, DONGIA INT,NGAYNHAP DATETIME ,MASACH  TEXT , MANCC TEXT ," +
                " MANV TEXT,FOREIGN KEY (MASACH) REFERENCES SACH(MASACH),FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC)" +
                "FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV))";
        if (code.Action(sql)==true) {
            Toast.makeText(this,"NHAP SACH thành công",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();
        }

    }


}
