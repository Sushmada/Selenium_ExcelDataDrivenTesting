import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataDriven {
	
	
	public ArrayList<String> getData(String testcaseName) throws IOException
	{
        ArrayList<String> al = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\W10-Lenovo\\OneDrive\\Desktop\\Udemy_Selenium\\datadriven.xlsx");
		
		XSSFWorkbook work = new XSSFWorkbook(fis);
		int sheetCount = work.getNumberOfSheets();
		
		for(int i=0; i<sheetCount; i++)
		{
			if(work.getSheetName(i).equalsIgnoreCase("datadriven"))
			{
				XSSFSheet sheet = work.getSheetAt(i);
				
				//identify the Test Cases column by scanning the entire 1st row
				
				Iterator<Row> rows = sheet.rowIterator();  //sheet is the collection of rows
				Row firstRow = rows.next();                //first row where headers are present
				Iterator<Cell> cells = firstRow.cellIterator();                  //row is the collection of cells
				
				int k=0;
				int column =0;
				
				while(cells.hasNext())
				{
					Cell cellValue = cells.next();
					if(cellValue.getStringCellValue().equalsIgnoreCase("TestCases"))
					{
						//this is the desired column where our test cases are present 
						column =k;
						
					}
					k++;
				}
				System.out.println(column);
				
				//once the column where the TestCases are present is identitied, iterate through all the rows only in the identified column
				//and search for Purchase testcases
				
				while(rows.hasNext())
				{
					Row desCol = rows.next();
					if(desCol.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
					{
						//after you get the Purchase Testcases, pull all the data of that row and feed it into the testcase
						Iterator<Cell> desRowVal = desCol.cellIterator();
						
						while(desRowVal.hasNext())
						{
							Cell val = desRowVal.next();
							
							if(val.getCellType()==CellType.STRING)
                           {
                        	   al.add(val.getStringCellValue());
	
                           }
							else
							{
								al.add(NumberToTextConverter.toText(val.getNumericCellValue()));
							}
							
						}
					}
				}
				
			}
		}
		return al;

	}


	public static void main(String[] args) throws IOException {
		
	}
}
