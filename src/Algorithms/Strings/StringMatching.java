package Algorithms.Strings;


/**
 * @author ehab arman
 * @date 21-9-2018
 */

public class StringMatching {

    private static int NO_OF_CHARS = 256; // number of characters used in finite statesearch

    /**
     * this method prints all first indexes of the occurrences of a given patter pat
     * it uses Finite Automata algorithm
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
                // can replaced by counting if needed
                System.out.println("Pattern found "
                        + "at index " + (i-M+1));
        }
    }

    /**
     *
     * This method return next finite state of a given state with input x
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

}
