# CNT-Programming-Exercise
Interview programming exercise for Cherokee Nation Technologies

* Does the solution work for larger graphs?
My solution should work for any size graph, as long as you have the memory needed to store the sub-paths.

* Can you think of any optimizations?
Due to the greedy nature of my solution, the longest path could be computed while creating or modifying the graph,
but I'd need to change the Vertex class to store their in-edges as well as their out-edges in order to keep the longest
path for each vertex up-to-date for every addEdge call.

* What’s the computational complexity of your solution?
Worst case O(V) where V is all vertices.
Average case O(Vs) where Vs is all vertices connected to the specified source vertex since vertices not connected to the
source are never explored.

* Are there any unusual cases that aren't handled?
Not that I can think of.