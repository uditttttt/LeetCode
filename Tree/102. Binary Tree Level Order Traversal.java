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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // List to store the result where each sublist represents one level of the tree.
        List<List<Integer>> ans = new ArrayList<>();
        
        // If the root is null, return an empty result as there is no tree to traverse.
        if (root == null) return ans;
        
        // Temporary list to store nodes at the current level.
        List<Integer> temp = new ArrayList<>();
        
        // Queue to facilitate the level-order traversal.
        // It will hold the tree nodes to be processed level by level.
        Queue<TreeNode> q = new LinkedList<>();
        
        // Start by adding the root node to the queue.
        q.add(root);
        
        // Add a `null` marker to indicate the end of the first level.
        q.add(null);
        
        // Loop until the queue becomes empty.
        while (!q.isEmpty()) {
            // Remove the front node from the queue.
            TreeNode currNode = q.remove();
            
            // If the current node is not null, process it.
            if (currNode != null) {
                // Add the value of the current node to the temporary list for this level.
                temp.add(currNode.val);
                
                // Add the left child to the queue if it exists.
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                
                // Add the right child to the queue if it exists.
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            } else {
                // If the current node is `null`, it means we've finished processing one level.
                // Add the current level's list to the result.
                ans.add(new ArrayList<>(temp));
                
                // Clear the temporary list for the next level.
                temp.clear();
                
                // If the queue is empty, we are done; break the loop.
                if (q.isEmpty()) {
                    break;
                } else {
                    // Otherwise, add another `null` marker for the next level.
                    q.add(null);
                }
            }
        }
        
        // Return the result containing level-order traversal.
        return ans;
    }
}
