package com.example.hhs.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hhs.service.PdfRecieptService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PdfReceiptController {
	 @Autowired
	    private PdfRecieptService pdfService;

	    @GetMapping("/receiptgeneratepdf")
	    public ResponseEntity<InputStreamResource> createPdf() {
	        ByteArrayInputStream pdf = pdfService.createPdf();
	        HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.add("Content-Disposition", "inline;filename=hhs-hms.pdf");

	        return ResponseEntity
	                .ok()
	                .headers(httpHeaders)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(pdf));
	    }

	    @GetMapping("/receiptgeneratepdf/{id}")
	    public ResponseEntity<InputStreamResource> generatePdfById(@PathVariable Long id) {
	        // Reuse the PDF generation logic from the service
	        byte[] pdfData = pdfService.generatePdfById(id);

	        if (pdfData != null) {
	            HttpHeaders httpHeaders = new HttpHeaders();
	            httpHeaders.add("Content-Disposition", "inline;filename=hhs-hms.pdf");

	            return ResponseEntity
	                    .ok()
	                    .headers(httpHeaders)
	                    .contentType(MediaType.APPLICATION_PDF)
	                    .body(new InputStreamResource(new ByteArrayInputStream(pdfData)));
	        } else {
	            // Handle the case where data for the given ID is not found
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // Add endpoints for other operations related to ReceiptVoucher
	    // For example, endpoints to create, update, delete, and list ReceiptVouchers
	    // You can use the ReceiptVoucherService to handle these operations.
	}