import java.util.Stack;

class Solution {
    public String smallestNumber(String pattern) {
        int n = pattern.length();  // Get the length of the pattern

        String num = "";  // This will store the final smallest number
        int counter = 1;  // Start from 1 and increment for each digit

        Stack<Character> s = new Stack<>();  // Stack to temporarily hold numbers for reversing

        // Loop from 0 to n (including n) because we need n+1 digits
        for (int i = 0; i <= n; i++) {
             // s.push((char)(counter + '0'));
            // Convert 'counter' (int) to a character and push it into the stack
            s.push((char)(Integer.toString(counter).charAt(0))); 
            counter++;  // Move to the next number

            // If we reach the end of the pattern OR we find an 'I' (increase)
            if (i == n || pattern.charAt(i) == 'I') {
                // Pop all elements from the stack and add them to 'num'
                while (!s.isEmpty()) {
                    num += s.pop(); // Numbers will be popped in reverse order
                }
            }
        }

        return num; // Return the final smallest number sequence
    }
}
           

/*

💡 Intuition Behind Using a Stack
When we see 'D', we push elements to the stack (delaying their usage).
When we see 'I', we pop elements from the stack, ensuring that the smallest numbers appear first.
This ensures that numbers are always placed in increasing order whenever possible while respecting the 'D' decrease conditions.

*/