/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
// class Solution {
//     public void solve(List<String> temp, TreeNode root, String s) {
//         if (root == null)
//             return;
//         if (root.left == null && root.right == null) {
//             s = s + root.val ;
//             temp.add(s);
//             return;
//         }
//         s = s + root.val +"->";
//         solve(temp, root.left, s);
//         solve(temp, root.right, s);
  

//     }

//     public List<String> binaryTreePaths(TreeNode root) {
//         String s = "";
//         List<String> temp = new ArrayList<>();
//         solve(temp, root , s);
//         return temp;

//     }
// }

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public void solve(List<String> temp, TreeNode root, StringBuilder s, int length) {
        if (root == null)
            return;
        
        s.append(root.val);
        
        if (root.left == null && root.right == null) {
            temp.add(s.toString());
        } else {
            s.append("->");
            solve(temp, root.left, s, s.length());
            solve(temp, root.right, s, s.length());
        }
        
        s.setLength(length); // backtracking
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> temp = new ArrayList<>();
        if (root == null) return temp;
        solve(temp, root, new StringBuilder(), 0);
        return temp;
    }
}
