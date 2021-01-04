package com.hamid.mymoney;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Tambah_Transaksi extends AppCompatActivity {

    //variable
    CheckBox pemasukan, pengeluaran;
    EditText inputNominal;
    Spinner inputKategori;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private ImageView btDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah__transaksi);

        inputKategori = findViewById(R.id.dropdown_kategori);
        inputNominal = findViewById(R.id.edtTxt_nominal);
        pemasukan = findViewById(R.id.tambah_pemasukan);
        pengeluaran = findViewById(R.id.tambah_pengeluaran);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tvDateResult = (TextView) findViewById(R.id.tgl_dipilih);
        btDatePicker = (ImageView) findViewById(R.id.btn_datepicker);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tambah_transaksi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_simpantambah:
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("transaksi");

                String kategori = inputKategori.getSelectedItem().toString();
                String nominal = inputNominal.getText().toString();
                String jenis = null;
                if(pemasukan.isChecked()){
                    jenis = pemasukan.getText().toString();
                }
                if(pengeluaran.isChecked()){
                    jenis = pengeluaran.getText().toString();
                }
                String tanggal = tvDateResult.getText().toString();

                Date time = new Date();
                String timeInsert = time.toString();

                Transaksi helperClass = new Transaksi(jenis, kategori, nominal, tanggal, timeInsert);

                reference.child(timeInsert).setValue(helperClass);

                Intent tambahtransaksi = new Intent(Tambah_Transaksi.this, Tambah_Transaksi.class);
                startActivity(tambahtransaksi);
                finish();
                return true;
            case R.id.menu_back:
                Intent home = new Intent(Tambah_Transaksi.this, MainActivity.class);
                startActivity(home);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDateDialog(){

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tvDateResult.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(Tambah_Transaksi.this, MainActivity.class);
        startActivity(back);
        finish();
    }
}