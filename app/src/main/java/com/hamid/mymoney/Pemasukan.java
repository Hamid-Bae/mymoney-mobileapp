package com.hamid.mymoney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Pemasukan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan);
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
                Intent pemasukan = new Intent(Pemasukan.this, Pemasukan.class);
                startActivity(pemasukan);
                finish();
                //Toast.makeText(this, "On Going selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.pengeluaran_menu:
                Intent pengeluaran = new Intent(Pemasukan.this, Pengeluaran.class);
                startActivity(pengeluaran);
                finish();
                //Toast.makeText(this, "Deadline selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.add_menu:
                Intent tambahtransaksi = new Intent(Pemasukan.this, Tambah_Transaksi.class);
                startActivity(tambahtransaksi);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}