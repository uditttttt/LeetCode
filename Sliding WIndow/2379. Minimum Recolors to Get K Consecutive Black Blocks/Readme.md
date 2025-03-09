# Minimum Recolors to Get K Consecutive Black Blocks

## Problem Statement
Given a string `blocks` consisting of characters `'B'` (black) and `'W'` (white), and an integer `k`, find the minimum number of recolors needed to get at least `k` consecutive `'B'` blocks.

## Intuition: When to Use Sliding Window?

### **Step 1: Identify a Subarray/Substring Constraint**
In this problem, we need to find a **continuous substring of length `k`** that requires the minimum recoloring.

- **Key clue:** The problem asks for a **fixed-length** subarray/substring (length `k`), which is a strong indicator that **Sliding Window** might be useful.

### **Step 2: Check if Brute Force is Inefficient**
A simple brute-force approach would involve:
- Checking **every possible substring** of length `k`.
- Counting the number of white (`'W'`) blocks in each substring.
- Returning the minimum count.

#### **Time Complexity of Brute Force:**  
Since there are `n-k+1` possible substrings and checking each one takes `O(k)`, the total complexity would be **O(n × k)**.  
If `n` is large, this approach is slow.

### **Step 3: Think About Repeated Work**
When checking substrings like this:
```plaintext
blocks = "WBWBBBW"
k = 3
```
We check:
- `"WBW"`
- `"BWBB"`
- `"WBBB"`
- `"BBBW"`

Notice how each new substring **mostly overlaps** with the previous one!  
Instead of recomputing everything from scratch, we can use **Sliding Window** to:
1. **Move the window** one step at a time.
2. **Update counts efficiently** instead of recalculating.

### **Step 4: Recognizing the Sliding Window Pattern**
- We keep track of a **fixed-length window (`k`)**.
- We **slide the window one step at a time**, updating our counts.
- Instead of **recounting from scratch**, we update our count by:
  - **Removing the effect of the leftmost element (going out of the window).**
  - **Adding the effect of the rightmost element (newly included).**

This makes the approach **O(n)** instead of **O(n × k)**, which is much faster!

### **General Takeaways for Using Sliding Window**
Use **Sliding Window** when:
✅ You need to find or optimize something in **contiguous subarrays/substrings**.  
✅ The problem involves **fixed-length or variable-length windows**.  
✅ A brute-force approach would repeat a lot of calculations.

