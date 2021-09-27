package Strategies;

public interface Strategy { // interface used as all strategies will have same input and output.

    Response play(int matchCount, Response[][] history, int stratNo);

    String getName();
}
