import javafx.scene.Parent;

import java.util.*;

/**
 * 8 Puzzle Class to solve 8 puzzle game using Dfs , Bfs , A* Algorithms
 *
 */
public class _8_Puzzle {

    // DFS Algorithm to solve 8 puzzle algorithm

    public boolean dfs(String initial,String goal)  {
        long startTime = System.nanoTime();                                        // Start Time
        Stack<Node>stack = new Stack<>();
        Set<String> visited = new HashSet<>();                             // Explored Nodes
        stack.add(new Node(initial));
        Node state;
        while(!stack.empty()){
            state=stack.pop();
            visited.add(state.state);
            if(state.state.equals(goal)){
                long endTime   = System.nanoTime();                                 // End Time
                int count = 0;
                printOneState(state.state);
                while(state.parent!=null){
                    count++;
                    printOneState(state.parent.state);
                    state.parent = state.parent.parent;
                }
                System.out.println("Path :\n" +count);

                System.out.println("Search Depth :\n" + (visited.size()-1) );           // Print Max Cost (Max Depth)
                System.out.println("Total Time Of DFS : "+ (endTime - startTime) + " ns ");  // Total Time

                return true;
            }
            getNeighbor(state );
            for(Node neighbor : state.adj){
                if(!stack.contains(neighbor) && !visited.contains(neighbor.state)){
                    neighbor.parent = state;
                    stack.push(neighbor);

                }
            }
        }
        long endTime  = System.nanoTime();                                         // End Time
        System.out.println("Total Time Of BFS : "+ (endTime - startTime)+ " ns ");  // Total Time
        return false;
    }

    public boolean bfs(String initial,String goal)  {
        long startTime = System.nanoTime();                                        // Start Time
        Queue<Node>queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();                             // Explored Nodes
        queue.add(new Node(initial));
        Node state;
        while(!queue.isEmpty()){
            state=queue.poll();
            visited.add(state.state);
            if(state.state.equals(goal)){
                long endTime   = System.nanoTime();                                 // End Time
                printOneState(state.state);
                int count=0;
                while(state.parent !=null){
                    count++;
                    printOneState(state.parent.state);
                    state.parent = state.parent.parent;
                }
                System.out.println("Path  :\n"+count);
                System.out.println("Search Expanded Depth :\n" + (visited.size() - 1) );           // Print Max Cost (Max Depth)
                System.out.println("Total Time Of BFS : "+ (endTime - startTime) + " ns ");  // Total Time

                return true;
            }
            getNeighbor(state);
            for(Node neighbor : state.adj ){
                if(!queue.contains(neighbor) && !visited.contains(neighbor.state)){
                    neighbor.parent = state;
                    queue.add(neighbor);
                }
            }
        }
        long endTime  = System.nanoTime();                                         // End Time
        System.out.println("Total Time Of BFS : "+ (endTime - startTime)+ " ns ");  // Total Time
        return false;
    }

    private void getNeighbor(Node state){
        int index = 0,stateLength = state.state.length();
        // Get the index of Empty  Cell
        for(int i =0;i<stateLength;i++){
            if(state.state.charAt(i) == '0'){
                index = i ;
                break;
            }
        }
        char ch[];
        // Move Up
        if( index>=3 &&  (index % 3)  ==  (index-3) % 3){
            // Swap
            ch = state.state.toCharArray();
            char temp = ch[index-3];
            ch[index-3] = ch[index];
            ch[index] = temp;
            state.adj.add(new Node(new String(ch)));
        }
        // Move Down
        if( index+3 < stateLength && (index % 3)== (index+3) % 3) {
            // Swap
            ch = state.state.toCharArray();
            char temp = ch[index+3];
            ch[index+3] = ch[index];
            ch[index] = temp;
            state.adj.add(new Node(new String(ch)));
        }
        // Move Left
        if( index>=1 &&  (index % 3 - 1) % 3 ==  (index-1) % 3 ){
            // Swap
            ch = state.state.toCharArray();
            char temp = ch[index-1];
            ch[index-1] = ch[index];
            ch[index] = temp;
            state.adj.add(new Node(new String(ch)));
        }
        // Move Right
        if( index+1 < stateLength &&  (index % 3 + 1) == (index+1) % 3 ){
            // Swap
            ch = state.state.toCharArray();
            char temp = ch[index+1];
            ch[index+1] = ch[index];
            ch[index] = temp;
            state.adj.add(new Node(new String(ch)));
        }
    }

    public boolean A_StarManhattanDistance(String initial,String goal) {
        Set<String> explored = new LinkedHashSet<String>();
        long startTime = System.nanoTime();                                             // Start Time
        Entry ans = a_star(explored,true,initial,goal);
        long endTime   = System.nanoTime();                                             // End Time
        if(ans == null){
            System.out.println("Total Time Of A* Manhattan Distance : "+ (endTime - startTime)+ " ns ");  // Total Time
            return false;
        }
        System.out.println("The explored states :");
        printState(explored);
        System.out.println("Path To Goal :" );                               // Print Path to Goal
        printOneState(ans.state.state);
        while(!ans.state.parent.state.equals("")){
            printOneState(ans.state.parent.state);
            ans.state.parent = ans.state.parent.parent;
        }
        System.out.println("Cost Of Path To Goal :\n"+ans.g );                      // Print Cost to Path to Goal
        System.out.println("Search Depth :\n" + (explored.size()-1) );                  // Print Search Depth
        System.out.println("Total Time Of A* Manhattan Distance : "+ (endTime - startTime) + " ns \n");  // Total Time
        System.out.println("The explored states :");
        return true;
    }

    public boolean A_StarEuclideanDistance(String initial,String goal) {
        Set<String> explored = new LinkedHashSet<String>();
        long startTime = System.nanoTime();                                             // Start Time
        Entry ans = a_star(explored,false,initial,goal);
        long endTime   = System.nanoTime();                                             // End Time
        if(ans == null){
            System.out.println("Total Time Of A* Euclidean Distance : "+ (endTime - startTime)+ " ns ");  // Total Time
            return false;
        }

        System.out.println("The explored states :");
        printState(explored);
        System.out.println("Path To Goal :" );                               // Print Path to Goal
        printOneState(ans.state.state);
        while(!ans.state.parent.state.equals("")){
            printOneState(ans.state.parent.state);
            ans.state.parent = ans.state.parent.parent;
        }
        System.out.println("Cost Of Path To Goal :\n"+ans.g );                          // Print Cost to Path to Goal
        System.out.println("Search Depth :\n" + (explored.size()-1) );                      // Print Search Depth
        System.out.println("Total Time Of A* Euclidean Distance  : "+ (endTime - startTime) + " ns \n");      // Total Time
        return true;
    }

    public Entry a_star(Set<String> explored,boolean MD,String initial,String goal) {

        PriorityQueue<Entry> frontier = new PriorityQueue<>();
        frontier.add(new Entry(0,calcH(initial,goal,MD),new Node(initial)));
        while(!frontier.isEmpty()) {
            Entry state = frontier.poll();
            explored.add(state.state.state);
            state.state.parent = state.state.parent!=null ? state.state.parent : new Node("");
            if(state.state.state.equals(goal)) {
                return state;
            }
            getNeighbor(state.state);
            for (Node n : state.state.adj) {
                Entry temp=null;
                for (Entry element : frontier) {
                    if(n.state.equals(element.state.state)) {
                        temp = element;
                        break;
                    }
                }
                if(temp!=null) {
                    System.out.println("AS");
                    if(state.g+1<temp.g) {
                        Node tempState = temp.state;
                        temp.g=state.g+1;
                        frontier.remove(temp);
                        temp.updateKey();
                        temp.state = tempState;
                        temp.state.parent = temp.state;
                        frontier.add(temp);
                    }
                }
                else if(!explored.contains(n.state)) {
                    n.parent = state.state;
                    frontier.add(new Entry(state.g+1,calcH(n.state,goal,MD),n));
                }

            }

        }

        return null;
    }
    // calculate the heuristic function
    public float calcH(String state,String goal,boolean MD) {
        float ans = 0;
        for(int i=0;i<goal.length();i++) {
            int j=state.indexOf(goal.charAt(i));
            int x1,y1,x2,y2;

            y1=i%3;x1=(i*3)/9;                            //calculate the x and y axes to the goal element
            y2=j%3;x2=(j*3)/9;						      //calculate the x and y axes to the state element
            if(MD)
                ans += Math.abs(x1-x2)+Math.abs(y1-y2);       //calculate the Manhattan Distance
            else
                ans += Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));       //calculate the Euclidean Distance
        }
        return ans;
    }

    // print the explored nodes in appropriate way
    public void printOneState(String s){
        System.out.println("|"+s.charAt(0)+"|"+s.charAt(1)+"|"+s.charAt(2)+"|");
        System.out.println("|"+s.charAt(3)+"|"+s.charAt(4)+"|"+s.charAt(5)+"|");
        System.out.println("|"+s.charAt(6)+"|"+s.charAt(7)+"|"+s.charAt(8)+"|");
        System.out.println("----------------------");
    }
    public void printState(Set<String> explored){
        ArrayList<String >arr= new ArrayList<>();
        for( String s : explored){
           printOneState(s);
           System.out.println();
        }
    }

    // print the path nodes in appropriate way
    public void printPath(String path) {

        String[] parts = path.split("-");

        for (int i =0;i<parts.length;i++){
            if(!parts[i].equals(""))
            printOneState(parts[i]);
        }
    }


}
class Node{
    String state;
    Node parent=null;
    ArrayList<Node> adj;
    Node(String state){
        this.state = state;
        this.adj = new ArrayList<>();
    }
}
class Entry implements Comparable<Entry> {
    public float key;
    public int g;
    public float h;
    public Node state;
//    String path;

    public Entry(int g,float h,Node state) {
        this.g=g;
        this.h=h;
        this.key = g+h;
        this.state = state;
    }
    public void updateKey() {
        key=g+h;
    }
    // getters

    @Override
    public int compareTo(Entry other) {
        if(this.key > other.key){
            return 1;
        }

        else if (this.key < other.key){
            return -1;
        }

        else{
            return 0;
        }
    }
}
