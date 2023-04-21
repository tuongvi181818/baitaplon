package cntt.k2020.sql_qlthuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;

public class csdlQlThuvien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdl_ql_thuvien);

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
                CreateTinhTrang();
                CreateNhanvien();
                CreateDocgia();
                CreateNhapsach();
                CreateTrangthaichung();
                CreateMuonsach();
                CreateXulyvipham();
                CreateSohuuSACH();
                CreateXuatbansach();





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
        SQLiteDatabase database=null;
        String sql ="CREATE TABLE THELOAI ("+ "MATL  TEXT NOT NULL PRIMARY KEY,"+ "TENTL TEXT)";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"tao THE LOAI thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }
    public  void CreateNhacungcap()
    {
        SQLiteDatabase database=null;
        String sql ="CREATE TABLE NHACUNGCAP ("+ "MANCC TEXT NOT NULL PRIMARY KEY,"+ "TENNCC TEXT)";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"tao NHA CUNG CAP thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }

    }
    public  void CreateNhaxuatban()
    {
        SQLiteDatabase database=null;
        String sql ="CREATE TABLE NHAXUATBAN ("+ "MANXB TEXT NOT NULL PRIMARY KEY,"+ "TENXB TEXT)";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"tao NHA XUAT BAN thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }

    }
    public  void CreateTacgia()
    {
        SQLiteDatabase database=null;
        String sql ="CREATE TABLE TACGIA ("+ "MATG TEXT NOT NULL PRIMARY KEY,"+ "TENTG TEXT)";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"tao TACGIA thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }

    }
    public  void CreateSach()
    {
        SQLiteDatabase database=null;
        String sql = "CREATE TABLE SACH (MASACH TEXT PRIMARY KEY,TESACH TEXT,SOTRANG INT ,MATL TEXT, FOREIGN KEY (MATL) REFERENCES THELOAI(MATL))";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"tao SÁCH thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }

    }
    public  void CreateTinhTrang()
    {;
        SQLiteDatabase database=null;
        String sql ="CREATE TABLE TINHTRANG ("+ "MATT TEXT NOT NULL PRIMARY KEY,"+ "TENTT TEXT)";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"tao TÌNH TRẠNG SÁCH thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }

    }
    public  void CreateNhanvien()
    {

        SQLiteDatabase database=null;
        String sql ="CREATE TABLE NHANVIEN (MANV TEXT PRIMARY KEY,TENNV TEXT,EMAIL TEXT, SODT INT , MATKHAUNV TEXT)";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"tao NHAN VIEEN thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"tao  KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }
    public  void CreateDocgia()
    {

        SQLiteDatabase database=null;
        String sql ="CREATE TABLE DOCGIA (MADG TEXT PRIMARY KEY,TENDG TEXT,PHAI BOOLEAN ,LOP TEXT, KHOA INT,MATKHAUDG TEXT)";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"TẠO thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"TẠO KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }
    public  void CreateNhapsach()
    {

        SQLiteDatabase database=null;
        String sql ="CREATE TABLE NHAPSACH (SOLUONGNHAP INT,DONGIA INT, NGAYNHAP DATETIME, MASACH TEXT NOT NULL,\n" +
                "MANCC TEXT NOT NULL,MANV TEXT NOT NULL," +
                "PRIMARY KEY( MASACH, MANCC,MANV )," +
                "FOREIGN KEY (MASACH) REFERENCES SACH(MASACH)," +
                "FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC)," +
                "FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV))";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"TẠO NHẬP SÁCH thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"TẠO KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }
    public  void CreateTrangthaichung()
    {

        SQLiteDatabase database=null;
        String sql ="CREATE TABLE TRANGTHAI (MASACH TEXT NOT NULL , MATT TEXT NOT NULL, " +
                "PRIMARY KEY (MASACH, MATT)," +
                "FOREIGN KEY (MASACH) REFERENCES SACH(MASACH)," +
                "FOREIGN KEY (MATT) REFERENCES TINHTRANG(MATT))";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"TẠO TRANGTHAI thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"TẠO KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }
    public  void CreateMuonsach()
    {

        SQLiteDatabase database=null;
        String sql ="CREATE TABLE MUONSACH (" +
                "NGAYMUON DATETIME," +
                "NGAYTRA DATETIME," +
                "SOLUONG INT," +
                "MASACH TEXT NOT NULL," +
                "MADG TEXT NOT NULL," +
                "PRIMARY KEY( MASACH, MADG)," +
                "FOREIGN KEY (MASACH) REFERENCES SACH(MASACH)," +
                "FOREIGN KEY (MADG) REFERENCES DOCGIA(MADG))";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"TẠO MƯỢN SÁCH thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"TẠO KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }
    public  void CreateXulyvipham()
    {

        SQLiteDatabase database=null;
        String sql ="CREATE TABLE XULYVIPHAM (" +
                "NGAYVIPHAM DATETIME," +
                "BIENPHAPXULY TEXT," +
                "MASACH TEXT NOT NULL," +
                "MADG TEXT NOT NULL," +
                "MANV TEXT NOT NULL," +
                "PRIMARY KEY( MASACH, MADG,MANV)," +
                "FOREIGN KEY (MASACH) REFERENCES SACH(MASACH)," +
                "FOREIGN KEY (MADG) REFERENCES DOCGIA(MADG)," +
                "FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV))";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"TẠO XỬ LÝ thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"TẠO KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }

    public  void CreateSohuuSACH()
    {

        SQLiteDatabase database=null;
        String sql ="CREATE TABLE SOHUU (MATG TEXT NOT NULL," +
                "MASACH TEXT NOT NULL," +
                "PRIMARY KEY( MATG, MASACH)," +
                "FOREIGN KEY (MATG) REFERENCES TACGIA(MATG)," +
                "FOREIGN KEY (MASACH) REFERENCES SACH(MASACH))";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"TẠO SO HUU thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"TẠO KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }
    public  void CreateXuatbansach()
    {

        SQLiteDatabase database=null;
        String sql ="CREATE TABLE XUATBAN (NAMXB DATETIME," +
                "MANXB TEXT NOT NULL," +
                "MASACH TEXT NOT NULL," +
                "PRIMARY KEY( MANXB, MASACH)," +
                "FOREIGN KEY (MANXB) REFERENCES NHAXUATBAN(MANXB)," +
                "FOREIGN KEY (MASACH) REFERENCES SACH(MASACH))";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"TẠO XUAT BAN thành công",Toast.LENGTH_LONG).show();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"TẠO KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }


    }




}