package com.example.hhs.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hhs.model.ExpenseVoucher;
import com.example.hhs.repository.ExpenseVoucherRepository;
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
public class PdfExpenseService {
	private Logger logger = LoggerFactory.getLogger(PdfPaymentService.class);

    @Autowired
    private ExpenseVoucherRepository ExpenseRepo;

    public ByteArrayInputStream createPdf() {
        logger.info("Create PDF started ");
        String title = "Expense";

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
            cell.setPhrase(new Phrase("Number", titleFont));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Date", titleFont));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Amount", titleFont));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Expense Category", titleFont));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Remark", titleFont));
            table.addCell(cell);

            List<ExpenseVoucher> paymentVouchers = ExpenseRepo.findAll();

            for (ExpenseVoucher payment : paymentVouchers) {
                table.addCell(String.valueOf(payment.getId()));
                table.addCell(payment.getVoucherNumber());
                table.addCell(payment.getVoucherDate().toString());
                table.addCell(payment.getAmount().toString());
                table.addCell(payment.getExpenseCategory());
                table.addCell(payment.getRemarks());
            }

            document.add(titlePara);
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
