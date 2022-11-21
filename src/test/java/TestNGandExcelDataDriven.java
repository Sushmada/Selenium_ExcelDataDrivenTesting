import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGandExcelDataDriven {

	DataFormatter formatter = new DataFormatter();
	
	@Test(dataProvider="dataDriven")
	public void getDataProvider(String greeting, String communication, String id)
	{
		System.out.println(greeting+communication+id);
	}
	
	@DataProvider(name="dataDriven")
	public Object getData() throws IOException
	{
//		Object[][] data = {{"hello","greetings","123"}, {"bye","message","143"}, {"solo","call","457"}};
//		return data;
		
		FileInputStream fis = new FileInputStream("C:\\Users\\W10-Lenovo\\OneDrive\\Desktop\\Udemy_Selenium\\datadriven.xlsx");
		
		XSSFWorkbook work = new XSSFWorkbook(fis);
		XSSFSheet sheet = work.getSheetAt(1);
		//XSSFSheet sheet = work.getSheet("testNGExcel");      //"WHY SHEET NAME IS NOT WORKING???" should investifate
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		
		Object[][] data = new Object[rowCount-1][colCount];
		for(int i=0; i<rowCount-1; i++)
		{
			row=sheet.getRow(i+1);
			for(int j=0; j<colCount; j++)
			{
				
				data[i][j] = formatter.formatCellValue(row.getCell(j));
			}
		}
		return data;
	}
	
}
