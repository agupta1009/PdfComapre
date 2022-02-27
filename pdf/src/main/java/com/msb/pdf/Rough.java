package com.msb.pdf;

import java.io.IOException;
import java.util.List;

import com.graysonnorland.pdfmantis.core.PdfMantis;
import com.graysonnorland.pdfmantis.exception.PdfMantisException;
import com.graysonnorland.pdfmantis.index.object.TextIndex;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.kernel.pdf.layer.PdfLayer;
import com.msb.pdf.DiffTool.Diff;

public class Rough {
	PdfMantis pdfOld, pdfNew;
	
	public Rough(String oldFile, String newFile) throws IOException{
		pdfOld = new PdfMantis(oldFile);
		pdfNew = new PdfMantis(newFile);
	}
	
	public void close() throws IOException{
		pdfOld.closePdf();
		pdfNew.closePdf();
	}
	
	public static List<TextIndex> getIndexList(String filePath, String text) throws IOException, PdfMantisException{
		PdfMantis pdf = new PdfMantis(filePath);
		List<TextIndex> textIndexForPhrase = pdf.getTextIndex().getForString(text);
		pdf.closePdf();
		return textIndexForPhrase;
	}
	
	public static void manipulatePdf(PdfDocument pdfDoc, 
			int pageNum, double x, double y, double width, double height, Color color)
					throws Exception {
		PdfLayer pdflayer = new PdfLayer("main layer", pdfDoc);
		pdflayer.setOn(true);
		
		PdfCanvas canvas = new PdfCanvas(pdfDoc.getPage(pageNum));
		canvas.beginLayer(pdflayer);
		canvas.saveState()
		.setFillColor(color)
		.setExtGState(new PdfExtGState().setFillOpacity(0.5f))
		.rectangle(x, y, width, height)
		.fill()
		.restoreState();
		canvas.endLayer();
		}
	
	public static void highlight(String oldFilePath, String oldFileHighlightedPath,
			String newFilePath, String newFileHighlightedPath, List<Diff> diffs)
					throws Exception{
		
		
		final double ratio = 1.59;
		
		
		PdfReader readerInsert = new PdfReader(newFilePath);
		PdfWriter writerInsert = new PdfWriter(newFileHighlightedPath);
		PdfReader readerDel = new PdfReader(oldFilePath);
		PdfWriter writerDel = new PdfWriter(oldFileHighlightedPath);
		PdfDocument pdfDocInsert = new PdfDocument(readerInsert, writerInsert);
		PdfDocument pdfDocDel = new PdfDocument(readerDel, writerDel);
		
	    for (Diff aDiff : diffs) {
	    	Color color;
	    	List<TextIndex> indexList;
//	    	String src="", dest="";
	    	
	    	switch (aDiff.operation) {
		      case INSERT:
			    	String textPara = aDiff.text;
			    	System.out.println(aDiff);
			    	
			    	String text = textPara.replace("\n", "").trim();
	    		  System.out.println(text);
	    		  System.out.println(111);
		    	  color = ColorConstants.GREEN;
//		    	  System.out.println(text);
//	    		  System.out.println(222);			    	  
		    	  String src = newFilePath;
//		    	  dest = newFileHighlightedPath;
			    	  
			    	  
			  		indexList = getIndexList(src, text);
			  		
					
	    	
	    	
					for(TextIndex t : indexList){
//							System.out.println(t.getPageNumber() + "," + t.getX() + ", " +
//									t.getY() + ", " + t.getWidth() + ", " + t.getHeight());
						 
//						PDPage page = pdfDoc.getPages().get(t.getPageNumber()-1);
//						842-ratio*(t.getY()+ t.getHeight())
						manipulatePdf(pdfDocInsert, t.getPageNumber(), t.getX(),842-ratio*(t.getY()+ t.getHeight()),
								t.getWidth(), t.getHeight(), color);
					}
	
	//					}
						System.out.println("highlighted");
//						break;
//		    	  }
		    	  break;
		    	  
//		      case DELETE:
//		    	  String textPara = aDiff.text;
//			    	System.out.println(aDiff);
////			    	
//		    	  for(String text: lines){
//			    	  color = ColorConstants.RED;
//			    	  System.out.println(text);
//			    	  
//			    	  src = newFilePath;
//			    	  dest = newFileHighlightedPath;
//			    	  
//			    	  
//			  		indexList = getIndexList(src, text);
//					
//						for(TextIndex t : indexList){
////							System.out.println(t.getPageNumber() + "," + t.getX() + ", " +
////									t.getY() + ", " + t.getWidth() + ", " + t.getHeight());
//							 
//	//						PDPage page = pdfDoc.getPages().get(t.getPageNumber()-1);
//	//						842-ratio*(t.getY()+ t.getHeight())
//							manipulatePdf(pdfDocDel, t.getPageNumber(), t.getX(),842-ratio*(t.getY()+ t.getHeight()),
//									t.getWidth(), t.getHeight(), color);
//						}
//		    	  }
		    	
	
	
			    	  
//		    	  break;
				case EQUAL:
					break;
				default:
					break;
				}
	    }
	    
	    pdfDocDel.close();
	    pdfDocInsert.close();
	
	}

	
}