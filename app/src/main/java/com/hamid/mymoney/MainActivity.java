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
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView transaksi;
    ArrayList<Transaksi> listTransaksi;
    ListViewMain transaksiAdapter;

    private TextView mPemasukan, mPengeluaran, mSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transaksi = findViewById((R.id.list_viewmain));
        transaksi.setLayoutManager(new LinearLayoutManager(this));
        listTransaksi = new ArrayList<Transaksi>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("transaksi");

        mPemasukan = (TextView)findViewById(R.id.txtview_pemasukan_nominal);
        mPengeluaran = (TextView)findViewById(R.id.txtview_pengeluaran_nominal);
        mSaldo = (TextView)findViewById(R.id.txtview_saldo_nominal);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Transaksi transaksi = ds.getValue(Transaksi.class);
                    listTransaksi.add(transaksi);
                }

                //SUM PEMASUKAN
                int sum=0;
                String txtPemasukan = "Pemasukan";

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Transaksi p = ds.getValue(Transaksi.class);

                    if(p.getJenis().equals(txtPemasukan)){
                        Object pemasukan = map.get("nominal");
                        int pValue = Integer.parseInt(String.valueOf((pemasukan)));
                        sum += pValue;
                        mPemasukan.setText(String.valueOf(sum));
                    }
                }

                //SUM PENGELUARAN
                int sumPengeluaran=0;
                String txtPengeluaran = "Pengeluaran";

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Transaksi p = ds.getValue(Transaksi.class);

                    if(p.getJenis().equals(txtPengeluaran)){
                        Object pengeluaran = map.get("nominal");
                        int pValue = Integer.parseInt(String.valueOf((pengeluaran)));
                        sumPengeluaran += pValue;
                        mPengeluaran.setText(String.valueOf(sumPengeluaran));
                    }
                }

                //SUM SALDO
                int sumSaldo=0;
                int sumPemasukan=0;
                int sumPengeluaran1=0;

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Transaksi p = ds.getValue(Transaksi.class);

                        p.getJenis().equals(txtPemasukan);
                        Object pemasukan1 = map.get("nominal");
                        int pValuePemasukan = Integer.parseInt(String.valueOf((pemasukan1)));
                        sumPemasukan += pValuePemasukan;

                        p.getJenis().equals(txtPengeluaran);
                        Object pengeluaran1 = map.get("nominal");
                        int pValuePengeluaran = Integer.parseInt(String.valueOf((pengeluaran1)));
                        sumPengeluaran1 += pValuePengeluaran;

                        sumSaldo = sumPemasukan;
                        mSaldo.setText(String.valueOf(sumSaldo));

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