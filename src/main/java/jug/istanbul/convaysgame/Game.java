package jug.istanbul.convaysgame;

import java.util.List;

public class Game
{
	private final Board board;

	public Game(Board board)
	{
		this.board = board;
	}

	public static Game withBoard(Board board)
	{
		return new Game(board);
	}

	public int getRowSize()
	{
		return board.getRowSize();
	}

	public int getColumnSize()
	{
		return board.getColumnSize();
	}

	public int getCountOfLiveCells()
	{
		return board.cellsWith(c -> c.isAlive()).size();
	}

	public void putLiveCell(int row, int column)
	{
		Cell cell = Cell.aNew(row, column, true);
		board.put(cell);
	}

	public int getCountOfDeadCells() {
		return board.cellsWith(c -> c.isDead()).size();
	}

	public boolean isCellOverPopulated(Cell cell)
	{
		return board.isCellOverPopulated(cell);
	}

	public boolean isCellUnderPopulated(Cell cell)
	{
		return board.isCellUnderPopulated(cell);
	}

	public GameStatistics start(int iteration) {
		GameStatistics stat = new GameStatistics();
		int currentIteration = 0;
		while(currentIteration<iteration)
		{
			List<Cell> cellsToKill = board.cellsWith(c->c.isAlive() && (board.isCellUnderPopulated(c) || board.isCellOverPopulated(c)));
			List<Cell> cellsToReborn = board.cellsWith(c->c.isDead() && board.sizeOfPopulation(c) == 3);
			
			board.kill(cellsToKill);
			stat.incCountOfDeadCell(cellsToKill.size());

			board.reborn(cellsToReborn);
			stat.incCountOfRebornedCell(cellsToReborn.size());

			currentIteration++;
		}
		return stat;
	}
}
