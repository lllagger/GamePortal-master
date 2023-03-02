package com.GamePortal.Dto;

import com.GamePortal.Entity.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserExcelImporterDto {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "User";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<User> excelToUserInformations(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<User> users = new ArrayList<User>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();

                User user = new User();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            user.setUserCredit((int) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            user.setUserName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            user.setUserPassword(currentCell.getStringCellValue());
                            break;
                        case 3:
                            user.setUserRole(currentCell.getStringCellValue());
                        default:
                            break;
                    }
                    cellIdx++;
                }
                users.add(user);
            }
            workbook.close();

            return users;
        } catch (IOException e) {
            throw new RuntimeException("Excell dosyası yüklenemedi" + e.getMessage());
        }
    }
}
