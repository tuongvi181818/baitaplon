package cntt.k2020.sql_qlthuvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class giaodien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien);


    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch (id)
        {
            case  R.id.item9:
                Intent item9=new Intent(giaodien.this,frmNhanvien.class);
                startActivity(item9);
                break;
            case  R.id.item10:
                Intent item10=new Intent(giaodien.this,frmDocgia.class);
                startActivity(item10);
                break;
            default:
        }
        return  super.onOptionsItemSelected(item);
    }
}