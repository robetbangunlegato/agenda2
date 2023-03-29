package com.example.agenda2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.VHAgenda>{
    private Context ctx;
    private ArrayList arrTangal, arrJam, arrKegiatan, arrId;

    public AdapterAgenda(Context ctx, ArrayList arrTangal, ArrayList arrJam, ArrayList arrKegiatan, ArrayList arrId){
        this.ctx = ctx;
        this.arrTangal = arrTangal;
        this.arrJam = arrJam;
        this.arrKegiatan = arrKegiatan;
        this.arrId = arrId;
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
        holder.tvId.setText(arrId.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrKegiatan.size();
    }

    public class VHAgenda extends RecyclerView.ViewHolder{
        TextView tvTanggal, tvJam, tvKegiatan, tvId;

        public VHAgenda(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_tangal);
            tvJam = itemView.findViewById(R.id.tv_jam);
            tvKegiatan = itemView.findViewById(R.id.tv_kegiatan);
            tvId = itemView.findViewById(R.id.tv_id);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Anda Memilih Agenda dengan ID "+tvId.getText().toString() +" Perintah apa yang anda inginkan ?");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(ctx);
                            long eks = myDB.hapusAgenda(tvId.getText().toString());
                            if (eks == -1){
                                Toast.makeText(ctx, "Gagal Hapus Data", Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(ctx, "Sukses Hapus Data!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MainActivity)ctx).onResume();
                            }
                        }
                    });
                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String varId, varTanggal, varJam, varKegiatan;

                            varId = tvId.getText().toString();
                            varTanggal = tvTanggal.getText().toString();
                            varJam = tvJam.getText().toString();
                            varKegiatan = tvKegiatan.getText().toString();
                            Intent kirim = new Intent(ctx, UbahActivity.class);
                            kirim.putExtra("xId", varId);
                            kirim.putExtra("xTanggal", varTanggal);
                            kirim.putExtra("xJam", varJam);
                            kirim.putExtra("xKegiatan", varKegiatan);
                            ctx.startActivity(kirim);
                        }
                    });
                    pesan.show();
                    return false;
                }
            });
        }
    }
}