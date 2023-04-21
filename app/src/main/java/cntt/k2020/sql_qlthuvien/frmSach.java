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
import android.widget.Toast;

import java.util.ArrayList;

public class frmSach extends AppCompatActivity {

    Button themS,suaS, xoaS, cancleS;
    ListView hthiS;
    EditText maS, tenS, strang;
    Spinner matl;
    SQLiteDatabase database=null;
    String mas,tens,trang;
    String matheloai;
    ArrayList theloai, listsach;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_sach);
        themS=(Button) findViewById(R.id.themS);
        suaS=(Button) findViewById(R.id.suaS);
        xoaS=(Button) findViewById(R.id.xoaS);
        cancleS=(Button) findViewById(R.id.cancleS);
        matl=(Spinner) findViewById(R.id.matl);
        maS=(EditText) findViewById(R.id.maS);
        tenS=(EditText) findViewById(R.id.tenS);
        strang=(EditText) findViewById(R.id.strang);
        hthiS=(ListView) findViewById(R.id.hthiS);

        hienThiSpiner();
        hthiSach();
        hthiS.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                sach noiDung = (sach) listsach.get(i);
                maS.setText(noiDung.maS);
                tenS.setText(noiDung.tenS);

                return false;
            }
        });
        matl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                xuattl chuDe = (xuattl) theloai.get(i);
                matheloai=chuDe.matl;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(frmSach.this, "Hãy chọn 1 sah", Toast.LENGTH_SHORT).show();
            }
        });
        themS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themSACH();
            }
        });
        suaS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public boolean doAction(String sql)
    {
        try
        {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            return true;

        }

        catch(Exception ex){
            return false;
        }
        finally {
            database.close();
        }
    }
    public void hienThiSpiner(){

        theloai=new ArrayList();

        String sql="Select * From THELOAI Order By TENTL";
        database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                theloai.add(new xuattl(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,theloai);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        matl.setAdapter(adapter);
    }

    public  void hthiSach()
    {


        String sql="SELECT MASACH,TESACH,MATL FROM SACH ";
        listsach= new ArrayList();

        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            Cursor cursor=database.rawQuery(sql,null);
            if(cursor.moveToFirst())
                do{
                    listsach.add(new sach(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
                }while (cursor.moveToNext());
        }

        catch (Exception EX)
        {
            Toast.makeText(this,EX.toString(),Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }
        ArrayAdapter adapter= new ArrayAdapter(frmSach.this, android.R.layout.simple_list_item_1,listsach);
        hthiS.setAdapter(adapter);
    }
    /*public  void loaddulieu(String MAs)
     {
         String sql= "select *from SACH where MASACH='"+MAs+"'";
         database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
         Cursor cursor =database.rawQuery(sql,null);
         if(cursor.moveToFirst())
         {
             do{
             tenS.setText(cursor.getString(1));
             strang.setText(cursor.getString(2));
             matl = cursor.getString(3);
             }while (cursor.moveToNext());
         }
     }*/
    public  void  themSACH()
    {
        mas=maS.getText().toString();
        tens=tenS.getText().toString();
        trang=strang.getText().toString();
        String sql="INSERT INTO SACH(MASACH,TESACH,SOTRANG,MATL) VALUES ('"+mas+"','"+tens+"','"+trang+"','"+matheloai+"')";
        try {
            database=openOrCreateDatabase("qlthuvien.db",MODE_PRIVATE,null);
            database.execSQL(sql);
            Toast.makeText(this,"THÊM thành công",Toast.LENGTH_LONG).show();
            maS.setText("");
            tenS.setText("");
            strang.setText("");
            maS.findFocus();
            hthiSach();
        }
        catch (Exception EX)
        {
            Toast.makeText(this,"THÊM  KHÔNG thành công",Toast.LENGTH_LONG).show();

        }
        finally {
            database.close();
        }
    }
}