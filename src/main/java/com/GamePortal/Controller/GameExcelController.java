package com.GamePortal.Controller;

import com.GamePortal.Service.AbstractServices.IExcelService;
import com.GamePortal.Service.GameExcelService;
import com.GamePortal.Utils.AbstractUtils.IResponceMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class GameExcelController {

    private IExcelService gameExcelService;

    public GameExcelController(GameExcelService gameExcelService) {
        this.gameExcelService = gameExcelService;
    }

    @GetMapping("/games/export/excel")
    public void exportGameToExcel(HttpServletResponse response) throws IOException {
        log.info("Games exported to excel from DB.");
        gameExcelService.excelExport(response);
    }

    @PostMapping("/games/import/excel")
    public ResponseEntity<IResponceMessage> importGameToDb(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Games imported to DB from excel.");
        return gameExcelService.excelImport(file);
    }
}
