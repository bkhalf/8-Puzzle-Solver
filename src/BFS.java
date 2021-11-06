import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;

public class BFS {
    private String initial;
    private String goal;

    public BFS(String initial_state,String goal){
        this.initial = initial_state;
        this.goal = goal;
    }
    public boolean bfs(){
        Queue<String>queue = new LinkedList<>();
        ArrayList<String> visited = new ArrayList<>();
        queue.add(this.initial);
        String state;
        while(!queue.isEmpty()){
            state=queue.poll();
            visited.add(state);
            if(state.equals(this.goal))return true;
            ArrayList<String> neighbors = getNeighbor(state);
            for(String neighbor : neighbors){
                if(!queue.contains(neighbor) && !visited.contains(neighbor)){
                    System.out.println(" Neighbor  " + neighbor);
                    queue.add(neighbor);
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
	
	public Entry A_StarManhattanDistance() {
		Set<String> explored = new LinkedHashSet<String>();  
		Entry ans = a_star(explored,true);
		System.out.println("The explored states");
		for (String element : explored) {
			printState(element);
		}
		return ans;
	}
	
	public Entry A_StarEuclideanDistance() {
		Set<String> explored = new LinkedHashSet<String>();  
		Entry ans = a_star(explored,false);
		System.out.println("The explored states :");
		for (String element : explored) {
			printState(element);
		}
		return ans;
	}
	
	public Entry a_star(Set<String> explored,boolean MD) {
		PriorityQueue<Entry> frontier = new PriorityQueue<>();
	
		frontier.add(new Entry(0,calcH(this.initial,this.goal,MD),this.initial,""));
		while(!frontier.isEmpty()) {
			Entry state = frontier.poll();
			explored.add(state.value);
			
			if(state.value.equals(this.goal)) {
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
				}else if(!explored.contains(n)) {
					frontier.add(new Entry(state.g+1,calcH(n,this.goal,MD),n,state.path+"-"+state.value));
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
	public void printState(String s){
		System.out.println("|"+s.charAt(0)+"|"+s.charAt(1)+"|"+s.charAt(2)+"|");
		System.out.println("|"+s.charAt(3)+"|"+s.charAt(4)+"|"+s.charAt(5)+"|");
		System.out.println("|"+s.charAt(6)+"|"+s.charAt(7)+"|"+s.charAt(8)+"|");
		System.out.println("----------------------");
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
