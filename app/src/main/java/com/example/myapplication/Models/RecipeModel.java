package com.example.myapplication.Models;


public class RecipeModel {
    int pic;    //image is stored in the form of binary digits. so its datatype has been taken as int here.

    String text;
    //String cartButton;

    public RecipeModel(int pic, String text) {
        this.pic=pic;
        this.text=text;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    //public String getCartButton() {
        //return cartButton;
   // }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    /*
       public void setCartButton(String cartButton) {
        this.cartButton = cartButton;
       }
       */

}


