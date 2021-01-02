package com.hamid.mymoney;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListTransaksiAdapter extends RecyclerView.Adapter<ListTransaksiAdapter.MyViewHolder>{
    Context context;
    ArrayList<Transaksi> transaksi;

    public ListTransaksiAdapter(Context c, ArrayList<Transaksi> p) {
        context = c;
        transaksi = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_data,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.tanggaltransaksi.setText(transaksi.get(i).getTanggal());
        myViewHolder.kategoritransaksi.setText(transaksi.get(i).getKategori());
        myViewHolder.nominaltransaksi.setText(transaksi.get(i).getNominal());

        final String getJenisTransaksi = transaksi.get(i).getJenis();
        final String getKategoriTransaksi = transaksi.get(i).getKategori();
        final String getNominalTransaksi = transaksi.get(i).getNominal();
        final String getTanggalTransaksi = transaksi.get(i).getTanggal();
        final String getCreatedAt = transaksi.get(i).getCreated();


        myViewHolder.itemView.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context,Edit_Transaksi.class);
                aa.putExtra("jenistransaksi", getJenisTransaksi);
                aa.putExtra("kategoritransaksi", getKategoriTransaksi);
                aa.putExtra("nominaltransaksi", getNominalTransaksi);
                aa.putExtra("tanggaltransaksi", getTanggalTransaksi);
                aa.putExtra("createdat", getCreatedAt);
                context.startActivity(aa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transaksi.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tanggaltransaksi, kategoritransaksi, nominaltransaksi;

        public MyViewHolder(android.view.View view) {
            super(view);
            tanggaltransaksi = (TextView) view.findViewById(R.id.listTanggalTransaksi);
            kategoritransaksi = (TextView) view.findViewById(R.id.listKategoriTransaksi);
            nominaltransaksi = (TextView) view.findViewById(R.id.listNominalTransaksi);
        }
    }
}
