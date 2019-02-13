//package work.javaprojects.apps.goallistapp.source.list;

//import work.javaprojects.apps.goallistapp.source.dstructs.DynamicArrayList;
import java.lang.IllegalArgumentException;
import java.lang.StringBuilder;
import java.lang.NumberFormatException;
import java.lang.IllegalStateException;

public class GoalListManager 
{
	private DynamicArrayList<Goal> goals;			// dynamic array of goals with their attendant subgoals
	private DynamicArrayList<Goal> visibleGoals;	// dynamic array of goals that are currently visible
	
	/**
	*	Constructor to intialize member vars
	*/
	public GoalListManager() 
	{
		goals = new DynamicArrayList<Goal>(10);
		visibleGoals = new DynamicArrayList<Goal>(20);
	}
	
	//private implementation methods
	
	/**
	*	Finds a goal by following the specified path.
	*/
	private Goal findGoal(Path path) 
	{
		Goal currentGoal = goals.getElement(path.getPathElement(0));;
		for(int pathPos = 1; pathPos < path.getSize(); pathPos++) 
		{
			currentGoal = currentGoal.getSubGoal(path.getPathElement(pathPos));
		}
		return currentGoal;	
	}
	
	/**
	*	Traverses through goals in DynamicArray<Goal> goals and inserts all goals whose
	*	parents are currently expanded into the visibleGoalsList.
	*/
	private void fillVisibleGoals() throws IllegalStateException 
	{
		if(goals.getSize() == 0) 
		{
			throw new IllegalStateException("There are currently no goals.");
		}
		//Clear visibleGoals.
		visibleGoals = new DynamicArrayList<Goal>();
		//Insert all goals that should be visible into visibleGoals array.
		Goal currentGoal;
		for(int goalsPos = 0; goalsPos < goals.getSize(); goalsPos++) 
		{
			visibleGoals.insertMultiple(goals.getElement(goalsPos).getSubGoals());
		}
	}
	
	//public getters and setters
	/**
	*	Converts a String representation of a path into a Path object and returns
	* 	the size of that path object.
	*/
	public int getPathSize(String pathString) 
	{
		Path path = convertToPath(pathString);
		return path.getSize();
	}
	
	/**
	*	Returns a boolean value indicating whether or not any goals are currently
	*	in the goals list.
	*/
	public boolean hasGoals() 
	{
		return goals.getSize() > 0;
	}
	
	/**
	*	Set all goals in DynamicArrayList<Goal> goals list and their subgoals to
	*	be collapsed.
	*/
	public void collapseAll() 
	{
		Goal currentGoal;
		for(int goalsPos = 0; goalsPos < goals.getSize(); goalsPos++) 
		{
			currentGoal = goals.getElement(goalsPos);
			currentGoal.collapse();
		}
	}
	
	/**
	*	Set all goals in DynamicArrayList<Goal> goals list and their subgoals to
	*	be expanded.
	*/
	public void expandAll() 
	{
		Goal currentGoal;
		for(int goalsPos = 0; goalsPos < goals.getSize(); goalsPos++) 
		{
			currentGoal = goals.getElement(goalsPos);
			currentGoal.expandAllSubGoals();
		}
	}
	
	/**
	*	Sets the goal specified by path to be expanded.  All parent goals of the
	* 	specified goal must expanded in order for the specified goal to be expanded
	*/
	public void expandGoal(String pathString) throws IllegalArgumentException 
	{
		Path path = convertToPath(pathString);	//pathString converted to Path object
		Goal currentGoal;			//Current target parent Goal
		Path currentPath = new Path();			//Path to current target parent Goal
		//Check to see if all parent paths are expanded before expanding goal specified by path argument.
		currentPath.insertPathElement(path.getPathElement(0)); 
		currentGoal = findGoal(currentPath);
		for(int pathPos = 1; pathPos < path.getSize(); pathPos++) 
		{
			//Exit if one of the parents is not expanded.
			if(!currentGoal.isExpanded()) 
			{
				throw new IllegalArgumentException("All parent paths must be expanded to expand goal specified by path");
			}
			currentPath.insertPathElement(path.getPathElement(pathPos)); 
			currentGoal = findGoal(currentPath);
		}
		//All ancestor goals were expanded; expand the goal at specified path
		currentGoal.expand();
	}
	
	public void collapseGoal(String pathString) 
	{
		Path path = convertToPath(pathString);
		Goal g = findGoal(path);
		g.collapse();
	}
	
	/**
	*	Method returns a DynamicArrayList of type Goal representing all goals
	*	that are currently visible (whose parent is expanded).
	*/
	public Goal[] getVisibleGoals() 
	{
		fillVisibleGoals();
		Goal[] visibleGoalArray = new Goal[visibleGoals.getSize()]
		for(int visibleGoalArrayPos =  0; visibleGoalArrayPos < visibleGoalArray.length; visibleGoalArrayPos++) 
		{
			visibleGoalArray[visibleGoalArrayPos] = visibleGoals.getElement(visibleGoalArrayPos);
		}
		return visibleGoalArray;
	}
	
	//manipulate goals list
	/**
	*	Add a Goal to goals
	*/
	public void createGoal(String goalName) 
	{
		System.out.println("I'm here")
		DynamicArrayList<Integer> path = new DynamicArrayList<Integer>();
		Goal newGoal = new Goal(goalName,null,goals.getSize());
		goals.insert(newGoal);
	}
	
	/**
	*	Creates a subgoal under a specific parent goal.  Requires a path of indices
	*	to locate the desired parent goal.
	*/
	public void createSubGoal(String goalName, String pathString)
	{
		Path path = convertToPath(pathString);
		Goal parentGoal = findGoal(path);
		parentGoal.addSubGoal(goalName);
	}
	
	/**
	*	Converts a string representing a path into a path object by parsing the string
	*	and inserting all extracted Integers into path one at a time.
	*/
	private Path convertToPath(String pathString) throws NumberFormatException
	{
		Path path = new Path();
		char c;		//Holds character currently at positoin pathStringPos of pathString
		for(int pathStringPos = 0; pathStringPos < pathString.length(); pathStringPos++) 
		{
			c = pathString.charAt(pathStringPos);
			if(c != '.') 
			{
				int startPos = pathStringPos;	//The starting position of target path subString
				int endPos = startPos;			//The ending position of target path subString
				String pathPart;				//String holding target path subString	
				int pathElement;				//One Integer object in path
				while(c != '.' && (pathStringPos < pathString.length())) 
				{
					endPos++;
					pathStringPos++;
					c = pathString.charAt(endPos);
				}
				//Get target substring.
				if(pathStringPos == pathString.length()) 
				{
					pathPart = pathString.substring(startPos);
				}
				else
				{
					pathPart = pathString.substring(startPos,endPos);
				}
				//Attempt to convert target subString to Integer; insert at end of path
				//if successful.
				try 
				{
			
					Integer pathSegment = Integer.valueOf(pathPart);
					path.insertPathElement(pathSegment);
				}
				catch(NumberFormatException nfe) 
				{
					throw new NumberFormatException("Invalid path string: " + pathPart);
				}
			}
		}
		return path;
	}
					
					
				
	
	/**
	*	Remove the subgoal indicated by path.  Any sibling goals that are beyond
	* 	it in its parents subGoal array will be moved downward one position.
	*	The paths of all subGoals affected by this change in their parent's position
	*	will need to be recalculated.
	*/
	public void deleteGoal(String pathString) 
	{
		Path path = convertToPath(pathString);
		Goal goal = findGoal(path);
		if(goal.hasParent()) 
		{
			goal.remove();
		}
		else {
			Goal currentGoal;
			for(int goalsPos = path.getPathElement(0); goalsPos < goals.getSize() - 1; goalsPos++) 
			{
				currentGoal = goals.getElement(goalsPos + 1);
				goals.insert(currentGoal, goalsPos);
				currentGoal.changePosition(goalsPos);
			}		
		}
	}
		
	
	/**
	*	Delete all parent goals by changing the size of parentGoalsList to 
	* 	zero
	*/
	public void deleteAllGoals()
	{
		goals = new DynamicArrayList<Goal>();	
		visibleGoals = new DynamicArrayList<Goal>();
	}
	
	/**
	*	Class of objects representing paths to goals (array of indices needed to find
	*	a goal starting at the top most parent goal (main goals of goalList))
	*/
	private class Path 
	{
		private DynamicArrayList<Integer> pathArray;
		
		private Path() 
		{
			pathArray = new DynamicArrayList<Integer>(10);
		}
		
		private Path(Path path) 
		{
			pathArray = new DynamicArrayList<Integer>(path.getSize());
			for(int pathPos = 0; pathPos < path.getSize(); pathPos++) 
			{
				pathArray.insert(path.getPathElement(pathPos));
			}	
		}
		
		//private getters and setters
		private Integer getPathElement(int pos) 
		{
			return pathArray.getElement(pos);
		}
		
		private void insertPathElement(Integer index, int pos) 
		{
			pathArray.insert(index,pos);
		}
		
		private void insertPathElement(Integer index) 
		{
			pathArray.insert(index);
		}
		
		private int getSize() 
		{
			return pathArray.getSize();
		}
		
		public String toString() 
		{
			StringBuilder sb = new StringBuilder(pathArray.getSize());
			for(int pathPos = 0; pathPos < pathArray.getSize(); pathPos++) 
			{
				sb.append(pathArray.getElement(pathPos));
				sb.append('.');
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
	}
			
	/**
	*	Class of objects representing a single goal and its attendant subgoals.
	*/
	public class Goal 
	{
		//goal details
		private String goalName;
		
		//sub goal variables
		private DynamicArrayList<Goal> subGoals;	// Dyanamic array of subgoals
		
		//book keeping
		private Goal parent;
		private Path path;								//path to this object
		private boolean goalExpanded;					//tells whether or not this goal is expanded to show subgoals
		
		/**
		*	Creates a new goal with a path that specifies where it fits in to the 
		*	heirarchy of goals.  A parentPath of null is assumed to mean this goal has no parent.
		*	The goal calculates its own path as path = parentPath + position.
		*	@position This goal's position in parent subGoals array.  Needed to construct a full
		*	path to itself.  
		*/
		public Goal(String name, Goal parentGoal, int position) 
		{
			goalName = name;
			subGoals = new DynamicArrayList<Goal>();
			parent = parentGoal;
			if(parentGoal != null) 
			{
				path = new Path(parentGoal.getPath());
			}
			else 
			{
				path = new Path();
			}
			path.insertPathElement(new Integer(position));
			goalExpanded = true;
		}
		
		// public accessor methods
		public String publicGetName() 
		{
			return getName();
		}
		
		public String publicGetPath() 
		{
			return path.toString();
		}
		
		//**********fix this***************//
		public int getPathSize() 
		{
			int fucku = 2;
			return fucku;
		}
		
		//Private mutator methods.
		/**
		* 	Sets the this goal to be showing subgoals
		*/
		private void expand() 
		{
			goalExpanded = true;
		}
		
		private void expandAllSubGoals() 
		{
			Goal subGoal;
			for(int subGoalsPos = 0; subGoalsPos < subGoals.getSize(); subGoalsPos++) 
			{
				subGoal = subGoals.getElement(subGoalsPos);
				subGoal.expand();
				if(subGoal.getSubGoalCount() > 0) {
					subGoal.expandAllSubGoals();
				}
			}
		}
		
		/**
		*	Sets this goal to no show subgoals.  Also calls collapse on all subgoals.
		*/
		private void collapse() 
		{
			goalExpanded = false;
			if(subGoals.getSize() > 0) 
			{
				Goal subGoal;
				for(int subGoalsPos = 0; subGoalsPos < subGoals.getSize(); subGoalsPos++) 
				{
					subGoal = subGoals.getElement(subGoalsPos);
					subGoal.collapse();
				}
			}
		}
		
		/**
		*	Add subgoal and specify its path based on the previous path.
		*/
		private void addSubGoal(String goalName) 
		{
			Goal subGoal = new Goal(goalName,parent,subGoals.getSize());
			subGoals.insert(subGoal);
		}
		
		private Goal getParent() 
		{
			return parent;
		}
		
		/** 
		* Remove all subgoals from this goal.
		*/
		private void removeAllSubGoals() 
		{
			 subGoals = new DynamicArrayList<Goal>();
		}
		
		//Private accessor methods.
		/**
		*	Returns the goal stored at indicated position
		*/
		private Goal getSubGoal(int pos) throws IllegalArgumentException 
		{
			if((pos >= subGoals.getSize()) || (pos < 0)) 
			{
				throw new IllegalArgumentException("Index is out of range");
			}
			else 
			{
				return subGoals.getElement(pos);
			}
				
		}
		/**
		*	Returns true if this goal has a parent goal.
		*/
		private boolean hasParent() 
		{
			return (parent != null);
		}
		
		/**
		*	Sets the text of the goal to gn
		*/
		private void setName(String gn) 
		{
			goalName = gn;
		}
		
		/**
		* 	Returns the text of the goal goalName
		*/
		private String getName() 
		{
			return goalName;
		}	
		
		private Path getPath() 
		{
			return path;
		}
		
		private String getPathString() 
		{
			return path.toString();
		}
		
		/**
		*	Returns the amount of subGoals of this goal
		*/
		private int getSubGoalCount() 
		{
			return subGoals.getSize();
		}
		
		/**
		*	Returns a DynamicArrayList of this goal's subGoals
		*/
		private DynamicArrayList<Goal> getSubGoals() 
		{
			DynamicArrayList<Goal> currSubGoals = new DynamicArrayList<Goal>();
			currSubGoals.insert(this);
			Goal subGoal;
			for(int subGoalsPos = 0; subGoalsPos < subGoals.getSize(); subGoalsPos++) 
			{
				subGoal = subGoals.getElement(subGoalsPos);
				if((subGoal.isExpanded()) && (subGoal.getSubGoalCount() > 0)) 
				{
					currSubGoals.insertMultiple(subGoal.getSubGoals());
				}
			}
			return currSubGoals;
		}

		/**
		*	Returns boolean value indicating whether this goal is expanded or not
		*/
		private boolean isExpanded() 
		{
			return goalExpanded;
		}
		
		/**
		*	Removes one subgoal element.  The goal must have a parent for this
		*	procedure to complete successfully.
		*/
		private void remove() throws IllegalArgumentException 
		{
			//Make sure this goal has a parent.
			if(parent == null) 
			{
				throw new IllegalArgumentException("Goal must have a parent to use this method");
			}
			
			Goal currentGoal;
			for(int goalsPos = path.getPathElement(0); goalsPos < goals.getSize() - 1; goalsPos++) 
			{
				currentGoal = parent.getSubGoal(goalsPos + 1);
				goals.insert(currentGoal, goalsPos);
				currentGoal.changePosition(goalsPos);
			}
		}
		
		/**
		*	Edits path to reflect a change to its position in parent subGoals list.
		*	Also calls recalculatePath on all subgoals to propogate a change in their
		*	and all theirs subGoals' paths to also reflect the change.
		*/
		private void changePosition(int newPos) 
		{
			path.insertPathElement(newPos, (path.getSize() - 1));
			
			Goal currentGoal;
			for(int subGoalsPos = 0; subGoalsPos < subGoals.getSize(); subGoalsPos++) 
			{
				currentGoal = subGoals.getElement(subGoalsPos);
				currentGoal.recalculatePath();
			}
		}
		
		/**
		*	Recalulates its path by appending its position in the parent array
		* 	to the end of its parent's current path.
		*/
		private void recalculatePath() 
		{
			//get the current position of the goal in parent subGoals list
			Integer position = path.getPathElement(path.getSize());
			/*	Recalculate the path by remaking it and initializing it to the value of its
				parent path and then adding its original position in at the end.*/
			path = new Path(parent.getPath());
			path.insertPathElement(position);
			//recalculate the path of all subgoals
			Goal currentGoal;
			for(int subGoalsPos = 0; subGoalsPos < subGoals.getSize(); subGoalsPos++) 
			{
				currentGoal = subGoals.getElement(subGoalsPos);
				currentGoal.recalculatePath();
			}
		}
	
	}
	
}
		