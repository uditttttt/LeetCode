import java.util.*;

class Solution {
    // Recursive function to generate subsets
    public void solve(int[] nums, HashSet<List<Integer>> ans, List<Integer> temp, int ind) {
        // Base condition: if we've processed all elements in the array
        if (ind == nums.length) {
            // Add a copy of the current subset (temp) to the set of subsets (ans)
            ans.add(new ArrayList<>(temp));
            return;
        }

        // Include the current element in the subset
        temp.add(nums[ind]);
        // Recur for the next index with the current element included
        solve(nums, ans, temp, ind + 1);
        // Backtrack: remove the current element to explore subsets without it
        temp.remove(temp.size() - 1);

        // Recur for the next index without including the current element
        solve(nums, ans, temp, ind + 1);
    }

    // Main function to generate subsets with duplicates removed
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // Use a HashSet to store unique subsets (to handle duplicates automatically)
        HashSet<List<Integer>> ans = new HashSet<>();
        // Temporary list to build subsets
        List<Integer> temp = new ArrayList<>();
        // Sort the input array to ensure duplicates are adjacent
        Arrays.sort(nums);

        // Start the recursive process from index 0
        solve(nums, ans, temp, 0);

        // Convert the HashSet of subsets into a List
        List<List<Integer>> ans1 = new ArrayList<>();
        for (List<Integer> i : ans) {
            ans1.add(i);
        }

        // Return the final list of unique subsets
        return ans1;
    }
}

