package Dummy;

import Utilities.TextFileUtility;

public class Dummy2 
{
	public static void main(String[] args) throws Exception 
	{
		String y[] = TextFileUtility.getValueInTextFile(System.getProperty("user.dir")+"\\src\\test\\resources\\datafiles\\logintestdata.txt",4);
		for(int i=0;i<y.length;i++)
		{
			System.out.println(y[i]);
		}

	}

}
