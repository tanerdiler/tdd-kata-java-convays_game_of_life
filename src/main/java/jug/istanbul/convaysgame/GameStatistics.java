package jug.istanbul.convaysgame;

import java.util.concurrent.atomic.AtomicInteger;

public class GameStatistics {

    private AtomicInteger countOfReborn = new AtomicInteger();
    private AtomicInteger countOfDead = new AtomicInteger();

    public int getCountOfReborn() {
        return countOfReborn.get();
    }

    public int getCountOfDead() {
        return countOfDead.get();
    }

    public int incCountOfDeadCell(int size) {
        return countOfDead.addAndGet(size);
    }

    public int incCountOfRebornedCell(int size){
        return countOfReborn.addAndGet(size);
    }
}
