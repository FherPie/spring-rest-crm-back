package com.componente.factinven.servicios.interfaz;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;


import com.componente.factinven.exceptions.FTPErrors;

public interface FTPService {
    void connectToFTP() throws FTPErrors;
    void uploadFileToFTP(MultipartFile file , String serverFilename, String namefile) throws FTPErrors;
    void downloadFileFromFTP(String ftpRelativePath, String copytoPath) throws FTPErrors;
    void disconnectFTP() throws FTPErrors;

}