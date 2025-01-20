class Solution {
    
    // Main function that returns a list of lists of strings, where each inner list contains
    // a valid palindrome partition of the input string 's'.
    public List<List<String>> partition(String s) {
        // This will store the final result (a list of palindrome partitions).
        List<List<String>> ans = new ArrayList<>();
        
        // Temporary list to hold the current partition being explored.
        List<String> temp = new ArrayList<>();
        
        // Start solving from index 0.
        solve(s, ans, temp, 0);
        
        // Return the result after exploring all possible partitions.
        return ans;
    }

    // A helper function to perform the backtracking.
    // It recursively explores all possible palindrome partitions of the string.
    public void solve(String s, List<List<String>> ans, List<String> temp, int index) {
        // If we've reached the end of the string, add the current partition to the result list.
        if (index == s.length()) {
            ans.add(new ArrayList<>(temp)); // Create a new list to avoid reference issues.
            return;
        }
        
        // Explore all possible substrings starting from 'index' to the end of the string.
        for (int i = index; i < s.length(); i++) {
            // Check if the substring s[index...i] is a palindrome.
            if (isPalindrome(s, index, i)) {
                // If it's a palindrome, add it to the current partition.
                temp.add(s.substring(index, i + 1));
                
                // Recur to find the next palindrome partition starting from the next index.
                solve(s, ans, temp, i + 1);
                
                // Backtrack: remove the last added palindrome to explore other possibilities.
                temp.remove(temp.size() - 1);
            }
        }
    }

    // Helper function to check if a substring of s starting from 'start' and ending at 'end' is a palindrome.
    public boolean isPalindrome(String s, int start, int end) {
        // While the start index is less than or equal to the end index, check if the characters match.
        while (start <= end) {
            // If the characters don't match, it's not a palindrome.
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        // If all characters match, it's a palindrome.
        return true;
    }
}

