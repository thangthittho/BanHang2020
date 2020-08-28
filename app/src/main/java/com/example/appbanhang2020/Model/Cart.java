package com.example.appbanhang2020.Model;

public class Cart {
    public Cart()
    {

    }

    String pid,pname,price,soluong,giamgia;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(String giamgia) {
        this.giamgia = giamgia;
    }

    public Cart(String pid, String pname, String price, String soluong, String giamgia) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.soluong = soluong;
        this.giamgia = giamgia;
    }


}
