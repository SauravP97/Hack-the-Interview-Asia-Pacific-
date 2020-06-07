# Hack-the-Interview-Asia-Pacific-
Source Code Solution for all the Problems of the Contest

<h2>Problems Discussed :</h2>
<ul>
  <li> <h2>Problem 1 : Arrange Students</h2>
       <a href="https://www.hackerrank.com/contests/hack-the-interview-iv-apac/challenges/arrange-students">Problem Link</a>
       <br><br>
       You have been provided with two arrays of heights of boys and girls respectively
       You need to arrange them in a non decreasing order with no two adjacent boys and girls
       present in the line. <br>
       This can be achieved by sorting both the arrays individually and then applying a merging
       algorithm (same which is used in Merge Sort). You can merge both the arrays in two ways,
       picking the first element from boys array or picking the first element from girls array.
       If any one of the merged array is in non decreasing order then we can say that the task
       can be achieved (Yes).
       Otherwise our answer will be No.
  </li>
  <li> Problem 2 : Optimal Network Routing</li>
       <a href="https://www.hackerrank.com/contests/hack-the-interview-iv-apac/challenges/optimal-path-1">Problem Link</a>
       <br><br>
       The Problem require to send a packet from top left cell of the grid to the bottom right cell with minimum
       effort. <br>
       The Problem can be solved through a slight changed version of dijkstras algorithm. Instead of calculating the 
       minimum distance you need to calculate the maximum edge length found along the path. If you can calculate that 
       then you can also pass all the test cases if you can implement Priority Queue for storing the visited nodes. This
       will allow you to extract the node with a minimum distance value in log(N) time, hence improving the entire time
       complexity of your algorithm.
  <li> Problem 3 : Number of Integers
       <a href="https://www.hackerrank.com/contests/hack-the-interview-iv-apac/challenges/maximum-or-1"></a>
       <br><br>
       You need to find all numbers greater than L and less than equal to R having exactly K non-zero digit.
       This problem had a very large constraints for L and R. Hence can not be solved through a simple brute force approach.
       It can be solved through a Dynamic Programming Approach. This approach has been beautifully explained at GeeksforGeeks
       in this <a href="https://www.geeksforgeeks.org/count-of-numbers-in-range-where-the-number-does-not-contain-more-than-k-non-zero-digits/">article</a>. You need to slightly change the approach (given in the article) in order to solve this 
       particular problem. 
       I have used the DP approach to calculate the particlular value for integers less then L and for inetgers less than R 
       respectively. Then subtracted the first from the later one in order to get the final solution.
   </li>
</ul>
