package me.ult1;

public interface Piece {
    public byte x = -1, y = -1;
    public boolean team = false;
    final int type = 0;
    abstract boolean canMove(byte px, byte py, byte x, byte y);
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
        if((xs > 0 && ys == 0) || (ys > 0 && xs == 0)) return true;
        byte _x = px;
        byte _y = py;
        while(_x != x && _y != y){
            _x += px - x;
            _y += py - y;
            if(App.getBoard().get(_x, _y) > 0) return true;
        }

        return false;
    }

    // public boolean canMove(byte x, byte y) {
    //     if(this.x - x == 0 && !(this.y - y == 0)) return true; // XOR
    //     return false;
    // }
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

    // public boolean canMove(byte x, byte y) {
    //     if((Math.abs(this.x - x) == 2 && Math.abs(this.y - y) == 1) ||
    //        (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 2)) return true;
    // return false;
    // }
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
        if(Math.abs(px - x) == Math.abs(py - y)) return true;

        byte _x = px;
        byte _y = py;
        while(_x != x && _y != y){
            _x += px - x;
            _y += py - y;
            if(App.getBoard().get(_x, _y) > 0) return true;
        }

        return false;
    }

    // public boolean canMove(byte x, byte y) {
    //     if(Math.abs(this.x - x) == Math.abs(this.y - y)) return true;
    //     return false;
    // }
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
        if((xs <= 1 && ys <= 1) && !(px == x && py == y)) return true;
        return false;
    }

    // public boolean canMove(byte x, byte y) {
    //     int xs = Math.abs(this.x - x);
    //     int ys = Math.abs(this.y - y);
    //     if((xs <= 1 && ys <= 1) && !(this.x == x && this.y == y)) return true;
    //     return false;
    // }
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
        if(!(px - x == 0 && py - y == 0) || (Math.abs(px - x) == Math.abs(py - y))) return true;

        byte _x = px;
        byte _y = py;
        while(_x != x && _y != y){
            _x += px - x;
            _y += py - y;
            if(App.getBoard().get(_x, _y) > 0) return true;
        }

        return false;
    }

    // public boolean canMove(byte x, byte y) {
    //     if(!(this.x - x == 0 && this.y - y == 0) || (Math.abs(this.x - x) == Math.abs(this.y - y)) ||
    //        (Math.abs(this.x - x) == Math.abs(this.y - y))) return true;
    //     return false;
    // }
}

class Pawn implements Piece {
    public byte x, y;

    private final byte _1;
    public boolean team;
    final int type = 6;
    boolean firstMove = true;

    Pawn(boolean team, int x, int y){
        this.x = (byte) x;
        this.y = (byte) y;
        this.team = team;
        _1 = (byte) (team ? 1 : 0);
    }

    @Override
    public boolean canMove(byte px, byte py, byte x, byte y) {
        firstMove = false;
        if(py - y == _1) return true;
        if(App.getBoard().get(x, y) > 0 && py - y == _1 && Math.abs(px - x) == 1) return true;
        if(firstMove && (py - y == _1 * 2)) return true;
        
        firstMove = true;
        return false;
    }

    // public boolean canMove(byte x, byte y) {
    //     if(this.y - y == _1) return true;
    //     else if(App.getBoard().get(x, y) > 0 && this.y - y == _1 && Math.abs(this.x - x) == 1) return true;
    //     return false; // the cool sounding french thing or smth ^^^^^^^^^^^^^^^^^^
    // }
}