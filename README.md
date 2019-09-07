# maze_depth_first_search
The goal of this assignment was to create an algorithm to solve a maze. 
My solution is an implementation of a Depth First Search Algorithm with some tweaks for the specifics of the project.

The goal of the DFS is to keep following the right most wall through a maze until it hits a dead end. Once it hits a dead 
end, the algorithm backtracks one spot at a time until it can  move in a different direction than it went the last time.
It also keeps track of all the points its visited, so that it can determine its previous paths. 
