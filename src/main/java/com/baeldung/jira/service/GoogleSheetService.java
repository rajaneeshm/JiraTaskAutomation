package com.baeldung.jira.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Data;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.Get;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.Values.Update;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.DeleteSheetRequest;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

@Service("googleSheetService")
public class GoogleSheetService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private Sheets sheetsService;

    public Spreadsheet createSpreadSheet(String spreadSheetId) throws IOException {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.setSpreadsheetId(spreadSheetId);
        Sheets.Spreadsheets.Create request = sheetsService.spreadsheets().create(spreadsheet);
        Spreadsheet response = request.execute();
        LOGGER.info("Sread sheet ID ::::  " + response.getSpreadsheetId());
        return response;
    }

    public UpdateValuesResponse writeData(List<Data> myData, String spreadsheetId) throws IOException {
        List<Object> list1 = new ArrayList<Object>() {
            {
                add("Item");
                add("Item");
                add("Item");
                add("Item");
            }
        };
        List<Object> list2 = new ArrayList<Object>() {
            {
                add("Item");
                add("Item");
                add("Item");
                add("Item");
            }
        };
        List<Object> list3 = new ArrayList<Object>() {
            {
                add("Item");
                add("Item");
                add("Item");
                add("Item");
            }
        };
        List<Object> list4 = new ArrayList<Object>() {
            {
                add("Item");
                add("Item");
                add("Item");
                add("Item");
            }
        };
        List<Object> list5 = new ArrayList<Object>() {
            {
                add("Item");
                add("Item");
                add("Item");
                add("Item");
            }
        };
        List<List<Object>> list = new ArrayList<List<Object>>() {
            {
                add(list1);
                add(list2);
                add(list3);
                add(list4);
                add(list5);
            }
        };

        String range = "Sheet1!A1:D5";
        ValueRange VRange = new ValueRange();
        VRange.setRange(range);
        VRange.setValues(list);
        VRange.setMajorDimension("ROWS");

        Update update = sheetsService.spreadsheets().values().update(spreadsheetId, range, VRange);
        update.setValueInputOption("USER_ENTERED");
        UpdateValuesResponse response = update.execute();
        return response;

    }

    public Spreadsheet getSpreadSheet(String spreadSheetId) throws IOException {
        return sheetsService.spreadsheets().get(spreadSheetId).execute();
    }

    // Delete API is not working
    public BatchUpdateSpreadsheetResponse deleteSpreadSheet(String spreadSheetId) throws IOException {
        List<Request> requests = new ArrayList<>();
        DeleteSheetRequest deleteSheetRequest = new DeleteSheetRequest();
        Spreadsheet spreadsheet = getSpreadSheet(spreadSheetId);
        deleteSheetRequest.set("sheetId", spreadsheet.getProperties().get("sheetId"));
        requests.add(new Request().setDeleteSheet(deleteSheetRequest));
        BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest();
        requestBody.setRequests(requests);
        Sheets.Spreadsheets.BatchUpdate request = sheetsService.spreadsheets().batchUpdate(spreadSheetId, requestBody);
        BatchUpdateSpreadsheetResponse response = request.execute();
        return response;
    }

}
