package tabula;

//import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.kernel.pdf.layer.PdfLayer;
import com.itextpdf.kernel.colors.Color;

public class color {

//	@SuppressWarnings("deprecation")
	public static void run() throws IOException {
		// TODO Auto-generated method stub
		String filename="C:/Users/ankush.gupta/Desktop/cordinates.pdf";
//		String filepath="C:/Users/ankush.gupta/Desktop/new.pdf";
//		String save="C:/Users/ankush.gupta/Desktop/Modified1.pdf";
	    PdfDocument pdfDoc1 = new PdfDocument(new PdfReader(new File("C:/Users/ankush.gupta/Desktop/new.pdf")), new PdfWriter(new File("C:/Users/ankush.gupta/Desktop/Modified1.pdf")));
	    PdfDocument pdfDoc2 = new PdfDocument(new PdfReader(new File("C:/Users/ankush.gupta/Desktop/old.pdf")), new PdfWriter(new File("C:/Users/ankush.gupta/Desktop/Original1.pdf")));

		Scanner sc=new Scanner(new File(filename));
		while(sc.hasNextLine())
		{
			String s=sc.nextLine();
			String arr[]=s.split(" ");
//			String filepath="";
//			String save="";
//			Color col;
			Color c;
			
			if(arr[1].equals("green")) {
//				filepath="C:/Users/ankush.gupta/Desktop/new.pdf";
//				save="C:/Users/ankush.gupta/Desktop/Modified1.pdf";
//				col=new Color(0.0f,1.0f,0.0f,0.2f);
				c=ColorConstants.GREEN;
			    test.work(pdfDoc1, arr,c);

				}
			else
			{
//				filepath="C:/Users/ankush.gupta/Desktop/old.pdf";
//				save="C:/Users/ankush.gupta/Desktop/Original1.pdf";
//				col=new Color(0.8f,0.0f,0.0f,0.5f);
				c=ColorConstants.RED;
			    test.work(pdfDoc2, arr,c);

				}
//			PDDocument document = PDDocument.load(new File(filepath));
//			PDPage page = document.getPage((Integer.parseInt(arr[7]))-1);
//			PDPageContentStream contentStream = new PDPageContentStream(document, page,AppendMode.APPEND,false);
//			contentStream.setNonStrokingColor(col);	
//			contentStream.fillRect(Float.parseFloat(arr[2]), (page.getArtBox().getHeight()-Float.parseFloat(arr[3])),Float.parseFloat(arr[5]),Float.parseFloat(arr[4]));
//			contentStream.close();
//			
//			
//		    document.save(save);
//		    document.close();
//		    pdfDoc.close();
		}
		pdfDoc1.close();
		pdfDoc2.close();
		sc.close();
	}
}

