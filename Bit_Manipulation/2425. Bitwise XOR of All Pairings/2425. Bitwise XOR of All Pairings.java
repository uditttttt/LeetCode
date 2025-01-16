class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int ans1 = 0; // Initialize a variable to store XOR result for nums1
        int ans2 = 0; // Initialize a variable to store XOR result for nums2
        
        // We check if nums2 has an odd length.
        // If nums2.length is odd, then each element in nums1 will be XORed an odd number of times with elements of nums2.
        // So, we need to include all elements of nums1 in the final XOR calculation.
        if (nums2.length % 2 == 1) {
            for (int i = 0; i < nums1.length; i++) {
                // XOR every element of nums1 with ans1 to get the XOR of all nums1 elements
                ans1 = ans1 ^ nums1[i];
            }
        }
        
        // Similarly, check if nums1 has an odd length.
        // If nums1.length is odd, then each element in nums2 will be XORed an odd number of times with elements of nums1.
        // Hence, we need to include all elements of nums2 in the final XOR calculation.
        if (nums1.length % 2 == 1) {
            for (int i = 0; i < nums2.length; i++) {
                // XOR every element of nums2 with ans2 to get the XOR of all nums2 elements
                ans2 = ans2 ^ nums2[i];
            }
        }

        // Finally, XOR the results of nums1 and nums2, as both may have contributed to the final XOR.
        return ans1 ^ ans2;
    }
}
