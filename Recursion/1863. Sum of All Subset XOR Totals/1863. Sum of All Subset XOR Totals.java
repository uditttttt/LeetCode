class Solution {
    public int subsetXORSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        // finding all subsets of arrayList
        solve(nums, ans, temp, 0);
        int sum = 0;
        for (List<Integer> i : ans) {
            int xor = 0;
            for (int j : i) {
                xor = xor ^ j;
            }
            sum = sum + xor;
        }

        return sum;
    }

    public void solve(int[] nums, List<List<Integer>> ans, List<Integer> temp, int i) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        solve(nums, ans, temp, i + 1);
        temp.add(nums[i]);
        solve(nums, ans, temp, i + 1);
        temp.remove(temp.size() - 1);
    }
}

    // More optimised code

class Solution {
    public int subsetXORSum(int[] nums) {
        // Start the recursion with the helper function.
        // Begin at index 0 and with an initial XOR value of 0 (empty subset).
        return calculateXORSum(nums, 0, 0);
    }

    private int calculateXORSum(int[] nums, int index, int currentXOR) {
        // Base case: If we have considered all elements in the array
        if (index == nums.length) {
            // Return the XOR value of the current subset (currentXOR).
            return currentXOR;
        }

        // Recursive case 1: Include the current element in the subset
        // XOR the current element with currentXOR and move to the next index.
        int include = calculateXORSum(nums, index + 1, currentXOR ^ nums[index]);

        // Recursive case 2: Exclude the current element from the subset
        // Keep currentXOR unchanged and move to the next index.
        int exclude = calculateXORSum(nums, index + 1, currentXOR);

        // Combine the results of both cases (include and exclude)
        // This gives the total XOR sum of subsets involving the current element.
        return include + exclude;
    }
}

