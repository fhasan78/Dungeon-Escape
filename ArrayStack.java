/**
 * 
 * @author Fatima Hasan  CS1027 - Assignment2 The
 *         class ArrayStack<T> implements StackADT<T> implements a stack using
 *         an array
 * @param <T>
 */

public class ArrayStack<T> implements StackADT<T> {
	// instance variables
	private T[] stack;
	private int top;

	/**
	 * The constructor ArrayStack() creates an empty stack and the initial
	 * capacity of the array is 5.
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		stack = (T[]) new Object[5];
		top = -1;
	}

	/**
	 * The second constructor creates an empty stack and the initial capacity is
	 * the capacity passed through the parameter
	 * 
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack(int initialCapacity) {
		stack = (T[]) new Object[initialCapacity];
		top = -1;
	}

	/**
	 * The method push(T dataItem) throws FullStackException, adds the dataItem
	 * to the top of the stack, and in case the array storing the data items is
	 * full it will increase its capacity depending on its original length
	 */

	@SuppressWarnings("unchecked")
	public void push(T dataItem) throws FullStackException {

		int len = stack.length;
		if (++top == len) {
			// if length is smaller than 20 it will increase by a factor of 2
			T[] largerStack;
			if (stack.length < 20) {
				largerStack = (T[]) new Object[stack.length * 2];
				// otherwise it will increase by 10
			} else if (stack.length >= 20 && stack.length <= 990) {
				largerStack = (T[]) new Object[stack.length + 10];
			}
			// if capacity is more than 1000 an exception will be thrown
			else {
				throw new FullStackException("Stack has capacity of more than a 1000");
			}
			for (int index = 0; index < stack.length; index++) {
				largerStack[index] = stack[index];
			}
			stack = largerStack;
		}

		stack[top] = dataItem; // dataItem is pushed in this statement

	}

	/**
	 * The T pop() throws EmptyStackException method removes and returns the
	 * dataItem at the top of the stack, and in case the stack is empty an
	 * exception will be thrown
	 */
	public T pop() throws EmptyStackException {
		if (!isEmpty()) {
			T poppedItem = (T) stack[top]; // storing the item to be popped so
											// as to not lose it
			stack[top--] = null;
			return poppedItem;
		} else {
			throw new EmptyStackException("Stack is empty!");
		}
	}

	/**
	 * The method T peek() throws EmptyStackException returns the data item at
	 * the top of the stack wihout removing it, and throws an exception if the
	 * stack is empty.
	 */
	public T peek() throws EmptyStackException {
		if (!isEmpty()) {
			return (T) stack[top];
		} else {
			throw new EmptyStackException("Stack is empty!");
		}
	}

	/**
	 * The method isEmpty() returns true if the stack is empty otherwise it
	 * returns boolean true if it's empty false if it's not
	 */
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		}
		return false;
	}

	/**
	 * The method size() returns the number of the data items in the stack
	 * 
	 * @return the size of the stack
	 */
	public int size() {
		return top + 1;
	}

	/**
	 * The method toString() returns a string representation of the stack
	 * 
	 * @return a string representation of the stack
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < top; i++)
			result = result + stack[i].toString() + "\n";
		return result;
	}

}
