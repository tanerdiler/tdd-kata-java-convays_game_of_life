package jug.istanbul.convaysgame;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class Board
{

	private final int rowSize;
	private final int columnSize;
	private Cell[][] cells;

	public Board(int rowSize, int columnSize)
	{
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.cells = new Cell[rowSize][columnSize];
		for(int row=0; row<rowSize; row++)
		{
			for(int column=0; column<columnSize; column++)
			{
				cells[row][column] = new Cell(row+1, column+1, false);
			}
		}
	}

	public static Board aNew(int rowSize, int columnSize)
	{
		return new Board(rowSize, columnSize);
	}

	public int getRowSize()
	{
		return rowSize;
	}

	public int getColumnSize()
	{
		return columnSize;
	}

	public List<Cell> cellsWith(Predicate<Cell> predicate)
	{
		return Arrays.stream(cells).flatMap(a-> Arrays.stream(a)).filter(predicate).collect(toList());
	}

	public void put(Cell cell)
	{
		this.cells[cell.getRow()-1][cell.getColumn()-1]=cell;
	}

	private boolean checkCellIsExists(Cell cell) {
		int row = cell.getRow();
		int column = cell.getColumn();

		return !(row<1 || row>rowSize || column<1 || column>columnSize);
	}

	private Cell getCell(int row, int column)
	{
		if(row<1 || row>rowSize || column<1 || column>columnSize )
		{
			return null;
		}
		return cells[row-1][column-1];
	}

	public boolean isCellOverPopulated(Cell cell)
	{
		cell = getCell(cell.getRow(), cell.getColumn());

		return sizeOfPopulation(cell) > 3;
	}

	public int sizeOfPopulation(Cell cell)
	{
		if(!checkCellIsExists(cell))
		{
			throw new CellIsNotExistException(cell.getRow(),cell.getColumn());
		}

		int row = cell.getRow();
		int column = cell.getColumn();
		int size = 0;
		cell = getCell(row-1, column-1);
		size += cell!=null && cell.isAlive()?1:0;
		cell = getCell(row-1, column);
		size += cell!=null && cell.isAlive()?1:0;
		cell = getCell(row-1, column+1);
		size += cell!=null && cell.isAlive()?1:0;
		cell = getCell(row, column-1);
		size += cell!=null && cell.isAlive()?1:0;
		cell = getCell(row, column+1);
		size += cell!=null && cell.isAlive()?1:0;
		cell = getCell(row+1, column-1);
		size += cell!=null && cell.isAlive()?1:0;
		cell = getCell(row+1, column);
		size += cell!=null && cell.isAlive()?1:0;
		cell = getCell(row+1, column+1);
		size += cell!=null && cell.isAlive()?1:0;
		return size;
	}

	public boolean isCellUnderPopulated(Cell cell) {

		cell = getCell(cell.getRow(), cell.getColumn());

		return sizeOfPopulation(cell) < 2;
	}

	public void kill(List<Cell> cellsToKill) {
		cellsToKill.stream().forEach(c-> Board.this.put(c.die()) );
	}

	public void reborn(List<Cell> cellsToReborn) {
		cellsToReborn.stream().forEach(c-> Board.this.put(c.reborn()) );
	}

	public int size() {
		return columnSize*rowSize;
	}

	public long getCountOfAliveCells() {
		return Arrays.stream(cells).flatMap(r->Arrays.stream(r)).filter(c->c.isAlive()).count();
	}

	public long getCountOfDeadCells() {
		return Arrays.stream(cells).flatMap(r->Arrays.stream(r)).filter(c->c.isDead()).count();
	}
}
