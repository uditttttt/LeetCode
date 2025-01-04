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


