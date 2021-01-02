package com.hamid.mymoney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_utama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pemasukan_menu:
                Intent pemasukan = new Intent(MainActivity.this, PemasukanActivity.class);
                startActivity(pemasukan);
                finish();
                //Toast.makeText(this, "On Going selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.pengeluaran_menu:
                Intent pengeluaran = new Intent(MainActivity.this, PengeluaranActivity.class);
                startActivity(pengeluaran);
                finish();
                //Toast.makeText(this, "Deadline selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.add_menu:
                Intent tambahtransaksi = new Intent(MainActivity.this, Tambah_Transaksi.class);
                startActivity(tambahtransaksi);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}