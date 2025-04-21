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
// class Solution {
//     public void flatten(TreeNode root) {
//         // Base case: if tree is empty, do nothing
//         if (root == null) return;

//         // Use a stack to simulate preorder traversal
//         Stack<TreeNode> s = new Stack<>();
//         s.push(root); // Start with the root node

//         // Loop until stack is empty
//         while (!s.isEmpty()) {
//             TreeNode curr = s.pop(); // Pop the current node

//             // If there is a right child, push it first (so it's processed after left)
//             if (curr.right != null) {
//                 s.push(curr.right);
//             }

//             // If there is a left child, push it after right (so it's processed next)
//             if (curr.left != null) {
//                 s.push(curr.left);
//             }

//             // If stack is not empty, the top of the stack is the next node in preorder
//             if (!s.isEmpty()) {
//                 curr.right = s.peek(); // Link current node's right to next node in preorder
//             }

//             curr.left = null; // Remove the left child to make it a singly linked list
//         }
//     }
// }
// /*

// 🧠 Step-by-Step Thought Process
// ✅ Step 1: Recognize Traversal Order
// Since the output should be in preorder traversal, your first thought should be:

// Can I do a preorder traversal and connect nodes accordingly?

// ✅ Step 2: Think Recursion vs Iteration
// Recursion is good, but in exams you may prefer iterative (stack-based) for clarity and control.

// Stack is commonly used for preorder traversal.

// ✅ Step 3: Simulate Preorder with Stack
// Imagine how preorder traversal works with a stack:

// java
// Copy
// Edit
// stack.push(root);
// while (!stack.isEmpty()) {
//     node = stack.pop();
//     process(node)
//     push right child
//     push left child
// }
// Why push right first?
// Because stack is LIFO, and you want to process left first (preorder: root-left-right).

// ✅ Step 4: Modify Tree In-Place
// Now the question says flatten the tree, not just traverse it.

// So how do we modify it?

// 💡 Think:

// In a linked list, each node has only a .right pointer.

// We need to disconnect left, and connect .right to the next node in preorder.

// So at every step, you want to say:

// java
// Copy
// Edit
// curr.right = stack.peek(); // next node in preorder
// curr.left = null;


// */

public class Solution {
  public void flatten(TreeNode root) {
      if (root == null) return;

      // Step 1: Recursively flatten left and right
      flatten(root.left);
      flatten(root.right);

      // Step 2: Store left and right
      TreeNode left = root.left;
      TreeNode right = root.right;

      // Step 3: Put left as right
      root.left = null;
      root.right = left;

      // Step 4: Go to the end of new right and attach old right
      TreeNode current = root;
      while (current.right != null) {
          current = current.right;
      }
      current.right = right;
  }
}
/*

🧩 Step-by-Step Method to Reach Recursive Solution
✅ Step 1: Understand what you need at each node
Ask:

What do I need to do with this node?

Answer:

I need to put the flattened left subtree between the node and its right subtree.

So:

Flatten left subtree.

Flatten right subtree.

Attach flattened left subtree to right.

Then attach the original right subtree to the end of the new right chain.

✅ Step 2: Think of Post-order traversal
Why post-order?

Because we need to first flatten left and right before changing current node’s pointers.

So think:

Recurse on left → flatten it.

Recurse on right → flatten it.

Rearrange pointers.

✅ Step 3: Dry run on small tree to visualize
Say:

markdown
Copy
Edit
  1
 /
2
Flatten left → 2

Right is null

So 1.right = 2, 1.left = null

🧠 In Exam, Think Like This:
Understand what is required (flatten into linked list using right pointers).

Think traversal type – Here, pre-order, but rearrangement needs post-order to process children first.

Work with left and right subtrees at each node.

Visualize a small example (2-3 nodes) to be confident.

Then code recursively – base case first, then recurse, then rearrange.

*/