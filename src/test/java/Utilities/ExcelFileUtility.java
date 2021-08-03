package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility 
{
	//Properties
	private File f;
	private FileInputStream fi;
	private Workbook wb;
	private Sheet sh;
	private FileOutputStream fo;
	
	//Constructor methods
	public ExcelFileUtility (String filepath) throws Exception
	{
		f = new File(filepath);
		fi = new FileInputStream(f);
		wb = WorkbookFactory.create(fi);
		fo = new FileOutputStream(f);
	}
	
	//Operational methods
	public void openSheet(String sheetname)
	{
		sh = wb.getSheet(sheetname);
	}
	
	public int getRowsCount()
	{
		int nour = sh.getPhysicalNumberOfRows();
		return(nour);
	}
	
	public int getColumnCount(int rowindex)
	{
		int nouc = sh.getRow(rowindex).getLastCellNum();
		return(nouc);
	}
	
	public void createResultColumn(int colindex)
	{
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt = new Date();
		//Create results column in first row by default
		Cell rc = sh.getRow(0).createCell(colindex);
		rc.setCellValue(sf.format(dt));
		sh.autoSizeColumn(colindex); //autofit
	}
	
	public String getCellValue(int rowindex , int colindex)
	{
		DataFormatter df = new DataFormatter();
		String value = df.formatCellValue(sh.getRow(rowindex).getCell(colindex));
		return(value);
	}
	
	public void setCellValue(int rowindex ,int colindex ,String value)
	{
		Cell c = sh.getRow(rowindex).createCell(colindex);
		c.setCellValue(value);
		sh.autoSizeColumn(colindex); //autofit
	}
	
	public void saveAndCloseExcel() throws Exception
	{
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();
	}
	
}
