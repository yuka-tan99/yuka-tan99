//********************************************************
//  CPSC-112 CLASS PROJECT
//
//  File: Pet.java
//
//  Author: Yuchen Tan         Email: yuchen.tan@yale.edu
//
//  Project Title: Virtual Pet Maniac
//
//  Class: Pet
//
//  ----------------------
//    This class defines the object "pet":
//    1) Constructor: x and y positions. 
//    2) Modifiers for moving the positions of the pet object
//    and changing the madness level.
//    2) Accessors: image, getX(), getY(), getMadness()
//    
//********************************************************

import java.util.Scanner;

public class Pet {

    private String image;
    protected static int madness;
    private double x;
    private double y;
    private int SIZE_X = 100;
    private int SIZE_Y = 50;

    // CONSTRUCTOR

    public Pet (double x, double y) {
        
        this.x = x;
        this.y = y;
        madness = 50;
    }


    // MODIFIER
    // check if clicked
    public boolean clicked(double testX, double testY) {
        x = getX();
        y = getY();
        if (x == testX && y == testY) {
            return true;     
        } 
        return false;
    }

    // move Pet around with input x and y position
    public void move (double x, double y) {

        this.x = x;
        this.y = y;
        draw();

    }

    public boolean inBounds(double x0, double y0, double sizeX, double sizeY) {


        if (x >= sizeX || x < x0 || y >= sizeY || y < x0) 
            return true;        
        return false;
    }


    // decrease madness by -2 if hit one target
    public void madness () {
        madness -= 2; 

    }


    // display image
    public void draw () {
        
        StdDraw.picture(x, y, image, SIZE_X, SIZE_Y);
    }

    // change image
    public void newImage (String name) {
        image = name;
    } 


    // ACCESSORS
    // get X and Y position
    public double getX() { 
        return this.x;
    }
    
    public double getY() { 
        return this.y;
    }

    
    // use Scanner to extract images, and select one by index
    public String getImage (String[] imageArr, int num) {
        
        return imageArr[num];
    }
    
    // get rate of madness and cuteness
    public int getMadness () {
        return madness;
    }


}
