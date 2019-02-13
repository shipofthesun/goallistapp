public class TestDynamicArrayList 
{
	public static void main(String[] args) 
	{
		//testInsert3();
		//testInsertMultiple();
		//testDelete1();
		//testDelete2();
		//testToArray();
		testToString();
	}
	
	//CHECK
	public static void testConstructor1() 
	{
		DynamicArrayList<String> strings = new DynamicArrayList();
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		strings.insert("tits");
		System.out.println(strings.getSize());
		System.out.println(strings.getElement(0));
	}
	
	//CHECK
	public static void testConstructor2() 
	{
		DynamicArrayList<String> strings = new DynamicArrayList(3);
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		strings.insert("tits");
		System.out.println(strings.getSize());
		System.out.println(strings.getElement(0));
	}
	
	//CHECK
	public static void testInsert1() 
	{
		DynamicArrayList<String> strings = new DynamicArrayList();
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		strings.insert("tits");
		System.out.println(strings.getSize());
		System.out.println(strings.getElement(0));
		for(int i = 0; i < 12; i++) 
		{
			System.out.println(i);
			strings.insert("fuck" + i);
		}
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());		
	}
	
	//CHECK
	public static void testInsert2() 
	{
		DynamicArrayList<String> strings = new DynamicArrayList();
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		for(int i = 0; i < 12; i++) 
		{
			System.out.println(i);
			strings.insert("fuck" + i, i);
		}
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());		
	}
	
	public static void testInsert3() 
	{
		DynamicArrayList<String> strings = new DynamicArrayList();
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		for(int i = 0; i < 12; i++) 
		{
			strings.insert("fuck" + i, i);
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		strings.insert("fuck",4);
		strings.insert("fuck",13);
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());		
	}
	
	//CHECK
	public static void testInsertMultiple() 
	{
		DynamicArrayList<String> strings = new DynamicArrayList();
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		for(int i = 0; i < 12; i++) 
		{
			strings.insert("fuck" + i, i);
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		strings.insert("fuck",13);
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		DynamicArrayList<String> strgs = new DynamicArrayList();
		for(int i = 0; i < 12; i++) 
		{
			strgs.insert("shit" + i, i);
		}
		for(int i = 0; i < strgs.getSize(); i++) 
		{
			System.out.println(strgs.getElement(i));
		}
		System.out.println(strgs.getCapacity());
		System.out.println(strgs.getSize());
		
		strings.insertMultiple(strgs);
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strgs.getCapacity());
		System.out.println(strgs.getSize());
	}
	
	//CHECK
	public static void testDelete1() 
	{
		DynamicArrayList<String> strings = new DynamicArrayList();
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		for(int i = 0; i < 12; i++) 
		{
			strings.insert("fuck" + i, i);
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		strings.insert("fuck",13);
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		DynamicArrayList<String> strgs = new DynamicArrayList();
		for(int i = 0; i < 12; i++) 
		{
			strgs.insert("shit" + i, i);
		}
		for(int i = 0; i < strgs.getSize(); i++) 
		{
			System.out.println(strgs.getElement(i));
		}
		System.out.println(strgs.getCapacity());
		System.out.println(strgs.getSize());
		
		strings.insertMultiple(strgs);
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		strings.delete(25);
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
	}
	
	//CHECK
	public static void testDelete2() 
	{
		DynamicArrayList<String> strings = new DynamicArrayList();
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		for(int i = 0; i < 12; i++) 
		{
			strings.insert("fuck" + i, i);
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		strings.insert("fuck",13);
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		DynamicArrayList<String> strgs = new DynamicArrayList();
		for(int i = 0; i < 12; i++) 
		{
			strgs.insert("shit" + i, i);
		}
		for(int i = 0; i < strgs.getSize(); i++) 
		{
			System.out.println(strgs.getElement(i));
		}
		System.out.println(strgs.getCapacity());
		System.out.println(strgs.getSize());
		
		strings.insertMultiple(strgs);
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		strings.delete();
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		strings.delete();
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		strings.delete();
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
	}
	
	public static void testToString() 
	{
		DynamicArrayList<String> strings = new DynamicArrayList();
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		for(int i = 0; i < 12; i++) 
		{
			strings.insert("fuck" + i, i);
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		strings.insert("fuck",13);
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		DynamicArrayList<String> strgs = new DynamicArrayList();
		for(int i = 0; i < 12; i++) 
		{
			strgs.insert("shit" + i, i);
		}
		for(int i = 0; i < strgs.getSize(); i++) 
		{
			System.out.println(strgs.getElement(i));
		}
		System.out.println(strgs.getCapacity());
		System.out.println(strgs.getSize());
		
		strings.insertMultiple(strgs);
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		strings.delete();
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		strings.delete();
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		strings.delete();
		for(int i = 0; i < strings.getSize(); i++) 
		{
			System.out.println(strings.getElement(i));
		}
		System.out.println(strings.getCapacity());
		System.out.println(strings.getSize());
		
		String contents = strings.toString();
		System.out.println(contents);
	}
		
}
		
		