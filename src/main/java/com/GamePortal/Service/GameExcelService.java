package com.GamePortal.Service;

import com.GamePortal.Dto.GameExcelExporterDto;
import com.GamePortal.Dto.GameExcelImporterDto;
import com.GamePortal.Entity.Game;
import com.GamePortal.Repository.IGameRepository;
import com.GamePortal.Service.AbstractServices.IExcelService;
import com.GamePortal.Utils.AbstractUtils.IResponceMessage;
import com.GamePortal.Utils.GameResponseMessage;
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
public class GameExcelService implements IExcelService {
    IGameRepository iGameRepository;

    public GameExcelService(IGameRepository iGameRepository) {
        this.iGameRepository = iGameRepository;
    }

    @Override
    public ResponseEntity<IResponceMessage> excelImport(MultipartFile file) throws IOException {
        if (GameExcelImporterDto.hasExcelFormat(file)) {
            List<Game> games = GameExcelImporterDto.excelToGameInformations(file.getInputStream());
            iGameRepository.saveAll(games);
            log.warn("Excel başarılı bir şekilde yüklendi");
            return ResponseEntity.status(HttpStatus.OK).body(new GameResponseMessage("Excel başarılı bir şekilde yüklendi"));
        }
        log.error("Excel yüklenemedi.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GameResponseMessage("Excel yüklenemedi!"));
    }

    @Override
    public void excelExport(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDataType = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Games" + currentDataType + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Game> listGame = iGameRepository.findAll(Sort.by("gameName").ascending());
        GameExcelExporterDto gameExcelExporterDto = new GameExcelExporterDto(listGame);
        gameExcelExporterDto.export(response);
    }


    public void excelImport(File file) throws IOException {
            List<Game> games = GameExcelImporterDto.excelToGameInformations(new FileInputStream(file));
            iGameRepository.saveAll(games);
    }

}
