class Solution {
  public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> ans = new ArrayList<>(); // Stores the final list of all permutations.
      List<Integer> temp = new ArrayList<>(); // Stores the current permutation being built.
      generate(nums, ans, temp); // Start generating permutations.
      return ans; // Return all generated permutations.
  }

  public static void generate(int[] nums, List<List<Integer>> ans, List<Integer> temp) {
      // Base condition: If temp has all elements, we have a valid permutation.
      if (temp.size() == nums.length) {
          ans.add(new ArrayList<>(temp)); // Store a copy of temp in ans.
          return; // Stop further recursion and backtrack.
      }

      // Loop through each number in the input array
      for (int i = 0; i < nums.length; i++) {
          // Avoid duplicates: Only add nums[i] if it is not already in temp.
          if (!temp.contains(nums[i])) {
              temp.add(nums[i]); // Step 1: Do something (add nums[i] to temp)
              generate(nums, ans, temp); // Step 2: Explore (recursive call)
              temp.remove(temp.size() - 1); // Step 3: Revert (Backtrack to explore other options)
          }
      }
  }
}


/*

Backtracking Concept

1. Do Something
2. Explore
3. Revert step 1 & further explore

Intuition and Thought Process
The given code generates all permutations of an array using backtracking.

Key Idea
A permutation is a unique arrangement of elements.

We use recursion to explore all possible orderings.

Backtracking ensures that we revert changes after exploring a path to try other possibilities.

*/


