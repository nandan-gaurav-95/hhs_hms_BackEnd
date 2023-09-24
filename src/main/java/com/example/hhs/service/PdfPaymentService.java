package com.example.hhs.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hhs.model.PaymentVoucher;
import com.example.hhs.repository.PaymentVoucherRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


@Service
public class PdfPaymentService {
	private Logger  logger = LoggerFactory.getLogger(PdfPaymentService.class);
	
	@Autowired
	private PaymentVoucherRepository paymentRepo;
	// List to hold all Students
		

	
	public ByteArrayInputStream createPdf() {
		
		logger.info("Create PDF started ");
		String title="Payment";
//		String content = "Hii I am content ";
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		  Document document = new Document(PageSize.A4);
	
		PdfWriter.getInstance(document, out);
		
		HeaderFooter footer =new HeaderFooter(true, new Phrase("page"));
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorderWidthBottom(0);
		document.setFooter(footer);
		
		
		document.open();
		// Creating a table of 3 columns
		PdfPTable table = new PdfPTable(6);
		// Setting width of table, its columns and spacing
		
				table.setWidthPercentage(100f);
				table.setWidths(new int[] { 1,2,2,2,2,1 });
				table.setSpacingBefore(5);
				
	    // Create Table Cells for table header
				PdfPCell cell = new PdfPCell();
				
		// Setting the background color and padding

				cell.setPadding(5);
		
		Font titleFont =FontFactory.getFont(FontFactory.HELVETICA_BOLD,20);
//		titleFont.setColor(Color.BLUE);
		
		Paragraph titlePara =new Paragraph(title,titleFont);
		titlePara.setAlignment(Element.ALIGN_CENTER);
		
		// Adding headings in the created table cell/ header
				// Adding Cell to table
				cell.setPhrase(new Phrase("ID", titleFont));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Number", titleFont));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Date", titleFont));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Amount", titleFont));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Payment Type", titleFont));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Remark", titleFont));
				table.addCell(cell);
				
				// Iterating over the list of Payment 
				List<PaymentVoucher> paymentVoucher=paymentRepo.findAll();

			
				for (PaymentVoucher payment : paymentVoucher) {
					// Adding student id
					table.addCell(String.valueOf(payment.getId()));
					
					table.addCell(payment.getVoucherNum());
					
					table.addCell(payment.getVoucherDate().toString());
					table.addCell(payment.getAmount().toString());
					table.addCell(payment.getPaymentMethod());
					table.addCell(payment.getRemark());
				}
				
		document.add(titlePara);
//		Font paraFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//		Paragraph paragraph = new Paragraph(content);
//		paragraph.add(new Chunk("This text is Added after creating "));
//		
//		document.add(paragraph);
		
		// Adding the created table to document
		document.add(table);
		document.close();
		
	return new ByteArrayInputStream(out.toByteArray());
	}
	 public byte[] generatePdfById(Long id) {
	        Optional<PaymentVoucher> paymentVoucherOptional = paymentRepo.findById(id);

	        if (paymentVoucherOptional.isPresent()) {
	        	PaymentVoucher paymentVoucher = paymentVoucherOptional.get();

	            // Create a ByteArrayOutputStream to hold the PDF content
	            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	            try {
	                Document document = new Document(PageSize.A4);
	                PdfWriter.getInstance(document, byteArrayOutputStream);

	                document.open();

	                PdfPTable table = new PdfPTable(2);
	                table.setWidthPercentage(100f);
	                table.setSpacingBefore(10);

	                PdfPCell cell = new PdfPCell();
	                cell.setPadding(5);

	                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
	                Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

	                cell.setPhrase(new Phrase("Payment ID", titleFont));
	                table.addCell(cell);
	                table.addCell(new Phrase(String.valueOf(paymentVoucher.getId()), normalFont));

	                cell.setPhrase(new Phrase("VoucherNum", titleFont));
	                table.addCell(cell);
	                table.addCell(new Phrase(paymentVoucher.getVoucherNum(), normalFont));

	                cell.setPhrase(new Phrase("Amount", titleFont));
	                table.addCell(cell);
	                table.addCell(new Phrase(String.valueOf(paymentVoucher.getAmount()), normalFont));

	                cell.setPhrase(new Phrase("PaymentMethod", titleFont));
	                table.addCell(cell);
	                table.addCell(new Phrase(String.valueOf(paymentVoucher.getPaymentMethod()), normalFont));

	                // Add more details as needed
	                cell.setPhrase(new Phrase(" Date", titleFont));
	                table.addCell(cell);
	                table.addCell(new Phrase(String.valueOf(paymentVoucher.getVoucherDate()), normalFont));

	                cell.setPhrase(new Phrase("Remark", titleFont));
	                table.addCell(cell);
	                table.addCell(new Phrase(String.valueOf(paymentVoucher.getRemark()), normalFont));

	                document.add(table);
	                document.close();

	                return byteArrayOutputStream.toByteArray();
	            } catch (Exception e) {
	                e.printStackTrace();
	                return null;
	            }
	        } else {
	            // Handle the case where the expense voucher with the given ID is not found
	            return null;
	        }
	    }
	}