class Solution {
    
  // Helper function to map each node to its parent
  public static void findParent(Node root, HashMap<Node, Node> map) {
      Queue<Node> q = new LinkedList<>();
      q.add(root);
      
      while (!q.isEmpty()) {
          Node curr = q.poll();
          
          if (curr.left != null) {
              q.add(curr.left);
              map.put(curr.left, curr); // child -> parent
          }
          
          if (curr.right != null) {
              q.add(curr.right);
              map.put(curr.right, curr); // child -> parent
          }
      }
  }
  
  // Helper function to find the target node in the tree
  public static Node findTarget(Node root, int target) {
      if (root == null) return null;
      if (root.data == target) return root;
      
      Node left = findTarget(root.left, target);
      if (left != null) return left;
      
      Node right = findTarget(root.right, target);
      return right;
  }
  
  // Main function to find the minimum time to burn the entire tree from target node
  public static int minTime(Node root, int target) {
      // Step 1: Create a parent map for all nodes
      HashMap<Node, Node> parentMap = new HashMap<>();
      findParent(root, parentMap);
      
      // Step 2: Find the target node (where the fire starts)
      Node targetNode = findTarget(root, target);
      
      // Step 3: Start BFS from the target node
      Queue<Node> q = new LinkedList<>();
      Set<Node> visited = new HashSet<>();
      
      q.add(targetNode);
      visited.add(targetNode);
      
      int time = 0;
      
      while (!q.isEmpty()) {
          int size = q.size();
          
          // Flag to check if fire spreads in this round
          boolean fireSpread = false;
          
          for (int i = 0; i < size; i++) {
              Node curr = q.poll();
              
              // Check and burn left child
              if (curr.left != null && !visited.contains(curr.left)) {
                  q.add(curr.left);
                  visited.add(curr.left);
                  fireSpread = true;
              }
              
              // Check and burn right child
              if (curr.right != null && !visited.contains(curr.right)) {
                  q.add(curr.right);
                  visited.add(curr.right);
                  fireSpread = true;
              }
              
              // Check and burn parent
              Node parent = parentMap.get(curr);
              if (parent != null && !visited.contains(parent)) {
                  q.add(parent);
                  visited.add(parent);
                  fireSpread = true;
              }
          }
          
          // If fire spread to at least one new node, increment time
          if (fireSpread) time++;
      }
      
      return time;
  }
}

/*

🔥 Full Intuition of the minTime() Function:
Create parent links to enable upward traversal of fire.

Find the target node where fire starts.

Perform multi-directional BFS:

Fire spreads to left, right, and parent in 1 second.

Count levels in BFS → each level = 1 second.

Stop when there are no more nodes to burn.

🧭 Mental Strategy Summary (Exam Mode)

Step	What to Think
🔍 Understand	What directions does fire spread? (Left, Right, Parent)
💡 Realize	We can't go to parent directly — so we need a map.
✍️ Plan	1. Make parent map
2. Find target node
3. Do BFS from target
🧠 Optimize	Use visited set to avoid infinite loops
⌛ Count time	Count BFS levels where fire actually spreads

*/