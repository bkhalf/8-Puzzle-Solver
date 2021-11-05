import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFS {
    private String initial;
    private String goal;

    public DFS(String initial_state,String goal){
        this.initial = initial_state;
        this.goal = goal;
    }

    public boolean dfs(){
        Stack<String> stack = new Stack<String>();
        stack.push(this.initial);
        Set<String> visited = new HashSet<>();
        String state;
        while(!stack.empty()){
            state = stack.pop();
            visited.add(state);
            System.out.println(" State is   " + state);
            if(state.equals(this.goal)) return true;
            ArrayList<String> neighbors = getNeighbor(state);
            for(String neighbor : neighbors){
                if(!stack.contains(neighbor) && !visited.contains(neighbor)){
                    System.out.println(" Neighbor  " + neighbor);
                    stack.push(neighbor);
                }
            }
        }


        return false;
    }
    private ArrayList<String> getNeighbor(String state){
        ArrayList<String> adj = new ArrayList<>();
        int index = 0,stateLength = state.length();
        for(int i =0;i<stateLength;i++){
            if(state.charAt(i) == '0'){
                index = i ;
                break;
            }
        }
        char ch[];
        if( index+1 < stateLength &&  (index % 3 + 1) == (index+1) % 3 ){
            System.out.println("right befor " + state);
            ch = state.toCharArray();
            char temp = ch[index+1];
            ch[index+1] = ch[index];
            ch[index] = temp;
            String newState = new String(ch);
            System.out.println("right after " + newState);

            adj.add(newState);
        }
        if( index>=1 &&  (index % 3 - 1) % 3 ==  (index-1) % 3 ){
            System.out.println("left befor " + state);

            ch = state.toCharArray();
            char temp = ch[index-1];
            ch[index-1] = ch[index];
            ch[index] = temp;
            String newState = new String(ch);
            System.out.println("left after " + newState);

            adj.add(newState);
        }
        if( index+3 < stateLength && (index % 3)== (index+3) % 3)
        {
            System.out.println("down befor " + state);

            ch = state.toCharArray();
            char temp = ch[index+3];
            ch[index+3] = ch[index];
            ch[index] = temp;
            String newState = new String(ch);
            System.out.println("down after " + newState);

            adj.add(newState);
        }
        if( index>=3 &&  (index % 3)  ==  (index-3) % 3){
            System.out.println("up befor " + state);

            ch = state.toCharArray();
            char temp = ch[index-3];
            ch[index-3] = ch[index];
            ch[index] = temp;
            String newState = new String(ch);
            System.out.println("up after " + newState);

            adj.add(newState);
        }
        return adj ;
    }
}
