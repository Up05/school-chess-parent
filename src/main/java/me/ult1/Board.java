package me.ult1;

public class Board {

    public short[][] board = {
        {1, 2, 3, 4, 5, 3, 2, 1},
        {6, 6, 6, 6, 6, 6, 6, 6},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {12, 12, 12, 12, 12, 12, 12, 12},
        {7, 8, 9, 10, 11, 9, 8, 7},
    };


    Board(){
        
    }


    void print(){
        System.out.println(Ansi.BLACK + " o | A | B | C | D | E | F | G | H | o");
        System.out.println("---+---+---+---+---+---+---+---+---+---");
        for(int x = 0; x < board.length; x ++){
            System.out.print(" " + (x + 1) + " ");
            for(int y = 0; y < board[0].length; y ++){
                short absPiece = board[x][y];
                System.out.print("|");
                if(board[x][y] == 0){
                    System.out.print("   ");
                    continue;
                }

                if(absPiece < 7)
                    System.out.print(Ansi.BLUE + "b ");
                else {
                    System.out.print(Ansi.WHITE + "w ");
                    absPiece -= 6;
                }

                // System.out.println("absPiece\: " + absPiece);
                switch(absPiece){
                    case 1:
                        System.out.print("R");
                        break;
                    case 2:
                        System.out.print("k");
                        break;
                    case 3:
                        System.out.print("B");
                        break;
                    case 4:
                        System.out.print("K");
                        break;
                    case 5:
                        System.out.print("Q");
                        break;
                    case 6:
                        System.out.print("p");
                        break;
                    default:
                        System.out.print("_");
                }
                System.out.print(Ansi.BLACK);
                
            }
                System.out.print("| " + (x + 1) + "\n");
                System.out.println("---+---+---+---+---+---+---+---+---+---");
            }
            System.out.println(" o | A | B | C | D | E | F | G | H | o" + Ansi.RESET);
    }

    void move(byte px, byte py, byte x, byte y){
        board[x][y] = board[px][py];
        board[px][py] = 0;

        App.turn = App.turn ? false : true;

    }

    void debugPrint(){
        for(short[] yes : board){
            for(short no : yes)
                System.out.print(no + " ");
            System.out.println();    
        }
    }

    public short get(short x, short y){
        return board[x][y];
    }
    public Board set(short x, short y, short piece){
        board[x][y] = piece;
        return this;
    }
    public Board set(short x, short y, int piece){
        board[x][y] = (short) piece;
        return this;
    }
}
