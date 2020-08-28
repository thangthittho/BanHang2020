package com.example.appbanhang2020.Model;

public class Admin_order {
   public Admin_order(){


   }
   String tongtien,date,shipp,pname,diachi,phone;

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShipp() {
        return shipp;
    }

    public void setShipp(String shipp) {
        this.shipp = shipp;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Admin_order(String tongtien, String date, String shipp, String pname, String diachi, String phone) {
        this.tongtien = tongtien;
        this.date = date;
        this.shipp = shipp;
        this.pname = pname;
        this.diachi = diachi;
        this.phone = phone;
    }
}
