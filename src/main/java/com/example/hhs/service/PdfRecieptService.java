package com.example.hhs.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.hhs.model.ReceiptVoucher;
import com.example.hhs.repository.ReceiptVoucherRepository;
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
public class PdfRecieptService {
	private Logger logger = LoggerFactory.getLogger(PdfRecieptService.class);

    @Autowired
    private ReceiptVoucherRepository receiptRepo;

    public ByteArrayInputStream createPdf() {
        logger.info("Create PDF started");
        String title = "Receipt";

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, out);

            HeaderFooter footer = new HeaderFooter(true, new Phrase("page"));
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setBorderWidthBottom(0);
            document.setFooter(footer);

            document.open();

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100f);
            table.setWidths(new float[] { 1, 2, 2, 2, 2, 2 }); // Adjust column widths as needed
            table.setSpacingBefore(10);

            PdfPCell cell = new PdfPCell();
            cell.setPadding(5);

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph titlePara = new Paragraph(title, titleFont);
            titlePara.setAlignment(Element.ALIGN_CENTER);

            cell.setPhrase(new Phrase("ID", titleFont));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Voucher Number", titleFont));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Voucher Date", titleFont));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Amount", titleFont));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Payment Method", titleFont));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Remark", titleFont));
            table.addCell(cell);
            
            List<ReceiptVoucher> ReceiptVouchers = receiptRepo.findAll();

            for (ReceiptVoucher receipt : ReceiptVouchers) {
            table.addCell(String.valueOf(receipt.getId()));
            table.addCell(receipt.getVoucherNum());
            table.addCell(receipt.getVoucherDate().toString());
            table.addCell(String.valueOf(receipt.getAmount()));
            table.addCell(receipt.getPaymentMethod());
            table.addCell(receipt.getRemark());
            }
            document.add(titlePara);
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public byte[] generatePdfById(Long id) {
        Optional<ReceiptVoucher> receiptVoucherOptional = receiptRepo.findById(id);

        if (receiptVoucherOptional.isPresent()) {
            ReceiptVoucher receiptVoucher = receiptVoucherOptional.get();

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

                cell.setPhrase(new Phrase("Receipt ID", titleFont));
                table.addCell(cell);
                table.addCell(new Phrase(String.valueOf(receiptVoucher.getId()), normalFont));

                cell.setPhrase(new Phrase("Payment Method", titleFont));
                table.addCell(cell);
                table.addCell(new Phrase(receiptVoucher.getPaymentMethod(), normalFont));

                cell.setPhrase(new Phrase("Amount", titleFont));
                table.addCell(cell);
                table.addCell(new Phrase(String.valueOf(receiptVoucher.getAmount()), normalFont));

                cell.setPhrase(new Phrase("Voucher Number", titleFont));
                table.addCell(cell);
                table.addCell(new Phrase(String.valueOf(receiptVoucher.getVoucherNum()), normalFont));
                
                cell.setPhrase(new Phrase("Voucher Date", titleFont));
                table.addCell(cell);
                table.addCell(new Phrase(String.valueOf(receiptVoucher.getVoucherDate()), normalFont));
                
                cell.setPhrase(new Phrase("Remark", titleFont));
                table.addCell(cell);
                table.addCell(new Phrase(String.valueOf(receiptVoucher.getRemark()), normalFont));

                document.add(table);
                document.close();

                return byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            // Handle the case where the receipt voucher with the given ID is not found
            return null;
        }
    }
}