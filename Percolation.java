public class Percolation
{
   private WQUnionFind grid;
   private WQUnionFind grid2;
    private boolean[] open;
    private int n;
    private int counto=0;
    public Percolation(int n)
    {
        
        this.n = n;
        if (n <= 0)
            throw new IllegalArgumentException();
        grid = new WQUnionFind(n*n+2);
        grid2 = new WQUnionFind(n*n+1);
        open = new boolean[n*n+2];
        for (int i=0; i < open.length; i++)
            open[i]=false;
        open[0] = true;
        open[n * n + 1] = true;
    }

    public void open(int row, int col)
    {
        if (row<=0 || row>n || col<=0 || col>n)
            throw new IllegalArgumentException();
        if(!open[(row-1)*n+col]){
            counto++;
        
            int top = (row - 2) * n + col;        
            int left = (row - 1) * n + col - 1;
            int right = (row - 1) * n + col + 1;
            int self = (row - 1) * n + col;
            int below = row * n + col;
       
            open[self]=true;
            if(row==1){
                grid.union(0,self);
                grid2.union(0,self);
            }
            if(row==n)
                grid.union(n*n+1,self);
        
            if (row> 1 && open[top]){
                grid.union(top, self);
                grid2.union(top,self);
            }   
            if(row <n && open[below]){
                grid.union(below,self);
                grid2.union(below,self);
            } 
            if(col>1 && open[left]){
                grid.union(left,self);
                grid2.union(left,self);
            } 
            if(col<n && open[right]){
                grid.union(right,self);
                grid2.union(right, self);
            }

        }
    }

    public boolean isOpen(int row, int col)
    {
        if(row<=0||row>n||col<=0||col>n)
            throw new IllegalArgumentException();
        return open[(row-1)*n+col];
    }

    public boolean isFull(int row, int col)
    {
        if(row<=0||row>n||col<=0||col>n)
            throw new IllegalArgumentException();
        int self = (row-1)*n+col;
      
       return grid2.connected(self, 0);
    }

    public int numberOfOpenSites()
    {
        return counto;
    }

    public boolean percolates()
    {
      return grid.connected(0,n*n+1);
    }
   
}

