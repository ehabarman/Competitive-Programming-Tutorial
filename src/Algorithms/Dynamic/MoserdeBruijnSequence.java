package Algorithms.Dynamic;


/**
 * @author Ehab Arman
 * @date 23-9-2018
 */
public class MoserdeBruijnSequence {

    /**
     * Summary:
     *
     * Given an integer ‘n’, print the first ‘n’ terms of the Moser-de Bruijn Sequence.
     *
     * The Moser-de Bruijn sequence is the sequence obtained by adding up the distinct powers of the number 4
     * (For example 1, 4, 16, 64, etc).
     *
     * Examples :
     *
     * Input : 5
     * Output : 0 1 4 5 16
     *
     * Input : 10
     * Output : 0 1 4 5 16 17 20 21 64 65
     */


    /**
     *
     *  Function to generate nth term of Moser-de Bruijn Sequence
     *
     */
    static int gen(int n)
    {
        int []S = new int [n + 1];

        S[0] = 0;
        if(n != 0)
            S[1] = 1;

        for (int i = 2; i <= n; i++)
        {

            // S(2 * n) = 4 * S(n)
            if (i % 2 == 0)
                S[i] = 4 * S[i / 2];

                // S(2 * n + 1) = 4 * S(n) + 1
            else
                S[i] = 4 * S[i/2] + 1;
        }

        return S[n];
    }

    /**
     *
     *  Generating the first 'n' terms of Moser-de Bruijn Sequence
     *
     */
    static void moserDeBruijn(int n)
    {
        for (int i = 0; i < n; i++)
            System.out.print(gen(i)+" ");
    }

    /**
     *
     *  Driver Code
     *
     */
    public static void main(String[] args)
    {
        int n = 15;
        System.out.println("First " + n +
                " terms of " +
                "Moser-de Bruijn Sequence : ");
        moserDeBruijn(n);
    }
}
