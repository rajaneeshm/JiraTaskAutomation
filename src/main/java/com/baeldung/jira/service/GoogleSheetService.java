package com.baeldung.jira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.sheets.v4.Sheets;

@Service("googleSheetService")
public class GoogleSheetService {

    @Autowired
    private Sheets sheetsService;

}
