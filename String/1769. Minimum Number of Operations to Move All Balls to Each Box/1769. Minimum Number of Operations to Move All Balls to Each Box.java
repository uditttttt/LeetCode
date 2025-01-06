class Solution {
    public int[] minOperations(String boxes) {
        // Initialize variables:
        // cumValue: Keeps track of the number of balls ('1's) encountered so far.
        // cumValueSum: Tracks the cumulative cost of moving those balls.
        int cumValue = 0;
        int cumValueSum = 0;

        // Create an array to store the result (minimum operations for each position).
        int[] ans = new int[boxes.length()];

        // First pass: Calculate the contribution of balls from the left side.
        for (int i = 0; i < boxes.length(); i++) {
            // Add the cumulative cost from the left to the current position.
            ans[i] += cumValueSum;

            // If the current box contains a ball ('1'), increase the ball count.
            cumValue += boxes.charAt(i) == '0' ? 0 : 1;

            // Add the ball count to the cumulative cost for the next position.
            cumValueSum += cumValue;
        }

        // Reset cumValue and cumValueSum for the second pass.
        cumValue = 0;
        cumValueSum = 0;

        // Second pass: Calculate the contribution of balls from the right side.
        for (int i = boxes.length() - 1; i >= 0; i--) {
            // Add the cumulative cost from the right to the current position.
            ans[i] += cumValueSum;

            // If the current box contains a ball ('1'), increase the ball count.
            cumValue += boxes.charAt(i) == '0' ? 0 : 1;

            // Add the ball count to the cumulative cost for the next position.
            cumValueSum += cumValue;
        }

        // Return the result array containing the minimum operations for each position.
        return ans;
    }
}

/*

Brute Force 

class Solution {

    // This method takes a string `boxes` and returns an array `answer` where each
    // element represents the minimum number of moves required to move all balls to
    // the box at the corresponding index.
    public int[] minOperations(String boxes) {
        // Initialize an array to hold the answer, where `answer[i]` will store the minimum
        // number of operations to move all balls to the i-th box.
        int[] answer = new int[boxes.length()];
        
        // Loop over each box to check if it contains a ball ('1').
        for (int currentBox = 0; currentBox < boxes.length(); currentBox++) {
            // If the current box contains a ball ('1'), we need to calculate the number
            // of moves required for every other box.
            if (boxes.charAt(currentBox) == '1') {
                // Loop over every box in the `boxes` string to calculate the number of moves
                // required to move the ball from `currentBox` to `newPosition`.
                for (int newPosition = 0; newPosition < boxes.length(); newPosition++) {
                    // Calculate the absolute distance between the `currentBox` and `newPosition`
                    // and add it to the `answer[newPosition]`. This reflects the cost of moving
                    // the ball from `currentBox` to `newPosition`.
                    answer[newPosition] += Math.abs(newPosition - currentBox);
                }
            }
        }

        // Return the array `answer` which now contains the minimum number of moves required
        // to move all balls to each box position.
        return answer;
    }
}

*/