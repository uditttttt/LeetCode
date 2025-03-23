import java.util.*;  // Importing required Java utility classes

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Create an adjacency list to represent the course dependency graph.
        // Each index (course) in `adj` stores a list of courses that depend on it.
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());  // Initialize each course with an empty list
        }
        
        // Step 2: Create an in-degree array to track the number of prerequisites for each course.
        int[] indeg = new int[numCourses];

        // Step 3: Populate the adjacency list and in-degree array based on prerequisites.
        for (int[] pre : prerequisites) {
            int course = pre[0];  // The course that needs a prerequisite
            int prereq = pre[1];  // The prerequisite course
            adj.get(prereq).add(course);  // Add dependency (prereq -> course)
            indeg[course]++;  // Increment in-degree of 'course' (it has one more prerequisite)
        }

        // Step 4: Initialize the queue with courses that have no prerequisites (in-degree 0).
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) q.add(i);  // Add courses that can be taken immediately
        }

        int count = 0;  // Counter to track the number of courses we can complete

        // Step 5: Perform a BFS (Kahn’s Algorithm for Topological Sorting).
        while (!q.isEmpty()) {
            int curr = q.poll();  // Remove a course from the queue
            count++;  // One more course can be completed

            // Reduce the in-degree of all dependent courses
            for (int next : adj.get(curr)) {  
                indeg[next]--;  // Remove dependency
                if (indeg[next] == 0) q.add(next);  // If no more prerequisites, add to queue
            }
        }

        // Step 6: Check if we were able to process all courses.
        return count == numCourses;  // If count matches numCourses, all can be completed
    }
}

/*

Intuition Behind the Approach
Graph Representation: The problem is about finding if there’s a cycle in a Directed Graph, where nodes represent courses and edges represent prerequisites.

Topological Sorting: We use Kahn’s Algorithm (BFS-based Topological Sorting) to check if all courses can be taken.

Queue Processing: Courses with in-degree = 0 (no prerequisites) can be taken first. When a course is completed, it reduces the prerequisites of dependent courses.

Cycle Detection: If we process all numCourses, it means there is no cycle. If we get stuck (i.e., some courses are left unprocessed), a cycle exists.

**Time Complexity Analysis**  
Building adjacency list: **𝑂(𝑛)**  

Processing all nodes (courses) and edges (dependencies): **𝑂(𝑛+𝑚)**, where **𝑛 = numCourses** and **𝑚 = prerequisites.length**  

Overall Complexity: **𝑂(𝑛+𝑚)**  

Space Complexity: **𝑂(𝑛+𝑚)** (Adjacency list + In-degree array + Queue)  

Why This Works?
This algorithm ensures that we process courses in the correct order. If there’s a cycle, some courses will be left unprocessed. The final check count == numCourses confirms whether we could finish all courses or not.

This is an efficient and simple method to solve the problem using BFS and in-degree tracking. 🚀









*/