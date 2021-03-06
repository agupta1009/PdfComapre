package tabula;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.kernel.pdf.layer.PdfLayer;
import com.itextpdf.kernel.colors.Color;


public class test {

	public static void work(PdfDocument pdfDoc,String[] arr,Color col)
	{
		 PdfLayer pdflayer = new PdfLayer("main layer", pdfDoc);
		    pdflayer.setOn(true);
		    PdfPage page=pdfDoc.getPage((Integer.parseInt(arr[7])));
		    PdfCanvas canvas = new PdfCanvas(page);
		    canvas.beginLayer(pdflayer);
		    canvas.saveState()
		    .setFillColor(col)
		    .setExtGState(new PdfExtGState().setFillOpacity(0.5f))
		    .rectangle(Float.parseFloat(arr[2]), (page.getArtBox().getHeight()-Float.parseFloat(arr[3])),Float.parseFloat(arr[5]),Float.parseFloat(arr[4]))
		    .fill()
		    .restoreState();
		    canvas.endLayer();
	}
}
