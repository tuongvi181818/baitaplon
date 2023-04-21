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
        khoa=(EditText) findViewById(R.id.khoa);
        nam=(RadioButton) findViewById(R.id.nam);
        nu=(RadioButton) findViewById(R.id.nu);
        phai=(RadioGroup) findViewById(R.id.phai);
        hthidg=(ListView)findViewById(R.id.hthidg);


        cancledg=(Button) findViewById(R.id.cancledg);
        ///arsfdgjgk

    }
}