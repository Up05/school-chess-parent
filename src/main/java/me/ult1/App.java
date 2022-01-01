package me.ult1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.System.Logger;
import java.util.List;

public class App {
    static List<Piece> pieces = null;
    private static Board board = null;
    public static boolean closed = false;
    public static boolean turn = false;
    public static Logger logger = System.getLogger(App.class.getName());
       
    public static BufferedReader reader;
    
    public static void main( String[] args )
    {
        reader = new BufferedReader(new InputStreamReader(System.in));

        Ansi.defineAnsi();
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
            System.out.println(Ansi.WHITE + "          school-chess-base          " + Ansi.RESET);
            Thread.sleep(100);
            System.out.println(Ansi.GREEN + "\ntype: help   for cmd list          " + Ansi.RESET);
            Thread.sleep(100);
            System.out.println("\nparent project in java for:        ");
            System.out.println("    \"school-chess\" by Ult1             ");
            Thread.sleep(100);
            System.out.println("\n-------------------------------------\n\n");
            Thread.sleep(200);

        } catch(Exception _____________e){

        }
    }

    public static boolean error(String error){
        System.out.println(Ansi.RED + error + Ansi.RESET);
        return true;
    }

    public static boolean debug(Object object){
        System.out.println(Ansi.YELLOW + object.getClass().getName() + ": " + object + Ansi.RESET);
        return true;
    }
}
