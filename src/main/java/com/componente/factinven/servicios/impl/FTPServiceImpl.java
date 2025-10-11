package com.componente.factinven.servicios.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.componente.factinven.exceptions.ErrorMessage;
import com.componente.factinven.exceptions.FTPErrors;
import com.componente.factinven.servicios.interfaz.FTPService;

@Service
public class FTPServiceImpl implements FTPService {

	@Value("${ftp.host}")
	private String host;

	@Value("${ftp.user}")
	private String user;

	@Value("${ftp.password}")
	private String pass;

	@Value("${ftp.port}")
	private String port;

	@Value("${ftp.hostDir}")
	private String ftpHostDir;

	FTPClient ftpconnection;

	private Logger logger = LoggerFactory.getLogger(FTPServiceImpl.class);

	@Override
	public void connectToFTP() throws FTPErrors {

		ftpconnection = new FTPClient();
		ftpconnection.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;

		try {
			ftpconnection.connect(this.host);
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-1,
					"No fue posible conectarse al FTP a través del host=" + host);
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

		reply = ftpconnection.getReplyCode();

		if (!FTPReply.isPositiveCompletion(reply)) {

			try {
				ftpconnection.disconnect();
			} catch (IOException e) {
				ErrorMessage errorMessage = new ErrorMessage(-2,
						"No fue posible conectarse al FTP, el host=" + host + " entregó la respuesta=" + reply);
				logger.error(errorMessage.toString());
				throw new FTPErrors(errorMessage);
			}
		}

		try {
			ftpconnection.login(user, pass);
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-3,
					"El usuario=" + user + ", y el pass=**** no fueron válidos para la autenticación.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

		try {
			ftpconnection.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-4, "El tipo de dato para la transferencia no es válido.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

		ftpconnection.enterLocalPassiveMode();
	}

	@Override
	public void uploadFileToFTP(MultipartFile file, String serverFilename, String namefile) throws FTPErrors {

		String directorio = this.ftpHostDir + serverFilename;
		try {
			boolean created = ftpconnection.makeDirectory(serverFilename);
//			if (created) {
				InputStream initialStream = file.getInputStream();
				byte[] buffer = new byte[initialStream.available()];
				initialStream.read(buffer);
				File archivo= new File(this.ftpHostDir+serverFilename+"\\"+namefile);
				try (OutputStream outStream = new FileOutputStream(archivo)) {
				    outStream.write(buffer);
				}
				InputStream input = new FileInputStream(archivo);
				this.ftpconnection.storeFile(directorio, input);
				// the directory is created, everything is going well

//			} else {
//
//				// something unexpected happened...
//			}

		
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo subir el archivo al servidor.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

	}

	@Override
	public void downloadFileFromFTP(String ftpRelativePath, String copytoPath) throws FTPErrors {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnectFTP() throws FTPErrors {
		if (this.ftpconnection.isConnected()) {
			try {
				this.ftpconnection.logout();
				this.ftpconnection.disconnect();
			} catch (IOException f) {
				throw new FTPErrors(
						new ErrorMessage(-8, "Ha ocurrido un error al realizar la desconexión del servidor FTP"));
			}
		}
	}

}
