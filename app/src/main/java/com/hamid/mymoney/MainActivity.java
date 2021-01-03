package com.hamid.mymoney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    RecyclerView transaksi;
    ArrayList<Transaksi> listTransaksi;
    ListViewMain transaksiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transaksi = findViewById((R.id.list_viewmain));
        transaksi.setLayoutManager(new LinearLayoutManager(this));
        listTransaksi = new ArrayList<Transaksi>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("transaksi");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Transaksi transaksi = ds.getValue(Transaksi.class);
                    listTransaksi.add(transaksi);
                }

                transaksiAdapter = new ListViewMain(MainActivity.this, listTransaksi);
                transaksi.setAdapter(transaksiAdapter);
                transaksiAdapter.notifyDataSetChanged();

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });

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