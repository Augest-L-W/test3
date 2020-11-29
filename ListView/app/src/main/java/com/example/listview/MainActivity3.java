package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        txt=findViewById(R.id.tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /** 创建菜单*/
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    /** 设置选项的效果*/
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.submenu_item1:
                txt.setTextSize(10);break;
            case R.id.submenu_item2:
                txt.setTextSize(16);break;
            case R.id.submenu_item3:
                txt.setTextSize(20);break;
            case R.id.item1:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();break;
            case R.id.submenu_item4:
                txt.setTextColor(Color.RED);break;
            case R.id.submenu_item5:
                txt.setTextColor(Color.BLACK);break;
        }
        return true;
    }


}
