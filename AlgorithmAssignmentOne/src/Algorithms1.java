
public class Algorithms1 {
	public static void algor1(int[] A)
	{
		int N = A.length;
		int j = 1;
		int[] B = new int [N];
		while(j<=N-1)
		{
			int i = 1;
			while(i<=N-j)
			{
				int m = i + j -1;
				//int r = min(m+j,N); not sure what this min function is
				
				int u; 
				//random value because I don't understand line 7 in the pseudo code that sets the value
				int r = 0;
				for(u = i; u < r; u++)
				{
					B[u]=A[u];
				}
				u = i;
				int v = m + 1;
				for(int w = i; w < r;w++)
				{
					if(u>m || (v<=r && B[v]<B[u]))
					{
						A[w] = B[v];
						v=u+1;
					}
					else
					{
						A[w]=B[u];
						u = u + 1;
					}
					i = i + (2 * j);
				}
			j = 2 * j;
			}
		}
	}
	public static void algor2(int[] A, int[] B, int i)
	{
		
	}
}
