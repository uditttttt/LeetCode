class Solution {
    public void find(int ind, int[] candidates, int target, List<Integer> curr, List<List<Integer>> ans) {
        if (ind == candidates.length) {
            if (target == 0) {
                // ans.add(curr); // it will give a error
                ans.add(new ArrayList<>(curr));
            }
            return;
        }
        if (candidates[ind] <= target) {
            curr.add(candidates[ind]);
            find(ind, candidates, target - candidates[ind], curr, ans);
            curr.remove(curr.size() - 1); // backtracking

        }
        find(ind + 1, candidates, target, curr, ans);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        int ind = 0;
        find(ind, candidates, target, curr, ans);
        return ans;
    }
}