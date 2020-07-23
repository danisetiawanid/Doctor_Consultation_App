package com.dani.dokter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dani.dokter.DetailDokter;
import com.dani.dokter.R;
import com.dani.dokter.model.DataDokter;

import java.util.ArrayList;

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<DataDokter> dataDokters;

    public DokterAdapter(Context cont, ArrayList<DataDokter> data){
        context=cont;
        dataDokters = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.vdoctorname.setText(dataDokters.get(position).getDoctorname());
        holder.vdoctorspecialist.setText(dataDokters.get(position).getDoctorspecialist());
        holder.vdoctorphonenumber.setText(dataDokters.get(position).getDoctorphonenumber());
        holder.vdoctormonth.setText(dataDokters.get(position).getDoctormonth());
        holder.vdoctordate.setText(dataDokters.get(position).getDoctordate());
        holder.vdoctorfirsttime.setText(dataDokters.get(position).getDoctorfirsttime());
        holder.vdoctorsecondtime.setText(dataDokters.get(position).getDoctorsecondtime());
        holder.vdoctorabout.setText(dataDokters.get(position).getDoctorabout());


        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(context.getApplicationContext(), DetailDokter.class);
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                detail.putExtra("DOCTOR_NAME", dataDokters.get(position).getDoctorname());
                detail.putExtra("DOCTOR_SPECIALIST", dataDokters.get(position).getDoctorspecialist());
                detail.putExtra("DOCTOR_PHONENUMBER", dataDokters.get(position).getDoctorphonenumber());
                detail.putExtra("DOCTOR_MONTH", dataDokters.get(position).getDoctormonth());
                detail.putExtra("DOCTOR_DATE", dataDokters.get(position).getDoctordate());
                detail.putExtra("DOCTOR_FIRSTTIME", dataDokters.get(position).getDoctorfirsttime());
                detail.putExtra("DOCTOR_SECONDTIME", dataDokters.get(position).getDoctorsecondtime());
                detail.putExtra("DOCTOR_ABOUT", dataDokters.get(position).getDoctorabout());

                context.startActivity(detail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataDokters.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vdoctorname, vdoctorspecialist, vdoctorphonenumber, vdoctormonth,vdoctordate,vdoctorfirsttime,vdoctorsecondtime,vdoctorabout;
        ImageButton imageButton;

        ImageView foto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vdoctorname = itemView.findViewById(R.id.doctor_name);
            vdoctorspecialist = itemView.findViewById(R.id.doctorspecialist);
            vdoctorphonenumber = itemView.findViewById(R.id.doctorphonenumber);
            vdoctormonth = itemView.findViewById(R.id.doctormonth);
            vdoctordate = itemView.findViewById(R.id.doctordate);
            vdoctorfirsttime = itemView.findViewById(R.id.doctorfirsttime);
            vdoctorsecondtime = itemView.findViewById(R.id.doctorsecondtime);
            vdoctorabout = itemView.findViewById(R.id.doctorabout);
            imageButton = itemView.findViewById(R.id.image_detail);


            foto = itemView.findViewById(R.id.foto_dokter);
        }
    }
}
