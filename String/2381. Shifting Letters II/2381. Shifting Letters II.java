class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        // Step 1: Create a difference array to mark the start and end points of shifts
        int[] diff = new int[s.length()];

        // Step 2: Populate the difference array based on the shifts
        for (int i = 0; i < shifts.length; i++) {
            int st = shifts[i][0]; // Start index of the range
            int e = shifts[i][1]; // End index of the range
            int d = shifts[i][2]; // Direction of the shift (0 for left, 1 for right)

            // Determine the value to add/subtract for the shift
            int x = (d == 0) ? -1 : 1; // Left shift (-1), Right shift (+1)

            // Mark the start of the shift
            diff[st] += x;

            // Mark the end of the shift
            if (e + 1 < diff.length) {
                diff[e + 1] -= x; // Subtract x at the position after the end of the range
            }
        }

        // Step 3: Convert the difference array into a prefix sum array
        // This computes the net effect of all shifts at each position
        for (int i = 1; i < diff.length; i++) {
            diff[i] += diff[i - 1]; // Cumulative sum of the shifts
        }

        // Step 4: Apply the shifts to the characters of the string
        char[] str = s.toCharArray(); // Convert the string to a character array for in-place modification
        for (int i = 0; i < str.length; i++) {
            int shift = diff[i] % 26; // Normalize the shift to the range [0, 25] (length of the alphabet)

            // If the shift is negative, adjust it to ensure it's within [0, 25]
            if (shift < 0) {
                shift += 26; // Adding 26 makes a negative shift wrap correctly
            }

            // Compute the new character by adding the shift and wrapping around
            // 'a' + (current position + shift) % 26 ensures we stay within 'a' to 'z'
            str[i] = (char) ('a' + (str[i] - 'a' + shift) % 26);
        }

        // Step 5: Convert the character array back to a string and return it
        return new String(str);
    }
}


/*

It will give TLE

class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        char[] str = s.toCharArray();
        for (int i = 0; i < shifts.length; i++) {
            int st = shifts[i][0];
            int e = shifts[i][1];
            int d = shifts[i][2];
            for (int j = st; j <= e; j++) {
                if (d == 0) {
                    // Left shift with wrapping
                    // str[j] = str[j] == 'a' ? 'z' : (char) (str[j] - 1);
                    str[j] = str[j] == 'a' ? 'z' : --str[j];
                } else {
                    // Right shift with wrapping
                    // str[j] = str[j] == 'z' ? 'a' : (char) (str[j] + 1);
                    str[j] = str[j] == 'z' ? 'a' : ++str[j];
                }
            }
        }
        return new String(str);
    }
}


*/