/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

  public TreeNode buildTree(int[] inorder, int[] postorder) {
      // Map to store value -> index mapping of inorder traversal for O(1) access
      HashMap<Integer , Integer> map = new HashMap<>();
      for(int i = 0; i < inorder.length; i++) {
          map.put(inorder[i], i);  // Mapping inorder value to its index
      }

      // Call recursive function to build the tree
      return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
  }

  // Recursive function to build the tree
  public static TreeNode build(
      int[] inorder, int inStart, int inEnd,
      int[] postorder, int postStart, int postEnd,
      HashMap<Integer , Integer> map
  ) {
      // Base case: if invalid range, return null (no tree to build)
      if (postStart > postEnd || inStart > inEnd) return null;

      // The last element in postorder is the root of the current subtree
      TreeNode root = new TreeNode(postorder[postEnd]);

      // Get the index of root in inorder to split into left and right subtrees
      int inIndex = map.get(root.val);

      // Number of elements in the left subtree
      int numLeft = inIndex - inStart;

      // Recursively build the left subtree
      // Left inorder range: inStart to inIndex - 1
      // Left postorder range: postStart to postStart + numLeft - 1
      root.left = build(
          inorder, inStart, inIndex - 1,
          postorder, postStart, postStart + numLeft - 1,
          map
      );

      // Recursively build the right subtree
      // Right inorder range: inIndex + 1 to inEnd
      // Right postorder range: postStart + numLeft to postEnd - 1
      root.right = build(
          inorder, inIndex + 1, inEnd,
          postorder, postStart + numLeft, postEnd - 1,
          map
      );

      // Return the root of this subtree
      return root;
  }
}

/*

🔍 Intuition (Thought Process)
You're given:

inorder traversal of a binary tree.

postorder traversal of the same binary tree.

You need to reconstruct the original binary tree.

🔁 Traversal Recap
Inorder (L, Root, R): helps in finding left and right subtrees.

Postorder (L, R, Root): the last element is always the root of the current subtree.

🔧 Core Idea
Use postorder to identify the root of the current subtree (last element).

Use inorder to split elements into left and right subtrees.

Recursively build the tree.

🧠 Why This Works
We use postorder to know the current root.

We use inorder to divide nodes into left and right subtrees.

We repeat this recursively to construct the entire tree.



*/