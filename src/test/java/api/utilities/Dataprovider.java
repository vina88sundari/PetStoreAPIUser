package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataprovider {
	
	XLUtility xl;
	
	@DataProvider(name="Data")
	
	public String[][] getAllData() throws IOException
	

	{
		
		String path =System.getProperty("user.dir")+"\\testdata\\Userdata.xlsx";
		xl=new XLUtility(path);
		
		int rowcount =xl.getRowCount("Sheet1");
		int colcount=xl.getCellCount("Sheet1", 1);
		String apidata[][]=new String[rowcount][colcount];
		
		for (int i=1;i<=rowcount;i++)
			
		{
			for (int j=0;j<colcount;j++)
				
			{
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
		}
		
		return apidata;
	}
	
	@DataProvider(name="UserName")
	
	public String[] getUsernameData() throws IOException
	

	{
		
		String path =System.getProperty("user.dir")+"//testdata//Userdata.xlsx";
		xl=new XLUtility(path);
		
		int rowcount =xl.getRowCount("Sheet1");
	
		String apidata[]=new String[rowcount];
		
		for (int i=1;i<=rowcount;i++)
			
		{
		  apidata[i-1]=xl.getCellData("Sheet1", i,1);
			
		}
		
		return apidata;
	}

}
