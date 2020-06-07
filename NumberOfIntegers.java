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

    static final int M = 101;  
    static final long mod = 1000000007;
    
    static long dp[][][]= new long[M][M][2];
      
    static int K;  
    static Vector<Integer> num; 
  
    public static long countInRangeUtil(int pos, int cnt, int tight ){  
        if (pos == num.size()) {   
            if (cnt == K)  
                return 1;  
            
            return 0;  
        }  
  
        if (dp[pos][cnt][tight] != -1)  
            return dp[pos][cnt][tight];  
  
        long ans = 0;  
        
        int limit = (tight!=0 ? 9 : num.get(pos));  
  
        for (int dig = 0; dig <= limit; dig++) {  
            int currCnt = cnt;  
  
            if (dig != 0)  
                currCnt++;  
  
            int currTight = tight;  

            if (dig < num.get(pos))  
                currTight = 1;  
  
            ans += countInRangeUtil(pos + 1, currCnt, currTight); 
            ans = ans%mod;
        }  
        return dp[pos][cnt][tight] = ans;  
    }

    public static long countInRange(String x)  {  
        num= new Vector<Integer>(); 
        
        for(int i=0; i<x.length(); i++) {  
            char ca = x.charAt(i);
            int val = (int)ca - 48;
            num.add(val);
        }  

        for(int i=0;i<M;i++) 
            for(int j=0;j<M;j++) 
                for(int k=0;k<2;k++) 
                    dp[i][j][k]=-1; 
        
        return countInRangeUtil(0, 0, 0);  
    } 

    public static int getNumberOfIntegers(String L, String R, int k) {
        K = k;
        long val1 = countInRange(L);
        long val2 = countInRange(R);
        long res = (val2%mod - val1%mod + mod)%mod;
        return (int)res;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String L = bufferedReader.readLine();

        String R = bufferedReader.readLine();

        int K = Integer.parseInt(bufferedReader.readLine().trim());

        int ans = Result.getNumberOfIntegers(L, R, K);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
