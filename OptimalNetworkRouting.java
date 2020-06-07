import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

//Node Class Structure
class Node{
    int x;
    int y;
    
    Node(int x,int y){
        this.x = x;
        this.y = y;
    }
}

//Priority Queue Class Structure
class Heap{
    ArrayList<String> heap = new ArrayList<>();
    HashMap<String,Integer> indmap = new HashMap<>();
    HashMap<String,Integer> dist = new HashMap<>();
    
    int size = 0;
    
    public void insert(Node node, int distance){
        String index = Integer.toString(node.x)+"#"+ Integer.toString(node.y);
        indmap.put(index,size);
        
        if(size < heap.size()){
            heap.set(size,index);
        }
        else{
            heap.add(index);
        }
        
        dist.put(index, distance);
        int csize = size;
 
        while(csize > 0){
            int parent = getParent(csize);
            if(dist.get(heap.get(parent)) > dist.get(heap.get(csize))){
                String temp = heap.get(csize);
                heap.set(csize, heap.get(parent));
                indmap.put(heap.get(parent),csize);
                heap.set(parent, temp);
                indmap.put(temp, parent);
                csize = csize/2;
            }
            else{
                break;
            }
        }
        size++;
    }
    
    public void updateKey(Node node, int points){
        String ele = Integer.toString(node.x)+"#"+ Integer.toString(node.y);
        if(points >= dist.get(ele)){
            return;
        }
        int csize = indmap.get(ele);
        dist.put(ele,points);

        while(csize > 0){
            int parent = getParent(csize);
            if(dist.get(heap.get(parent)) > dist.get(heap.get(csize))){
                String temp = heap.get(csize);
                heap.set(csize, heap.get(parent));
                indmap.put(heap.get(parent),csize);
                heap.set(parent, temp);
                indmap.put(temp,parent);
                csize = csize/2;
            }
            else{
                break;
            }
        }
    }
    
    public void heapify(int index){
        int left = getLeft(index);
        int right = getRight(index);
        int minind = index;
        
        if(left < size && dist.get(heap.get(left)) < dist.get(heap.get(minind))){
            minind = left;
        }
        if(right < size && dist.get(heap.get(right)) < dist.get(heap.get(minind))){
            minind = right;
        }
        if(minind != index){
            String temp = heap.get(minind);
            heap.set(minind, heap.get(index));
            indmap.put(heap.get(index),minind);
            heap.set(index, temp);
            indmap.put(temp,index);
            heapify(minind);
        }
    }
    public int getParent(int index){
        return index/2;
    }
    public int getLeft(int index){
        return 2*index+1;
    }
    public int getRight(int index){
        return 2*index+2;
    }
    public Node getMin(){
        if(size == 1){
            String cind = heap.get(0);
            size--;
            String[] cinds = cind.split("#");
            return new Node(Integer.parseInt(cinds[0]),Integer.parseInt(cinds[1]));
        }
        else{
            String cind = heap.get(0);
            heap.set(0, heap.get(size-1));
            indmap.put(heap.get(size-1),0);
            size--;
            heapify(0);
            String[] cinds = cind.split("#");
            return new Node(Integer.parseInt(cinds[0]),Integer.parseInt(cinds[1]));
        }
    }
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }
}

class Result {

    //Function calculating Minimum Path
    public static int getMinEffort(List<List<Integer>> C) {
   
        int[][] mat = new int[C.size()][C.get(0).size()];
        int[][] dist = new int[C.size()][C.get(0).size()];
        int i=0,j=0;
        
        for(List<Integer> arr : C){
            j=0;
            for(int ele : arr){
                mat[i][j] = ele;
                dist[i][j] = Integer.MAX_VALUE;
                j++;
            }
            i++;
        }
        
        int[][] vis = new int[i][j];
        Heap heap = new Heap();
        heap.insert(new Node(i-1,j-1),0);
        dist[i-1][j-1] = 0;
        vis[i-1][j-1] = 1;
        
        //Starting Dijkstra Algo with source node N-1, M-1 (destination)
        while(!heap.isEmpty()){
            Node node = heap.getMin();
            
            if(node.x+1 < i){
                int reach = Math.max(dist[node.x][node.y], Math.abs(mat[node.x+1][node.y]-mat[node.x][node.y]));
                dist[node.x+1][node.y] = Math.min(dist[node.x+1][node.y], reach);
                if(vis[node.x+1][node.y] == 0){
                    heap.insert(new Node(node.x+1,node.y),dist[node.x+1][node.y]); 
                    vis[node.x+1][node.y] = 1;
                }
                else{
                    heap.updateKey(new Node(node.x+1,node.y),dist[node.x+1][node.y]);
                }
            }
            if(node.y+1 < j){
                int reach = Math.max(dist[node.x][node.y], Math.abs(mat[node.x][node.y+1]-mat[node.x][node.y]));
                dist[node.x][node.y+1] = Math.min(dist[node.x][node.y+1], reach);
                if(vis[node.x][node.y+1] == 0){
                    heap.insert(new Node(node.x,node.y+1),dist[node.x][node.y+1]);    
                    vis[node.x][node.y+1] = 1;
                }
                else{
                    heap.updateKey(new Node(node.x,node.y+1),dist[node.x][node.y+1]);
                }
            }
            if(node.x-1 >= 0){
                int reach = Math.max(dist[node.x][node.y], Math.abs(mat[node.x-1][node.y]-mat[node.x][node.y]));
                dist[node.x-1][node.y] = Math.min(dist[node.x-1][node.y], reach);
                if(vis[node.x-1][node.y] == 0){
                    heap.insert(new Node(node.x-1,node.y),dist[node.x-1][node.y]);    
                    vis[node.x-1][node.y] = 1;
                }
                else{
                    heap.updateKey(new Node(node.x-1,node.y),dist[node.x-1][node.y]);
                }
            }
            if(node.y-1 >= 0){
                int reach = Math.max(dist[node.x][node.y], Math.abs(mat[node.x][node.y-1]-mat[node.x][node.y]));
                dist[node.x][node.y-1] = Math.min(dist[node.x][node.y-1], reach);
                if(vis[node.x][node.y-1] == 0){
                    heap.insert(new Node(node.x,node.y-1),dist[node.x][node.y-1]);  
                    vis[node.x][node.y-1] = 1;
                }
                else{
                    heap.updateKey(new Node(node.x,node.y-1),dist[node.x][node.y-1]);
                }
            }
        }
        
        return dist[0][0];
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int answer = Result.getMinEffort(arr);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
