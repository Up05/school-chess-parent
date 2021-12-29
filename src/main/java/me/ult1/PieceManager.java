package me.ult1;

public class PieceManager {
    {
    // private static List<Piece> pieces = new ArrayList<Piece>(){
    //     {
    //     add(new Rook  (false, 0, 0));
    //     add(new Knight(false, 1, 0));
    //     add(new Bishop(false, 2, 0));
    //     add(new Queen (false, 3, 0));
    //     add(new King  (false, 4, 0));
    //     add(new Bishop(false, 5, 0));
    //     add(new Knight(false, 6, 0));
    //     add(new Rook  (false, 7, 0));

    //     add(new Pawn(false, 0, 1)); add(new Pawn(false, 1, 1)); add(new Pawn(false, 2, 1));
    //     add(new Pawn(false, 3, 1)); add(new Pawn(false, 4, 1)); add(new Pawn(false, 5, 1));
    //     add(new Pawn(false, 6, 1)); add(new Pawn(false, 7, 1));

    //     add(new Pawn(true,  0, 6)); add(new Pawn(true,  1, 6)); add(new Pawn(true,  2, 6));
    //     add(new Pawn(true,  3, 6)); add(new Pawn(true,  4, 6)); add(new Pawn(true,  5, 6));
    //     add(new Pawn(true,  6, 6)); add(new Pawn(true,  7, 6));

    //     add(new Rook  (true, 0, 7));
    //     add(new Knight(true, 1, 7));
    //     add(new Bishop(true, 2, 7));
    //     add(new Queen (true, 3, 7));
    //     add(new King  (true, 4, 7));
    //     add(new Bishop(true, 5, 7));
    //     add(new Knight(true, 6, 7));
    //     add(new Rook  (true, 7, 7));
    //     }
    // }; // i feel like there is a wayyyyyyy better way of doing this, but who cares (also... yes... for loops...)

    // public static List<Piece> getPieceList(){
    //     return pieces;
    // }
    }

    private static Piece[][] pieces = {
        {
            new Rook  (false, 0, 0),
            new Knight(false, 1, 0),
            new Bishop(false, 2, 0),
            new Queen (false, 3, 0),
            new King  (false, 4, 0),
            new Bishop(false, 5, 0),
            new Knight(false, 6, 0),
            new Rook  (false, 7, 0),
        }, 
        {
            new Pawn(false, 0, 1), 
            new Pawn(false, 1, 1), 
            new Pawn(false, 2, 1),
            new Pawn(false, 3, 1), 
            new Pawn(false, 4, 1), 
            new Pawn(false, 5, 1),
            new Pawn(false, 6, 1), 
            new Pawn(false, 7, 1),
        }, 
        {null, null, null, null, null, null, null, null,}, 
        {null, null, null, null, null, null, null, null,}, 
        {null, null, null, null, null, null, null, null,}, 
        {null, null, null, null, null, null, null, null,},
        {
            new Pawn(true,  0, 6), 
            new Pawn(true,  1, 6), 
            new Pawn(true,  2, 6),
            new Pawn(true,  3, 6), 
            new Pawn(true,  4, 6), 
            new Pawn(true,  5, 6),
            new Pawn(true,  6, 6), 
            new Pawn(true,  7, 6),
        },
        {
            new Rook  (true, 0, 7),
            new Knight(true, 1, 7),
            new Bishop(true, 2, 7),
            new Queen (true, 3, 7),
            new King  (true, 4, 7),
            new Bishop(true, 5, 7),
            new Knight(true, 6, 7),
            new Rook  (true, 7, 7),
        }


    };

    public static Piece[][] getPieces() {
        return pieces;
    }

    public static Piece getPieceAt(int x, int y){
        return pieces[x][y];
    }
    
    public static void setPiece(int x, int y, Piece piece){
        pieces[x][y] = piece;
    }

    public static boolean movePiece(int px, int py, int x, int y){
        if(pieces[px][py] == null) return true;
        if(pieces[x][y] != null && (pieces[px][py].team == pieces[x][y].team)) {System.out.println(App.ANSI_RED + "Your team's piece is there! " + pieces[x][y].team + ", " + pieces[px][py].team + App.ANSI_RESET); return true;}
        if(pieces[px][py].team == App.turn) {System.out.println(App.ANSI_RED + "Wait for your turn!" + App.ANSI_RESET); return true;}

        pieces[x][y] = pieces[px][py];
        pieces[px][py] = null;
        App.getBoard().move((byte) px, (byte) py, (byte) x, (byte) y);
        return false;
    }

}
