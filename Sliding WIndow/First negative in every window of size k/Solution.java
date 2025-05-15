import java.util.*;

class Solution {
    static List<Integer> firstNegInt(int arr[], int k) {

        // Start and end pointers for sliding window
        int i = 0;
        int j = 0;

        // Stores all negative elements in the current window
        ArrayList<Integer> neg = new ArrayList<>();

        // Stores the final answer: first negative of each window
        ArrayList<Integer> ans = new ArrayList<>();

        // Traverse the array using j as the right end of the window
        while (j < arr.length) {

            // If current element is negative, add it to the neg list
            if (arr[j] < 0) {
                neg.add(arr[j]);
            }

            // Check if the current window size is equal to k
            if (j - i + 1 == k) {

                // If neg list is empty, no negative element in window ➝ add 0
                if (neg.isEmpty()) {
                    ans.add(0);
                } else {
                    // Else, first negative element of current window is at front of neg list
                    ans.add(neg.get(0));
                }

                // Before sliding the window, check if the element at i is negative
                // If yes, and it's the same as first in neg list, remove it
                if (arr[i] < 0) {
                    neg.remove(0); // remove from neg list since it's going out of the window
                }

                // Slide the window forward
                i++;  // move start of window
            }

            // Always move the end of window
            j++;
        }

        // Return the list of first negatives for each window
        return ans;
    }
}

/*

✅ Intuition & Thought Process:
What type of problem is this?

You’re checking all subarrays of size k ➝ use Sliding Window (Fixed Size)

What do we care about in each window?

The first negative number (if it exists)

What should we track while sliding the window?

All negative numbers in the current window ➝ use a list or queue to store them

When we slide the window, if the outgoing element is the same as the first negative, remove it

Goal: As we move window forward, maintain list of negative numbers that are in current window.


*/