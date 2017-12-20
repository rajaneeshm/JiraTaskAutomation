package com.baeldung.jira.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.Get;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.DeleteSheetRequest;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.Spreadsheet;

@Service("googleSheetService")
public class GoogleSheetService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private Sheets sheetsService;

    public void createSpreadSheet(String spreadSheetId) throws IOException {

        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.setSpreadsheetId(spreadSheetId);
        Sheets.Spreadsheets.Create request = sheetsService.spreadsheets().create(spreadsheet);
        Spreadsheet response = request.execute();
        LOGGER.info(response.getSpreadsheetUrl());

    }

    public Get getSpreadSheet(String spreadSheetId) throws IOException {
        return sheetsService.spreadsheets().get(spreadSheetId);
    }

    public BatchUpdateSpreadsheetResponse deleteSpreadSheet(Integer spreadSheetId) throws IOException {
        List<Request> requests = new ArrayList<>();
        requests.add(new Request().setDeleteSheet(new DeleteSheetRequest().setSheetId(spreadSheetId)));
        BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest();
        requestBody.setRequests(requests);
        Sheets.Spreadsheets.BatchUpdate request = sheetsService.spreadsheets().batchUpdate(String.valueOf(spreadSheetId), requestBody);
        LOGGER.info(request.getJsonContent().toString());
        LOGGER.info(request.getUriTemplate());
        LOGGER.info(request.getRequestHeaders().toString());
        LOGGER.info(request.toString());
        BatchUpdateSpreadsheetResponse response = request.execute();
        LOGGER.info(response.toString());
        return response;
    }

}
