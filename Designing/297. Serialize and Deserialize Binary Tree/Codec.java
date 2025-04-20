/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// public class Codec {

//     // Encodes a tree to a single string.
//     public String serialize(TreeNode root) {
//         if (root == null) return ""; // If tree is empty, return empty string

//         String s = ""; // This string will store the serialized result
//         Queue<TreeNode> q = new LinkedList<>(); // Queue for BFS traversal
//         q.add(root); // Start from the root

//         while (!q.isEmpty()) {
//             TreeNode curr = q.poll(); // Get next node from queue

//             if (curr == null) {
//                 // If the node is null, append "#" to indicate null and continue
//                 s = s + "#" + ",";
//             } else {
//                 // Otherwise, append its value
//                 s = s + curr.val + ",";

//                 // Add left and right children (even if they are null) to maintain tree structure
//                 if (curr.left == null) {
//                     q.add(null);
//                 } else {
//                     q.add(curr.left);
//                 }

//                 if (curr.right == null) {
//                     q.add(null);
//                 } else {
//                     q.add(curr.right);
//                 }
//             }
//         }
//         return s; // Return the complete serialized string
//     }

//     // Decodes your encoded data to tree.
//     public TreeNode deserialize(String s) {
//         if (s.length() == 0) return null; // If string is empty, return null tree

//         String ch[] = s.split(","); // Split the string into values
//         TreeNode root = new TreeNode(Integer.parseInt(ch[0])); // First value is root
//         Queue<TreeNode> q = new LinkedList<>();
//         q.add(root); // Start BFS reconstruction
//         int n = ch.length;
//         int i = 0; // Index to track position in string array

//         while (!q.isEmpty()) {
//             TreeNode curr = q.poll(); // Get next node to attach children

//             // Left child
//             if (2 * i + 1 < n && !ch[2 * i + 1].equals("#")) {
//                 System.out.println(ch[2 * i + 1]); // Debug print
//                 curr.left = new TreeNode(Integer.parseInt(ch[2 * i + 1]));
//                 q.add(curr.left);
//             }

//             // Right child
//             if (2 * i + 2 < n && !ch[2 * i + 2].equals("#")) {
//                 curr.right = new TreeNode(Integer.parseInt(ch[2 * i + 2]));
//                 q.add(curr.right);
//             }

//             i++; // Move to next node
//         }

//         return root; // Return the reconstructed root
//     }
// }
// // your current indexing (2*i + 1) works only for perfect binary trees, but may fail otherwise.

import java.util.*;
public class Codec {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
      // Base case: if the root is null, return an empty string
      if(root == null) return "";

      String s = ""; // Initialize a string to build the serialized result
      Queue<TreeNode> q = new LinkedList<>(); // Use queue for level order traversal (BFS)
      q.add(root); // Start with the root node

      while(!q.isEmpty()){
          TreeNode curr = q.poll(); // Get the current node from the queue

          if(curr == null){
              // If node is null, add "#" to represent null child
              s = s + "#" + ",";
          }else{
              // If node is not null, add its value to the string
              s = s + curr.val + ",";

              // Whether child is null or not, we still add it to maintain position
              // This ensures we can reconstruct the exact tree structure later
              q.add(curr.left);
              q.add(curr.right);
          }
      }
      return s; // Return the final serialized string
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String s) {
      // If the string is empty, return null tree
      if(s.length() == 0) return null;

      // Split the string by comma to get the node values
      String ch[] = s.split(",");

      // The first value is the root node value
      TreeNode root = new TreeNode(Integer.parseInt(ch[0]));
      Queue<TreeNode> q = new LinkedList<>();
      q.add(root); // Add root to queue to start building the tree
      int i = 1; // Start reading from second element in the array

      while(!q.isEmpty()){
          TreeNode curr = q.poll(); // Get the current node

          // If we still have elements left and next value is not "#"
          if(i < ch.length && !ch[i].equals("#")){
              // Create left child and add to queue
              curr.left = new TreeNode(Integer.parseInt(ch[i]));
              q.add(curr.left);
          }
          i++; // Move to the next value

          // Same check for the right child
          if(i < ch.length && !ch[i].equals("#")){
              curr.right = new TreeNode(Integer.parseInt(ch[i]));
              q.add(curr.right);
          }
          i++; // Move to next node's children
      }

      return root; // Return the root of the reconstructed tree
  }
}
/*

🔍 Key Concepts:
Level Order Traversal (BFS): Used in both serialize and deserialize to keep track of structure.

Nulls Matter: Adding # helps retain tree shape even if some nodes are missing.

Queue Usage: Helps traverse tree in order and build it back in the same way.

Comma Separated String: Used for easy splitting and parsing later.

*/

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));