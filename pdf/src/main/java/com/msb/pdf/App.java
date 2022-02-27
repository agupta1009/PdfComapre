package com.msb.pdf;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

import com.graysonnorland.pdfmantis.exception.PdfMantisException;
import com.msb.pdf.DiffTool.Diff;

public class App {
    public static void main( String[] args ) throws java.io.IOException{
    	String oldFile = "C:/Users/ankush.gupta/Desktop/old.pdf";
    	String newFile = "C:/Users/ankush.gupta/Desktop/new.pdf";

    	String oldFileHighlighted =  "C:/Users/ankush.gupta/Desktop/old_highlight.pdf";
    	String newFileHighlighted = "C:/Users/ankush.gupta/Desktop/new_highlight.pdf";
    	
    	LinkedList<Diff> diffs = PdfCompareText.compare(oldFile, newFile);
//    	for(Diff adiff: diffs){
//    		System.out.println(adiff.text);
//    	}
//    	
    	
    	
//    	WritePdf.write(oldFileHighlighted, newFileHighlighted, diffs);
    	
    	//******************************************
    	
    	File file=new File("C:/Users/ankush.gupta/Desktop/diff.txt");
    	FileWriter f=new FileWriter(file,true);
    	for(Diff c:diffs) {
    		
    		System.out.println(c);
    		f.write(c.text);
    		f.write("\t");
    		f.write(c.operation.name());
    		f.write("\n");

    	
    	}
    	f.close();
    	
    	
    	try {
			TextCompare.highlight(oldFile, oldFileHighlighted, newFile, newFileHighlighted, diffs);
		} catch (PdfMantisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}