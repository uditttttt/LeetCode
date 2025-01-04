Intuition and Approach for the Problem
The task is to count all unique palindromic subsequences of length 3 in the string s. A palindromic subsequence of length 3 has the form a_b_a, where:
•	The first and last characters are the same (a).
•	The middle character (b) can be any character that lies between the first and last occurrences of a.
The goal is to efficiently determine how many such unique triplets exist in the string.
________________________________________
Step-by-Step Approach
1.	Unique Start and End Characters:
o	First, identify all unique characters in the string (a, b, c, etc.) using a HashSet. These are the potential candidates for the start and end characters of the palindromic subsequences.
2.	Finding First and Last Occurrences:
o	For each unique character a:
	Find its first occurrence index st in the string.
	Find its last occurrence index e in the string.
o	If st == e, skip this character because it cannot form a valid palindromic subsequence.
3.	Counting Middle Characters:
o	Between st + 1 and e - 1 (the indices between the first and last occurrences of a), collect all unique characters (b) that can form a_b_a. Use a HashSet for this.
4.	Add to Total Count:
o	The number of unique characters in the middle gives the number of palindromic subsequences for that specific character a.
o	Add this count to a global total.
5.	Return the Result:
o	After processing all unique characters, return the total count.
________________________________________
Detailed Time Complexity Analysis
Let:
•	NNN be the length of the string s.
•	UUU be the number of unique characters in s.
1. Extract Unique Characters:
Using a HashSet, iterate through the string once to store all unique characters.
Time Complexity: O(N)O(N)O(N).
2. Iterate Over Unique Characters:
For each unique character UUU:
•	Find the first occurrence (indexOf) and last occurrence (lastIndexOf) of the character in the string. Each of these operations takes O(N)O(N)O(N) in the worst case.
•	Collect all characters between st + 1 and e - 1 using a loop. In the worst case, this requires traversing the entire string (O(N)O(N)O(N)).
Time Complexity per Unique Character:
Finding first/last index: O(N)O(N)O(N)
Collecting middle characters: O(N)O(N)O(N)
Total per unique character: O(N)O(N)O(N).
Total Time Complexity:
Since this process repeats for each unique character: O(U×N)O(U \times N)O(U×N)
In the worst case, U=26U = 26U=26 (for lowercase alphabets), so the complexity simplifies to: O(26×N)=O(N)O(26 \times N) = O(N)O(26×N)=O(N)
However, for strings with many repeated characters, U≪NU \ll NU≪N, making the algorithm efficient.
________________________________________
Space Complexity
1.	Space for HashSets:
o	set1 to store unique characters: O(U)O(U)O(U).
o	set2 to store middle characters for each iteration: O(N)O(N)O(N) in the worst case.
2.	Auxiliary Space:
o	No additional arrays or data structures apart from the HashSets.
Total Space Complexity: O(N)O(N)O(N) for the intermediate set2.
________________________________________
Summary
1.	Intuition:
o	Identify unique characters as start and end of the palindromic subsequence.
o	Count unique middle characters between first and last occurrences.
2.	Time Complexity: O(U×N)O(U \times N)O(U×N), which is O(N)O(N)O(N) for practical inputs.
3.	Space Complexity: O(N)O(N)O(N).

