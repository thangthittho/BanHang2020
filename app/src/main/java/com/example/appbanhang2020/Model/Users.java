package com.example.appbanhang2020.Model;

public class Users {
    String phone;
    String ten;
    String matkhau;
    String image;
    String diachi;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Users(String phone, String ten, String matkhau, String image, String diachi) {
        this.phone = phone;
        this.ten = ten;
        this.matkhau = matkhau;
        this.image = image;
        this.diachi = diachi;
    }

    public Users() {

    }
}