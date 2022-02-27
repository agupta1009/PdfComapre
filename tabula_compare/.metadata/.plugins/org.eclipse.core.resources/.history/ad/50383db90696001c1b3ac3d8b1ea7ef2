package com.msb.pdf;

import java.util.LinkedList;

import com.msb.pdf.DiffTool.Diff;

public class PdfCompareText {

	public static LinkedList<Diff> compare(String oldFile,String newFile){
		String oldText = ReadPdf.read(oldFile);
		String newText = ReadPdf.read(newFile);
		
		DiffTool differ = new DiffTool();
//		differ.setDiff_Timeout(2.0f);
		
		LinkedList<Diff> diffList = differ.diff_main(oldText, newText);
		differ.diff_cleanupSemantic(diffList);
		return diffList;
		
		
		
//		String diffHtml = differ.diff_prettyHtml(diffList);
//		return diffHtml;
	}
}
