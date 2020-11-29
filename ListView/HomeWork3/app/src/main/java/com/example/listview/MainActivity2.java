package com.example.listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btn =findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        customDialog();
    }
    public void customDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        final AlertDialog dialog =builder.create();
        View dialogView =View.inflate(MainActivity2.this,R.layout.dialog,null);
        dialog.setView(dialogView);
        dialog.show();

    }
}