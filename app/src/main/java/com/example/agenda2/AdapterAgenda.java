package com.example.agenda2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.VHAgenda>{
    private Context ctx;
    private ArrayList arrTangal, arrJam, arrKegiatan;

    public AdapterAgenda(Context ctx, ArrayList arrTangal, ArrayList arrJam, ArrayList arrKegiatan){
        this.ctx = ctx;
        this.arrTangal = arrTangal;
        this.arrJam = arrJam;
        this.arrKegiatan = arrKegiatan;
    }

    @NonNull
    @Override
    public VHAgenda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_agenda, parent, false);

        return new VHAgenda(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHAgenda holder, int position) {
        holder.tvTanggal.setText(arrTangal.get(position).toString());
        holder.tvJam.setText(arrJam.get(position).toString());
        holder.tvKegiatan.setText(arrKegiatan.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrKegiatan.size();
    }

    public class VHAgenda extends RecyclerView.ViewHolder{
        TextView tvTanggal, tvJam, tvKegiatan;

        public VHAgenda(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_tangal);
            tvJam = itemView.findViewById(R.id.tv_jam);
            tvKegiatan = itemView.findViewById(R.id.tv_kegiatan);
        }
    }
}

