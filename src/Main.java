public class Main {
    public static void main(String[] args) {
        DFS dfs = new DFS("125340678","012345678");
        BFS bfs = new BFS("125340678","012345678");
        System.out.println(bfs.bfs());
    }
}
