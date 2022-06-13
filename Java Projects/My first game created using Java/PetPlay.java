//********************************************************
//  CPSC-112 CLASS PROJECT
//
//  File: PetPlay.java
//
//  Author: Yuchen Tan         Email: yuchen.tan@yale.edu
//
//  Project Title: Virtual Pet Maniac
//
//  Class: PetPlay
//
//  ----------------------
//    This class includes methods that input and
//    display images from scanner and the play screen 
//    that take in mouse events.
//    
//********************************************************

import java.util.Random;
import java.util.Scanner;
import java.io.File; 
import java.io.FileNotFoundException;
import java.lang.Math;
import java.awt.*;


public class PetPlay{

    // size of canvas
    protected static int width = 670;
    protected static int height = 500;

    private static int N; // number of poses or images for pet object

    // objects
    protected static Pet pet; // define pet
    protected static Item item; // define item
    protected static String[] petImage; // an array of pet images with different poses
    protected static int madLevel = 50; // level of madness starting at 50
    
    // files names
    protected static String BACKGROUND = "room2.jpeg"; // background room pic
    protected static String itemPic = "b1.png"; // item image
    protected static String itemAudio = "breakingGlass.wav"; // audio of collision
    protected static String IMAGEFILE = "image.txt"; // pet image
    
    protected final static int SIZE_X = 100; // width of pet image
    protected final static int SIZE_Y = 50; // height of pet image
    protected final static int SIZE = 20; // size of item

    protected static Scanner scan; // scanner for inputting images

    protected static double imageX = 0; // x position for item
    protected static double imageY = 0; // y position for item
    protected static int second = 60; // count down
    protected static int FRAME_1 = 1000; // frame rate of count down 
    protected static double drawFrame = 0; 


    // Play method that inputs mouse events
    public static void Play(String imageName, String audioName)  {
        

        boolean released = true;
        while (madLevel > 0 && second >= 0) {
            
            
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();

            
            // process mouse clicks if NO object is selected
            if (StdDraw.isMousePressed() && released) {
                released = selected(x, y);
            }           


            // process mouse input if object is selected
            if (StdDraw.isMousePressed() && !released) {
                released = unselected(x, y);
                pet = null;
                pet = new Pet(x, y);


            }

            // follow the mouse while an object IS selected
            if (!released) {
                drawBackground();
                // redraw the images
                if (!(item == null)) {
                    item.setImage(imageX, imageY, imageName);
                if (StdDraw.isMousePressed()) {
                    released = selected(x, y);
                }
            }

            // if collide, print "bam to the position" and reset item location
            boolean x_bound = Math.abs(pet.getX()) + SIZE_X > Math.abs(imageX) && Math.abs(x) < Math.abs(imageX + 25.0) ;
            boolean y_bound = Math.abs(pet.getY()) + SIZE_Y > Math.abs(imageY) && Math.abs(y) < Math.abs(imageY + 25.0) ;
            if ( x_bound && y_bound ) {
                StdDraw.picture(imageX + 20, imageY + 20, "bam.png", 10);
                xyPos_null();
                StdAudio.play(audioName);   
                pet.madness();
                madLevel = pet.getMadness();
            }    

            // follow mouse movements, display madness and timer.
            pet.move(x, y);
            Font f = new Font("Arial", Font.BOLD, 20);
            StdDraw.setFont(f);
            StdDraw.text(width / 3, height / 2.5, "MADNESS: " + madLevel);
            drawFrame += 1;
            setTimer(drawFrame);

            StdDraw.show(); 

            
            }
            
        } // end of while
    
    } // end of main

    
    // timer counts down
    public static void setTimer(double drawFrame) {

        if (drawFrame % 38 == 0) {
            second --;
        }
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(- width / 4, height / 2.5, "REMAINING SECONDS: " + second);
            
    }

    // main title when game starts
    public static void text() {
        Font f = new Font("Arial", Font.BOLD, 50);
        
        StdDraw.setFont(f);
        StdDraw.text(0, height / 4, "Quarantine Pet Maniac");
    }


    // draw background picture of a room
    public static void drawBackground() {

        StdDraw.picture(0, 0, BACKGROUND);
        
        
    }

    // initialize the start screen
    public static void initialize () {

        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(- width / 2, width / 2);
        StdDraw.setYscale( - height / 2, height / 2);
        
        drawBackground();
        initFile();
        initPet();
        text(); // title of start screen
        xyPos_null(); // set item
        second = 60; // set timer that counts down from 60
        instruction(); 
        
        StdDraw.show();

    }

    // end scenes
    public static void endScene_WIN () {
        StdDraw.clear();
        drawBackground();
        initFile();
        initPet();
        Font f = new Font("Serif", Font.ITALIC, 50);
        StdDraw.setFont(f);
        StdDraw.text(0, height / 4, "Maniac Over!");
        StdDraw.show();
        
    }

    public static void endScene_LOSE () {
        StdDraw.clear();
        drawBackground();
        initFile();

        pet = new Pet (width / 4, - height / 4);
        pet.newImage(petImage[3]);
        pet.draw();
        Font f = new Font("Serif", Font.ITALIC, 50);
        StdDraw.setFont(f);
        StdDraw.text(0, height / 4, "Sorry The Maniac Ensues...");
        StdDraw.show();
        
    }

    // Input image files
    public static void initFile () {
        try {
            scan = null;
            scan = new Scanner( new File (IMAGEFILE));
        
            N = scan.nextInt(); // num of images or poses
            petImage = new String[N];
    
            // input images
            for (int i = 0; i < N; i++) {
                petImage[i] = scan.next();    
            } 
    
            scan.close();
        } catch (FileNotFoundException e) {

        }
        
    }

    // initialize pet object
    public static void initPet () {
        pet = null;
        pet = new Pet (width / 4, - height / 4);
        pet.newImage(petImage[0]);
        pet.draw();

    }

    // Instruction for the start screen
    public static void instruction () {

        // frame of the instruction box
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledRectangle(- width / 4.8, - height / 5, width / 3.5, height / 6);
        StdDraw.setPenColor(Color.BLACK);
    
    
        StdDraw.setPenRadius(0.02);
        StdDraw.rectangle(- width / 4.8, - height / 5, width / 3.5, height / 6);
        StdDraw.show();


        // text of instruction
        String instr_a = "Click the pet to begin.";
        String instr_b = "Reduce the madness of your pet";
        String instr_c = "to ZERO within 60 seconds.";
        StdDraw.setPenColor(Color.BLACK);
        Font font = new Font("Serif", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.text(-width / 5, - height / 5 + 25, instr_a);
        StdDraw.text(-width / 5, - height / 5, instr_b);
        StdDraw.text(-width / 5, - height / 5 - 25, instr_c);
    }
    

    
    public static boolean selected (double x, double y) {


        if (StdDraw.isMousePressed()) {
            pet = null;
            pet = new Pet (x, y);
            pet.newImage(petImage[1]);
            pet.draw();
            
            StdDraw.show();
            StdDraw.pause(200);
            return false;
        }
        return true;
    }
    
    public static boolean unselected (double x, double y) {

        if (pet.inBounds(width / 4.0, - height / 4.0, (width / 4.0 + SIZE_X), (- height / 4.0 + SIZE_Y)) ) {
            pet.move(x, y); 

            drawBackground();
            StdDraw.show();
            StdDraw.pause(200);
            return true;
        }
        return false;
    }

    // (re)set the item to a random positions on canvas.
    public static void xyPos_null () {
        
        double randX = new Random().nextDouble() * (double) width * 0.8 - width / 2.0 * 0.8;
        double randY = new Random().nextDouble() * (double) height * 0.8 - height / 2.0 * 0.8;
        
        imageX = randX;
        imageY = randY;
        item = null;
        item = new Item (imageX, imageY);
        item.setImage(imageX, imageY, itemPic);
    
    }
    
}
