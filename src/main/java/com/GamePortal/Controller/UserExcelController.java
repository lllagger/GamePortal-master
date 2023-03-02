package com.GamePortal.Controller;

import com.GamePortal.Service.AbstractServices.IExcelService;
import com.GamePortal.Service.UserExcelService;
import com.GamePortal.Utils.AbstractUtils.IResponceMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class UserExcelController {

    private IExcelService userExcelService;

    public UserExcelController(UserExcelService userExcelService) {
        this.userExcelService = userExcelService;
    }

    @GetMapping("/users/export/excel")
    public void exportUserToExcel(HttpServletResponse response) throws IOException {
        log.info("Users exported to excel from DB.");
        userExcelService.excelExport(response);
    }

    @PostMapping("/user/import/excel")
    public ResponseEntity<IResponceMessage> importUserToDb(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Users imported to DB from excel.");
        return userExcelService.excelImport(file);
    }
}
