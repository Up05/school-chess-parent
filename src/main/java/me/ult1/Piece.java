package me.ult1;

public interface Piece {
    abstract boolean canMove(byte px, byte py, byte x, byte y);
    abstract boolean getTeam();
    abstract byte getX();
    abstract byte getY();
}

class Rook implements Piece {
    public byte x, y;
    public boolean team;
    final int type = 1;
    
    Rook(boolean team, int x, int y){
        this.x = (byte) x;
        this.y = (byte) y;
        this.team = team;
    }

    @Override
    public boolean canMove(byte px, byte py, byte x, byte y) {
        int xs = Math.abs(px - x);
        int ys = Math.abs(py - y);
        // if(!(px - x == 0 && py - y == 0)) return true; // XOR <- not anymore, i think
        if(xs != 0 && !(ys != 0) || ys != 0 && !(xs != 0)); else return false; // cba
        if(PieceManager.willCollide(px, py, x, y)) return false;
        return true;
    }

    @Override
    public boolean getTeam() {
        return this.team;
    }

    @Override
    public byte getX() {
        return this.x;
    }

    @Override
    public byte getY() {
        return this.y;
    }
}

class Knight implements Piece {
    public byte x, y;
    public boolean team;
    final int type = 2;

    Knight(boolean team, int x, int y){
        this.x = (byte) x;
        this.y = (byte) y;
        this.team = team;
    }

    @Override
    public boolean canMove(byte px, byte py, byte x, byte y) {
        if((Math.abs(px - x) == 2 && Math.abs(py - y) == 1) || (Math.abs(px - x) == 1 && Math.abs(py - y) == 2))
            return true;
        return false;
    }

    @Override
    public boolean getTeam() {
        return this.team;
    }

    @Override
    public byte getX() {
        return this.x;
    }

    @Override
    public byte getY() {
        return this.y;
    }
}

class Bishop implements Piece {
    public byte x, y;
    public boolean team;
    final int type = 3;

    Bishop(boolean team, int x, int y){
        this.x = (byte) x;
        this.y = (byte) y;
        this.team = team;
    }

    @Override
    public boolean canMove(byte px, byte py, byte x, byte y) {

        if(Math.abs(px - x) == Math.abs(py - y)); else return false;
        if(PieceManager.willCollide(px, py, x, y)) return false;

        return true;
    }

    @Override
    public boolean getTeam() {
        return this.team;
    }

    @Override
    public byte getX() {
        return this.x;
    }

    @Override
    public byte getY() {
        return this.y;
    }
}

class King implements Piece {
    public byte x, y;
    public boolean team;
    final int type = 4;

    King(boolean team, int x, int y){
        this.x = (byte) x;
        this.y = (byte) y;
        this.team = team;
    }

    @Override
    public boolean canMove(byte px, byte py, byte x, byte y) {
        int xs = Math.abs(px - x);
        int ys = Math.abs(py - y);

        System.out.println("1: " + (xs == 1 && ys == 0));
        System.out.println("2: " + (xs == 0 && ys == 1));
        System.out.println("3: " + (xs == 1 && ys == 1));

        if((xs == 1 && ys == 0) || (xs == 0 && ys == 1) || (xs == 1 && ys == 1)) return true;
        return false;
    }

    @Override
    public boolean getTeam() {
        return this.team;
    }

    @Override
    public byte getX() {
        return this.x;
    }

    @Override
    public byte getY() {
        return this.y;
    }
}

class Queen implements Piece {
    public byte x, y;
    public boolean team;
    final int type = 5;

    Queen(boolean team, int x, int y){
        this.x = (byte) x;
        this.y = (byte) y;
        this.team = team;
    }

    @Override
    public boolean canMove(byte px, byte py, byte x, byte y) {
        int xs = Math.abs(px - x);
        int ys = Math.abs(py - y);

        if(xs != 0 && !(ys != 0) || ys != 0 && !(xs != 0) || xs == ys); else return false;
        if(PieceManager.willCollide(px, py, x, y)) return false;

        return true;
    }

    @Override
    public boolean getTeam() {
        return this.team;
    }

    @Override
    public byte getX() {
        return this.x;
    }

    @Override
    public byte getY() {
        return this.y;
    }
}

class Pawn implements Piece {

    public byte x, y;

    private final byte _1;
    public boolean team = true;
    final int type = 6;
    boolean firstMove = true;

    Pawn(boolean _team, int x, int y){
        this.x = (byte) x;
        this.y = (byte) y;
        this.team = _team;
        _1 = (byte) (_team ? 1 : -1);
    }

    @Override
    public boolean canMove(byte px, byte py, byte x, byte y) {

        boolean isPieceAt = App.getBoard().get(px, (short) (py + _1)) > 0;

        if(py - y == _1 && px - x == 0) {firstMove = false; return true;}
        if(isPieceAt  && py - y == _1 && Math.abs(px - x) == 1) {firstMove = false; return true;}
        if(firstMove && (py - y == _1 * 2) && px - x == 0) {firstMove = false; return true;}
        
        return false;
    }

    @Override
    public boolean getTeam() {
        return this.team;
    }

    @Override
    public byte getX() {
        return this.x;
    }

    @Override
    public byte getY() {
        return this.y;
    }
}