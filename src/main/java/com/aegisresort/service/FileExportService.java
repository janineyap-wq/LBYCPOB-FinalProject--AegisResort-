package com.aegisresort.service;

import com.aegisresort.model.LostItemEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;

@Service
public class FileExportService {


    public ByteArrayInputStream exportLostItemsToCsv(List<LostItemEntity> items) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PrintWriter writer = new PrintWriter(out)) {
            // Write CSV Header
            writer.println("Item ID,Description,Location Found,Date Logged,Associated Guest,Status");

            // Write Data Rows
            if (items != null) {
                for (LostItemEntity item : items) {
                    writer.printf("%s,\"%s\",\"%s\",%s,%s,%s%n",
                            sanitizeForCsv(item.getItemId()),
                            sanitizeForCsv(item.getDescription()),
                            sanitizeForCsv(item.getLocationFound()),
                            item.getDateLogged(),
                            item.getAssociatedGuestId() != null ? sanitizeForCsv(item.getAssociatedGuestId()) : "UNLINKED",
                            item.isClaimed() ? "CLAIMED" : "UNCLAIMED"
                    );
                }
            }
            writer.flush();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }


    private String sanitizeForCsv(String data) {
        if (data == null) return "";
        return data.replace("\"", "\"\"");
    }
}

