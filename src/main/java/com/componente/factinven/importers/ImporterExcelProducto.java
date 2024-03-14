package com.componente.factinven.importers;

import java.io.InputStream;
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

import com.componente.factinven.entidades.Producto;
import com.componente.factinven.repositorios.ProductoRepositorio;
import com.componente.factinven.servicios.impl.ProductoServicioImpl;

@Service
public class ImporterExcelProducto implements ImporterExcelInterface{
	
	

	@Autowired
	ProductoRepositorio productoRespotorio;
	

	@Autowired
	ProductoServicioImpl productoService;
	
	public ImporterExcelProducto() {
		// TODO Auto-generated constructor stub
		
	
	}

	@Override
	public void LeerExcel(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		
		List<Producto> listaProdcuto= new ArrayList<>();
		int sid;
		String nombre;
		Double precioUnitario;
		Integer stock;
		String unidad;
		Double precioCompra;	
		InputStream initialStream;
		try {
			initialStream = multipartFile.getInputStream();
			Workbook workBook = new  XSSFWorkbook(initialStream);
			Sheet firstSheet= workBook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();
			rowIterator.next();
			
			while(rowIterator.hasNext()) {
				Row nextRow= rowIterator.next();
				Producto productoguardar= new Producto();
				Iterator<Cell> cellIterador= nextRow.cellIterator();
				while(cellIterador.hasNext()) {
					Cell  nextCell= cellIterador.next();
					int columnIndex= nextCell.getColumnIndex();
		
					switch (columnIndex) {
					case 0:
						sid=(int) nextCell.getNumericCellValue();
						System.out.println(sid);
						//productoguardar.setIdProducto(sid);
						break;
					case 1:
						nombre=nextCell.getStringCellValue();
						System.out.println(nombre);
						productoguardar.setNombre(nombre);
						break;
					case 2:
						precioUnitario=(double) nextCell.getNumericCellValue();
						productoguardar.setPrecioUnitario(precioUnitario);
						System.out.println(precioUnitario);
						break;
					default:
						break;
					}

					
				}
				
				productoService.guardarProductoDesdeExcel(productoguardar);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
	
	

}
