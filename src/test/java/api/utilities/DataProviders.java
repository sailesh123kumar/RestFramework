package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;




public class DataProviders {
	
	@DataProvider(name="Data")
	public String [][] getAlldata() throws IOException{
		
		String path=System.getProperty("user.dir")+"//testData//UserTestData.xlsx";
		ExcelUtilities xl=new ExcelUtilities(path);
		
		int rownum=xl.getRowcount("sheet1");
		int colnum=xl.getcellcount("sheet1", 1);
		
		String apidata[][]=new String[rownum][colnum];
		
		for (int i = 1; i <=rownum; i++) {
			for (int j = 0; j < colnum; j++) {
				apidata[i-1][j]=xl.getcellData("sheet1", i, j);
			}
		}
		
		return apidata;
	}

	
	@DataProvider(name="Username")
	public String[] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//UserTestData.xlsx";
		ExcelUtilities xl=new ExcelUtilities(path);
		
		int rownum=xl.getRowcount("sheet1");
		
		String apidata[]=new String[rownum];
		
		for (int i = 1; i <= rownum; i++) {
			apidata[i-1]=xl.getcellData("sheet1", i, 1);
		}
		
		return apidata;
		
	}
}
