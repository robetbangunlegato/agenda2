package com.example.agenda2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TambahActivity extends AppCompatActivity {
    private EditText etTanggal, etJam, etKegiatan;
    private Button btnTambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etTanggal = findViewById(R.id.et_tanggal);
        etJam = findViewById(R.id.et_jam);
        etKegiatan = findViewById(R.id.et_kegiatan);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String tanggal, jam, kegiatan;

                tanggal = etTanggal.getText().toString();
                jam = etJam.getText().toString();
                kegiatan = etKegiatan.getText().toString();
//trim adalah untuk mengabaikan spasi
                if (tanggal.trim().equals("")){
                    etTanggal.setError("Tanggal harus di isi!");
                } else if (jam.trim().equals("")) {
                    etJam.setError("Jam harus di isi!");
                } else if (kegiatan.trim().equals("")) {
                    etKegiatan.setError("Kegiatan harus di isi!");
                } else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(TambahActivity.this);
                    long eks = myDB.tambahAgenda(tanggal, jam, kegiatan);
                    if (eks == -1){
                        Toast.makeText(TambahActivity.this, "Tambah Data Gagal", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(TambahActivity.this, "Tambah Data Sukses", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });
    }
}