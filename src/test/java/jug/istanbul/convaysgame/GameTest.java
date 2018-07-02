package jug.istanbul.convaysgame;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Convay's Game Of Life")
public class GameTest
{
	@Test
    @DisplayName("has a two dimensional board")
	public void shouldGameHasABoardWithDimension()
	{
		Board board = Board.aNew(4,3);
		Game game = Game.withBoard(board);

		assertAll(
                ()->assertEquals(4, game.getRowSize(), "Game has a board with 4 rows"),
                ()->assertEquals(3, game.getColumnSize(), "Game has a board with 3 rows.")
        );
	}

	@Test
    @DisplayName("fills board with dead cells by default")
	public void shouldGameFillBoardWithDeadCellsInitially()
	{
		Board board = Board.aNew(4,3);
		Game game = Game.withBoard(board);

		assertEquals(12, game.getCountOfDeadCells(),"Game should fill board with dead cell");
	}

	@Test
    @DisplayName("returns count of cells alive")
	public void shouldReturnLiveCellCount()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,2);
		game.putLiveCell(3,1);

		assertEquals(2, game.getCountOfLiveCells(), "Game should return count of live cells");
	}

	@Test
    @DisplayName("says cell is over populated if there are live cells more than 3")
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

		assertTrue(game.isCellOverPopulated(Cell.aNew(2,2,false)), "Game should check a cell is over populated");
	}

	@Test
    @DisplayName("says cell is under populated if there are live cells less than 2")
	public void shouldSayACellIsUnderPopulated()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,2);
		game.putLiveCell(3,2);

		assertTrue(game.isCellUnderPopulated(Cell.aNew(2,2,false)),"Game should check a cell is under populated");
	}

	@Test
    @DisplayName("says cell on edge is under populated if there are live cells less than 2")
	public void shouldSayACellOnEdgeIsUnderPopulated()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,1);
		game.putLiveCell(3,2);

		assertTrue(game.isCellUnderPopulated(Cell.aNew(2,1,false)), "Game should check a cell is under populated");
	}

	@Test
    @DisplayName("kills a cell under populated")
	public void shouldKillACellUnderPopulated()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,1);
		game.putLiveCell(3,2);

		game.start(1);

		assertEquals(0, game.getCountOfLiveCells(), "Game should kill a cell is under populated");
	}

	@Test
    @DisplayName("kills a cell over populated")
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

		assertEquals(6, game.getCountOfLiveCells(), "Game should kill a cell is under populated");
	}

	@Test
    @DisplayName("kills a cell over populated (v2)")
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

		assertEquals(8, game.getCountOfLiveCells(), "Game should return count of alive cells");
	}

	@Test
    @DisplayName("give life a dead cell surrounded by three alive cells")
	public void shouldRebornACellWithThreeAliveCells()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(2,2);
		game.putLiveCell(2,3);
		game.putLiveCell(2,1);

		GameStatistics gameStat = game.start(1);

		assertEquals(3, game.getCountOfLiveCells(),"Game should return count of alive cells");
		assertEquals( 2, gameStat.getCountOfReborn());
		assertEquals( 2, gameStat.getCountOfDead());
	}

	@Test
    @DisplayName("give life a dead cell surrounded by three alive cells (v2)")
	public void shouldRebornACellWithThreeAliveCells_v2()
	{
		Board board = Board.aNew(3,3);
		Game game = Game.withBoard(board);
		game.putLiveCell(1,2);
		game.putLiveCell(2,2);
		game.putLiveCell(3,1);

		GameStatistics gameStat = game.start(1);

		assertEquals(2, game.getCountOfLiveCells(),"Game should return count of alive cells");
		assertEquals( 1, gameStat.getCountOfReborn());
		assertEquals( 2, gameStat.getCountOfDead());
	}

}
