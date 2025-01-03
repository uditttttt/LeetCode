 

class Solution {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        // cant take int data type bcz data is very large
        long[] temp = new long[nums.length];
        temp[0] = nums[0];
        for(int i=1; i<n; i++){
             temp[i] = temp[i-1] + nums[i];
        }
        int count =0;
        for(int i=0; i<n-1; i++){
            long first = temp[i];
            long last = temp[n-1] - temp[i];
            if(first >= last) count++;
        }

        return count;
    }
}


class Solution {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        long[] pre = new long[n];
        long[] suff = new long[n];
        
        // Create prefix sum array
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }
        
        // Create suffix sum array
        suff[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suff[i] = suff[i + 1] + nums[i];
        }
        
        // Count the valid splits
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (pre[i] >= suff[i + 1]) {
                count++;
            }
        }
        
        return count;
    }
}
