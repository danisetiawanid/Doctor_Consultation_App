package com.dani.dokter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dani.dokter.adapter.DokterAdapter;
import com.dani.dokter.model.DataDokter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dokter extends AppCompatActivity {
    CircleImageView profileaccountda;
    private DatabaseReference reference;
    ImageButton menudan, caridani;


    ArrayList<DataDokter> list;
    DokterAdapter adapter;

    private RecyclerView mRecylcer;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);

        profileaccountda = findViewById(R.id.profileimage);
        menudan = findViewById(R.id.menudani);
        caridani = findViewById(R.id.caridani);

        EditText editText = findViewById(R.id.searchdani);



        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            Glide.with(getApplicationContext()).load(signInAccount.getPhotoUrl()).into(profileaccountda);
        }

        caridani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dokter.this, "Plese see the doctor :)", Toast.LENGTH_SHORT).show();
            }
        });

        menudan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dokter.this, "Coming Soon :)", Toast.LENGTH_SHORT).show();
            }
        });

        profileaccountda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               opendialog(v);


            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    search(s.toString());
                } else {
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
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    DataDokter mhs = dataSnapshot1.getValue(DataDokter.class);
                    list.add(mhs);
                }
                adapter = new DokterAdapter(getApplicationContext(), list);
                mRecylcer.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void opendialog(View view) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("Are you sure logout this account ?");
        alertBuilder.setPositiveButton("LOGOUT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
            }
        });
        alertBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();

    }

    private void search(String text) {
        Query query = reference.orderByChild("doctorspecialist").startAt(text).endAt(text + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    list.clear();
                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {

                        DataDokter mhs = dataSnapshot1.getValue(DataDokter.class);
                        list.add(mhs);
                    }

                    DokterAdapter dokterAdapter = new DokterAdapter(getApplicationContext(), list);
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
