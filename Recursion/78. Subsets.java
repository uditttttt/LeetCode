import java.util.ArrayList;
import java.util.List;

class Solution {
    // Helper method for recursion
    public void solve(int[] ip, List<Integer> op, int i, List<List<Integer>> ans) {
        // If we've processed all elements, add the current subset to the answer list
        if (i == ip.length) {
            ans.add(new ArrayList<>(op)); // Add a copy of op
            return;
        }

        // Include the current element in the subset
        op.add(ip[i]);
        solve(ip, op, i + 1, ans); // Recurse with element included

        // Exclude the current element from the subset (implicitly done by not adding it)
        op.remove(op.size() - 1);  // Remove last added element
        solve(ip, op, i + 1, ans); // Recurse with element excluded
    }

    // Main method to generate subsets
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> op = new ArrayList<>();
        solve(nums, op, 0, ans); // Start recursion from index 0
        return ans;
    }
}


Recursion tree
Start
|
i = 0 (Include 1)
|
  i = 1 (Include 2)
  |
    i = 2 (Include 3)  
    |  
      i = 3 (Base Case) → [[]] -> [1, 2, 3]
    |
    i = 2 (Exclude 3)  
    |  
      i = 3 (Base Case) → [[]] -> [1, 2]
  |
  i = 1 (Exclude 2)
  |
    i = 2 (Include 3)
    |  
      i = 3 (Base Case) → [1] -> [1, 3]
    |
    i = 2 (Exclude 3)
    |  
      i = 3 (Base Case) → [1] -> [1]
|
i = 0 (Exclude 1)
|
  i = 1 (Include 2)
  |
    i = 2 (Include 3)
    |  
      i = 3 (Base Case) → [ ] -> [2, 3]
    |
    i = 2 (Exclude 3)  
    |  
      i = 3 (Base Case) → [ ] -> [2]
  |
  i = 1 (Exclude 2)
  |
    i = 2 (Include 3)
    |  
      i = 3 (Base Case) → [ ] -> [3]
    |
    i = 2 (Exclude 3)
    |  
      i = 3 (Base Case) → [ ] -> []

