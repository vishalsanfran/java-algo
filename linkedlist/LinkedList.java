import java.lang.*;
import java.util.*;
import java.io.*;

public class LinkedList {
  Node head;

  public void push(int val) {
    if (head == null) {
      head = new Node(val);
      return;
    }
    Node curNode = head;
    while(curNode.next != null) {
      curNode = curNode.next;
    }
    curNode.next = new Node(val);
  }

  public String toString() {
    return nodeStr(head);
  }

  public static String nodeStr(Node n) {
    StringBuilder sb = new StringBuilder();
    Node cur = n;
    while(cur != null) {
      sb.append(cur.val);
      if(cur.next != null)
        sb.append("->");
      cur = cur.next;
    }
    return sb.toString();
  }

  static class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }

  public static Node sortUtil(Node a) {
    if(a.next == null)
      return a;
    Node slow = a;
    Node fast = a.next;
    while(fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    Node b = slow.next;
    slow.next = null;
    return mergeNodes(sortUtil(a), sortUtil(b));
  }

  public static Node mergeNodes(Node a, Node b) {
    Node head = a.val < b.val ? a:b;
    Node curA = a;
    Node curB = b;
    Node cur = head;
    while(curA != null && curB != null) {
      while (curA.next != null && curA.next.val < curB.val) {
        curA = curA.next;
      }
      if (curA.val < curB.val) {
        Node nextA = curA.next;
        curA.next = curB;
        curA = nextA;
      } else {
        Node nextB = curB.next;
        curB.next = curA;
        curB = nextB;
      }
    }
    return head;
  }

  public static Node rev(Node a) {
    if(a == null || a.next == null)
      return a;
    Node n = a.next;
    Node c = a;
    Node p = null;
    while(c != null) {
      Node nxt = c.next;
      c.next = p;
      p = c;
      c = nxt;
    }
    return p;
  }

  public static Node mergeKLists(LinkedList[] lists) {
    PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b)->{return a.val - b.val;});
    for(LinkedList list: lists)
      pq.add(list.head);

    Node head = pq.peek();
    while(pq.size() >= 2) {
      Node small = pq.poll();
      Node big = pq.peek();
      while(small.next != null && small.next.val < big.val) {
        small = small.next;
      }
      Node nxt;
      if (small.val < big.val) {
        nxt = small.next;
        small.next = big;
      } else {
        nxt = big.next;
        big.next = small;
      }
      if (nxt != null) {
        pq.add(nxt);
      }
    }
    return head;
  }

  public static void main(String args[]) throws Exception {
    BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));
    int sz = Integer.valueOf(rdr.readLine());
    LinkedList[] lls = new LinkedList[sz];
    for (int i=0; i<sz; i++) {
      lls[i] = new LinkedList();
      String[] words = rdr.readLine().split("\\s+");
      for(String word: words) {
        lls[i].push(Integer.valueOf(word));
      }
      //System.out.println("original " + nodeStr(lls[i].head));
      lls[i].head = sortUtil(lls[i].head);
      System.out.println("sorted " + nodeStr(lls[i].head));
      //System.out.println("rev "+ nodeStr(rev(lls[i].head)));
    }
    System.out.println("merged "+ nodeStr(mergeKLists(lls)));
  }
}
