package Dummy;

import Utilities.PropertiesFileUtility;

public class Dummy1 
{
	public static void main(String[] args) throws Exception 
	{
		String value = PropertiesFileUtility.getValueInPropertiesFile("sleep");
		System.out.println(value);

	}

}
