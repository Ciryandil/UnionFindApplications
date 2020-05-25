import java.util.Random;
public class PercolationStats
{
   private double record[]; 
   private final int t; 
   private final double sd2 = 1.96;
    public PercolationStats(int n, int trials)
    {
        if(n<=0||trials<=0)
            throw new IllegalArgumentException();
        t=trials;
        record = new double[t];
        for(int i=0;i<t;i++)
            record[i]=0.0;
        for(int i=0;i<trials;i++)
        {
            Percolation p = new Percolation(n);
            Random r = new Random();
            while(!p.percolates())
            {
                int x = r.nextInt(n)+1;
                int y = r.nextInt(n)+1;
                
                        p.open(x,y);
            }
            record[i] = (double)p.numberOfOpenSites()/(n*n);
        }
    }

    public double mean()
    {
        double sum=0;
        for(int i=0;i<t;i++)
            sum+=record[i];
        return sum/t;
    }
    public double stddev()
    {
      double avg = mean();
      double variance =0;
      for(int i=0;i<t;i++)
        variance += Math.pow(avg-record[i],2);
    variance /= (t-1);
    return Math.sqrt(variance);
        
    }
    public double confidenceLo()
    {
        
        return mean()-(sd2*stddev())/Math.sqrt(t);
    }
    public double confidenceHi()
    {
        return mean()+(sd2*stddev())/Math.sqrt(t);
    }
    public static void main(String args[])
    {
        int n = Integer.parseInt(args[0]);
        int tri = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, tri);
        System.out.println("mean \t = "+ps.mean());
        System.out.println("stddev \t = "+ps.stddev());
        System.out.println("95% confidence interval \t = ["+ps.confidenceLo()+", "+ps.confidenceHi()+"]");
       

        

    }
}