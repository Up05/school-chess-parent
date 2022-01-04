package me.ult1;

public class PieceManager {
    private static Piece[][] pieces = {
        {
            new Rook  (false, 0, 0),
            new Knight(false, 1, 0),
            new Bishop(false, 2, 0),
            new King  (false, 4, 0),
            new Queen (false, 3, 0),
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
            new King  (true, 4, 7),
            new Queen (true, 3, 7),
            new Bishop(true, 5, 7),
            new Knight(true, 6, 7),
            new Rook  (true, 7, 7),
        }


    };

    public static Piece[][] getPieces() {
        return pieces;
    }

    public static Piece getPieceAt(int x, int y){
        return pieces[y][x];
    }
    
    public static void setPiece(int x, int y, Piece piece){
        pieces[y][x] = piece;
    }

    public static boolean movePiece(int px, int py, int x, int y){
        boolean ohno = false;

        if(getPieceAt(px, py) == null)                                                                 ohno = App.error("No piece there!");
        if(getPieceAt(x, y) != null && (getPieceAt(px, py).getTeam() == getPieceAt(x, y).getTeam()))   ohno = App.error("Your team's piece is there!");
        if(getPieceAt(px, py).getTeam() != App.turn)                                                   ohno = App.error("Please wait for your turn!");

        if(ohno) return true; // one-liners (〜￣▽￣)〜

        setPiece(x, y, getPieceAt(px, py));
        setPiece(px, py, null);
        App.getBoard().move((byte) px, (byte) py, (byte) x, (byte) y);
        return false;
    }

    public static boolean willCollide(int px, int py, int x, int y){ // return true if will collide!!! (can't move)
        byte _x = (byte) px;
        byte _y = (byte) py;
        int i = 0;
        while((_x != x || _y != y) && i < 100){
            _x += (byte) Math.signum(x - px);
            _y += (byte) Math.signum(y - py);
            try {
                i ++;
                if(App.getBoard().get(_x, _y) > 0) return true;
            } catch(Exception _________________e){System.out.println("ouch!");}
        }

        return false;
    }
}
