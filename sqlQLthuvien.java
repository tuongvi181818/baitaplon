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
        SQLiteDatabase database=null;
            String sql ="CREATE TABLE THELOAI ("+ "MATL  IDENTITY(1,1) PRIMARY KEY,"+ "TENTL TEXT)";
            try {
                database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
                database.execSQL(sql);
                Toast.makeText(this,"tao THE LOẠI thành công",Toast.LENGTH_LONG).show();
            }
            catch (Exception EX)
            {
                Toast.makeText(this,"tao THE LOẠI KHÔNG thành công",Toast.LENGTH_LONG).show();

            }
            finally {
                database.close();
            }

    }
    public  void CreateNhacungcap()
    {
        SQLiteDatabase database=null;
        String sql ="CREATE TABLE NHACUNGCAP ("+ "MANCC IDENTITY(1,1) PRIMARY KEY,"+ "TENNCC TEXT)";
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
        String sql ="CREATE TABLE NHAXUATBAN ("+ "MANXB IDENTITY(1,1) PRIMARY KEY,"+ "TENXB TEXT)";
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
        String sql ="CREATE TABLE TACGIA ("+ "MATG IDENTITY(1,1) PRIMARY KEY,"+ "TENTG TEXT)";
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
        String sql = "CREATE TABLE SACH (MASACH TEXT PRIMARY KEY,TESACH TEXT,SOTRANG INT ,MATL NUMERIC, FOREIGN KEY (MATL) REFERENCES THELOAI(MATL))";
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
    {
        SQLiteDatabase database=null;
        String sql ="CREATE TABLE TINHTRANG ("+ "MATT IDENTITY(1,1) PRIMARY KEY,"+ "TENTT TEXT)";
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


}