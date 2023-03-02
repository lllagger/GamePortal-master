package com.GamePortal.Service;

import com.GamePortal.Dto.GameExcelImporterDto;
import com.GamePortal.Dto.UserExcelExporterDto;
import com.GamePortal.Dto.UserExcelImporterDto;
import com.GamePortal.Entity.User;
import com.GamePortal.Repository.IUserRepository;
import com.GamePortal.Service.AbstractServices.IExcelService;
import com.GamePortal.Utils.AbstractUtils.IResponceMessage;
import com.GamePortal.Utils.UserResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserExcelService implements IExcelService {

    IUserRepository iUserRepository;

    public UserExcelService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }


    @Override
    public ResponseEntity<IResponceMessage> excelImport(MultipartFile file) throws IOException {
        if (GameExcelImporterDto.hasExcelFormat(file)) {
            List<User> users = UserExcelImporterDto.excelToUserInformations(file.getInputStream());
            iUserRepository.saveAll(users);
            log.warn("Excel okuma gerçekleştirildi.");
            return ResponseEntity.status(HttpStatus.OK).body(new UserResponseMessage("Excel başarılı bir şekilde yüklendi"));
        }
        log.error("Excel okuma servisini kontrol ediniz.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponseMessage("Excel yüklenemedi!"));
    }

    @Override
    public void excelExport(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDataType = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Users" + currentDataType + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<User> listUser = iUserRepository.findAll(Sort.by("userName").ascending());
        UserExcelExporterDto userExcelExporterDto = new UserExcelExporterDto(listUser);
        userExcelExporterDto.export(response);
    }

    public void excelImport(File file) throws IOException {
        List<User> users = UserExcelImporterDto.excelToUserInformations(new FileInputStream(file));
        iUserRepository.saveAll(users);
    }
}
