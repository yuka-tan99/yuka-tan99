//********************************************************
//  CPSC-112 CLASS PROJECT
//
//  File: Item.java
//
//  Author: Yuchen Tan         Email: yuchen.tan@yale.edu
//
//  Project Title: Virtual Pet Maniac
//
//  Class: Item
//
//  ----------------------
//    This class defines the object "item":
//    1) Constructor: Item (x, y) 
//    2) Modifiers: set image
//    2) Accessors: getX(), getY()
//    
//********************************************************

import java.util.Random;

public class Item  {

    private double x;
    private double y;
    public static String itemPic = "b1.png";

    // Constructor
    public Item (double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Modifiers: pick random image and display
    public void setImage (double x, double y, String petImage) {

        StdDraw.picture(x, y, petImage, 25, 25);
    }
    

    // Accessors: get location of the item
    public double getX() { 
        return this.x;
    }
    
    public double getY() { 
        return this.y;
    }



}
