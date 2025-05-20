/*
🧩 Tip for Recognizing This Pattern
Whenever you are asked to find the minimum cost of some sequence of operations, 
and you're allowed to change the order (associative) — it's likely a DP on 
intervals problem like Matrix Chain Multiplication.

*/

// Recursive method will give TLE

// class Solution {

//     // This function calculates the minimum multiplication cost to multiply matrices from index i to j
//     public static int solve(int arr[] , int i , int j){
//         // Base Case: If there is only one matrix or no matrix, no multiplication is needed
//         // as for single matrix this would not satisfy (arr[i-1] x arr[i]) = (arr[-1] x arr[0])
//         if(i >= j){
//             return 0;
//         }

//         // Initialize min to a large number to find the minimum cost in this function call
//         int min = Integer.MAX_VALUE;

//         // Try placing the bracket at different positions (from i to j-1)
//         for(int k = i; k < j; k++){
//             // Cost of multiplying matrices from i to k
//             int cost1 = solve(arr , i , k);

//             // Cost of multiplying matrices from k+1 to j
//             int cost2 = solve(arr , k+1 , j);

//             // Cost of multiplying the result of the above two parts
//             int costToMultiply = arr[i-1] * arr[k] * arr[j];

//             // Total cost for this split
//             int tempAns = cost1 + cost2 + costToMultiply;
            
//             // or in single line it would be like
//             // int tempAns = solve(arr , i , k) + solve(arr , k+1 , j) + (arr[i-1]*arr[k]*arr[j]);

//             // Update min if this split gives a lesser cost
//             min = Math.min(min , tempAns);
//         }

//         // Return the minimum cost for multiplying matrices from i to j
//         return min;
//     }

//     // Wrapper function to initiate the matrix chain multiplication
//     static int matrixMultiplication(int arr[]) {
//         // Start multiplying from the first matrix (index 1) to the last (index arr.length - 1)
//         // i = 1 to satisy arr[i-1] x arr[i] condition
//         // j = arr.length-1 to satify k+1 condition
//         int i = 1;
//         int j = arr.length - 1;

//         // Call the recursive function to solve the problem
//         return solve(arr , i , j);
//     }
// }


/*

🧠 Step-by-Step Thinking to Reach the MCM Solution
✅ Step 1: Understand the Problem
You're given an array arr[] of length n, where each arr[i] represents the dimension of a matrix.

Matrix A₁ has dimensions arr[0] x arr[1]

Matrix A₂ has dimensions arr[1] x arr[2]

And so on...

You need to find the minimum number of scalar multiplications required to multiply the matrices from A₁ to An-1.

✅ Step 2: Identify Decision Points
Whenever you face a problem like this, ask:

"Where can I divide the problem?"

In MCM, you have to decide:

Where to place the first bracket (split)?

This is the main decision.

✅ Step 3: Try All Possibilities
You try placing the bracket at every possible index between i and j.
Suppose you're multiplying matrices from index i to j, then for every k from i to j-1, you try:

java
Copy
Edit
Cost = solve(i, k) + solve(k+1, j) + (arr[i-1] * arr[k] * arr[j]);
Why this?

solve(i, k): Multiply matrices from i to k

solve(k+1, j): Multiply matrices from k+1 to j

The multiplication cost of these two resulting matrices: arr[i-1] * arr[k] * arr[j]

✅ Step 4: Recursively Solve and Combine
Once you have split at k, solve both halves recursively, and add the cost of multiplying the two resulting matrices.

You do this for every k from i to j-1 and take the minimum cost.

✅ Step 5: Use Recursion First, Then DP
Always start with recursion. Once recursion is working:

Add memoization to avoid repeated work (Top-down DP)

Or write an iterative solution (Bottom-up DP)


🧠 Intuition Behind the Logic
Let’s say you have 4 matrices: A, B, C, D

Their dimensions are: arr = [10, 20, 30, 40, 30]
→ A: 10x20, B: 20x30, C: 30x40, D: 40x30

Now, you can multiply them in different orders, e.g.:

((A×B)×C)×D

(A×(B×C))×D

A×((B×C)×D)

... many more!

Each order gives a different cost because matrix multiplication cost depends on the order:

To multiply a p x q matrix with a q x r matrix → p * q * r operations

So, your goal is to try all possible places to place brackets, and for each:

Solve left and right subproblems

Add cost of combining them

Choose the minimum

🧩 Visual Thought Process
If you are solving from i to j:

css
Copy
Edit
arr[i-1] x arr[i] x ... x arr[j]
Then, try putting a bracket at every k between i and j-1:

css
Copy
Edit
((arr[i-1] x ... x arr[k]) × (arr[k+1] x ... x arr[j]))
For each such split:

Solve left: solve(arr, i, k)

Solve right: solve(arr, k+1, j)

Cost of multiplying the two: arr[i-1] * arr[k] * arr[j]

Then take the minimum of all possible splits.

❓ Why Use arr[i-1] * arr[k] * arr[j]?
Think about this:

solve(arr, i, k) gives you one matrix (left result)

solve(arr, k+1, j) gives you another matrix (right result)

Now you need to multiply those two resulting matrices

But what are their dimensions?

🧩 How to find Dimensions of Resulting Matrices:

The matrix formed by multiplying arr[i] to arr[k] will be of dimension:
arr[i-1] x arr[k]

The matrix formed by multiplying arr[k+1] to arr[j] will be:
arr[k] x arr[j]

So when you multiply these two resulting matrices:

Left = arr[i-1] x arr[k]

Right = arr[k] x arr[j]

Cost = arr[i-1] * arr[k] * arr[j]

✅ That’s why we use:

java
Copy
Edit
arr[i-1] * arr[k] * arr[j]
It reflects the dimensions of the final two matrices we are combining after the recursive calls.




*/

// Memoization method
import java.util.*;
class Solution {

    // Recursive function to calculate minimum number of scalar multiplications
    public static int solve(int arr[], int dp[][], int i, int j){
        
        // Base case: if only one matrix or none, no multiplication needed
        if(i >= j){
            return 0;
        }

        // If we already solved this subproblem, return stored result (memoization)
        if(dp[i][j] != -1) return dp[i][j];
        
        // Initialize minimum cost to a large value
        int min = Integer.MAX_VALUE;

        // Try placing brackets at all possible positions from i to j-1
        for(int k = i; k < j; k++){

            // Recursively solve left and right subproblems
            int leftCost = solve(arr , dp , i , k);       // cost to multiply matrices from i to k
            int rightCost = solve(arr , dp , k+1 , j);    // cost to multiply matrices from k+1 to j

            // Cost to multiply two resulting matrices: 
            // Left matrix dimensions: arr[i-1] x arr[k]
            // Right matrix dimensions: arr[k] x arr[j]
            int multiplyCost = arr[i-1] * arr[k] * arr[j];

            // Total cost = left + right + current multiplication
            int tempAns = leftCost + rightCost + multiplyCost;

            // Take the minimum of all possible splits
            min = Math.min(min , tempAns);
        }
        
        // Store the result in dp table and return it
        return dp[i][j] = min;
    }

    static int matrixMultiplication(int arr[]) {
        // i = 1 and j = n-1 because arr[0] is only used for dimension, not a matrix
        int i = 1;
        int j = arr.length - 1;

        // Create a DP table to store solutions to subproblems
        int dp[][] = new int[101][101];

        // Fill the table with -1 to indicate unsolved subproblems
        for(int[] a : dp){
            Arrays.fill(a , -1);
        }

        // Call the recursive solve function to compute the answer
        return solve(arr , dp , i , j);
    }
}
