package com.msb.pdf;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;


public class ReadPdf {
  
  public static String read(String file) {
	  
	  StringBuilder sb = new StringBuilder("");
	  try {
	    // PDFReader
	    PdfReader reader = new PdfReader(file);
	    PdfDocument pdfDoc = new PdfDocument(reader);
	    
	    // get the number of pages in PDF
	    int noOfPages = pdfDoc.getNumberOfPages();
	    System.out.println("No. of pages read: " + noOfPages);
	    
	//    System.out.println("Extracted content of PDF---- ");
	    for(int i = 1; i <= noOfPages; i++) {
	      // Extract content of each page
	      String contentOfPage = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i));
	//      System.out.println(contentOfPage );
	      sb.append(contentOfPage);
	    }
	    pdfDoc.close();
	    
	    }catch (IOException e) {
	      System.out.println("Exception occurred " + e.getMessage());
	    }
	  
	  return sb.toString();
	  }
}