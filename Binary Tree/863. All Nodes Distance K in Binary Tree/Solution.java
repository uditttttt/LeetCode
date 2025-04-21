/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // In this method we wiil make graph for upward movement

// class Solution {
//     public static void buildGraph(TreeNode root , TreeNode parent , Map<TreeNode , List<TreeNode>> map){
//         if(root == null) return;
//         map.putIfAbsent(root , new ArrayList<>() );
//         if(parent != null){
//             map.get(root).add(parent);
//             map.get(parent).add(root);
//         }
//         buildGraph(root.left , root , map);
//         buildGraph(root.right , root , map);
//     }
//     public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
//         List<Integer> ans = new ArrayList<>();
//         Map<TreeNode , List<TreeNode> > graph = new HashMap<>();
//         buildGraph(root , null  , graph);
//         int d = 0;
//         Queue<TreeNode> q = new LinkedList<>();
//         Set<TreeNode> vis = new HashSet<>();
//         q.offer(target);
//         vis.add(target);
//         while(!q.isEmpty()){
//             if(d == k){
//                 while(!q.isEmpty()){
//                    ans.add(q.poll().val);
//                 }
//             }
//             int s = q.size();
//             for(int i =0; i<s; i++ ){
//                 TreeNode curr = q.poll();
//                 for(TreeNode neigh : graph.get(curr)){
//                     if(!vis.contains(neigh)){
//                         vis.add(neigh);
//                         q.add(neigh);
//                     }
//                 }
//             }
//             d++;
//         }

//         return ans;
//     }
// }

// in this we will use map to store parents of  each node for upward traversal
class Solution {

  // Helper function to map each node to its parent.
  public static void findParent(TreeNode root , HashMap<TreeNode , TreeNode> map , TreeNode parent){
      Queue<TreeNode> q = new LinkedList<>();
      q.add(root); // Start BFS traversal from root

      while(!q.isEmpty()){
          TreeNode curr = q.poll(); // Get the current node

          // If left child exists, store its parent and add to queue
          if(curr.left != null){
              map.put(curr.left , curr); // Store parent of left child
              q.add(curr.left);
          }

          // If right child exists, store its parent and add to queue
          if(curr.right != null){
              map.put(curr.right , curr); // Store parent of right child
              q.add(curr.right);
          }
      }
  }

  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
      HashMap<TreeNode , TreeNode> map = new HashMap<>();
      
      // Step 1: Create a mapping of each node to its parent
      findParent(root , map , null);

      Queue<TreeNode> q = new LinkedList<>();
      Set<TreeNode> vis = new HashSet<>(); // To avoid visiting the same node again

      // Step 2: Start BFS from the target node
      q.add(target);
      vis.add(target); // Mark target as visited

      int d = 0; // Distance from the target node

      // BFS until we reach distance k
      while(!q.isEmpty()){
          if(d == k) break; // Stop when we reach required distance
          int size = q.size(); // Number of nodes at current distance

          for(int i = 0; i < size; i++){
              TreeNode curr = q.poll(); // Get current node

              // Add left child to queue if it exists and not visited
              if(curr.left != null && !vis.contains(curr.left)){
                  q.add(curr.left);
                  vis.add(curr.left);
              }

              // Add right child to queue if it exists and not visited
              if(curr.right != null && !vis.contains(curr.right)){
                  q.add(curr.right);
                  vis.add(curr.right);
              }

              // Also add the parent node to queue if not visited
              TreeNode parent = map.get(curr);
              if(parent != null && !vis.contains(parent)){
                  q.add(parent);
                  vis.add(parent);
              }
          }

          d++; // Increase the current distance from target
      }

      // Step 3: All nodes in queue are at distance k from target
      ArrayList<Integer> ans = new ArrayList<>();
      while(!q.isEmpty()){
          ans.add(q.poll().val); // Add all node values to result list
      }

      return ans;
  }
}




/*
💡 Intuition Summary:
First, build a parent map to treat the binary tree as an undirected graph (can go left, right, or to parent).

Then use BFS from the target node to explore all nodes at distance k.

Keep track of visited nodes to avoid cycles.

Once you reach distance k, return the values of nodes at that level.

🧭 Realization Path (Mental Flow):
pgsql
Copy
Edit
Target node given
↓
Need to find nodes at distance K
↓
Distance → Think BFS
↓
Can move only down? No → Need parent info
↓
Build parent map
↓
Do BFS from target, move left, right, and parent
↓
Track distance → Stop at K
↓
Collect result

*/