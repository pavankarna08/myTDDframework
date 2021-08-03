package Utilities;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TextFileUtility 
{
	public static String[] getValueInTextFile(String filepath,int linenumber) throws Exception
	{
		//Access existing text file or csv file ,which consists of text data
		File f = new File(filepath);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		//move up to a line which is the before line for required in sequential text file
		for(int i=1;i<linenumber;i++)
		{
			br.readLine();
		}
		String temp = br.readLine();
		String output[] = temp.split(",");
		br.close();
		fr.close();
		return (output);
	}

}
