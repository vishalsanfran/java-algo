import java.io.*;
import java.lang.*;
class LCS {
  public static int lcs(String a, String b) {
    int al = a.length();
    int bl = b.length();
    int[][] r = new int[al][bl];
    r[0][0] = a.charAt(0) == b.charAt(0) ? 1:0;
    int i,j;
    for(i=1;i<al;i++)
      r[i][0] = Math.max(a.charAt(i) == b.charAt(0) ? 1:0, r[i-1][0]);
    for(j=1;j<bl;j++)
      r[0][j] = Math.max(a.charAt(0) == b.charAt(j) ? 1:0, r[0][j-1]);
    int tmp;
    for(i=1;i<al;i++){
      for(j=1;j<bl;j++){
        tmp = Math.max(r[i-1][j], r[i][j-1]);
        r[i][j] = Math.max(tmp, (a.charAt(i-1) == b.charAt(j-1)? r[i-1][j-1]:0) + (a.charAt(i) == b.charAt(j)? 1:0));
      }
    }/*
    for(i=0;i<al;i++){
      for(j=0;j<bl;j++){
        System.out.print(r[i][j]+" ");
      }
      System.out.println("");
    }*/
    return r[al-1][bl-1];
  }
  public static void main(String[] args) throws Exception {
    BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));
    String s1 = rdr.readLine();
    String s2 = rdr.readLine();
    System.out.println(lcs(s1, s2));
  }
}
