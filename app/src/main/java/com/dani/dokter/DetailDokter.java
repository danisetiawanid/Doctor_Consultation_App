package com.dani.dokter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class DetailDokter extends AppCompatActivity {

    TextView vdoctorname, vdoctorspecialist,  vdoctormonth,vdoctordate,vdoctorfirsttime,vdoctorsecondtime,vdoctorabout;
    EditText vdoctorphonenumber, vdoctoraccount;
    ImageButton imageButtonback, waphone, wachat, wavideo;
    String doctorname, doctorspecialist,doctorphonenumber,doctormonth,doctordate,doctorfirsttime,doctorsecondtime,doctorabout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);

        imageButtonback = findViewById(R.id.back);
        vdoctorname = findViewById(R.id.dt_doctorname);
        vdoctorspecialist = findViewById(R.id.dt_doctorspecialist);
        vdoctorphonenumber = findViewById(R.id.dt_doctorphonenumber);
        vdoctormonth = findViewById(R.id.dt_doctormonth);
        vdoctordate = findViewById(R.id.dt_doctordate);
        vdoctorfirsttime = findViewById(R.id.dt_doctorfirsttime);
        vdoctorsecondtime = findViewById(R.id.dt_doctorsecondtime);
        vdoctorabout = findViewById(R.id.dt_doctorabout);
        vdoctoraccount = findViewById(R.id.dt_doctoraccount);


        waphone = findViewById(R.id.sendwaphone);
        wachat = findViewById(R.id.sendwachat);
        wavideo = findViewById(R.id.sendwavideo);

        imageButtonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailDokter.this, Dokter.class);
                startActivity(intent);
            }
        });

        waphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean installed = appInstalledOrNot("com.whatsapp");

                if (installed) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "62+" + doctorphonenumber + "&text=Hello " + doctorname+".\nYour profile match with my problem My Name Is " + vdoctoraccount.getText().toString()+ "i had a problem. Can you diagnose what kind of what illness I have? Thank You and I waiting for your answer :)"));
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailDokter.this, "Whatsapp Not Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        wachat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean installed = appInstalledOrNot("com.whatsapp");

                if (installed) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "62+" + doctorphonenumber + "&text=Hello " + doctorname+".\nYour profile match with my problem My Name Is " + vdoctoraccount.getText().toString()+ "i had a problem. Can you diagnose what kind of what illness I have? Thank You and I waiting for your answer :)"));
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailDokter.this, " Whatsapp Not Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        wavideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean installed = appInstalledOrNot("com.whatsapp");

                if (installed) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "62+" + doctorphonenumber + "&text=Hello " + doctorname+".\nYour profile match with my problem My Name Is " + vdoctoraccount.getText().toString()+ "i had a problem. Can you diagnose what kind of what illness I have? Thank You and I waiting for your answer :)"));
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailDokter.this, "Whatsapp Not Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        doctorname = getIntent().getStringExtra("DOCTOR_NAME");
        doctorspecialist = getIntent().getStringExtra("DOCTOR_SPECIALIST");
        doctorphonenumber = getIntent().getStringExtra("DOCTOR_PHONENUMBER");
        doctormonth = getIntent().getStringExtra("DOCTOR_MONTH");
        doctordate = getIntent().getStringExtra("DOCTOR_DATE");
        doctorfirsttime = getIntent().getStringExtra("DOCTOR_FIRSTTIME");
        doctorsecondtime = getIntent().getStringExtra("DOCTOR_SECONDTIME");
        doctorabout = getIntent().getStringExtra("DOCTOR_ABOUT");



        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            vdoctoraccount.setText(signInAccount.getDisplayName());

        }

        vdoctorname.setText(doctorname);
        vdoctorspecialist.setText(doctorspecialist);
        vdoctorphonenumber.setText(doctorphonenumber);
        vdoctormonth.setText(doctormonth);
        vdoctordate.setText(doctordate);
        vdoctorfirsttime.setText(doctorfirsttime);
        vdoctorsecondtime.setText(doctorsecondtime);
        vdoctorabout.setText(doctorabout);

    }

    private boolean appInstalledOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }


}
