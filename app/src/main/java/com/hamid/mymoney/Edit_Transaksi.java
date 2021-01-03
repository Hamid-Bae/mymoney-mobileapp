package com.hamid.mymoney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Edit_Transaksi extends AppCompatActivity {

    Spinner editKategori;
    EditText editNominal;
    CheckBox editPemasukan, editPengeluaran;

    DatabaseReference reference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private ImageView btDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__transaksi);

        editKategori = findViewById(R.id.editkategori);
        editNominal = findViewById(R.id.editnominal);
        editPemasukan = findViewById(R.id.editpemasukan);
        editPengeluaran = findViewById(R.id.editpengeluaran);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tvDateResult = (TextView) findViewById(R.id.edittanggal);
        btDatePicker = (ImageView) findViewById(R.id.btn_datepicker);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        // set sesuai data yang di pass
        editNominal.setText(getIntent().getStringExtra("nominaltransaksi"));
        tvDateResult.setText(getIntent().getStringExtra("tanggaltransaksi"));

        String compareValue = getIntent().getStringExtra("kategoritransaksi");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_kategori, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editKategori.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            editKategori.setSelection(spinnerPosition);
        }

        String jenistransaksi = getIntent().getStringExtra("jenistransaksi");
        if(jenistransaksi.equals("Pemasukan")){
            editPemasukan.setChecked(true);
        }else if(jenistransaksi.equals("Pengeluaran")){
            editPengeluaran.setChecked(true);
        }

        final String keyCreatedAt = getIntent().getStringExtra("createdat");

        reference = FirebaseDatabase.getInstance().getReference().child("transaksi").child(keyCreatedAt);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_back:
                finish();
                return true;
            case R.id.btnupdate:
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String editJenis = null;
                        if(editPemasukan.isChecked()){
                            editJenis = editPemasukan.getText().toString();
                        }
                        if(editPengeluaran.isChecked()){
                            editJenis = editPengeluaran.getText().toString();
                        }

                        String kategori = editKategori.getSelectedItem().toString();


                        snapshot.getRef().child("jenis").setValue(editJenis);
                        snapshot.getRef().child("kategori").setValue(kategori);
                        snapshot.getRef().child("nominal").setValue(editNominal.getText().toString());
                        snapshot.getRef().child("tanggal").setValue(tvDateResult.getText().toString());

                        Intent pemasukan = new Intent(Edit_Transaksi.this, MainActivity.class);
                        startActivity(pemasukan);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return true;
            case R.id.btndelete:
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent a = new Intent(Edit_Transaksi.this, MainActivity.class);
                            startActivity(a);
                        }else{
                            Toast.makeText(getApplicationContext(), "Fail delete!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
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
}