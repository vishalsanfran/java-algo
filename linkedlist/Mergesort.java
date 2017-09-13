import java.lang.*;
import java.util.*;
import java.io.*;

public class Mergesort {
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
    System.out.println(nodeStr(head));
    return head;
  }

  public static void main(String args[]) throws Exception {
    BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));
    Mergesort ll = new Mergesort();
    int sz = Integer.valueOf(rdr.readLine());
    for (int i=0; i<sz; i++) {
      ll.push(Integer.valueOf(rdr.readLine()));
    }
    Node sorted = sortUtil(ll.head);
    ll.head = sorted;
    System.out.println(ll);
  }
}
