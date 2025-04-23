class Solution {

  // 🔍 Step 1: Find the node with the given key using standard tree traversal
  public static Node find(Node root , int key){
      if(root == null) return null;
      if(root.data == key) return root;

      Node left = find(root.left , key);
      if(left != null) return left;

      Node right = find(root.right , key);
      if(right != null) return right;

      return null;
  }

  // 🔽 Step 2: Find the minimum node in right subtree (i.e., successor)
  public static Node finds(Node curr){
      while(curr != null && curr.left != null){
          curr = curr.left;
      }
      return curr;
  }

  // 🔼 Step 3: Find the maximum node in left subtree (i.e., predecessor)
  public static Node findp(Node curr){
      while(curr != null && curr.right != null){
          curr = curr.right;
      }
      return curr;
  }

  // ⭐ Main function to find predecessor and successor of a given key in BST
  public static void findPreSuc(Node root, Node[] pre, Node[] suc, int key) {
      pre[0] = null;  // Initialize predecessor
      suc[0] = null;  // Initialize successor

      Node curr = root;

      // Step 4: Traverse the tree like in binary search
      while (curr != null) {
          if (curr.data == key) {
              // 🎯 If key is found:

              // 🧠 Predecessor = rightmost in left subtree (if it exists)
              if (curr.left != null)
                  pre[0] = findp(curr.left);

              // 🧠 Successor = leftmost in right subtree (if it exists)
              if (curr.right != null)
                  suc[0] = finds(curr.right);

              return;  // Done processing key
          }
          else if (key < curr.data) {
              // 🧭 If key is smaller, this node might be successor
              suc[0] = curr;
              curr = curr.left;
          } else {
              // 🧭 If key is larger, this node might be predecessor
              pre[0] = curr;
              curr = curr.right;
          }
      }
  }
}
