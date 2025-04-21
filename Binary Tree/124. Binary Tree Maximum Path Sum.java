class Solution {
    public int solve(TreeNode root, int[] sum) {
        if (root == null) return 0;

        // Recursively get the maximum path sum from left and right subtrees.
        int lftval = Math.max(0, solve(root.left, sum));  // Only consider positive sums
        int rhtval = Math.max(0, solve(root.right, sum)); // Only consider positive sums

        // Update the global maximum path sum considering the current node as the root of the path
        sum[0] = Math.max(sum[0], root.val + lftval + rhtval);

        // Return the maximum sum path through the current node that can extend to the parent node
        return root.val + Math.max(lftval, rhtval);
    }

    public int maxPathSum(TreeNode root) {
        int[] sum = new int[1];
        sum[0] = Integer.MIN_VALUE;  // Initialize to a very small value, since the sum can be negative
        solve(root, sum);
        return sum[0];
    }
}
