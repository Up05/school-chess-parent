package me.ult1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class App {
    static List<Piece> pieces = null;
    private static Board board = null;
    public static boolean closed = false;
    public static boolean turn = true;

    public static BufferedReader reader;
    
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static String ANSI_RESET = "";
    public static String ANSI_BLACK = "";
    public static String ANSI_RED   = "";
    public static String ANSI_GREEN = "";
    public static String ANSI_YELLOW= "";
    public static String ANSI_BLUE  = "";
    public static String ANSI_PURPLE= "";
    public static String ANSI_CYAN  = "";
    public static String ANSI_WHITE = ""; // yes.
    
    
    private static void defineAnsii(){
        // https://stackoverflow.com/questions/41057014/check-if-console-supports-ansi-escape-codes-in-java
        boolean supportsAnsi_unix = System.console() != null && System.getenv().get("TERM") != null;
        boolean supportsAnsi_windows = true;
        System.out.print("Does you terminal support Ansii codes? (Y/N/idk): ");
        try {
            String yesnoidk = reader.readLine().toLowerCase(); 
                 if(yesnoidk.contains("y"));
            else if(yesnoidk.contains("n")) supportsAnsi_windows = false;
            else if(yesnoidk.contains("i") || yesnoidk.contains("d") || yesnoidk.contains("k")) {
                System.out.print("\u001B[31m Is this text red for you?\u001B[37m (Y/N): ");
                if(reader.readLine().toLowerCase().contains("n")) supportsAnsi_windows = false;
            }
        } catch(IOException _e){
            // yes!
        }
        if(supportsAnsi_unix || supportsAnsi_windows){
            ANSI_RESET = "\u001B[0m";
            ANSI_BLACK = "\u001B[30m";
            ANSI_RED = "\u001B[31m";
            ANSI_GREEN = "\u001B[32m";
            ANSI_YELLOW = "\u001B[33m";
            ANSI_BLUE = "\u001B[34m";
            ANSI_PURPLE = "\u001B[35m";
            ANSI_CYAN = "\u001B[36m";
            ANSI_WHITE = "\u001B[37m";
        }
    } // b o d g i n g .

    public static void toggleAnsi(boolean bool){
        if(bool){
            ANSI_RESET = "\u001B[0m";
            ANSI_BLACK = "\u001B[30m";
            ANSI_RED = "\u001B[31m";
            ANSI_GREEN = "\u001B[32m";
            ANSI_YELLOW = "\u001B[33m";
            ANSI_BLUE = "\u001B[34m";
            ANSI_PURPLE = "\u001B[35m";
            ANSI_CYAN = "\u001B[36m";
            ANSI_WHITE = "\u001B[37m";
        } else {
            ANSI_RESET = "";
            ANSI_BLACK = "";
            ANSI_RED   = "";
            ANSI_GREEN = "";
            ANSI_YELLOW= "";
            ANSI_BLUE  = "";
            ANSI_PURPLE= "";
            ANSI_CYAN  = "";
            ANSI_WHITE = "";
        }
    }
    public static void main( String[] args )
    {
        reader = new BufferedReader(new InputStreamReader(System.in));

        defineAnsii();
        board = new Board();
        startInfo();

        board.print();
        new ConsoleListener();
        {
        // TimerTask task = new TimerTask() {
        //     public void run() {
        //         // System.out.print("\033[H\033[2J");
        //         // System.out.flush();
        //         int px = (int) Math.floor(Math.random() * 7);
        //         int py = (int) Math.floor(Math.random() * 2 + 1);
        //         int x =  (int) Math.floor(Math.random() * 7);
        //         int y =  (int) Math.floor(Math.random() * 2 + 2);
        //         try{
                    // new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        //         } catch (Exception ____________e){}
        //         if(PieceManager.getPieceAt(px, py) == null)
        //             return;
        //         if(PieceManager.getPieceAt(px, py).canMove((byte) px, (byte) py, (byte) x, (byte) y) &&
        //           (PieceManager.getPieceAt(x, y).team != PieceManager.getPieceAt(px, py).team)){ 
        //             PieceManager.movePiece(px, py, x, y);
        //         }
                

        //         board.print();
        //         // System.out.println("repeat!");
                
        //     }
        // };

        // Timer timer = new Timer();
        // timer.scheduleAtFixedRate(task, 0, 100);
        }
    }


    public static Board getBoard(){
        return board;
    }

    public static void startInfo(){
        try {
            System.out.println("\n-------------------------------------");
            Thread.sleep(100);
            System.out.println(ANSI_WHITE + "          school-chess-base          " + ANSI_RESET);
            Thread.sleep(100);
            System.out.println(ANSI_GREEN + "\ntype: help   for cmd list          " + ANSI_RESET);
            Thread.sleep(100);
            System.out.println("\nparent project in java for:        ");
            System.out.println("    \"school-chess\" by Ult1             ");
            Thread.sleep(100);
            System.out.println("\n-------------------------------------\n\n");
            Thread.sleep(200);

        } catch(Exception _____________e){

        }
    }
}
