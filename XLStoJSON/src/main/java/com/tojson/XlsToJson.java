package com.tojson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
public class XlsToJson {
	
	public static void main(String args[]) throws IOException  
	{  
	
		//obtaining input bytes from a file  
	FileInputStream fis=new FileInputStream(new File("SampleXLS.xls"));  
	//creating workbook instance that refers to .xls file  
	
	Workbook workbook = new HSSFWorkbook(fis);
    Sheet firstSheet = workbook.getSheetAt(0);
    FormulaEvaluator formulaEvaluator=workbook.getCreationHelper().createFormulaEvaluator(); 
    Iterator<Row> iterator = firstSheet.iterator();
    List<String> list= new ArrayList<String>();
    
    Row Row1 = iterator.next();
    Iterator<Cell> cellIterator1 = Row1.cellIterator();
    int count =0;
    try {
    	FileWriter myWriter = new FileWriter("XSLtoJson.txt");
    	while (cellIterator1.hasNext()) {
            Cell cell = cellIterator1.next();
             
            switch (cell.getCellType()) {
                case STRING:
                	list.add(cell.getStringCellValue());
                    //System.out.print("List added: "+cell.getStringCellValue());
                    break;
                
            }
            //System.out.print("  ");
        }
        //System.out.println("\n"+list.size()); 
    
    System.out.println("[\n");
    myWriter.write("[\n");   
    while (iterator.hasNext()) {
    	Row nextRow = iterator.next();
    	if(nextRow.cellIterator().next().getCellType()!= CellType.BLANK) {
    	if (count!=0) {
    		System.out.println("  ,");
    		myWriter.write("  ,");
    	}else {}
        
        
        
        Iterator<Cell> cellIterator = nextRow.cellIterator();
       
        System.out.println("  {\n");
        myWriter.write("  {\n");
        for(int i = 0 ; i< list.size(); i++) {
        	
        	Cell cell = cellIterator.next();
            
            
            switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                case STRING:
                    System.out.println("     "+'"'+list.get(i)+'"'+":"+'"'+cell.getStringCellValue()+'"');
                    myWriter.write("     "+'"'+list.get(i)+'"'+":"+'"'+cell.getStringCellValue()+'"');
                    break;
                case BOOLEAN:
                    System.out.println("     "+'"'+list.get(i)+'"'+":"+'"'+cell.getBooleanCellValue()+'"');
                    myWriter.write("     "+'"'+list.get(i)+'"'+":"+'"'+cell.getBooleanCellValue()+'"');
                    break;
                case NUMERIC:
                    if(i==0){System.out.println("     "+'"'+list.get(i)+'"'+":"+'"'+(int)cell.getNumericCellValue()+'"');
                    myWriter.write("     "+'"'+list.get(i)+'"'+":"+'"'+(int)cell.getNumericCellValue()+'"');}else {
                    	System.out.println("     "+'"'+list.get(i)+'"'+":"+'"'+cell.getNumericCellValue()+'"');
                        myWriter.write("     "+'"'+list.get(i)+'"'+":"+'"'+cell.getNumericCellValue()+'"');
                    	
                    }
                    
                    break;
                case BLANK:
                	break;
            }
            System.out.print("  ");
            myWriter.write("  ");
       } 
        count +=1;
        System.out.println(" }\n"); 
        myWriter.write(" }\n"); 
    }else {}
    
    
    }
    System.out.println("]\n");
    myWriter.write("]\n");	
    }catch(IOException e) {
		 System.out.println("An error occurred.");
		 e.printStackTrace();
		 }
    
    workbook.close();
    fis.close();
    System.out.print("count:"+count);
}}
