/*************************************************************************
*
*  Pace University
*  Spring 2023
*  Algorithms and Computing Theory
*
*  Course: CS 608
*  Team members: Filippo Zallocco, Ananthula Saivyshnav, Lokeshwar Anchuri, Sakshi Singh
*  Other collaborators: Professor Chaudhari, Daulet Kapezov
*  References: Divide & Conquer Algorithm In 3 Minutes - YouTube
*  Maximum sum sub-array - YouTube
*  Maximum Subarray - Amazon Coding Interview Question - Leetcode 53 - Python - YouTube
*  Assignment No.: 1
*  Problem: Explain the problem asked in 1-2 lines
* The prompt asks the user to find the maximum contiguous subarray inside the integer data type Myarray with both brute force method and divide and conquer and measure the time difference.
*
*  Input:  1000, 10,000, 100,000, 1,000,000
*  Output: 2175, 22,373, 224,007, 2,248,905
*
*  Visible data fields: int array[]
*
*
* Problem 1.
* PROVIDE YOUR WRITTEN RESPONSE. IF THE QUESTION IS A CODING QUESTION JUST PROVIDE YOUR CODE AS ASKED BELOW.
*
* Problem 2. All times reported are in nanoseconds
*                     n = 10^3   n = 10^4     n = 10^5     n = 10^6
* Brute force   16691701, 82481995, 6512468801, 583704830400
* Divide and conquer  9019100, 152591071, 4343266500, 432605815301
*
*
*
*
*
* Problem 3.
* PROVIDE YOUR WRITTEN RESPONSE. IF THE QUESTION IS A CODING QUESTION *JUST PROVIDE YOUR CODE AS ASKED BELOW.
*The running times of the divide and conquer methods do not match with the running times of the brute force method.
*The time difference between the execution of brute force and divide and conquer are given below:
*
********************************************************************
*Array Size: 1000, 10,000, 100,000, 1,000,000, type of function
*Brute force:16691701,83424308,6512468801,583704830400 O(n^2) slowest
*Divide and Conquer:9019100,10484151,4343266500,432605815301 O(NLog(N)) least slowest
*T(BF)-T(DC):7672601,72940157,2169202301, 151099015100
************************************************************************
*In the above table, the time derived is in nanoseconds and we may appreciate that the brute force method takes longer to solve the maximum subarray problem than divide and conquer method does.
*Furthermore, there is a growing difference between the two methods’ execution times as the size of the array increases.
*The time complexity for finding the maximum subarray using the brute force method is O(N2) which means the execution time will increase exponentially with the increase in size of the array
*whereas the time complexity of the divide and conquer method is O(NLog(N)).
*This finding suggests that as the array grows longer, the execution time of divide and conquer increases almost linearly.
*Hence, divide and conquer is the better method for finding the subarray because it has less time complexity than the brute force method.

*************************************************************************/




import java.util.*;
import java.lang.Math;

public class Algorithms{

  //Bruteforce
  public int MaxSubArray(int[] nums) {
      if(nums == null || nums.length == 0)
          return 0;
      int maximum_Sum =Integer.MIN_VALUE;

      for(int i = 0; i < nums.length; i++)
      {
          int current_Sum = 0;

          for(int j = i; j < nums.length; j++)
          {
              current_Sum += nums[j];
              maximum_Sum = Math.max(maximum_Sum, current_Sum);
          }
      }

      return maximum_Sum;
  }//end of Bruteforce

  //DivideandConquer
  public int maxsubarray(int array[], int low, int high)  {
      if(high==low) {
        return array[low];
      }
      int mid = (int)Math.floor((low+high)/2);
      int leftsum = maxsubarray(array, low, mid);
      int rightsum = maxsubarray(array, mid+1, high);
      int crossum = maxcrossingsubarray(array, low, high, mid);
      return Math.max(Math.max(crossum, leftsum), rightsum);
    }

    public int maxcrossingsubarray(int array[], int low, int high, int mid) {
      int leftsum = Integer.MIN_VALUE;
      int sum = 0;
      for (int i = mid; i>=1; i--) {
        sum = sum + array[i];
        if (sum>leftsum)
          leftsum = sum;
      }//end of for loop
      int rightsum = Integer.MIN_VALUE;
      int rsum = 0;
      for (int i = mid; i<=high; i++) {
          rsum = rsum + array[i];
          if(rsum > rightsum)
            rightsum = rsum;
      }//end of for loop
      //return leftsum + rightsum;
      return Math.max(leftsum + rightsum - array[mid],
                        Math.max(leftsum, rightsum));
    }//end of maxcrossingsubarray
//end of DivideandConquer

public static void main(String[] args) {

  System.out.println("Enter size of the array as powers of ten: ");
  Scanner sc = new Scanner(System.in);
  int userSize = sc.nextInt();
  int Myarray[] = new int[userSize];

  Algorithms msa = new Algorithms();

  System.out.println(" ");
  Random myrandomInt = new Random();
  for(int x=0; x<Myarray.length; x++) {

    Myarray[x]= Math.abs(myrandomInt.nextInt()%10);

    if(myrandomInt.nextInt()%2 == 1) {
      Myarray[x]=Myarray[x] * (-1);
    }
    //System.out.print(Myarray[x]+" ");

  }//end of for loop
  System.out.println(" \n");
  long startTime1 = System.nanoTime();
  System.out.println("Runnning Bruteforce algorithm yields: " +msa.MaxSubArray(Myarray));
  System.out.println(" \n");
  System.out.println("time of Bruteforce execution=	"+(System.nanoTime()	- startTime1)+"	nanosecs.\n");
  System.out.println(" \n");
  long startTime2 = System.nanoTime();
  System.out.println("Runnning DivideandConquer algorithm yields: " +msa.maxsubarray(Myarray,0,userSize-1));
  System.out.println(" \n");
  System.out.println("time of DivideandConquer execution=	"+(System.nanoTime()	- startTime2)+"	nanosecs.");
}//end of main

}//end of class
