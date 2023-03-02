package com.GamePortal.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Service
public class ZipExcelFileService {

    @Value("${new.file.path}")
    public String zipFilePath;

    public void zip(File file) {
        try {
            File destination = new File(zipFilePath);
            if (!destination.exists() || !destination.isDirectory()) {
                throw new IOException("Invalid destination path");
            }
            FileOutputStream fos = new FileOutputStream(zipFilePath + "/" + file.getName().replaceAll(".xlsx",".zip"));
            ZipOutputStream zos = new ZipOutputStream(fos);

            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            zos.putNextEntry(new ZipEntry(file.getName()));

            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }

            zos.closeEntry();
            zos.close();
            fis.close();
            fos.close();

            System.out.println(file.getName() + " successfully zipped to " + zipFilePath + "!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
