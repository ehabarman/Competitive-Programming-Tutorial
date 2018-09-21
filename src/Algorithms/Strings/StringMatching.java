package Algorithms.Strings;


/**
 * @author ehab arman
 * @date 21-9-2018
 */

public class StringMatching {

    private static int NO_OF_CHARS = 256; // number of characters used in finite statesearch and boyer-moore algorithm


    public static void main(String[] args){

        char[] txt = "AABAACAADAABAAABAA".toCharArray();
        char[] pat = "AABA".toCharArray();
        FASearch(pat,txt);
        searchBoyerMoore(pat,txt);
    }

    /**
     * this method prints all first indexes of the occurrences of a given patter pat
     * it uses Finite Automata algorithm
     * suitable for small patterns because at bigger ones it requires alot of time to calculate
     * finite state table and m^2 size to save it in memory
     * in case of big pattern using Boyer-Moore algorithm is better [ searchBoyerMoore ]
     *
     */
    public static void FASearch(char[] pat, char[] txt)
    {
        int M = pat.length;
        int N = txt.length;
        int[][] TF = new int[M+1][NO_OF_CHARS];
        computeTF(pat, M, TF);
        int i, state = 0;
        for (i = 0; i < N; i++)
        {
            state = TF[state][txt[i]];
            if (state == M)
                System.out.println("Pattern found " + "at index " + (i-M+1));
        }
    }

    /**
     *  this method used at start of searchForMatches to calculate table of finite state
     *  table is passed as parameter (TF) and modified inside the method
     */
    private static void computeTF(char[] pat, int M, int TF[][])
    {
        int state, x;
        for (state = 0; state <= M; ++state)
            for (x = 0; x < NO_OF_CHARS; ++x)
                TF[state][x] = getNextState(pat, M, state, x);
    }

    /**
     * used to find next state of a given state when input is x
     */
    private static int getNextState(char[] pat, int M, int state, int x)
    {
        if(state < M && x == pat[state])
            return state + 1;
        int ns, i;
        for (ns = state; ns > 0; ns--)
        {
            if (pat[ns-1] == x)
            {
                for (i = 0; i < ns-1; i++)
                    if (pat[i] != pat[state-ns+1+i])
                        break;
                if (i == ns-1)
                    return ns;
            }
        }
        return 0;
    }

    /***************************************************************************************************************/

    /**
     * this method prints all first indexes of the occurrences of a given patter pat
     * it uses Boyer Moore algorithm
     * suitable for large patterns because it doesn't require to pre compute table like Finite automata
     * and doesnt require to keep M^2 data in memory
     * in case of large pattern using Finite automata algorithm is better [ FASearch ]
     *
     */
    public static void searchBoyerMoore( char[] pat, char[] txt)
    {
        int m = pat.length;
        int n = txt.length;
        int[] badchar = new int[NO_OF_CHARS];
        badCharHeuristic(pat, m, badchar);

        int s = 0;
        while(s <= (n - m))
        {
            int j = m-1;
            while(j >= 0 && pat[j] == txt[s+j])
                j--;
            if (j < 0)
            {
                System.out.printf("\n pattern occurs at shift = %d", s);
                s += (s+m < n)? m-badchar[txt[s+m]] : 1;
            }
            else
                s +=  (1 > j - badchar[txt[s+j]]) ? 1: j - badchar[txt[s+j]];
        }
    }

    /**
     *  heuristic method used in searchBoyerMoore to find bad characters ( miss match )
     */
    private static void badCharHeuristic( char[] str, int size, int[] badchar)
    {
        int i;
        for (i = 0; i < NO_OF_CHARS; i++)
            badchar[i] = -1;
        for (i = 0; i < size; i++)
            badchar[(int) str[i]] = i;
    }




}
