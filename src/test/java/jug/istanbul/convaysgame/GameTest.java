package jug.istanbul.convaysgame;

import org.junit.Assert;
import org.junit.Test;

public class GameTest
{
	@Test
	public void shouldGameHasABoardWithDimension()
	{
		Board board = Board.aNew(4,3);
		Game game = Game.withBoard(board);
		Assert.assertEquals("Game has a board with 4 rows", 4, game.getRowSize());
		Assert.assertEquals("Game has a board with 3 rows.", 3, game.getColumnSize());
	}

	@Test
	public void shouldGameFillBoardWithDeadCellsInitially()
	{
		Board board = Board.aNew(4,3);
		Game game = Game.withBoard(board);
		Assert.assertEquals("Game should fill board with dead cell", 12, game.getCountOfDeadCells());
	}

	@Test
	public void shouldReturnLiveCellCount()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,2);
		game.putLiveCell(3,1);
		Assert.assertEquals("Game should return count of live cells", 2, game.getCountOfLiveCells());
	}

	@Test
	public void shouldSayACellIsOverPopulated()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,2);
		game.putLiveCell(3,1);
		game.putLiveCell(3,3);
		game.putLiveCell(3,2);
		game.putLiveCell(1,3);
		game.putLiveCell(1,2);
		game.putLiveCell(1,1);

		Assert.assertTrue("Game should check a cell is over populated", game.isCellOverPopulated(Cell.aNew(2,2,false)));
	}

	@Test
	public void shouldSayACellIsUnderPopulated()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,2);

		game.putLiveCell(3,2);

		Assert.assertTrue("Game should check a cell is under populated", game.isCellUnderPopulated(Cell.aNew(2,2,false)));
	}

	@Test
	public void shouldSayACellOnEdgeIsUnderPopulated()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,1);

		game.putLiveCell(3,2);

		Assert.assertTrue("Game should check a cell is under populated", game.isCellUnderPopulated(Cell.aNew(2,1,false)));
	}

	@Test
	public void shouldKillACellUnderPopulated()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,1);
		game.putLiveCell(3,2);
		game.start(1);

		Assert.assertEquals("Game should kill a cell is under populated", 0, game.getCountOfLiveCells());
	}

	@Test
	public void shouldKillACellOverPopulated()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,2);
		game.putLiveCell(3,1);
		game.putLiveCell(3,3);
		game.putLiveCell(3,2);
		game.putLiveCell(1,3);
		game.putLiveCell(1,2);
		game.putLiveCell(1,1);
		game.start(1);

		Assert.assertEquals("Game should kill a cell is under populated", 6, game.getCountOfLiveCells());
	}

	@Test
	public void shouldKillACellOverPopulated_v2()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,2);
		game.putLiveCell(2,3);
		game.putLiveCell(2,1);
		game.putLiveCell(3,2);
		game.putLiveCell(1,2);
		game.start(1);

		Assert.assertEquals("Game should return count of alive cells", 8, game.getCountOfLiveCells());
	}

	@Test
	public void shouldRebornACellWithThreeAliveCells()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,2);
		game.putLiveCell(2,3);
		game.putLiveCell(2,1);
		GameStatistics gameStat = game.start(1);

		Assert.assertEquals("Game should return count of alive cells", 3, game.getCountOfLiveCells());
		Assert.assertEquals( 2, gameStat.getCountOfReborn());
		Assert.assertEquals( 2, gameStat.getCountOfDead());
	}

	@Test
	public void shouldRebornACellWithThreeAliveCells_v2()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(1,2);
		game.putLiveCell(2,2);
		game.putLiveCell(3,1);
		GameStatistics gameStat = game.start(1);

		Assert.assertEquals("Game should return count of alive cells", 2, game.getCountOfLiveCells());
		Assert.assertEquals( 1, gameStat.getCountOfReborn());
		Assert.assertEquals( 2, gameStat.getCountOfDead());
	}

}
