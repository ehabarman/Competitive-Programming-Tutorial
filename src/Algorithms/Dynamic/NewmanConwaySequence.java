package Algorithms.Dynamic;

/**
 * @author Ehab Arman
 * @Date 23-9-2018
 */
public class NewmanConwaySequence {

    /**
     * Summary:
     *
     * Newman-Conway Sequence is the one which generates the following integer sequence.
     * 1 1 2 2 3 4 4 4 5 6 7 7…
     *
     * In mathematical terms, the sequence P(n) of Newman-Conway numbers is defined by recurrence relation
     *
     * P(n) = P(P(n - 1)) + P(n - P(n - 1))
     *
     * with seed values P(1) = 1 and P(2) = 1
     *
     * Given a number n, print n-th number in Newman-Conway Sequence.
     *
     * Examples :
     *
     *
     * Input : n = 2
     * Output : 1
     *
     * Input : n = 10
     * Output : 6
     */


    /**
     * function to find nth element in Newman-ConwaySequence
     * in the process it saves all values of elements in sequence
     *
     * time complexity O(n)
     * space O (n)
     *
     * can return array f[] in case all sequence needed
     *
     * @param n nth element
     * @return return nth element
     */
    static int sequence(int n)
    {
        // Declare array to store sequence
        int f[] = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        f[2] = 1;

        int i;

        for (i = 3; i <= n; i++)
            f[i] = f[f[i - 1]] +
                    f[i - f[i - 1]];

        return f[n];
    }

    /* Driver program to test above function */
    public static void main(String[] args)
    {
        int n = 10;
        System.out.println(sequence(n));

    }
}
