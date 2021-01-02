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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PengeluaranActivity extends AppCompatActivity {

    TextView jenis, kategori, nominal, tanggal;
    ListView listTransaksi;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengeluaran2);

//        jenis = findViewById(R.id.pengeluaran_jenis);
//        kategori = findViewById(R.id.pengeluaran_kategori);
//        nominal = findViewById(R.id.pengeluaran_nominal);
//        tanggal = findViewById(R.id.pengeluaran_tanggal);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("transaksi");

        listTransaksi = findViewById(R.id.list_pengeluaran);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listTransaksi.setAdapter(arrayAdapter);
//        reference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                String jenis = snapshot.child("jenis").getValue(String.class);
//                String pengeluaran = "Pengeluaran";
//
//                if(jenis.equals(pengeluaran)){
//                    Transaksi newTransaksi = snapshot.getValue(Transaksi.class);
////                    arrayList.add(newTransaksi.getList());
//                    arrayAdapter.notifyDataSetChanged();
////                    Log.i("MyReports", snapshot.getValue().toString());
//                }
////                Log.d("Jenis", jenis);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
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
                Intent home = new Intent(PengeluaranActivity.this, MainActivity.class);
                startActivity(home);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}