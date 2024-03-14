package com.componente.factinven.importers;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.componente.factinven.dto.ClienteDto;
import com.componente.factinven.entidades.Producto;
import com.componente.factinven.servicios.impl.ClienteServicioImpl;

@Service
public class ImporterExcelCliente implements ImporterExcelInterface {

	
	
	@Autowired
	ClienteServicioImpl clienteService;
	
	@Override
	public void LeerExcel(MultipartFile multipartFile)  {

		List<Producto> listaProdcuto= new ArrayList<>();
		int sid;
		String nombres;
		String apellidos;
		String direccion;
	    String telefono;
	    String email;
		LocalDate fechaNacimiento;
	    String nombreUsuario;
	    String pin;
	    String identificacion;
	    Integer idPersona;	
		InputStream initialStream;
		
		
		try {
			initialStream = multipartFile.getInputStream();
			Workbook workBook = new  XSSFWorkbook(initialStream);
			Sheet firstSheet= workBook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();
			rowIterator.next();
			
			while(rowIterator.hasNext()) {
				Row nextRow= rowIterator.next();
				ClienteDto clienteRequest= new ClienteDto();
				Iterator<Cell> cellIterador= nextRow.cellIterator();
				while(cellIterador.hasNext()) {
					Cell  nextCell= cellIterador.next();
					int columnIndex= nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						sid=(int) nextCell.getNumericCellValue();
						System.out.println(sid);
						break;
					case 1:
						nombres=nextCell.getStringCellValue();
						System.out.println("Nombres");
						clienteRequest.setNombres(nombres);
						break;
					case 2:
						apellidos=nextCell.getStringCellValue();
						System.out.println("Apellidos");
						clienteRequest.setApellidos(apellidos);
						break;
					case 3:
						direccion=nextCell.getStringCellValue();
						System.out.println("Direccion");
						clienteRequest.setDireccion(direccion);
						break;
					case 4:
						telefono=nextCell.getStringCellValue();
						System.out.println("TELEFONO");
						clienteRequest.setTelefono(telefono);
						break;
					case 5:
						email=nextCell.getStringCellValue();
						System.out.println("email");
						clienteRequest.setEmail(email);
						break;
					case 6:
						identificacion= nextCell.getStringCellValue();
						System.out.println("identificacion");
						clienteRequest.setIdentificacion(identificacion.toString());
						break;
					default:
						break;
					}	
				}
				clienteService.guardarCliente(clienteRequest);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
