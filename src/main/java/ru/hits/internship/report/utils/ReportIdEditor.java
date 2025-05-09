package ru.hits.internship.report.utils;

import ru.hits.internship.report.models.ReportId;

import java.beans.PropertyEditorSupport;
import java.util.UUID;

public class ReportIdEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        UUID uuid = UUID.fromString(text);
        setValue(new ReportId(uuid));
    }
}
