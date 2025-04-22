package ru.hits.internship.file.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.unit.DataSize;

@UtilityClass
public class FileUtils {
    private static final String BAD_SYMBOLS_PATTERN = "[^а-яА-Яa-zA-Z0-9._-]";

    public String getContentDisposition(String fileName) {
        return "attachment; filename=\"" + fileName + "\"";
    }

    public String extractExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1);
    }

    public String sanitizeOriginalFileName(String originalFileName) {
        if (originalFileName == null || originalFileName.isEmpty()) {
            return "file";
        }
        return originalFileName.replaceAll(BAD_SYMBOLS_PATTERN, "_");
    }

    public long parseFileSize(String text) {
        try {
            DataSize dataSize = DataSize.parse(text);
            return dataSize.toBytes();
        } catch (IllegalArgumentException e) {
            return parseSizeFromString(text);
        }
    }


    public String formatFileSize(long size) {
        if (size < 1024) {
            return size + " B";
        } else if (size < 1024 * 1024) {
            return size / 1024 + " KB";
        } else if (size < 1024 * 1024 * 1024) {
            return size / (1024 * 1024) + " MB";
        } else {
            return size / (1024 * 1024 * 1024) + " GB";
        }
    }

    private long parseSizeFromString(String text) {
        text = text.toUpperCase().trim();

        long sizeValue = Long.parseLong(text.substring(0, text.length() - 2).trim());

        if (text.endsWith("KB")) {
            return sizeValue * 1024;
        } else if (text.endsWith("MB")) {
            return sizeValue * 1024 * 1024;
        } else if (text.endsWith("GB")) {
            return sizeValue * 1024 * 1024 * 1024;
        } else if (text.endsWith("TB")) {
            return sizeValue * 1024 * 1024 * 1024 * 1024;
        } else {
            try {
                return Long.parseLong(text);
            } catch (NumberFormatException e) {
                return 1024L * 1024 * 1024;
            }
        }
    }
}
