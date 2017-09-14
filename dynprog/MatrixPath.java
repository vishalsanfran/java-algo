import java.io.*;
import java.lang.*;
class MatrixPath {
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] res = new int[m][n];

        res[0][0] = grid[0][0];
        int r,c;
        for(r=1; r<m; r++){
          res[r][0] = grid[r-1][0] + grid[r][0];
        }
        for(c=1; c<n; c++){
          res[0][c] = grid[0][c-1] + grid[0][c];
        }
        for(r=1; r<m; r++) {
          for(c=1; c<n; c++) {
            res[r][c] = grid[r][c] + Math.min(res[r-1][c], res[r][c-1]);
          }
        }
        return res[m-1][n-1];
    }

    public static int[][] readMatrix(BufferedReader rdr) throws Exception {
      String[] itms = rdr.readLine().split("\\s+");
      int m = Integer.valueOf(itms[0]);
      int n = Integer.valueOf(itms[1]);
      int[][] matrix = new int[m][n];
      for(int r=0;r<m;r++) {
        itms = rdr.readLine().split("\\s+");
        for(int c=0;c<n;c++) {
          matrix[r][c] = Integer.valueOf(itms[c]);
        }
      }
      return matrix;
    }

    public static void main(String[] args) throws Exception {
      BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));
      int[][] grid = readMatrix(rdr);
      System.out.println("minpathsum " + minPathSum(grid));
    }
}
//echo -e "2 2\n2 4\n3 8"|java MatrixPath
