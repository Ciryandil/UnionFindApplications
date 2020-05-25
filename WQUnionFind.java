//Weighted Quick Union with Path Compression

public class WQUnionFind
{
    private int[] index;
    private int[] size;
    public WQUnionFind(int n)
    {
        index = new int[n];
        size = new int[n];
        for(int i=0;i<n;i++)
        {
            index[i] = i;
            size[i]=1;
        }
    }
    public int root(int node)
    {
        while(index[node]!=node){
            index[node]=index[index[node]];
            node=index[node];
        }
        return node;
    }
    public void union(int node1, int node2)
    {
        int root_node1 = root(node1);
        int root_node2 = root(node2);
        if(root_node1==root_node2)
            return; 
        if(size[root_node1]>size[root_node2]){
            index[root_node2]=root_node1;
            size[root_node1]+=size[root_node2];
        }
    else{
        index[root_node1]=root_node2;
        size[root_node2]+=size[root_node1];
        }
    }
public boolean connected(int node1, int node2)
    {
        return root(node1)==root(node2);
    }
}