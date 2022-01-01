package me.ult1;

import java.io.BufferedReader;
import java.io.IOException;

public class Ansi {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    // https://gist.github.com/dainkaplan/4651352

    public static String SANE = "";

    public static String HIGH_INTENSITY = "";
    public static String LOW_INTENSITY = "";

    public static String ITALIC = "";
    public static String UNDERLINE = "";
    public static String BLINK = "";
    public static String RAPID_BLINK = "";
    public static String REVERSE_VIDEO = "";
    public static String INVISIBLE_TEXT = "";

    public static String RESET = "";
    public static String BLACK = "";
    public static String RED   = "";
    public static String GREEN = "";
    public static String YELLOW= "";
    public static String BLUE  = "";
    public static String PURPLE= "";
    public static String CYAN  = "";
    public static String WHITE = ""; // yes.

    public static void defineAnsi(){
        BufferedReader reader = App.reader;
        // https://stackoverflow.com/questions/41057014/check-if-console-supports-ansi-escape-codes-in-java
        boolean supportsunix = System.console() != null && System.getenv().get("TERM") != null;
        boolean supportswindows = true;
        System.out.print("Does you terminal support Ansii codes? (Y/N/idk): ");
        try {
            String yesnoidk = reader.readLine().toLowerCase(); 
                if(yesnoidk.contains("y"));
            else if(yesnoidk.contains("n")) supportswindows = false;
            else if(yesnoidk.contains("i") || yesnoidk.contains("d") || yesnoidk.contains("k")) {
                System.out.print("\u001B[31m Is this text red for you?\u001B[37m (Y/N): ");
                if(reader.readLine().toLowerCase().contains("n")) supportswindows = false;
            }
        } catch(IOException _e){
            // yes!
        }
        if(supportsunix || supportswindows){

            SANE= "\u001B[0m";

            HIGH_INTENSITY= "\u001B[1m";
            LOW_INTENSITY= "\u001B[2m";
        
            ITALIC= "\u001B[3m";
            UNDERLINE= "\u001B[4m";
            BLINK= "\u001B[5m";
            RAPID_BLINK= "\u001B[6m";
            REVERSE_VIDEO= "\u001B[7m";
            INVISIBLE_TEXT= "\u001B[8m";

            RESET = "\u001B[0m";
            BLACK = "\u001B[30m";
            RED = "\u001B[31m";
            GREEN = "\u001B[32m";
            YELLOW = "\u001B[33m";
            BLUE = "\u001B[34m";
            PURPLE = "\u001B[35m";
            CYAN = "\u001B[36m";
            WHITE = "\u001B[37m";
        }
    } // b o d g i n g .

    public static void toggleAnsi(boolean bool){
        if(bool){
            SANE = "\u001B[0m";

            HIGH_INTENSITY = "\u001B[1m";
            LOW_INTENSITY = "\u001B[2m";
        
            ITALIC = "\u001B[3m";
            UNDERLINE = "\u001B[4m";
            BLINK = "\u001B[5m";
            RAPID_BLINK = "\u001B[6m";
            REVERSE_VIDEO = "\u001B[7m";
            INVISIBLE_TEXT = "\u001B[8m";

            RESET = "\u001B[0m";
            BLACK = "\u001B[30m";
            RED = "\u001B[31m";
            GREEN = "\u001B[32m";
            YELLOW = "\u001B[33m";
            BLUE = "\u001B[34m";
            PURPLE = "\u001B[35m";
            CYAN = "\u001B[36m";
            WHITE = "\u001B[37m";
        } else {
            SANE= "";

            HIGH_INTENSITY= "";
            LOW_INTENSITY= "";
        
            ITALIC= "";
            UNDERLINE= "";
            BLINK= "";
            RAPID_BLINK        = "";
            REVERSE_VIDEO= "";
            INVISIBLE_TEXT= "";

            RESET = "";
            BLACK = "";
            RED   = "";
            GREEN = "";
            YELLOW= "";
            BLUE  = "";
            PURPLE= "";
            CYAN  = "";
            WHITE = "";
        }
    }

}