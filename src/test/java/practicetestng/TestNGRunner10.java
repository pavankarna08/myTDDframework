package practicetestng;

import org.testng.annotations.Factory;

public class TestNGRunner10 
{
	@Factory
	public Object[] method()
	{
		//create an object to runner classes to be executed
		TestNGRunner2 obj1 = new TestNGRunner2();
		TestNGRunner3 obj2 = new TestNGRunner3();
		//store runner class objects in ana array
		Object[] tests = new Object[2];
		tests[0] = obj1;
		tests[1] = obj2;
		//return that array to testng to run those runner classes sequentially
		return(tests);
	}

}
