import java.io.IOException;
import java.util.ArrayList;

public class DataDrivenSample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ExcelDataDriven edd = new ExcelDataDriven();
		
		ArrayList<String> data = edd.getData("login");
		
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		
		
		//if data has to be passed to our testcase then write as
		//driver.findElement(By.xpath("data.get(0)"))   if username is present in the 0th index

	}

}
