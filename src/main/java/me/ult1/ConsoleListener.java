package me.ult1;
import java.util.LinkedList;
import java.util.Queue;

public class ConsoleListener { // constructor -> listen -> parse -> execute -> listen -> parse ...
    long ansi_i = 1;

    ConsoleListener(){
        listen();
    }

    private void listen(){
        if(App.closed)
            return;
        System.out.print(App.ANSI_YELLOW + "command: " + App.ANSI_RESET);
        try {
            parse(App.reader.readLine());
        } catch (Exception e) {
            System.out.println(App.ANSI_RED + "Please insert a valid value! use \"help\" for list of commands" + App.ANSI_RESET);
        }
    }

    public void parse(String str){
        String cmd;
        Queue<String> params = new LinkedList<String>();

        String[] _str = str.split(" ");
        cmd = _str[0];
        for(int i = 1; i < _str.length; i ++){
                params.add(_str[i]);
        }
        execute(cmd, params);
    }

    public void execute(String cmd, Queue<String> params){
        System.out.println("cmd: " + cmd);

        if(cmd.matches(".*mv*")){
            String[] pxy = params.poll().split("");
            String[] xy = params.poll().split("");

            System.out.println("params: " + pxy[0] + pxy[1] + ", " + xy[0] + xy[1]);
            
            int px = Integer.parseInt(pxy[1], 10) - 1;
            int py = ((int) pxy[0].toLowerCase().charAt(0)) - 97;
            
            int x  = Integer.parseInt( xy[1], 10) - 1;
            int y = ((int) xy[0].toLowerCase().charAt(0)) - 97;

            if(PieceManager.getPieceAt(px, py) != null){
                if(PieceManager.getPieceAt(px, py).canMove((byte) px, (byte) py, (byte) x, (byte) y) ){ 
                    if(PieceManager.movePiece(px, py, x, y));
                    else App.getBoard().print();
                } else {
                    System.out.println(App.ANSI_RED + "Cannot move to: " + xy[0] + xy[1] + "!" + App.ANSI_RESET);
                }
            } else {
                System.out.println(App.ANSI_RED + "There is no piece there!" + App.ANSI_RESET);
            }

        } else if(cmd.matches(".*debugboard*")){
            App.getBoard().debugPrint();
        } else if(cmd.matches(".*help*")){
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------\n" +
                    "commands: \n" +
                    "mv A1 H8 - \'mv\' is used to move chess pieces A1 -> H8.\n" +
                    "debugboard - outputs a clean board without enumerations or strings.\n" +
                    "help - outputs this.\n" + 
                    "exit - exits the program.\n" + 
                    "ansi - TOGGLES ansi format. Do this if your terminal has a bunch of random characters like: 001B[0m\n" +


                    "\n\nCommands are used by typing in the command and then each parameter seperated by a single space.\n" +
                    "Example: command param1 param2 param3\n" +
                
                    "----------------------------------------------------------------------------------------------------------------"
                );
        } else if(cmd.matches(".*clear*")){
            App.getBoard().print();
        } else if(cmd.matches(".*exit*")){
            App.closed = true;
        } else if(cmd.matches(".*ansi*")){
            ansi_i ++;
            App.toggleAnsi(ansi_i % 2 == 0 ? false : true);
        }
        else {
            System.out.println(App.ANSI_RED + "The command doesn't exist!" + App.ANSI_RESET);
        }

        listen();
    } 
}
