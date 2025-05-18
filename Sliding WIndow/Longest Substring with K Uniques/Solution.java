// User function Template for Java

/*
 Hint to self: Use sliding window when problem involves:

Substrings

Length/count

Conditions like "at most", "exactly", etc.

Track Frequencies → Use HashMap
Ask yourself:

What condition am I tracking?
→ ✅ Number of distinct characters

What data structure tracks frequency?
→ ✅ HashMap<Character, Integer>

*/

import java.util.*;
class Solution {
    public int longestkSubstr(String s, int k) {
        // HashMap to store the frequency of characters in the current window
        HashMap<Character , Integer> map = new HashMap<>();
        
        // i: start of the window, j: end of the window
        int i = 0;
        int j = 0;

        int n = s.length();  // total length of string

        // max: store the length of the longest valid substring
        int max = Integer.MIN_VALUE;

        // Start sliding window
        while (j < n) {
            // Get the character at position j
            char c = s.charAt(j);

            // If character not present, insert it with value 0
            // putIfAbsent avoids overwriting existing counts
            map.putIfAbsent(c , 0);

            // Increment the frequency of the current character
            map.put(c , map.get(c) + 1);

            // If window has exactly k distinct characters → valid window
            if (map.size() == k) {
                // Update max length if this window is larger
                max = Math.max(max , j - i + 1);
            }

            // If window has more than k distinct characters → shrink from left
            else if (map.size() > k) {
                // Shrink until we have only k distinct characters
                while (map.size() > k) {
                    // Character at start of the window
                    char c2 = s.charAt(i);

                    // Decrease its frequency
                    map.put(c2 , map.get(c2) - 1);

                    // If frequency becomes 0, remove it from map
                    if (map.get(c2) == 0) {
                        map.remove(c2);
                    }

                    // Move the left pointer ahead
                    i++;
                }
            }

            // Expand the window
            j++;
        }

        // If no valid substring found, return -1
        return max == Integer.MIN_VALUE ? -1 : max;
    }
}

/*
🧠 Intuition:
You’re given a string s and a number k.

You need to slide through the string, checking windows (substrings) with exactly k unique characters.

The moment the window has more than k distinct characters, you shrink it from the left.

Use a HashMap to track frequency of characters in the current window.

This is a Sliding Window + HashMap pattern!

*/