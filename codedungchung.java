package com.example.phone;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


public class codedungchung {

    public  boolean Action(String sql)
    {
        SQLiteDatabase database=null;

        try {
            database= openOrCreateDatabase("qlthuvien.db",null);
            database.execSQL(sql);
            return true;

        }
        catch (Exception EX)
        {
            return false;
        }
        finally {
            database.close();
        }

    }


}
