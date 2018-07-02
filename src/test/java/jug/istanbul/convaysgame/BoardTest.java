package jug.istanbul.convaysgame;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells that")
public class BoardTest {

    @Test
    @DisplayName("should know row size")
    public void should_know_row_size()
    {
        Board board = Board.aNew(4,5);

        assertEquals(4, board.getRowSize());
    }

    @Test
    @DisplayName("should know column size")
    public void should_know_column_size()
    {
        Board board = Board.aNew(4,5);

        assertEquals(5, board.getColumnSize());
    }

    @Test
    @DisplayName("should hold cells on n*m matrix")
    public void should_hold_cells_on_nxm_matrix()
    {
        Board board = Board.aNew(5,5);

        assertEquals(25, board.size());
    }

    @Test
    @DisplayName("should be created with dead cells by default")
    public void should_be_created_with_dead_cells_by_default()
    {
        Board board = Board.aNew(5,5);

        assertEquals(25, board.getCountOfDeadCells());
    }

    @Test
    @DisplayName("should return count of cells alive")
    public void should_return_count_of_cells_alive()
    {
        Board board = Board.aNew(5,5);

        assertEquals(0, board.getCountOfAliveCells());
    }

    @Test
    @DisplayName("should change state of a cell after board creating")
    public void should_change_state_of_a_cell_after_board_creating()
    {
        Board board = Board.aNew(5,5);
        board.put(Cell.aNew(2,3, true));

        assertAll(
                ()->assertEquals(1, board.getCountOfAliveCells()),
                ()->assertEquals(24 ,board.getCountOfDeadCells())
        );
    }

    @Test
    @DisplayName("should return 0 when no alive cell exists around of specified cell")
    public void should_return_zero_when_no_alive_cell_exists_around_of_specified_cell()
    {
        Board board = Board.aNew(3,3);

        int sizeOfPopulation = board.sizeOfPopulation(Cell.aNew(1,1, false));

        assertEquals(0, sizeOfPopulation);
    }

    @Test
    @DisplayName("should return 8 when all cell around of specified alive cell are alive")
    public void should_return_eight_when_all_cells_around_of_specified_alive_cell_are_alive()
    {
        Board board = Board.aNew(3,3);
        board.put(Cell.aNew(1,1,true));
        board.put(Cell.aNew(1,2,true));
        board.put(Cell.aNew(1,3,true));
        board.put(Cell.aNew(2,1,true));
        board.put(Cell.aNew(2,3,true));
        board.put(Cell.aNew(3,1,true));
        board.put(Cell.aNew(3,2,true));
        board.put(Cell.aNew(3,3,true));

        int sizeOfPopulation = board.sizeOfPopulation(Cell.aNew(2,2, true));

        assertEquals(8, sizeOfPopulation);
    }

    @Test
    @DisplayName("should return 8 when all cells around of specified dead cell are alive")
    public void should_return_eight_when_all_cells_around_of_specified_dead_cell_are_alive()
    {
        Board board = Board.aNew(3,3);
        board.put(Cell.aNew(1,1,true));
        board.put(Cell.aNew(1,2,true));
        board.put(Cell.aNew(1,3,true));
        board.put(Cell.aNew(2,1,true));
        board.put(Cell.aNew(2,3,true));
        board.put(Cell.aNew(3,1,true));
        board.put(Cell.aNew(3,2,true));
        board.put(Cell.aNew(3,3,true));

        int sizeOfPopulation = board.sizeOfPopulation(Cell.aNew(2,2, false));

        assertEquals(8, sizeOfPopulation);
    }

    @Test
    @DisplayName("should return 3 then target cell at corner")
    public void should_return_three_when_target_cell_at_corner()
    {
        Board board = Board.aNew(3,3);
        board.put(Cell.aNew(1,1,true));
        board.put(Cell.aNew(1,2,true));
        board.put(Cell.aNew(1,3,true));
        board.put(Cell.aNew(2,1,true));
        board.put(Cell.aNew(2,2,true));
        board.put(Cell.aNew(2,3,true));
        board.put(Cell.aNew(3,1,true));
        board.put(Cell.aNew(3,2,true));
        board.put(Cell.aNew(3,3,true));

        int sizeOfPopulation = board.sizeOfPopulation(Cell.aNew(1,1, false));

        assertEquals(3, sizeOfPopulation);
    }

    @Test
    @DisplayName("should return five when target cell at edge")
    public void should_return_five_when_target_cell_at_edge()
    {
        Board board = Board.aNew(3,3);
        board.put(Cell.aNew(1,1,true));
        board.put(Cell.aNew(1,2,true));
        board.put(Cell.aNew(1,3,true));
        board.put(Cell.aNew(2,1,true));
        board.put(Cell.aNew(2,2,true));
        board.put(Cell.aNew(2,3,true));
        board.put(Cell.aNew(3,1,true));
        board.put(Cell.aNew(3,2,true));
        board.put(Cell.aNew(3,3,true));

        int sizeOfPopulation = board.sizeOfPopulation(Cell.aNew(2,1, false));

        assertEquals(5, sizeOfPopulation);
    }

    @Test
    @DisplayName("throws CellIsNotExistsException while querying a coordinate that is out of size")
    public void should_throw_CellIsNotExistsException()
    {
        Board board = Board.aNew(3,3);

        Throwable throwable = assertThrows(CellIsNotExistException.class,  ()->board.sizeOfPopulation(Cell.aNew(-1,-1, false)));

        assertEquals("Cell{-1,-1} is not exists", throwable.getMessage());
    }
}
