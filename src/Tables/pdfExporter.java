package Tables;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

public class pdfExporter{

    public static Document prototypePDF(String DEST) throws Exception{
        PdfWriter writer = new PdfWriter(new FileOutputStream(DEST));
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);

        //adding a structure format to the document
        doc.add(new Paragraph("BookEz").setTextAlignment(TextAlignment.CENTER).setFontSize(20));
        doc.add(new Paragraph("Bookkeeping made easy").setTextAlignment(TextAlignment.CENTER).setFontSize(15));

        return doc;
    }

    public static void createPDF(JTable roiTable, JTable totalsTable) throws Exception {
        //creating a file chooser dialog to let the user select the destination file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save PDF File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
    
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
    
            String DEST = fileToSave.getAbsolutePath();
            if (!DEST.endsWith(".pdf")) {
                DEST += ".pdf";
            }
    
            Document doc = prototypePDF(DEST);

            // Add the logo to the top left corner of the PDF document
            ImageData logoData = ImageDataFactory.create("src/UI_Formatter/Images/BookEzlogo100px.png");
            Image logoImage = new Image(logoData);
            logoImage.setWidth(100);
            logoImage.setHeight(100);
            doc.add(logoImage.setHorizontalAlignment(HorizontalAlignment.LEFT));

            doc.add(new Paragraph("ROI Table From: " + LocalDate.now().toString()).setTextAlignment(TextAlignment.LEFT).setFontSize(12));
    
            //creating a table for the ROI table in the pdf (modified column length to exclude the checkbox)
            Table roiPdfTable = new Table(UnitValue.createPercentArray(roiTable.getColumnCount() - 1)).useAllAvailableWidth();
    
            //adding headers for the ROI table
            for (int j = 0; j < roiTable.getColumnCount() - 1; j++) {
                Cell headerCell = new Cell();
                Paragraph headerParagraph = new Paragraph(roiTable.getColumnName(j));
                headerCell.add(headerParagraph);
                roiPdfTable.addHeaderCell(headerCell);
            }
    
            //adding data rows for the ROI table
            for (int i = 0; i < roiTable.getRowCount(); i++) {
                for (int j = 0; j < roiTable.getColumnCount() - 1; j++) {
                    Cell cell = new Cell();
                    Paragraph paragraph = new Paragraph(String.valueOf(roiTable.getValueAt(i, j)));
                    cell.add(paragraph);
                    roiPdfTable.addCell(cell);
                }
            }
    
            //creating a table for the totals table in the pdf
            Table totalsPdfTable = new Table(UnitValue.createPercentArray(totalsTable.getColumnCount())).useAllAvailableWidth();
    
            //adding headers for the totals table
            for (int j = 0; j < totalsTable.getColumnCount(); j++) {
                Cell headerCell = new Cell();
                Paragraph headerParagraph = new Paragraph(totalsTable.getColumnName(j));
                headerCell.add(headerParagraph);
                totalsPdfTable.addHeaderCell(headerCell);
            }
    
            //adding data row for the totals table
            for (int j = 0; j < totalsTable.getColumnCount(); j++) {
                Cell cell = new Cell();
                Paragraph paragraph = new Paragraph(String.valueOf(totalsTable.getValueAt(0, j)));
                cell.add(paragraph);
                totalsPdfTable.addCell(cell);
            }
    
            doc.add(roiPdfTable);
            doc.add(new Paragraph().setHeight(10));
            doc.add(totalsPdfTable);
            doc.close();
    
            //open the PDF file after export
            Desktop.getDesktop().open(new File(DEST));
        }
    }
    
}