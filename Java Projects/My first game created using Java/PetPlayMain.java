//********************************************************
//  CPSC-112 CLASS PROJECT
//
//  File: PetPlayMain.java
//
//  Author: Yuchen Tan         Email: yuchen.tan@yale.edu
//
//  Project Title: Virtual Pet Maniac
//
//  Class: PetPlayMain
//
//  ---------------------
//    This is the main class that executes the game
//    by implementing methods defined in PetPlay.java, 
//    Pet.java, and Item.java
//********************************************************
 
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class PetPlayMain extends PetPlay {

    public static void main(String[] args) {

        String yes = "y";
        Scanner scanner = new Scanner(System.in);

        do {
            // initialize canvas
            initialize();
            // if mouse click, starts to play
            Play(itemPic, itemAudio);
            // check the final madness level before timer stops
            if (madLevel == 0) {
                endScene_WIN();
            } else if (madLevel > 0) {
                endScene_LOSE();
            }
            
            System.out.print("Want to play another round? (y/n)");
            yes = scanner.nextLine();
            System.out.println();
            madLevel = 100;

        } while (yes.equalsIgnoreCase("y")); 

        System.exit(1);
        scanner.close();
        
        
    } // end of main

} // end of class