package com.msb.pdf;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.html2pdf.HtmlConverter;
import com.msb.pdf.DiffTool.Diff;

public class WritePdf {
	
	public static void write(String NewDelFilePath, String NewInsertFilePath, List<Diff> diffs) throws FileNotFoundException, IOException{
		ArrayList<String> htmlDelAndInsert = toHtml(diffs);
		
		HtmlConverter.convertToPdf(htmlDelAndInsert.get(0), new FileOutputStream(NewDelFilePath));
		HtmlConverter.convertToPdf(htmlDelAndInsert.get(1), new FileOutputStream(NewInsertFilePath));
		System.out.println("files written.");
	}
	
	private static ArrayList<String> toHtml(List<Diff> diffs) {
		ArrayList<String> arr = new ArrayList<>(2);
	    StringBuilder htmlWithDeletions = new StringBuilder();
	    StringBuilder htmlWithInsertions = new StringBuilder();
	    for (Diff aDiff : diffs) {
	      String text = aDiff.text.replace("&", "&amp;").replace("<", "&lt;").replace('\u00b6', '\n')
	          .replace(">", "&gt;");//.replace("\n", "&para;<br>");
	      switch (aDiff.operation) {
	      case INSERT:
	        htmlWithInsertions.append("<ins style=\"background:#e6ffe6;\">").append(text)
	            .append("</ins>");
	        break;
	      case DELETE:
	        htmlWithDeletions.append("<del style=\"background:#ffe6e6;\">").append(text)
	            .append("</del>");
	        break;
	      case EQUAL:
	        htmlWithDeletions.append("<span>").append(text).append("</span>");
	        htmlWithInsertions.append("<span>").append(text).append("</span>");
	        break;
	      }
	    }
	    arr.add(htmlWithDeletions.toString());
	    arr.add(htmlWithInsertions.toString());
	    
	    return arr;
	  }

}
