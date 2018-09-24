package Algorithms.Miscellaneous;


/**
 * @author  Ehab Arman
 * @Date    24-9-2018
 */

/**
 * Algorithm explanation (english): https://www.youtube.com/watch?v=-Lgda-6_AiY
 * Algorithm explanation (arabic):  https://www.youtube.com/watch?v=IXas-DD1HFM
 */

public class HeavyLightDecomposition {

    private static int N = 1024;
    private static int [][]tree = new int [N][N];// Matrix representing the tree
    private static TreeNode [] node = new TreeNode[N];
    private static Edge[] edge = new Edge[N];
    private static segmentTree s = new segmentTree(N);



    // A function to add Edges to the Tree matrix
    // e is Edge ID, u and v are the two nodes, w is weight
    private static void addEdge(int e, int u, int v, int w)
    {
        /*tree as undirected graph*/
        tree[u-1][v-1] = e-1;
        tree[v-1][u-1] = e-1;

        edge[e-1] = new Edge();
        edge[e-1].weight = w;
    }


    /* A recursive function for DFS on the tree
    curr is the current node, prev is the parent of curr,
    dep is its depth*/
    private static void dfs(int curr, int prev, int dep, int n)
    {
        /* set parent of current node to predecessor*/
        node[curr] = new TreeNode();
        node[curr].par = prev;
        node[curr].depth = dep;
        node[curr].size = 1;

        /* for node's every child */
        for (int j=0; j<n; j++)
        {
            if (j!=curr && j!=node[curr].par && tree[curr][j]!=-1)
            {
                /* set deeper end of the Edge as this child*/
                edge[tree[curr][j]].deeper_end = j;

                /* do a DFS on subtree */
                dfs(j, curr, dep+1, n);

                /* update subtree size */
                node[curr].size+=node[j].size;
            }
        }
    }

    // A recursive function that decomposes the Tree into chains
    private static void hld(int curr_node, int id, Container c,
                            int n, int chain_heads[])
    {
        /* if the current chain has no head, this node is the first node
         * and also chain head */
        if (chain_heads[c.curr_chain]==-1)
        chain_heads[c.curr_chain] = curr_node;

        /* set chain ID to which the node belongs */
        node[curr_node].chain = c.curr_chain;

    /* set position of node in the array acting as the base to
       the segment tree */
        node[curr_node].pos_segbase = c.edge_counted;

        /* update array which is the base to the segment tree */
        if(edge[id]==null)
            edge[id] = new Edge();
        s.base_array[(c.edge_counted)++] = edge[id].weight;

        /* Find the special child (child with maximum size)  */
        int spcl_chld = -1, spcl_edg_id =0;
        for (int j=0; j<n; j++)
            if (j!=curr_node && j!=node[curr_node].par && tree[curr_node][j]!=-1)
                if (spcl_chld==-1 || node[spcl_chld].size < node[j].size)
                {
                    spcl_chld = j;
                    spcl_edg_id = tree[curr_node][j];
                }

        /* if special child found, extend chain */
        if (spcl_chld!=-1)
        {
            hld(spcl_chld, spcl_edg_id, c, n, chain_heads);
        }

    /* for every other (normal) child, do HLD on child subtree as separate
       chain*/
        for (int j=0; j<n; j++)
        {
            if (j!=curr_node && j!=node[curr_node].par &&
                    j!=spcl_chld && tree[curr_node][j]!=-1)
            {
                (c.curr_chain)++;
                hld(j, tree[curr_node][j], c, n, chain_heads);
            }
        }
    }

    // A recursive function that constructs Segment Tree for array[ss..se).
    // si is index of current node in segment tree st
    private static int construct_ST(int ss, int se, int si)
    {
        // If there is one element in array, store it in current node of
        // segment tree and return
        if (ss == se-1)
        {
            s.tree[si] = s.base_array[ss];
            return s.base_array[ss];
        }

        // If there are more than one elements, then recur for left and
        // right subtrees and store the minimum of two values in this node
        int mid = (ss + se)/2;
        s.tree[si] =  Math.max(construct_ST(ss, mid, si*2),
                construct_ST(mid, se, si*2+1));
        return s.tree[si];
    }


    /*  A recursive function that updates the Segment Tree
        x is the node to be updated to value val
        si is the starting index of the segment tree
        ss, se mark the corners of the range represented by si*/
    private static int update_ST(int ss, int se, int si, int x, int val)
    {

        if(  !(ss > x || se <= x) && (ss == x && ss == se-1))s.tree[si] = val;
        else if ( !(ss > x || se <= x) && !(ss == x && ss == se-1) )
        {
            int mid = (ss + se)/2;
            s.tree[si] =  Math.max(update_ST(ss, mid, si*2, x, val),
                    update_ST(mid, se, si*2+1, x, val));
        }

        return s.tree[si];
    }

    // A function to update Edge e's value to val in segment tree
    private static void change(int e, int val, int n)
    {
        update_ST(0, n, 1, node[edge[e].deeper_end].pos_segbase, val);

        // following lines of code make no change to our case as we are
        // changing in ST above
        // Edge_weights[e] = val;
        // segtree_Edges_weights[deeper_end_of_edge[e]] = val;
    }

    // A function to get the LCA of nodes u and v
    private static int LCA(int u, int v, int n)
    {
        /* array for storing path from u to root */
        int[] LCA_aux = new int[n+5];

        // Set u is deeper node if it is not
        if (node[u].depth < node[v].depth)
        {
            int temp = u;
            u = v;
            v = temp;
        }
        /* LCA_aux will store path from node u to the root*/
        arraySet(LCA_aux);

        while (u!=-1)
        {
            LCA_aux[u] = 1;
            u = node[u].par;
        }

    /* find first node common in path from v to root and u to
       root using LCA_aux */
        while (v!=0)
        {
            if (LCA_aux[v]==1)break;
            v = node[v].par;
        }

        return v;
    }

    //set all elements in the passed array to val
    private static void arraySet(int[] array){
        for(int i =0 ;i<array.length;i++)
            array[i]= -1;
    }

    /*  A recursive function to get the minimum value in a given range
     of array indexes. The following are parameters for this function.
    st    --> Pointer to segment tree
    index --> Index of current node in the segment tree. Initially
              0 is passed as root is always at index 0
    ss & se  --> Starting and ending indexes of the segment represented
                  by current node, i.e., st[index]
    qs & qe  --> Starting and ending indexes of query range */
    private static int RMQUtil(int ss, int se, int qs, int qe, int index)
    {
        //printf("%d,%d,%d,%d,%d\n", ss, se, qs, qe, index);

        // If segment of this node is a part of given range, then return
        //  the min of the segment
        if (qs <= ss && qe >= se-1)
            return s.tree[index];

        // If segment of this node is outside the given range
        if (se-1 < qs || ss > qe)
            return -1;

        // If a part of this segment overlaps with the given range
        int mid = (ss + se)/2;
        return Math.max(RMQUtil(ss, mid, qs, qe, 2*index),
                RMQUtil(mid, se, qs, qe, 2*index+1));
    }


    // Return minimum of elements in range from index qs (quey start) to
    // qe (query end).  It mainly uses RMQUtil()
    private static int RMQ(int qs, int qe, int n)
    {
        // Check for erroneous input values
        if (qs < 0 || qe > n-1 || qs > qe)
        {
            System.out.print("Invalid Input");
            return -1;
        }
        return RMQUtil(0, n, qs, qe, 1);
    }

    // A function to move from u to v keeping track of the maximum
    // we move to the surface changing u and chains
    // until u and v donot belong to the same
    private static int crawl_tree(int u, int v, int n, int[] chain_heads)
    {
        int chain_u, chain_v = node[v].chain, ans = 0;

        while (true)
        {
            chain_u = node[u].chain;

            /* if the two nodes belong to same chain,
             * we can query between their positions in the array
             * acting as base to the segment tree. After the RMQ,
             * we can break out as we have no where further to go */
            if (chain_u==chain_v)
            {
                if (u!=v)   //trivial
                    ans = Math.max(RMQ(node[v].pos_segbase+1, node[u].pos_segbase, n),
                            ans);
                break;
            }

        /* else, we query between node u and head of the chain to which
           u belongs and later change u to parent of head of the chain
           to which u belongs indicating change of chain */
            else
            {
                ans = Math.max(ans,
                        RMQ(node[chain_heads[chain_u]].pos_segbase,
                                node[u].pos_segbase, n));

                u = node[chain_heads[chain_u]].par;
            }
        }

        return ans;
    }


    // A function for MAX_EDGE query
    private static void maxEdge(int u, int v, int n, int chain_heads[])
    {
        int lca = LCA(u, v, n);
        int ans = Math.max(crawl_tree(u, lca, n, chain_heads),
                crawl_tree(v, lca, n, chain_heads));
        System.out.printf("%d\n", ans);
    }

    private static void arraySet(int[][] array){
        for(int i = 0; i< array.length;i++)
            for(int j =0; j< array[i].length;j++)
                array[i][j]= -1;
    }
    // driver function
    public static void main(String[] args)
    {
        /* fill adjacency matrix with -1 to indicate no connections */
        arraySet(tree);

        int n = 11;

        /* arguments in order: Edge ID, node u, node v, weight w*/
        addEdge(1, 1, 2, 13);
        addEdge(2, 1, 3, 9);
        addEdge(3, 1, 4, 23);
        addEdge(4, 2, 5, 4);
        addEdge(5, 2, 6, 25);
        addEdge(6, 3, 7, 29);
        addEdge(7, 6, 8, 5);
        addEdge(8, 7, 9, 30);
        addEdge(9, 8, 10, 1);
        addEdge(10, 8, 11, 6);

        /* our tree is rooted at node 0 at depth 0 */
        int root = 0, parent_of_root=-1, depth_of_root=0;

        /* a DFS on the tree to set up:
         * arrays for parent, depth, subtree size for every node;
         * deeper end of every Edge */
        dfs(root, parent_of_root, depth_of_root, n);

        int []chain_heads = new int[N];

        /*we have initialized no chain heads */
        arraySet(chain_heads);

        /* Stores number of edges for construction of segment
       tree. Initially we haven't traversed any Edges. */
        int edge_counted = 0;

        /* we start with filling the 0th chain */
        int curr_chain = 0;

        /* HLD of tree */
        Container c = new Container(edge_counted, curr_chain);
        hld(root, n-1, c , n, chain_heads);
        edge_counted = c.edge_counted;

        /* ST of segregated Edges */
        construct_ST(0, edge_counted, 1);

        /* Since indexes are 0 based, node 11 means index 11-1,
       8 means 8-1, and so on*/
        int u = 11, v  = 9;

        System.out.print("Max edge between " + u + " and " + v + " is ");
        maxEdge(u-1, v-1, n, chain_heads);

        // Change value of edge number 8 (index 8-1) to 28
        change(8-1, 28, n);
        System.out.print("After Change: max edge between " + u + " and "+ v + " is ");
        maxEdge(u-1, v-1, n, chain_heads);

        v = 4;
        System.out.print("Max edge between " + u + " and " + v + " is ");
        maxEdge(u-1, v-1, n, chain_heads);

        // Change value of edge number 5 (index 5-1) to 22
        change(5-1, 22, n);
        System.out.print("After Change: max edge between " + u + " and "+ v + " is ");
        maxEdge(u-1, v-1, n, chain_heads);

    }
}


/* a tree node structure. Every node has a parent, depth,
subtree size, chain to which it belongs and a position
in base array*/
class TreeNode{
    int par = 0;
    int depth = 0;
    int size = 0;
    int pos_segbase = 0;
    int chain = 0;
}


/* every Edge has a weight and two ends. We store deeper end */
class Edge{
    int weight = 0;
    int deeper_end = 0;

}

/* we construct one segment tree, on base array */
class segmentTree{
    int[] base_array;
    int[] tree;

    segmentTree(int n){
        base_array= new int[n];
        tree = new int[6*n];
    }
}

class Container{

    int edge_counted ;
    int curr_chain ;

    Container(int edge_counted,int curr_chain){
        this.edge_counted=edge_counted;
        this.curr_chain=curr_chain;
    }
}