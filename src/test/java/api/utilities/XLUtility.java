package api.utilities;

	import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class XLUtility {
		
		public FileInputStream fi;
		public FileOutputStream fo;
		public XSSFWorkbook workbook;
		public XSSFSheet sheet;
		public XSSFRow row;
		public XSSFCell cell;
		public CellStyle style;
		String path;
		
		public XLUtility(String path)
		{
			this.path=path;
		}
		
		public int getRowCount(String sheetName) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			int rowcount=sheet.getLastRowNum();
			workbook.close();
			fi.close();
			return rowcount;
		}
		
		public int getCellCount(String sheetName,int rownum) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			int cellcount=row.getLastCellNum();
			workbook.close();
			fi.close();
		    return cellcount;
		}
		
		public String getCellData(String sheetName,int rownum,int colnum) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			cell=row.getCell(colnum);
			
			DataFormatter formatter = new DataFormatter();
				String data;
				try {
					data=formatter.formatCellValue(cell);
				}
				catch(Exception e)
				{
					data="";
				}
				workbook.close();
				fi.close();
				return data;
		}
		/*
		 * public void setCellData(String sheetName,int rownum,int colnum,String data)
		 * throws IOException { File xlfile=new File(path); if(!xlfile.exists()) {
		 * workbook=new XSSFWorkbook(); fo=new FileOutputStream(path);
		 * workbook.write(fo); } fi=new FileInputStream(path); workbook=new
		 * XSSFWorkbook(fi);
		 * 
		 * if(workbook.getSheetIndex(sheetName)==-1) workbook.createSheet(sheetName);
		 * sheet=workbook.getSheet(sheetName);
		 * 
		 * if(sheet.getRow(rownum)==null) sheet.createRow(rownum);
		 * row=sheet.getRow(rownum);
		 * 
		 * cell=row.createCell(colnum); cell.setCellValue(data); fo=new
		 * FileOutputStream(path); workbook.write(fo); workbook.close(); fi.close();
		 * fo.close();
		 * 
		 * }
		 */
		
		public static void main (String[] args) throws IOException
		
		{
			
			String path =System.getProperty("user.dir")+"\\testdata\\Userdata.xlsx";
			XLUtility xl=new XLUtility(path);
			
			System.out.println(path);
			
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
			
		     System.out.println(apidata[0][1]);
		}

	}


