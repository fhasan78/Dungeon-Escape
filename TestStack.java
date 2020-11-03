public class TestStack {

	public static void main(String[] args) {
		boolean testPassed = true;
		Integer val = null;

		ArrayStack<Integer> s = new ArrayStack<Integer>(10);

		// Test 1. Small stack, test push and peek
		try {
			for (int i = 0; i < 11; ++i)
				s.push(new Integer(i));

			val = s.peek();
			if (val.intValue() == 10 && s.size() == 11)
				System.out.println("Test 1 passed");
			else
				System.out.println("Test 1 failed");
		} catch (Exception e) {
			System.out.println("Test 1 failed");
		}

		// Test 2. Small stack. Test pop.
		try {
			for (int i = 0; i < 5; ++i)
				val = s.pop();
			if (val.intValue() == 6 && !s.isEmpty())
				System.out.println("Test 2 passed");
			else
				System.out.println("Test 2 failed");
		} catch (Exception e) {
			System.out.println("Test 2 failed");
		}

		// Test 3. Pop on an empty stack
		try {
			for (int i = 0; i < 10; ++i)
				val = s.pop();
		} catch (EmptyStackException e) {
			System.out.println("Test 3 passed");
		} catch (Exception e) {
			System.out.println("Test 3 failed");
		}

		// Test 5. Test upper bound on size of stack
		ArrayStack<String> stack = new ArrayStack<String>(991);

		try {
			// Size of stack should be increased to 1001, triggering an
			// exception
			for (int i = 0; i < 993; ++i)
				stack.push("" + i);
			testPassed = false;
		} catch (FullStackException e) {
			testPassed = true;
		} catch (Exception e) {
			testPassed = false;
		}

		if (!testPassed)
			System.out.println("Test 4 failed");
		else {
			try {
				stack = new ArrayStack<String>(502);
				// Size of the stack should be increased to 512, not throwing an
				// exception
				for (int i = 0; i < 505; ++i)
					stack.push("" + i);
				System.out.println("Test 4 passed");
			} catch (Exception e) {
				System.out.println("Test 4 failed");
			}
		}

		// Test 5. Large stack, test push, pop, size
		testPassed = true;
		try {
			s = new ArrayStack<Integer>();
			for (int i = 0; i < 1000; ++i)
				s.push(new Integer(i));

			if (s.size() != 1000)
				testPassed = false;
			for (int i = 999; i >= 0; --i) {
				val = s.pop();
				if (val.intValue() != i) {
					testPassed = false;
					break;
				}
			}
			if (testPassed)
				System.out.println("Test 5 passed");
			else
				System.out.println("Test 5 failed");
		} catch (Exception e) {
			System.out.println("Test 5 failed");
		}

	}

}