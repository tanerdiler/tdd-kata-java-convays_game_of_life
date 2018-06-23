package jug.istanbul.convaysgame;

public class Cell {

    private Board board;
    private final int row;
    private final int column;
    private final boolean alive;


    public Cell(int row, int column, boolean alive) {
        this.row = row;
        this.column = column;
        this.alive = alive;
    }


    public static Cell aNew(int row, int column, boolean alive) {
        return new Cell(row, column, alive);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public Cell die() {
        return new Cell(row, column, false);
    }

    public Cell reborn() {
        return new Cell(row, column, true);
    }
}
