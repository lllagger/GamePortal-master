package com.GamePortal.Component;

import com.GamePortal.Service.GameExcelService;
import com.GamePortal.Service.UserExcelService;
import com.GamePortal.Service.ZipExcelFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@Slf4j
@Component
public class ImportExcelToDBArchiveProcess {
    @Value("${game.sheet.name}")
    public String gameSheetName;
    @Value("${user.sheet.name}")
    public String userSheetName;
    @Value("${excel.folder}")
    public String excelFolder;
    @Autowired
    ZipExcelFileService zipExcelFileService;
    @Autowired
    GameExcelService gameExcelService;
    @Autowired
    UserExcelService userExcelService;

    @Scheduled(fixedDelay = 100000, initialDelay = 1000)
    public void importExcelsToDBAndArchiveProcess() throws SQLException {

        String currentFileName = null;
        try {
            File folder = new File(excelFolder);
            for (File excelFile : folder.listFiles()) {
                currentFileName = excelFile.getPath();
                Workbook workbook = new XSSFWorkbook(excelFile);
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet.getSheetName().equals(gameSheetName)) {
                    gameExcelService.excelImport(excelFile);
                } else if (sheet.getSheetName().equals(userSheetName)) {
                    userExcelService.excelImport(excelFile);
                } else {
                    System.out.printf("Wrong Sheet Name. %s, %s%n%n", gameSheetName, userSheetName);
                }
                workbook.close();
                zipExcelFileService.zip(excelFile);
                excelFile.delete();
            }
        } catch (IOException e) {
            log.error(String.format("Excel %s didnt close.", currentFileName));
        } catch (SecurityException se) {
            log.error("Excel didnt delete.");
        } catch (InvalidFormatException ife) {
            log.error("Excel object didnt created.");
        }
    }
    ////Game excel reand and db save process
    //private void importGameExcelToDB(Sheet gameSheet) {
    //    int gameRows = gameSheet.getLastRowNum();
    //    List<Game> games = new ArrayList<>();
    //    for (int r = 1; r <= gameRows; r++) {
    //        Game game = new Game();
    //        XSSFRow gameRow = (XSSFRow) gameSheet.getRow(r);
    //        game.setGameCost((int) gameRow.getCell(0).getNumericCellValue());
    //        game.setGameName(gameRow.getCell(1).getStringCellValue());
    //        games.add(game);
    //    }
    //    gameRepository.saveAll(games);
    //    System.out.println("Oyun bilgileri veri tabanına kaydedildi.");
    //}
//
    ////User excel read and db save process
    //private void importUserExcelToDB(Sheet userSheet) {
    //    int userRows = userSheet.getLastRowNum();
    //    List<User> users = new ArrayList<>();
    //    for (int r = 1; r <= userRows; r++) {
    //        User user = new User();
    //        XSSFRow userRow = (XSSFRow) userSheet.getRow(r);
    //        user.setUserCredit((int) userRow.getCell(0).getNumericCellValue());
    //        user.setUserName(userRow.getCell(1).getStringCellValue());
    //        user.setUserPassword(userRow.getCell(2).getStringCellValue());
    //        user.setUserRole(userRow.getCell(3).getStringCellValue());
    //        users.add(user);
    //    }
    //    userRepository.saveAll(users);
    //    System.out.println("User bilgileri veri tabanına kaydedildi.");
    //}
}