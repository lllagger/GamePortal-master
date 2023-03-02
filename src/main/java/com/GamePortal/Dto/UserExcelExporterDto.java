package com.GamePortal.Dto;

import com.GamePortal.Entity.Game;
import com.GamePortal.Entity.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserExcelExporterDto {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<User> userList;

    public UserExcelExporterDto(List<User> userList) {
        this.userList = userList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("User");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        createCell(row, 0, "userId", style);
        createCell(row, 1, "userName", style);
        createCell(row, 2, "userCredit", style);
        createCell(row, 3, "userPassword", style);
        createCell(row, 4, "userRole", style);
        createCell(row, 5, "usersGame", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {

        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (User user : userList) {
            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;

            createCell(row, columnCount++, user.getUserId().intValue(), style);
            createCell(row, columnCount++, user.getUserName(), style);
            createCell(row, columnCount++, user.getUserCredit(), style);
            createCell(row, columnCount++, user.getUserPassword(), style);
            // createCell(row, columnCount++, user.getUserRole(), style);

            for (Game game : user.getUserGames()) {
                createCell(row, columnCount++, game.getGameName(), style);
                createCell(row, columnCount++, user.getUserRole(), style);
            }

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}