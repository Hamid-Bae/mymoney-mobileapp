package com.hamid.mymoney;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PemasukanActivity extends AppCompatActivity {

    TextView jenis, kategori, nominal, tanggal;
    RecyclerView transaksi;
    ArrayList<Transaksi> listTransaksi;
    ListTransaksiAdapter transaksiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan2);

        transaksi = findViewById(R.id.list_pemasukan);
        transaksi.setLayoutManager(new LinearLayoutManager(this));
        listTransaksi = new ArrayList<Transaksi>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("transaksi");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pemasukan = "Pemasukan";

                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    Transaksi p = dataSnapshot1.getValue(Transaksi.class);
                    if(p.getJenis().equals(pemasukan)){
                        listTransaksi.add(p);
                    }
                }
                transaksiAdapter = new ListTransaksiAdapter(PemasukanActivity.this, listTransaksi);
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
        inflater.inflate(R.menu.menu_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_back:
                Intent home = new Intent(PemasukanActivity.this, MainActivity.class);
                startActivity(home);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(PemasukanActivity.this, MainActivity.class);
        startActivity(back);
        finish();
    }
}