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

public class ListViewMain extends RecyclerView.Adapter<ListViewMain.MyViewHolder>{
    Context context;
    ArrayList<Transaksi> transaksi;

    public ListViewMain(Context c, ArrayList<Transaksi> p) {
        context = c;
        transaksi = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.content_main,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tanggaltransaksi.setText(transaksi.get(i).getTanggal());
        holder.jenistransaksi.setText(transaksi.get(i).getJenis());
        holder.nominaltransaksi.setText(transaksi.get(i).getNominal());

        final String getJenisTransaksi = transaksi.get(i).getJenis();
        final String getKategoriTransaksi = transaksi.get(i).getKategori();
        final String getNominalTransaksi = transaksi.get(i).getNominal();
        final String getTanggalTransaksi = transaksi.get(i).getTanggal();
        final String getCreatedAt = transaksi.get(i).getCreated();

        holder.itemView.setOnClickListener(new android.view.View.OnClickListener() {
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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tanggaltransaksi, jenistransaksi, nominaltransaksi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tanggaltransaksi = (TextView) itemView.findViewById(R.id.listTanggalTransaksi);
            jenistransaksi = (TextView) itemView.findViewById(R.id.listJenisTransaksi);
            nominaltransaksi = (TextView) itemView.findViewById(R.id.listNominalTransaksi);
        }
    }
}
