import java.util.*;
import java.lang.*;
import java.io.*;
public class NQueens {
  List<String> board = new ArrayList<>();
  List<List<String>> solns = new ArrayList<>();
  private String getColStr(int col, int size) {
    StringBuilder sb = new StringBuilder();
    for (int c=0; c< size; c++) {
      char ch = (col == c) ? 'Q' : '.';
      sb.append(ch);
    }
    return sb.toString();
  }

  public NQueens(int size) {
    String res = getColStr(-1, size);
    for(int r=0; r < size; r++) {
      board.add(res);
    }
  }

  private boolean isSafe(int row, int col) {
    int r, c;
    for (r=0; r<row; r++) {
      if (board.get(r).charAt(col) == 'Q')
        return false;
    }
    for (r=row, c=col; r>=0 && c>=0; r--, c--)
      if (board.get(r).charAt(c) == 'Q')
        return false;
    for (r=row, c=col; r>=0 && c<board.get(row).length(); r--, c++)
      if (board.get(r).charAt(c) == 'Q')
        return false;
    return true;
  }

  public boolean util(int row) {
    if (row >= board.size()) {
      return true;
    }
    int sz = board.get(row).length();
    for (int col=0; col<sz; col++) {
      if (isSafe(row, col)) {
        this.board.set(row, getColStr(col, sz));
        if (util(row+1) == true) {
          return true;
        }
        this.board.set(row, getColStr(-1, sz));
      }
    }
    return false;
  }

  public static List<List<String>> getAll(int sz) {
    NQueens nq = new NQueens(sz);
    List<List<String>> res = new ArrayList<>();
    for (int c=0; c<sz; c++) {
      nq.board.set(0, nq.getColStr(c, sz));
      if (nq.util(1)) {
        res.add(new ArrayList<String>(nq.board));
      }
      nq.board.set(0, nq.getColStr(-1, sz));
    }
    return res;
  }

  private static void show(List<String> brd) {
    for(String str: brd)
      System.out.println(str);
  }

  public static void main(String args[]) throws Exception {
    System.out.println("Enter the size of the board:");
    String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
    for(List<String> brd: getAll(Integer.valueOf(line))) {
      show(brd);
      System.out.println();
    }
  }
}
