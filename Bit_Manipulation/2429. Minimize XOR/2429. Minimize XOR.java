class Solution {
    // Helper method to check if a specific bit in `num1` is set (1)
    boolean isSet(int num1, int bit) {
        return (num1 & (1 << bit)) != 0; // Perform bitwise AND with 1 shifted left by `bit` positions
    }

    // Helper method to check if a specific bit in `num1` is unset (0)
    boolean isUnSet(int num1, int bit) {
        return (num1 & (1 << bit)) == 0; // Perform bitwise AND with 1 shifted left by `bit` positions
    }

    public int minimizeXor(int num1, int num2) {
        // Step 1: Calculate the number of set bits in `num2`
        int reqsetbits = Integer.bitCount(num2); // Determines how many bits need to be set in the result
        
        int x = 0; // Initialize the result number `x` to 0

        // Step 2: First pass - Preserve set bits from `num1`
        // Start from the most significant bit (MSB, bit 31) and move downwards
        for (int bit = 31; bit >= 0 && reqsetbits > 0; bit--) {
            if (isSet(num1, bit)) { // If the current bit in `num1` is set
                x = x | (1 << bit); // Set the same bit in `x`
                reqsetbits--;       // Decrease the count of required set bits
            }
        }

        // Step 3: Second pass - Fill unset bits in `num1`
        // Start from the least significant bit (LSB, bit 0) and move upwards
        for (int bit = 0; bit < 32 && reqsetbits > 0; bit++) {
            if (isUnSet(num1, bit)) { // If the current bit in `num1` is unset
                x = x | (1 << bit);  // Set this bit in `x`
                reqsetbits--;        // Decrease the count of required set bits
            }
        }

        // Step 4: Return the resulting number `x`
        return x;
    }
}

