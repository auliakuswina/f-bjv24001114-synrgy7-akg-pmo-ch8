package com.example.batch7.ch8.utils.itext;

import com.example.batch7.ch8.entity.oauth.User;
import com.example.batch7.ch8.repository.oauth.UserRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@SpringBootApplication
public class PDFCreateExample implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(PDFCreateExample.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createPdf();
    }

    public void createPdf() {
        try {
            // Create Document instance.
            Document document = new Document();

            // Create OutputStream instance.
            OutputStream outputStream =
                    new FileOutputStream(new File("./cdn/TestFile.pdf"));

            // Create PDFWriter instance.
            PdfWriter.getInstance(document, outputStream);

            // Open the document.
            document.open();

            // Fetch data from UserRepository with pagination
            Pageable pageable = PageRequest.of(0, 10); // Fetch first 10 users
            Page<User> usersPage = userRepository.getAllDataPage(pageable);
            List<User> users = usersPage.getContent();

            // Create a table with 2 columns.
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Set Column Titles
            table.addCell("User Name");
            table.addCell("Full Name");

            // Add data to the table
            for (User user : users) {
                table.addCell(user.getUsername());
                table.addCell(user.getFullname());
            }

            // Add the table to the document.
            document.add(table);

            // Close document and outputStream.
            document.close();
            outputStream.close();

            System.out.println("Pdf created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
