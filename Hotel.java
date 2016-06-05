package tcs.com.drawapp;

import android.graphics.Bitmap;

/**
 * Created by 1256104 on 6/3/2016.
 */
public class Hotel {
    static Bitmap image;

    String address;
    String rating;
    String price;
    String number;
    String city;

    Hotel(){

    }
    public Hotel(Bitmap image,String address, String city, String number, String price, String rating) {
        this.image = image;
        this.address = address;
        this.city = city;
        this.number = number;
        this.price = price;
        this.rating = rating;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Bitmap getImage() {
        return image;
    }

    public static void setImage(Bitmap image) {
        Hotel.image = image;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getNumber() {
        return number;
    }

    public String getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }
}

