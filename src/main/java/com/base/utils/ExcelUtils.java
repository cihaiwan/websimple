package com.base.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

			
public class ExcelUtils {

//	 Map map=new HashMap();  
//	  map.put("a", "a");
//	  List<String> myList=new ArrayList<String>();
//	  myList.add("aa");
//	  myList.add("ab");
//	  myList.add("ac");
//	  map.put("mylist", myList);
	
	
	public static void export( HttpServletResponse response,  HttpServletRequest  request,String xlsPathAndTemplateName,String createFileName,Map<String,Object> data) throws ParsePropertyException, InvalidFormatException{  
	    
		 
		  String basePath = request.getSession().getServletContext().getRealPath("/");  
		  //放置excel模板文件的位置  
		  InputStream is = null;  
		  try {  
		   is = new BufferedInputStream(new FileInputStream(basePath+xlsPathAndTemplateName));  
		  } catch (FileNotFoundException e1) {  
		   e1.printStackTrace();  
		  }  
		  //关联模板  
		  XLSTransformer transformer = new XLSTransformer();  
		  Workbook workBook=transformer.transformXLS(is, data);  
		  //输出excel  
		  saveWorkbook(workBook,response,createFileName);  
		  try {  
		   is.close();  
		  } catch (IOException e) {  
		   e.printStackTrace();  
		  }  
		    
		 }  
		  
		  
		private static void saveWorkbook(Workbook resultWorkbook,HttpServletResponse response,String fileName){  
		    
		  try{  
		   response.setHeader("content-disposition", "attachment; filename="+fileName);  
		      response.setContentType("application/msexcel");  
		   OutputStream os =response.getOutputStream();     
		    resultWorkbook.write(os);     
		   os.flush();     
		    os.close();  
		  }catch (FileNotFoundException e) {  
		  
		   e.printStackTrace();  
		  } catch (IOException e) {  
		   e.printStackTrace();  
		  }  
		             
		 }  
}
