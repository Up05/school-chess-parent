package me.ult1;
import java.util.LinkedList;
import java.util.Queue;

public class ConsoleListener { // constructor -> listen -> parse -> execute -> listen -> parse ...
    long ansi_i = 1;
    String help = "";

    private String _cmd = "";

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
        _cmd = cmd;
        for(int i = 1; i < _str.length; i ++){
                params.add(_str[i]);
        }
        execute(cmd, params);
    }

    public void execute(String cmd, Queue<String> params){
        if(is("mv")){ // cmd.matches\("\.\*(.*)\*"\)    <-- regex, ctrl + h
            String[] pxy = params.poll().split("");
            String[] xy = params.poll().split("");
            
            int px = ((int) pxy[0].toLowerCase().charAt(0)) - 97;
            int py = Integer.parseInt(pxy[1], 10) - 1;
            
            int x  = ((int) xy[0].toLowerCase().charAt(0)) - 97;
            int y  = Integer.parseInt( xy[1], 10) - 1;

            if(px - x == 0 && py - y == 0){
                App.error("Please move your piece!");
            }

            if(PieceManager.getPieceAt(px, py) != null){
                if(PieceManager.getPieceAt(px, py).canMove((byte) px, (byte) py, (byte) x, (byte) y) ){ 
                    boolean isKing = PieceManager.getPieceAt(x, y) instanceof King;
                    if(PieceManager.movePiece(px, py, x, y));
                    else {
                        App.getBoard().print();
                        if(isKing) {
                            System.out.print(Ansi.YELLOW + Ansi.BLINK);
                            System.out.println("---------------------------------------------------------------------------\n");
                            System.out.println("                            V  I  C  T  O  R  Y                            \n");
                            System.out.println("---------------------------------------------------------------------------");
                            System.out.print(Ansi.RESET);
                            App.closed = true;
                        }
                    }
                } else {
                    App.error("Cannot move to there!");
                }
            }
        } else if(is("debugboard")){
            App.getBoard().debugPrint();
        } else if(is("help")){
            System.out.println(help + Ansi.YELLOW + "\n\nCommands are used by typing in the command and then each parameter seperated by a single space.\n" + "Example: \n\tpug lola 30cm aww\n" + Ansi.RESET + "----------------------------------------------------------------------------------------------------------------");
        } else if(is("clear")){
            App.getBoard().print();
        } else if(is("exit")){
            App.closed = true;
        } else if(is("ansi")){
            ansi_i ++;
            Ansi.toggleAnsi(ansi_i % 2 == 0 ? false : true);
        } else if(is("debug")){
            System.out.println(Ansi.GREEN + "turn: " + App.turn + ", " + (!App.turn ? "black" : "white"));
            System.out.println("");
            System.out.println(Ansi.RESET);
        } else if(is("piece")){
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
        } else if(is("win")){
            System.out.print(Ansi.YELLOW + Ansi.BLINK);
            System.out.println("---------------------------------------------------------------------------\n");
            System.out.println("                            V  I  C  T  O  R  Y                            \n");
            System.out.println("---------------------------------------------------------------------------");
            System.out.print(Ansi.RESET);
        } else if(is("delete")){
            String[] xy = params.poll().split("");

            int x  = Integer.parseInt( xy[1], 10) - 1;
            int y = ((int) xy[0].toLowerCase().charAt(0)) - 97;

            PieceManager.setPiece(x, y, null);
            App.getBoard().set(x, y, 0);
            App.getBoard().print();
        }


        else System.out.println(Ansi.RED + "The command doesn't exist!" + Ansi.RESET);
        listen();
    }

    private boolean is(String str){
        if(_cmd.equalsIgnoreCase(str)) return true;
        return false;
    }

    public void addToHelp(String cmd, String expl){
        help += Ansi.PURPLE + cmd + Ansi.WHITE + " - ";
        help += Ansi.GREEN + expl + "\n" +  Ansi.RESET;
    }



}
