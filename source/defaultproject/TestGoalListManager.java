public class TestGoalListManager 
{
	public static void main(String[] args) 
	{
		testGetVisibleGoals();
	}
	
	//CHECK
	public static void testConstructor() 
	{
		GoalListManager manager = new GoalListManager();
		System.out.println(manager.hasGoals());
	}
	
	//CHECK
	public static void testCreateGoal()
	{
		GoalListManager manager = new GoalListManager();
		System.out.println(manager.hasGoals());
		manager.createGoal("GetLife");
		System.out.println(manager.hasGoals());
	}
	
	public static void testGetVisibleGoals()
	{
		GoalListManager manager = new GoalListManager();
		System.out.println(manager.hasGoals());
		manager.createGoal("Transcend");
		manager.createGoal("Help People");
		manager.createGoal("Be Strong");
		System.out.println(manager.hasGoals());
		GoalListManager.Goal[] goals;
		goals = manager.getVisibleGoals();
		for(int i = 0; i < goals.length; i++)
		{
			System.out.println(goals[i].publicGetName());
		}
	}
}