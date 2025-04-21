# Ist method

class Solution {
    /**
     * Helper method to calculate the height of a tree or subtree.
     * The height is defined as the number of edges on the longest path from the node to a leaf.
     * @param root The root of the tree or subtree.
     * @return The height of the tree.
     */
    public int height(TreeNode root) {
        // Base case: If the tree is empty, its height is 0
        if (root == null) {
            return 0;
        }

        // Recursively calculate the height of the left subtree
        int leftht = height(root.left);

        // Recursively calculate the height of the right subtree
        int rightht = height(root.right);

        // The height of the current node is 1 + maximum of the heights of left and right subtrees
        return 1 + Math.max(leftht, rightht);
    }

    /**
     * Helper method to calculate the diameter of a tree or subtree.
     * The diameter is the length of the longest path between any two nodes in the tree.
     * This path may or may not pass through the root of the tree.
     * @param root The root of the tree or subtree.
     * @return The diameter of the tree.
     */
    public int diameter(TreeNode root) {
        // Base case: If the tree is empty, the diameter is 0
        if (root == null) {
            return 0;
        }

        // Calculate the height of the left subtree
        int leftht = height(root.left);

        // Calculate the height of the right subtree
        int rightht = height(root.right);

        // Recursively calculate the diameter of the left subtree
        int leftdm = diameter(root.left);

        // Recursively calculate the diameter of the right subtree
        int rightdm = diameter(root.right);

        // Calculate the diameter that passes through the current node (root)
        // It is the sum of the heights of the left and right subtrees
        int selfdiam = leftht + rightht;

        // Return the maximum diameter:
        // - Diameter through the root (selfdiam)
        // - Diameter of the left subtree (leftdm)
        // - Diameter of the right subtree (rightdm)
        return Math.max(selfdiam, Math.max(leftdm, rightdm));
    }

    /**
     * Public method to calculate the diameter of the binary tree.
     * Calls the helper `diameter` method to compute the result.
     * @param root The root of the binary tree.
     * @return The diameter of the binary tree.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // Call the helper method to compute the diameter
        return diameter(root);
    }
}


# 2nd method

class Solution {
    /**
     * Recursive helper method to calculate the height of the tree and update the diameter.
     * @param root The current node of the tree.
     * @param diameter A reference array to store the maximum diameter.
     * @return The height of the subtree rooted at the current node.
     */
    public int solve(TreeNode root, int[] diameter) {
        // Base case: If the node is null, return a height of 0.
        if (root == null) return 0;

        // Recursively calculate the height of the left subtree.
        int lft = solve(root.left, diameter);

        // Recursively calculate the height of the right subtree.
        int rht = solve(root.right, diameter);

        // Calculate the diameter through the current node (sum of left and right heights).
        // Update the global diameter if the current diameter is greater.
        diameter[0] = Math.max(diameter[0], lft + rht);

        // Return the height of the subtree rooted at this node.
        // Height = 1 (current node) + max height of its left or right subtree.
        return 1 + Math.max(lft, rht);
    }

    /**
     * Calculates the diameter of the binary tree.
     * The diameter is the length of the longest path between any two nodes.
     * @param root The root of the binary tree.
     * @return The diameter of the binary tree.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // Use an array to store the diameter, as arrays are passed by reference in Java.
        int[] diameter = new int[1]; // Initialize the diameter as 0.
        
        // Call the helper method to calculate the diameter.
        solve(root, diameter);
        
        // Return the maximum diameter found.
        return diameter[0];
    }
}

