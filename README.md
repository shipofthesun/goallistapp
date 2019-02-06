# GoalsApplicationCommandLine
A java application enabling the creation of a goals list.  This is the command line version.  Sub-goals can be added to main goals in list.  These Sub-goals can also have Sub-goals, etc. to unlimited levels of nesting.
This project is meant mostly to test classes written to implement a goals application where the user can add goals with subgoals which can also have
subgoals to and "infinite" depth.  The application is written in this version for the command line and uses text commands to execute
operations on the goals list.  A GoalsListManager manages the goals list, which is made up of Goal objects which are instances of the
GoalListManager's inner class Goal.  Each goal has a DynamicArrayList of subgoals and contains a pointer to the parent goal (if the parent
goal is null, then the goal is a main goal with no parent).  DynamicArrayList is a class that I wrote instead of using one of Java's built
in data structures.  I did this mainly for some low level practice with arrays and generalized types.  It doubles in size when an attempt
is made to add an item into an index that does not yet exist.  I ran into some problems with generalized types and arrays.  The solution
I went with to solve that is one that I am not sure is ideal, but it did allow the class to compile.  It may cause me problems down the road
however.  One of the more challenging parts of designing the GoalListManager was dealing with deletions and other modifications to the 
goals list.  I used a path system to identify specific goals in the tree of goals. The path is a series of integers, each one representing
in sequence the index of each goal in its parent subgoal.  This allows (hopefully) quick access to specific goals as the indexes are used to quickly traverse 
the array from parent to child until the desired subgoal is reached.  This makes searching the data structure for specific goals unnecessary
as the elements in each sub array are accessed directly using their index.  I also had to find a way to deal with deletions and others changes
to the goal tree by editing paths when necessary to reflect changes to any ancestors of goals that would be affected by a change.
