import java.util.*;

/**
 * 8 Puzzle Class to solve 8 puzzle game using Dfs , Bfs , A* Algorithms
 *
 */
public class _8_Puzzle {

    // DFS Algorithm to solve 8 puzzle algorithm

    public boolean dfs(String initial,String goal){
        long startTime = System.nanoTime();                                         // Start Time
        Stack<String> stack = new Stack<>();                                          // Stack
        Stack<String> path = new Stack<>();                                          // Stack

        Set<String> visited = new HashSet<>();                                      // Visited Set
        stack.push(initial);
        visited.add(initial);
        String state;

        while(!stack.isEmpty()){
            state = stack.pop();
            path.push(state);
            if(state.equals(goal)){
                long endTime   = System.nanoTime();                                 // End Time
                System.out.println("Path To Goal From Top Of Stack To Bottom    \n");
                for(int i=0;i<path.size();i++){
                    System.out.print("  " + path.get(i) );  // Total Time
                }
                System.out.println("\nCost Of Path To Goal :\n"+ (path.size()-1) );            // Print Cost to Path to Goal
                System.out.println("Search Depth :\n" + visited.size() );           // Print Search Depth
                System.out.println("Total Time Of DFS : "+ (endTime - startTime)+ " ns ");  // Total Time

                return true;
            }
            ArrayList<String> neighbors = getNeighbor(state);
            boolean flag = true;
            for(String neighbor : neighbors){
                if(!visited.contains(neighbor)){
                    flag = false;
                    LinkedList sec_list = new LinkedList();
//                    sec_list = (LinkedList) state.path.clone();
                    stack.add(neighbor);
                    visited.add(neighbor);
                }
            }
            if(flag){
                path.pop();
            }
        }
        long endTime   = System.nanoTime();                                         // End Time
        System.out.println("Total Time Of DFS : "+ (endTime - startTime)+ " ns ");  // Total Time
        return false;
    }

    public boolean bfs(String initial,String goal)  {
        long startTime = System.nanoTime();                                        // Start Time
        Queue<Attr>queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();                             // Explored Nodes
        queue.add(new Attr(0,initial,new LinkedList<>(),""));
        Attr state;
        while(!queue.isEmpty()){
            state=queue.poll();
            if(state.value.equals(goal)){
                long endTime   = System.nanoTime();                                 // End Time
                state.path.add(state.value);
                System.out.println("\n Path To Goal is : \n");                         // Print Path to Goal
                for(int i=0;i<state.path.size();i++){
                    System.out.print("  " + state.path.get(i) );  // Total Time
                }
                System.out.println("\nCost Of Path To Goal :\n"+state.g );            // Print Cost to Path to Goal
                System.out.println("Search Depth :\n" + visited.size() );           // Print Max Cost (Max Depth)
                System.out.println("Total Time Of BFS : "+ (endTime - startTime) + " ns ");  // Total Time

                return true;
            }
            ArrayList<String> neighbors = getNeighbor(state.value);
            for(String neighbor : neighbors){
                if( !visited.contains(neighbor)){
                    LinkedList sec_list = new LinkedList();
                    sec_list = (LinkedList) state.path.clone();
                    queue.add(new Attr(state.g+1,neighbor,sec_list, state.value));
                    visited.add(neighbor);
                }
            }
        }
        long endTime  = System.nanoTime();                                         // End Time
        System.out.println("Total Time Of BFS : "+ (endTime - startTime)+ " ns ");  // Total Time
        return false;
    }



    private ArrayList<String> getNeighbor(String state){
        ArrayList<String> adj = new ArrayList<>();
        int index = 0,stateLength = state.length();
        // Get the index of Empty  Cell
        for(int i =0;i<stateLength;i++){
            if(state.charAt(i) == '0'){
                index = i ;
                break;
            }
        }
        char ch[];
        // Move Up
        if( index>=3 &&  (index % 3)  ==  (index-3) % 3){
            // Swap
            ch = state.toCharArray();
            char temp = ch[index-3];
            ch[index-3] = ch[index];
            ch[index] = temp;
            String newState = new String(ch);
            adj.add(newState);
        }
        // Move Down
        if( index+3 < stateLength && (index % 3)== (index+3) % 3) {
            // Swap
            ch = state.toCharArray();
            char temp = ch[index+3];
            ch[index+3] = ch[index];
            ch[index] = temp;
            String newState = new String(ch);
            adj.add(newState);
        }
        // Move Left
        if( index>=1 &&  (index % 3 - 1) % 3 ==  (index-1) % 3 ){
            // Swap
            ch = state.toCharArray();
            char temp = ch[index-1];
            ch[index-1] = ch[index];
            ch[index] = temp;
            String newState = new String(ch);
            adj.add(newState);
        }
        // Move Right
        if( index+1 < stateLength &&  (index % 3 + 1) == (index+1) % 3 ){
            // Swap
            ch = state.toCharArray();
            char temp = ch[index+1];
            ch[index+1] = ch[index];
            ch[index] = temp;
            String newState = new String(ch);
            adj.add(newState);
        }

        return adj ;
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
        System.out.println("Path To Goal :");                           // Print Path to Goal
        System.out.println(ans.path);
        System.out.println("Cost Of Path To Goal :\n"+ans.g );                      // Print Cost to Path to Goal
        System.out.println("Search Depth :\n" + explored.size() );                  // Print Search Depth
        System.out.println("Total Time Of A* Manhattan Distance : "+ (endTime - startTime) + " ns \n");  // Total Time
        System.out.println("The explored states :");
        for(String exp : explored){
            System.out.print(" "+exp);
        }
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
        System.out.println("Path To Goal :" );                               // Print Path to Goal
        System.out.println(ans.path);
        System.out.println("Cost Of Path To Goal :\n"+ans.g );                          // Print Cost to Path to Goal
        System.out.println("Search Depth :\n" + explored.size() );                      // Print Search Depth
        System.out.println("Total Time Of A* Euclidean Distance  : "+ (endTime - startTime) + " ns \n");      // Total Time
        System.out.println("The explored states :");
        for(String exp : explored){
            System.out.print(" "+exp);
        }
        return true;
    }

    public Entry a_star(Set<String> explored,boolean MD,String initial,String goal) {
        PriorityQueue<Entry> frontier = new PriorityQueue<>();
        frontier.add(new Entry(0,calcH(initial,goal,MD),initial,""));
        while(!frontier.isEmpty()) {
            Entry state = frontier.poll();
            explored.add(state.value);

            if(state.value.equals(goal)) {
                state.path=state.path+"-"+state.value;
                return state;
            }
            ArrayList<String> neighbors = getNeighbor(state.value);
            for (String n : neighbors) {
                Entry temp=null;
                for (Entry element : frontier) {
                    if(n.equals(element.value)) {
                        temp=element;
                        break;
                    }
                }
                if(temp!=null) {
                    if(state.g+1<temp.g) {
                        temp.g=state.g+1;
                        frontier.remove(temp);
                        temp.updateKey();
                        temp.path=state.path+"-"+state.value;
                        frontier.add(temp);
                    }
                }
                else if(!explored.contains(n)) {
                    frontier.add(new Entry(state.g+1,calcH(n,goal,MD),n,state.path+"-"+state.value));
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
    public void printState(Set<String> explored){
        for(int j=0;j<3;j++) {
            for(String s: explored) {
                System.out.print("|"+s.charAt(j*3+0)+"|"+s.charAt(j*3+1)+"|"+s.charAt(j*3+2)+"|");
                if(j==1)System.out.print("===>");
                else System.out.print("    ");
            }
            System.out.println("===>");
        }

    }

    // print the path nodes in appropriate way
    public void printPath(String path,int size) {
        ArrayList<String> temp=new ArrayList<String>();
        for (int i = 0; i <= path.length() / size; i++) {
            temp.add(path.substring(i * size, Math.min((i + 1) * size, path.length())));
        }
        temp.remove(temp.size()-1);
        for(int j=0;j<3;j++) {
            for(String s: temp) {
                System.out.print("|"+s.charAt(j*3+1)+"|"+s.charAt(j*3+2)+"|"+s.charAt(j*3+3)+"|");
                if(j==1)System.out.print("===>");
                else System.out.print("    ");
            }
            System.out.println("===>");
        }
    }
}
class Attr{
    public int g;
    public String value;
    public LinkedList<String> path;

    //LinkedList<String> path = new LinkedList<>() ;
    public Attr(int g,String value,LinkedList<String> path,String from) {
        this.g=g;
        this.value = value;
        this.path = path;
        if(!from.equals("")){
            this.path.add(from);
        }

    }

    public Attr() {

    }
}
class Entry implements Comparable<Entry> {
    public float key;
    public int g;
    public float h;
    public String value;
    String path;

    public Entry(int g,float h, String value,String from) {
        this.g=g;
        this.h=h;
        this.key = g+h;
        this.value = value;
        this.path=from;
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
