package Algorithms.Miscellaneous;


/**
 * @author  Ehab Arman
 * @Date 8-10-2018
 */
public class FenwickTree {

    /**
     *
     *  This tree structures is made to handle array of n elements where two operations is done.
     *  First is to change value of an element in array,
     *  second is to find sum of all values from index 0 to given index i.
     *  Tree used when multiple queries of change and find sum operations is done
     *  in order to reduce total time of query to log n instead of n.
     *
     */

    int []BITree ;//  Array that represents Binary Indexed Tree

    public FenwickTree(int n){
        BITree = new int[n+1];
    }
    /**
     * This method return sum of elements in the array in range of [0 .. index]
     * array is the static array BITree defined above
     *
     * time required is O(log n)
     *
     * @param index  is the last element to be summed
     * @return return sum of all elements of array in range [0 .. index]
     */
    public int getSum(int index)
    {
        int sum = 0; // Iniialize result

        // index in BITree[] is 1 more than
        // the index in arr[]
        index = index + 1;

        // Traverse ancestors of BITree[index]
        while(index>0)
        {
            // Add current element of BITree
            // to sum
            sum += BITree[index];

            // Move index to parent node in
            // getSum View
            index -= index & (-index);
        }
        return sum;
    }

    /**
     * this method is used to update values of element in BITree array when a value changed
     * time required is O(log n)

     * @param index
     * @param val
     */
    public void updateBIT( int index, int val)
    {
        // index in BITree[] is 1 more than
        // the index in arr[]
        index = index + 1;

        // Traverse all ancestors and add 'val'
        while(index <= this.BITree.length)
        {
            // Add 'val' to current node of BIT Tree
            BITree[index] += val;

            // Update index to that of parent
            // in update View
            index += index & (-index);
        }
    }

    /**
     * Function to construct fenwick tree from given array.
     * the construted array is BITree array
     *
     * @param arr passed array
     */
    public void constructBITree(int arr[])
    {
        int n = arr.length;
        // Initialize BITree[] as 0
        for(int i=1; i<=n; i++)
            BITree[i] = 0;

        // Store the actual values in BITree[]
        // using update()
        for(int i = 0; i < n; i++)
            updateBIT( i, arr[i]);
    }

    // Main function
    public static void main(String args[])
    {
        int freq[] = {2, 1, 1, 3, 2, 3,
                4, 5, 6, 7, 8, 9};
        FenwickTree tree = new FenwickTree(freq.length);

        tree.constructBITree(freq);

        System.out.println("Sum of elements in arr[0..5]"+
                " is "+ tree.getSum(5));

        freq[3] += 6;

        tree.updateBIT(3, 6);

        System.out.println("Sum of elements in arr[0..5]"+
                " after update is " + tree.getSum(5));
    }

}
