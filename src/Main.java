public class Main {
    public static void main(String[] args) {
//        DFS dfs = new DFS("125340678","012345678");
//        BFS bfs = new BFS("125340678","012345678");
    	BFS bfs = new BFS("123046758","123456780");
        Entry ans=bfs.A_StarEuclideanDistance();
//        System.out.println(ans.value);
        System.out.println(ans.key);
        System.out.println(ans.path);
    }
}
