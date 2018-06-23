package jug.istanbul.convaysgame;

import java.util.Arrays;
import java.util.List;
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
		for(int r=0; r<rowSize; r++)
		{
			for(int c=0; c<columnSize; c++)
			{
				cells[r][c] = new Cell(r+1, c+1, false);
			}
		}
	}

	public static Board aNew(int rowSize, int columnSize)
	{
		Board board = new Board(rowSize, columnSize);
		return board;
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
}
