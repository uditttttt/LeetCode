class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        // Step 1: Initialize a variable `xor` to 0.
        // This variable will be used to calculate the XOR of all elements in the `derived` array.
        int xor = 0;

        // Step 2: Loop through each element in the `derived` array.
        for (int i = 0; i < derived.length; i++) {
            // XOR the current element of `derived` with the running total `xor`.
            // This accumulates the XOR of all elements in `derived`.
            xor = xor ^ derived[i];
        }

        // Step 3: Check the final value of `xor`.
        // If `xor` equals 0, it means the circular constraints are consistent,
        // and a valid `original` array can exist. Return true in this case.
        if (xor == 0) {
            return true;
        } else {
            // Otherwise, if `xor` is not 0, the circular constraints are not satisfied,
            // and no valid `original` array can exist. Return false.
            return false;
        }
    }
}
