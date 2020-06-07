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

class Result {

    public static boolean isSorted(List<Integer> res){
        for(int i=1;i<res.size();i++){
            if(res.get(i-1)>res.get(i)){
                return false;
            }
        }
        return true;
    }
    
    public static String arrangeStudents(List<Integer> a, List<Integer> b) {
        List<Integer> res1 = new ArrayList<>();
        List<Integer> res2 = new ArrayList<>();

        Collections.sort(a);
        Collections.sort(b);
        
        // Merge Arrays a and b with 
        // first element as a[0]
        for(int i=0;i<a.size();i++){
            res1.add(a.get(i));
            res1.add(b.get(i));
        }
        
        // Merge Arrays a and b with 
        // first element as b[0]
        for(int i=0;i<a.size();i++){
            res2.add(b.get(i));
            res2.add(a.get(i));
        }
        
        //Check whether res1 or res2 is sorted
        if(isSorted(res1) || isSorted(res2)){
            return "YES";
        }
        return "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                String result = Result.arrangeStudents(a, b);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
