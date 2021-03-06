package tabula;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.awt.Color;
import java.awt.geom.Point2D;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.kernel.pdf.layer.PdfLayer;

public class main {
	public static String cordinate(RectangularTextContainer c)
	{
		String s="";
		s+=Double.toString(c.getPoints()[3].getX())+" ";
		s+=Double.toString(c.getPoints()[3].getY())+" ";
		s+=Double.toString(c.getBounds2D().getHeight())+" ";
		s+=Double.toString(c.getBounds2D().getWidth());
		return s;
	}
	
	public static void compare(id x,id y) throws IOException
	{
		Table a=x.getTable();
		Table b=y.getTable();
		int min_row=a.getRowCount()>b.getRowCount()?b.getRowCount():a.getRowCount();
		int min_col=a.getColCount()>b.getColCount()?b.getColCount():a.getColCount();
		for(int i=0;i<min_row;i++)
		{
			for(int j=0;j<min_col;j++)
			{
				if(a.getCell(i, j).getText().equals(b.getCell(i, j).getText()))
				{
					continue;
				}
				else
				{
				FileWriter writer=new FileWriter(new File("C:/Users/ankush.gupta/Desktop/cordinates.pdf"),true);
				String s=a.getCell(i, j).getText()+" "+"red"+" "+cordinate(a.getCell(i, j))+" "+x.getFilename()+" "+Integer.toString(x.getPage())+"\n";
				s+=b.getCell(i, j).getText()+" "+"green"+" "+cordinate(b.getCell(i, j))+" "+y.getFilename()+" "+Integer.toString(y.getPage())+" "+"\n";
				writer.write(s);
				writer.close();
				}
			}
		}
		for(int i=min_row;i<a.getRowCount();i++)
		{
			for(int j=0;j<a.getColCount();j++)
			{
				FileWriter writer=new FileWriter(new File("C:/Users/ankush.gupta/Desktop/cordinates.pdf"),true);
				String s=a.getCell(i, j).getText()+" "+"red"+" "+cordinate(a.getCell(i, j))+" "+x.getFilename()+" "+Integer.toString(x.getPage())+"\n";
				writer.write(s);
				writer.close();
			}
		}
		
		for(int i=0;i<a.getRowCount();i++)
		{
			for(int j=min_col;j<a.getColCount();j++)
			{
				FileWriter writer=new FileWriter(new File("C:/Users/ankush.gupta/Desktop/cordinates.pdf"),true);
				String s=a.getCell(i, j).getText()+" "+"red"+" "+cordinate(a.getCell(i, j))+" "+x.getFilename()+" "+Integer.toString(x.getPage())+"\n";
				writer.write(s);
				writer.close();
			}
		}
		for(int i=min_row;i<b.getRowCount();i++)
		{
			for(int j=0;j<b.getColCount();j++)
			{
				FileWriter writer=new FileWriter(new File("C:/Users/ankush.gupta/Desktop/cordinates.pdf"),true);
				String s=b.getCell(i, j).getText()+" "+"green"+" "+cordinate(b.getCell(i, j))+" "+y.getFilename()+" "+Integer.toString(y.getPage())+" "+"\n";
				writer.write(s);
				writer.close();
			}
		}
		
		for(int i=0;i<b.getRowCount();i++)
		{
			for(int j=min_col;j<b.getColCount();j++)
			{
				FileWriter writer=new FileWriter(new File("C:/Users/ankush.gupta/Desktop/cordinates.pdf"),true);
				String s=b.getCell(i, j).getText()+" "+"green"+" "+cordinate(b.getCell(i, j))+" "+y.getFilename()+" "+Integer.toString(y.getPage())+" "+"\n";
				writer.write(s);
				writer.close();
			}
		}
			
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PDDocument pd = PDDocument.load(new File("C:/Users/ankush.gupta/Desktop/Original.pdf"));
		ObjectExtractor oe = new ObjectExtractor(pd);
		SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm(); // Tabula algo.
		int n=pd.getNumberOfPages();
		List<Table> table1=new ArrayList<Table>();
		List<id> l1=new ArrayList<>();
		for(int k=1;k<=n;k++)
		{
			Page page = oe.extract(k); // extract only the ith page
			List<Table> table = sea.extract(page);
			for(Table c:table)	{
				table1.add(c);
				l1.add(new id(c,"Original.pdf",k));
				}
		}
		pd.close();
		oe.close();

		pd=PDDocument.load(new File("C:/Users/ankush.gupta/Desktop/Modified.pdf"));
		
//		for document 2
		
		oe=new ObjectExtractor(pd);
		n=pd.getNumberOfPages();
		List<Table> table2=new ArrayList<Table>();
		List<id> l2=new ArrayList<>();

		for(int k=1;k<=n;k++)
		{
			Page page = oe.extract(k); // extract only the ith page
			List<Table> table = sea.extract(page);
			for(Table c:table) {
				table2.add(c);
				l2.add(new id(c,"Modified.pdf",k));

			}
		}
		pd.close();
		oe.close();

		
		int i=0,j=0;
		for(;i<l1.size()&&j<l2.size();i++,j++)
		{
			id a=l1.get(i);
			id b=l2.get(j);
			compare(a,b);	
		}
		for(int m=i;m<l1.size();m++)
		{
			Table b=l1.get(m).getTable();
			for(int p=0;p<b.getRowCount();p++)
			{
				for(int q=0;q<b.getColCount();q++)
				{

					FileWriter writer=new FileWriter(new File("C:/Users/ankush.gupta/Desktop/cordinates.pdf"),true);
					String s=b.getCell(p, q).getText()+" "+"red"+" "+cordinate(b.getCell(p, q))+" "+l1.get(m).getFilename()+" "+Integer.toString(l1.get(m).getPage())+" "+"\n";
					writer.write(s);
					writer.close();
				}
			}
			
		}
		for(int m=i;m<l2.size();m++)
		{
			Table b=l2.get(m).getTable();
			for(int p=0;p<b.getRowCount();p++)
			{
				for(int q=0;q<b.getColCount();q++)
				{
					

					FileWriter writer=new FileWriter(new File("C:/Users/ankush.gupta/Desktop/cordinates.pdf"),true);
					String s=b.getCell(p, q).getText()+" "+"green"+" "+cordinate(b.getCell(p,q))+" "+l2.get(m).getFilename()+" "+Integer.toString(l2.get(m).getPage())+" "+"\n";
					writer.write(s);
					writer.close();
				}
			}
			
		}
		color.run();
		

	}


}

