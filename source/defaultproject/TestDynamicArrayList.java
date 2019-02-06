public class TestDynamicArrayList 
{
	public static void main(String[] args) 
	{
		testConstructor2();
	}
	
	public static void testConstructor1() {
		DynamicArrayList<String> strings = new DynamicArrayList();
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		strings.insert("tits");
		System.out.println(strings.getSize());
		System.out.println(strings.getElement(0));
	}
	
	public static void testConstructor2() {
		DynamicArrayList<String> strings = new DynamicArrayList(3);
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		strings.insert("tits");
		System.out.println(strings.getSize());
		System.out.println(strings.getElement(0));
	}
	
	
}
		
		