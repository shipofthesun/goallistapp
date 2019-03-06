package work.javaprojects.apps.goallistapp.source.goallistappmain;
//C:\Java\jdk1.8.0_151\work\javaprojects\apps\goallistapp\source\goallistappmain
import work.javaprojects.app.goallistapp.source.list.GoalListManager;
import java.util.Scanner;
import java.io.IOException;

public class GoalListApp 
{
	private static final int MAX_COMMAND_ELEMENTS = 3;
	
	private GoalListManager.Goal[] visibleGoals;
	private GoalListManager listManager;
	
	public static void main(String[] args) throws IOException 
	{	
		GoalListApp program = new GoalListApp();
		program.startProgramLoop();
	}
	
	public void startProgramLoop() 
	{
		listManager = new GoalListManager();
		
		//Main program loop.
		Scanner scanner = new Scanner(System.in);
		String userInput;
		String[] commandElements;
		while(true) {
			//print goals if any
			if(listManager.hasGoals()) 
			{
				printGoals();
			}
			//get user command
			System.out.print("->");
			userInput = scanner.nextLine().trim();
			//parse command and execute
			commandElements = parseCommand(userInput);
			System.out.print(commandElements[0]);
			System.out.print(commandElements[1]);
			switch(commandElements[0]) 
			{
				case "creategoal":		listManager.createGoal(commandElements[1]);	
				case "createsubgoal": 	listManager.createSubGoal(commandElements[1], commandElements[2]);
				case "deletegoal": 		listManager.deleteGoal(commandElements[1]);
				case "cleargoals": 		listManager.deleteAllGoals();
				case "collapseall": 	listManager.collapseAll();
				case "expandall": 		listManager.expandAll();
				case "expandgoal": 		listManager.expandGoal(commandElements[1]);
				case "collapsegoal": 	listManager.collapseGoal(commandElements[1]);
				case "exit":			System.exit(0);
			}
			System.out.println();
			printGoals();
		}
	}
		
	/**
	*	Parse a command into the seperate words of the command.
	*/
	private String[] parseCommand(String command) 
	{
		String[] commandElements = new String[MAX_COMMAND_ELEMENTS];
		int commandElementsPos = 0;
		int startPos = 0;
		int endPos = 0;
		char c;
		while(endPos < command.length() && startPos < command.length() && commandElementsPos < MAX_COMMAND_ELEMENTS) 
		{
			startPos = endPos;
			do 
			{
				c = command.charAt(endPos);
				endPos++;
				if(endPos == command.length()) 
					break;
			} while(c != ' ');
			commandElements[commandElementsPos] = command.substring(startPos,endPos).toLowerCase();
			commandElementsPos++;
			startPos++;
		}
		return commandElements;
	}
	
	/**
	*	Print visible goals.
	*/
	private void printGoals() 
	{
		GoalListManager.Goal[] visibleGoals = listManager.getVisibleGoals();
		System.out.println("Goals: ");
		
		GoalListManager.Goal currentGoal;		//Goal currently being printed.
		String goalName;						//Name of currentGoal.
		String path;							//Path to currentGoal
		int tabs;								//The number of tabs that must be inserted for this goal.
		for(int pos = 0; pos < visibleGoals.length; pos++) 
		{
			currentGoal = visibleGoals[pos];
			goalName = currentGoal.publicGetName();
			path = currentGoal.publicGetPath();
			tabs = currentGoal.getPathSize();
			for(int tabNumber = 0; tabNumber < tabs; tabNumber++) 
			{
				System.out.print("\t");
			}
			System.out.println(path + " " + goalName);
		}
	}
}
		