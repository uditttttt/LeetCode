import java.util.HashMap;

class TreeNode {
    int val;
    TreeNode left, right;

    // Constructors
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Start and end indices for inorder and preorder arrays
        int inStart = 0, inEnd = inorder.length - 1;
        int preStart = 0, preEnd = preorder.length - 1;

        // HashMap to store index of each value in inorder for quick lookup
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        // Start building the tree
        return build(inorder, inStart, inEnd, preorder, preStart, preEnd, map);
    }

    private static TreeNode build(int[] inorder, int inStart, int inEnd,
                                  int[] preorder, int preStart, int preEnd,
                                  HashMap<Integer, Integer> map) {
        // Base case: If the current subarray range is invalid, return null
        if (preStart > preEnd || inStart > inEnd) return null;

        // Step 1: Get the root node from preorder traversal
        TreeNode root = new TreeNode(preorder[preStart]);

        // Step 2: Find the root node's index in inorder traversal
        int inIndex = map.get(root.val);

        // Step 3: Calculate the number of elements in the left subtree
        int numLeft = inIndex - inStart;

        // Step 4: Recursively build the left subtree
        root.left = build(inorder, inStart, inIndex - 1,
                          preorder, preStart + 1, preStart + numLeft, map);

        // Step 5: Recursively build the right subtree
        root.right = build(inorder, inIndex + 1, inEnd,
                           preorder, preStart + numLeft + 1, preEnd, map);

        // Step 6: Return the constructed tree node
        return root;
    }
}

/*

Understanding Tree Traversals
Preorder (Root → Left → Right)

The first element in the preorder array is always the root of the tree.

Inorder (Left → Root → Right)

The inorder array helps determine the left and right subtrees of a given root.

Summary

The first element of preorder is always the root.

Use inorder to split left and right subtrees.

Use recursion to construct left and right subtrees.

The process continues until all elements are plac

*/
