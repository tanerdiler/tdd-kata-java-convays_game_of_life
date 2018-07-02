package jug.istanbul.convaysgame;

import static java.lang.String.format;

public class CellIsNotExistException extends RuntimeException {
    private final int row;
    private final int column;

    public CellIsNotExistException(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String getMessage() {
        return format("Cell{%d,%d} is not exists", row, column);
    }
}
