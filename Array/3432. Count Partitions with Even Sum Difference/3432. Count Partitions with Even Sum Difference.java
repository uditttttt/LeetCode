class Solution {
    public int countPartitions(int[] nums) {
        int count = 0;
        int suml = 0;
        int sumr  = 0;
        for(int i=0; i<nums.length; i++){
            suml = suml + nums[i];
        }

        for(int i =0; i<nums.length-1; i++){
            suml = suml- nums[i];
            sumr = sumr + nums[i];
            if((suml %  2 == 0 && sumr %2 == 0) || (suml %  2 != 0 && sumr %2 != 0)) count++;
        }

        return count;
    }
}

// odd and even property

// o - o = e
// e - e = e
// o - e = o
// e - o = o