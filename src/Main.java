import java.util.*;

public class Main {
    public static void main(String[] args) {
        _8_Puzzle puzzle = new _8_Puzzle();
        System.out.println(puzzle.dfs("123046758","123456780"));
        System.out.println(puzzle.bfs("123046758","123456780"));
        System.out.println(puzzle.A_StarEuclideanDistance("123046758","123456780"));
        System.out.println(puzzle.A_StarManhattanDistance("123046758","123456780"));



    }
}
