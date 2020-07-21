package com.dani.dokter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.dani.dokter.adapter.DokterAdapter;
import com.dani.dokter.model.DataDokter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dokter extends AppCompatActivity {
    private DatabaseReference reference;

    ArrayList<DataDokter> list;
    DokterAdapter adapter;


    private RecyclerView mRecylcer;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);

        EditText editText = findViewById(R.id.searchdani);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if (!s.toString().isEmpty()){
                        search(s.toString());
                    }else {
                        search("");
                    }
            }
        });


        mRecylcer = findViewById(R.id.list_doctor);
        mRecylcer.setHasFixedSize(true);



        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecylcer.setLayoutManager(mManager);

        reference = FirebaseDatabase.getInstance().getReference().child("doctorconsultation");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    DataDokter mhs = dataSnapshot1.getValue(DataDokter.class);
                    list.add(mhs);
                }
                adapter = new DokterAdapter(getApplicationContext(), list);
                mRecylcer.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void search(String text){
        Query query = reference.orderByChild("doctorspecialist").startAt(text).endAt(text +"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    list.clear();
                    for(DataSnapshot dataSnapshot1: snapshot.getChildren()){

                        DataDokter mhs = dataSnapshot1.getValue(DataDokter.class);
                        list.add(mhs);
                    }

                    DokterAdapter dokterAdapter = new DokterAdapter(getApplicationContext(),list);
                    mRecylcer.setAdapter(dokterAdapter);
                    dokterAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
