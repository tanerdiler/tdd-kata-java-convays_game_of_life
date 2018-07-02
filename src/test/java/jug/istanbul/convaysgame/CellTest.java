package jug.istanbul.convaysgame;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    public void should_know_self_as_alive()
    {
        Cell cell = new Cell(1,5, true);

        assertAll(
                ()->assertTrue(cell.isAlive(), "Cell is alive"),
                ()->assertFalse(cell.isDead(), "Cell is not dead")
        );
    }

    @Test
    public void should_know_self_as_dead()
    {
        Cell cell = new Cell(1,5, false);

        assertAll(
                ()->assertTrue(cell.isDead(),"Cell is dead"),
                ()->assertFalse(cell.isAlive(), "Cell is not alive")
        );
    }

    @Test
    public void should_know_position_on_board()
    {
        Cell cell = new Cell(1,5, true);

        assertAll(
                ()->assertEquals(1, cell.getRow(), "Cell returns row position as 1"),
                ()->assertEquals(5, cell.getColumn(),"Cell returns column position as 5")
        );

    }

    @Test
    public void should_be_dead_if_life_state_specified_as_dead_while_creating()
    {
        Cell cell = new Cell(1,5,false);

        assertAll(
                ()->assertTrue(cell.isDead()),
                ()->assertFalse(cell.isAlive())
        );
    }

    @Test
    public void should_be_dead_if_life_state_specified_as_alive_while_creating()
    {
        Cell cell = new Cell(1,5,true);
        assertAll(
                ()->assertTrue(cell.isAlive()),
                ()->assertFalse(cell.isDead())
        );
    }

    @Test
    public void should_be_killed()
    {
        Cell cell = new Cell(1,5,true);
        Cell deadCell = cell.die();

        assertAll(
                ()->assertFalse(deadCell.isAlive()),
                ()->assertTrue(deadCell.isDead())
        );
    }

    @Test
    public void should_reborn()
    {
        Cell cell = new Cell(1,5,false);
        Cell rebornedCell = cell.reborn();

        assertAll(
                ()->assertTrue(rebornedCell.isAlive()),
                ()->assertFalse(rebornedCell.isDead())
        );
    }

}
