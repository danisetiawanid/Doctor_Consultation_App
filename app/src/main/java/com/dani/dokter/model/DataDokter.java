package com.dani.dokter.model;

public class DataDokter {
    String doctorname, doctorspecialist, doctorphonenumber,doctormonth,doctordate,doctorfirsttime,doctorsecondtime,doctorabout, doctoraccount;

    public DataDokter(){}

    public DataDokter(String doctorname, String doctorspecialist, String doctorphonenumber, String doctormonth, String doctordate, String doctorfirsttime, String doctorsecondtime, String doctorabout, String doctoraccount) {
        this.doctorname = doctorname;
        this.doctorspecialist = doctorspecialist;
        this.doctorphonenumber = doctorphonenumber;
        this.doctormonth = doctormonth;
        this.doctordate = doctordate;
        this.doctorfirsttime = doctorfirsttime;
        this.doctorsecondtime = doctorsecondtime;
        this.doctorabout = doctorabout;
        this.doctoraccount = doctoraccount;
    }

    public String getDoctoraccount() {
        return doctoraccount;
    }

    public void setDoctoraccount(String doctoraccount) {
        this.doctoraccount = doctoraccount;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDoctorspecialist() {
        return doctorspecialist;
    }

    public void setDoctorspecialist(String doctorspecialist) {
        this.doctorspecialist = doctorspecialist;
    }

    public String getDoctorphonenumber() {
        return doctorphonenumber;
    }

    public void setDoctorphonenumber(String doctorphonenumber) {
        this.doctorphonenumber = doctorphonenumber;
    }

    public String getDoctormonth() {
        return doctormonth;
    }

    public void setDoctormonth(String doctormonth) {
        this.doctormonth = doctormonth;
    }

    public String getDoctordate() {
        return doctordate;
    }

    public void setDoctordate(String doctordate) {
        this.doctordate = doctordate;
    }

    public String getDoctorfirsttime() {
        return doctorfirsttime;
    }

    public void setDoctorfirsttime(String doctorfirsttime) {
        this.doctorfirsttime = doctorfirsttime;
    }

    public String getDoctorsecondtime() {
        return doctorsecondtime;
    }

    public void setDoctorsecondtime(String doctorsecondtime) {
        this.doctorsecondtime = doctorsecondtime;
    }

    public String getDoctorabout() {
        return doctorabout;
    }

    public void setDoctorabout(String doctorabout) {
        this.doctorabout = doctorabout;
    }
}
