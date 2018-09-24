package Algorithms.Miscellaneous;

import java.util.Arrays;

/**
 * @author Ehab Arman
 * @Date 24-9-2018
 */
public class MoQueries {

    // Driver program
    public static void main(String[] args)
    {
        int a[] = {1, 1, 2, 1, 3, 4, 5, 2, 8};
        int n = a.length;
        Query q[] = {new Query(0,4), new Query(1,3), new Query(2,4)};
        int m = q.length;
        queryResults(a, n, q, m);

    }



    /* Prints sum of all query ranges. m is number of queries
     n is size of array a[].*/
    public static void queryResults(int a[], int n, Query q[], int m)
    {
        // Find block size
        Query.block = (int)Math.sqrt(n);

        // Sort all queries so that queries of same blocks
        // are arranged together.
        Arrays.sort(q);

        // Initialize current L, current R and current sum
        int currL = 0, currR = 0;
        int currSum = 0;

        // Traverse through all queries
        for (int i=0; i<m; i++)
        {
            // L and R values of current range
            int L = q[i].L, R = q[i].R;

            // Remove extra elements of previous range. For
            // example if previous range is [0, 3] and current
            // range is [2, 5], then a[0] and a[1] are subtracted
            while (currL < L)
            {
                currSum -= a[currL];
                currL++;
            }

            // Add Elements of current Range
            while (currL > L)
            {
                currSum += a[currL-1];
                currL--;
            }
            while (currR <= R)
            {
                currSum += a[currR];
                currR++;
            }

            // Remove elements of previous range.  For example
            // when previous range is [0, 10] and current range
            // is [3, 8], then a[9] and a[10] are subtracted
            while (currR > R+1)
            {
                currSum -= a[currR-1];
                currR--;
            }

            // Print sum of current range
            System.out.println("Sum of ["+L+", "+R+ "] is " +currSum);
        }
    }

}

class Query implements Comparable<Query>{

    public static int block=0;
    public int L,R;

    public Query(int L,int R){
        this.L = L;
        this.R = R;
    }

    /* Function used to sort all queries so that all queries
       of the same block are arranged together and within a block,
       queries are sorted in increasing order of R values.*/
    @Override
    public int compareTo(Query y) {
        if (this.L/block != y.L/block)
            return this.L/block < y.L/block ? -1 : 1 ;

        return this.R < y.R ? -1 : 1;
        }
    }






