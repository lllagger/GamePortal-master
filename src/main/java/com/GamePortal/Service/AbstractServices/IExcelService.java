package com.GamePortal.Service.AbstractServices;

import com.GamePortal.Utils.AbstractUtils.IResponceMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IExcelService {

    ResponseEntity<IResponceMessage> excelImport(MultipartFile file) throws IOException;

    void excelExport(HttpServletResponse response) throws IOException;
}


