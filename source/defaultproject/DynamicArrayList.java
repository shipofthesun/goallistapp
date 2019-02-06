//package work.javaprojects.apps.goallistapp.source.dstructs;

import java.util.Arrays;

/**
* Creates a parameterized DynamicArray data structure.  An initial size can be chosen or a default
* size of 10 will be used instead.  When an attempt is made to access an array element
* that is out of bounds, the array contents are copied into a new array whose size
* is the position of the element that was attempted to be accessed mutiplied by 2.
* An attempt to write insert a value that is greater than the size of the array
* an exception will be thrown and now value will be inserted.
**/
public class DynamicArrayList<T> {
	private T[] dArray;			//internal array used for holding elements of any type
	private int listSize;		//current size of list
	
	/**
	*	Constructor which sets the initial size to 10.
	**/
	@SuppressWarnings("unchecked")
	DynamicArrayList() {
		dArray = (T[])new Object[10];
		listSize = 0;
	}
	
	/**
	*	Constructor which allows the specification of an initial lenght of dArray.
	**/
	@SuppressWarnings("unchecked")
	DynamicArrayList(int initialLength) {
		dArray = (T[])new Object[initialLength];
		listSize = 0;
	}
	
	/**
	*	Inserts an object T into dArray at specified pos.  The position must be 
	* 	less than the current listSize of the list.
	**/
	public void insert(T t, int pos) throws IndexOutOfBoundsException {
		if(pos > listSize + 1)
		{
			throw new IndexOutOfBoundsException("Index must be the current size of the list or less.");
		}
		if(pos > dArray.length)
		{
			dArray = Arrays.copyOf(dArray,pos * 2);
		}
		dArray[pos] = t;
		listSize++;
	}
	
	/**
	*	Insert an element T as the last element in the array
	*/
	public void insert(T t) {
		insert(t, listSize);
	}
	
	/**
	*	Accepts a DynamicArrayList<T> array which is used to insert multiple items
	* 	into dArray starting at the first null element.
	**/
	public void insertMultiple(DynamicArrayList<T> list)
	{
		for(int dArrayPos = listSize, listPos = 0; dArrayPos < listSize + list.getSize(); dArrayPos++, listPos++) {
			insert(list.getElement(listPos),dArrayPos);
		}
	}
	
	/**
	*	Delete the array element at pos by moving all elements that occur
	* 	later in the array towards the beginning by one position.
	*/
	public void delete(int pos) throws IndexOutOfBoundsException 
	{
		//Return if pos is not in bounds.
		if(pos < 0 || pos > listSize)
		{
			throw new IndexOutOfBoundsException("Index given for deletion was not in bounds");
		}
		//Eliminate item at pos from dArray by moving array items down one to replace it.
		for(int arrayPos = pos; arrayPos < listSize; arrayPos++)
		{
			dArray[arrayPos] = dArray[arrayPos + 1];
		}
		//Decrement listSize.
		listSize--;
	}
	
	/**
	*	Deletes the last element in the array by changing the reported size of the array
	*	to one less than itself.
	*/
	public void delete() throws IndexOutOfBoundsException 
	{
		listSize--;
	}
	
	/**
	*	Returns the array dArray element at position pos (dArray[pos])
	*/
	public T getElement(int pos) throws IndexOutOfBoundsException 
	{
		if(pos < 0 || pos >= listSize)
		{
			throw new IndexOutOfBoundsException("Index must be greater than zero and less than the current size of the list.");
		}
		return dArray[pos];
	}
	
	/**
	*	Returns the current listSize of the array.
	*/
	public int getSize() 
	{
		return listSize;
	}
	
	public int getCapacity() 
	{
		return dArray.length;
	}
	
	/**
	*	Makes a copy of the internal array dArray and returns it to the caller.
	*/
	public T[] toArray() {
		T[] sendArray = Arrays.copyOf(dArray,dArray.length);
		return sendArray;
	}
	
	/**
	* 	Returns a string of all array elements seperated by "|".
	*/
	public String toString() {
		//**Needs to be redone with string builder and ACTUALLY RETURN A STRING
		StringBuilder sb = new StringBuilder();
		for(int pos = 0; pos < dArray.length; pos++) {
			sb.append("| " + pos + ": ");
			if(dArray[pos] == null) {
				sb.append("null");
			}
			else {
				sb.append(dArray[pos]);
			}
			System.out.print(" | ");
		}
		return sb.toString();
	}
}
	
		
	