import java.util.*;
public class ListNode {
int val;
ListNode next;
ListNode() {}
ListNode(int val) { this.val = val; }
ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
 
class Solution {
  public ListNode mergeKLists(ListNode[] lists) {
      // Create a min heap (priority queue) to store node values in sorted order
      PriorityQueue<Integer> pq = new PriorityQueue<>();

      // Traverse through each linked list in the array
      for (int i = 0; i < lists.length; i++) {
          ListNode temp = lists[i]; // Get the head of the current linked list

          // Traverse the linked list and add each node's value to the priority queue
          while (temp != null) {
              pq.add(temp.val); // Insert value into min heap
              temp = temp.next; // Move to the next node
          }
      }

      // Create a dummy node to start building the merged linked list
      ListNode merge = new ListNode(-1); 
      ListNode temp = merge; // Temporary pointer to help build the new list

      // Remove elements from the priority queue (smallest first) and build the sorted linked list
      while (!pq.isEmpty()) {
          temp.next = new ListNode(pq.remove()); // Create a new node with the smallest value
          temp = temp.next; // Move to the next node
      }

      // Return the head of the merged sorted linked list (skip the dummy node)
      return merge.next;
  }
}