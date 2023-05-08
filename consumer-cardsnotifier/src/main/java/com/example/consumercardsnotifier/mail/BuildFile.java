package com.example.consumercardsnotifier.mail;

import com.example.consumercardsnotifier.model.Card;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * - The purpose component is  content different methods to build a file with some format like excel, pdf, etc
 * - In this demo there is just a method to create an excel from a list of cards but could be more.
 */
@Component
public class BuildFile {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * - This  method  create an excel file from a list of cards
     *
     * @param cards List<Card>
     * @param folder String
     * @return String
     */
    public String excelWriter(List<Card> cards, String folder){

        logger.info("####BuildFile.excelWriter:::Creating a mail" );

        String[] columns = {"card", "Card Holder Name"};

        Date dateBuild = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(dateBuild);
        String pathFile  = folder+date+".xlsx";

        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Card");

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        int rowNum = 1;
        for(Card card: cards) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(card.getCard());
            row.createCell(1).setCellValue(card.getCardHolderName());
        }

        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(pathFile);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathFile;
    }
}