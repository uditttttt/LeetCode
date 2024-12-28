class Solution {
    // Recursive function to solve the problem of generating permutations
    public void solve(String ip, String op, List<String> ans) {
        // Base case: when input string is empty
        if (ip.length() == 0) {
            ans.add(op); // Add the current output to the answer list
            return;
        }

        // Check if the first character of the input is a letter
        if (Character.isLetter(ip.charAt(0))) {
            // Create two branches: one with lowercase and one with uppercase
            String op1 = op; // Output string for lowercase branch
            String op2 = op; // Output string for uppercase branch
            
            // Add the lowercase version of the character to op1
            op1 = op1 + Character.toLowerCase(ip.charAt(0));
            // Add the uppercase version of the character to op2
            op2 = op2 + Character.toUpperCase(ip.charAt(0));
            
            // Remove the processed character from the input
            ip = ip.substring(1);
            
            // Recurse on the remaining input for both branches
            solve(ip, op1, ans); // Branch with lowercase
            solve(ip, op2, ans); // Branch with uppercase
        } else {
            // If the character is not a letter (e.g., a digit), no branching is needed
            String op1 = op; // Single output path
            
            // Add the character to the current output
            op1 = op1 + ip.charAt(0);
            
            // Remove the processed character from the input
            ip = ip.substring(1);
            
            // Recurse on the remaining input
            solve(ip, op1, ans);
        }
    }

    // Main function to generate all letter case permutations
    public List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>(); // To store all permutations
        String op = ""; // Initialize the output as an empty string
        String ip = s; // Input string remains unchanged initially
        
        // Start the recursive process
        solve(ip, op, ans);
        
        return ans; // Return the final list of permutations
    }
}

