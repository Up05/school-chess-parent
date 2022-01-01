package me.ult1;
import java.util.LinkedList;
import java.util.Queue;

public class ConsoleListener { // constructor -> listen -> parse -> execute -> listen -> parse ...
    long ansi_i = 1;
    String help = "";

    ConsoleListener(){
        help += "----------------------------------------------------------------------------------------------------------------\n";
        help += "commands: \n";
        addToHelp("mv A1 H8", "\'mv\' is used to move chess pieces A1 -> H8.");
        addToHelp("debugboard", "outputs a clean board without enumerations or strings.");
        addToHelp("help", "outputs this.");
        addToHelp("exit", "exits the program.");
        addToHelp("ansi", "TOGGLES ansi format. Do this if your terminal has a bunch of random characters like: 001B[0m");
        listen(); // ! ~return
    }

    private void listen(){
        if(App.closed)
            return;
        System.out.print(Ansi.YELLOW + "command: " + Ansi.RESET);
        try {
            System.out.print("\007");
            parse(App.reader.readLine());
        } catch (Exception e) {
            System.out.println(Ansi.RED + "Please insert a valid value! use \"help\" for list of commands" + Ansi.RESET);
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

        if(cmd.matches(".*mv*")){
            String[] pxy = params.poll().split("");
            String[] xy = params.poll().split("");

            System.out.println("params: " + pxy[0] + pxy[1] + ", " + xy[0] + xy[1]);
            
            int px = Integer.parseInt(pxy[1], 10) - 1;
            int py = ((int) pxy[0].toLowerCase().charAt(0)) - 97;
            
            int x  = Integer.parseInt( xy[1], 10) - 1;
            int y = ((int) xy[0].toLowerCase().charAt(0)) - 97;

            if(px - x == 0 && py - y == 0){
                App.error("Please move your piece!");
            }

            if(PieceManager.getPieceAt(px, py) != null){
                if(PieceManager.getPieceAt(px, py).canMove((byte) py, (byte) px, (byte) y, (byte) x) ){ 
                    if(PieceManager.movePiece(px, py, x, y));
                    else App.getBoard().print();
                } else {
                    App.error("Cannot move to there!");
                }
            }
            // } else {
            //     System.out.println(Ansi.RED + "There is no piece there!" + Ansi.RESET);
            // }

        } else if(cmd.matches(".*debugboard*")){
            App.getBoard().debugPrint();
        } else if(cmd.matches(".*help*")){
            System.out.println(help + Ansi.YELLOW + "\n\nCommands are used by typing in the command and then each parameter seperated by a single space.\n" + "Example: \n\tpug lola 30cm aww\n" + Ansi.RESET + "----------------------------------------------------------------------------------------------------------------");
        } else if(cmd.matches(".*clear*")){
            App.getBoard().print();
        } else if(cmd.matches(".*exit*")){
            App.closed = true;
        } else if(cmd.matches(".*ansi*")){
            ansi_i ++;
            Ansi.toggleAnsi(ansi_i % 2 == 0 ? false : true);
        } else if(cmd.matches(".*debug*")){
            System.out.println(Ansi.GREEN + "turn: " + App.turn + ", " + (!App.turn ? "black" : "white"));
            System.out.println("");
            System.out.println(Ansi.RESET);
        } else if(cmd.matches(".*piece*")){
            if(params.size() == 2){
                int x = Integer.parseInt(params.poll());
                int y = Integer.parseInt(params.poll());

                Piece piece = PieceManager.getPieceAt(x, y);
                System.out.println("piece: " + piece.toString());
                System.out.println("    team: " + piece.getTeam());
            } else {
                if(params.poll().contains("all")){
                    System.out.print(Ansi.BLUE);
                    for(int i = 0; i < 8; i ++)
                        for(int j = 0; j < 8; j ++){
                            Piece piece = PieceManager.getPieceAt(i, j);
                            if(piece == null){
                                System.out.print(Ansi.YELLOW);
                                continue;
                            }
                            System.out.println("piece: " + piece.toString().substring(8, piece.toString().length() - 9) + "    team: " + piece.getTeam());
                        }
                    System.out.print(Ansi.RESET);
                }
            }
        }
        else {
            System.out.println(Ansi.RED + "The command doesn't exist!" + Ansi.RESET);
        }

        listen();
    } 

    public void addToHelp(String cmd, String expl){
        help += Ansi.PURPLE + cmd + Ansi.WHITE + " - ";
        help += Ansi.GREEN + expl + "\n" +  Ansi.RESET;
    }

}
