package com.componente.factinven.servicios.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

import com.componente.factinven.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.componente.factinven.dto.ClienteDto;
import com.componente.factinven.dto.ClienteRespuestasDto;
import com.componente.factinven.dto.EntradaDto;
import com.componente.factinven.dto.OdontogramaRespuestasDto;
import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.exceptions.CedulaEcException;
import com.componente.factinven.exceptions.FTPErrors;
import com.componente.factinven.mappers.ClienteMapper;
import com.componente.factinven.mappers.ClienteRespuestasMapper;
import com.componente.factinven.mappers.DetalleMapper;
import com.componente.factinven.mappers.EntradaMapper;
import com.componente.factinven.mappers.OdontogramaRespuestasMapper;
import com.componente.factinven.mappers.VentaMapper;
import com.componente.factinven.repositorios.ClienteRepositorio;
import com.componente.factinven.repositorios.ClienteRespuestasRepositorio;
import com.componente.factinven.repositorios.DetalleRepositorio;
import com.componente.factinven.repositorios.EntradasRespository;
import com.componente.factinven.repositorios.VentaRepositorio;
import com.componente.factinven.repositorios.OdontogramaRespuestasRepositorio;
import com.componente.factinven.servicios.interfaz.FTPService;
import com.componente.factinven.servicios.interfaz.IClienteServicio;
import com.componente.factinven.utils.ValidadorResponse;
import com.componente.factinven.utils.ValidarIdenttificacion;


@Service
public class ClienteServicioImpl  implements IClienteServicio {

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Autowired
	private ClienteMapper clienteMapper;
	
	@Autowired
	private DetalleRepositorio detalleRepositorio;
	
	@Autowired
	private DetalleMapper detalleMapper;
	
	@Autowired
	private EntradasRespository entradaRepositorio;
	
	@Autowired
	private EntradaMapper entradaMapper;
	
	@Autowired
	private ClienteRespuestasRepositorio clienteRespuestaRepositorio;
	
	
	@Autowired
	private ClienteRespuestasMapper clienteRespuestasMapper;
	
	@Autowired
	private VentaRepositorio ventaRepositorio;
	
	@Autowired
	private VentaMapper ventaMapper;
	
	@Autowired
	private OdontogramaRespuestasMapper odontogramaRespuestasMapper;
	
	@Autowired
	private OdontogramaRespuestasRepositorio odontogramaRespuestasRespository;
	
	@Autowired
	private FTPService fTPService;
	
	@Value("${ftp.hostDir}")
	private String ftpHostDir;
	
	
	@Override
	@Transactional
	public ClienteDto guardarCliente(ClienteDto clienteDto) throws Exception {
		//String identificacion= clienteDto.getIdentificacion();
		//validarCedulaCliente(identificacion);
		Cliente cliente = clienteMapper.toEntity(clienteDto);
		ClienteDto clienteDtoNuevo =  new ClienteDto(clienteRepositorio.save(cliente));
		clienteDtoNuevo= instanciarPreguntasCliente(clienteDtoNuevo);
		clienteDtoNuevo= instanciarOdontogramaRepsuestas(clienteDtoNuevo);
		//clienteGuardar.setCategoria(cliente.getCategoria());
	    return clienteDtoNuevo;
	}
	
	
	//Con este validador se puede validar la cedula natural, ruc persona natural y empresa
	private void validarCedulaCliente(String cedula) throws Exception {
		 ValidarIdenttificacion validador = new ValidarIdenttificacion();
	     ValidadorResponse validarValorresponse = new ValidadorResponse();
         validarValorresponse = validador.validarCedula(cedula);
         if(!validarValorresponse.getValid()) {
        	 throw new CedulaEcException(validarValorresponse.getMensaje());
         }
	}


	@Override
	public void buscarClienteApellido(String apellido) {
		
		
	}


	@Override
	public Boolean eliminarCliente(Integer id) {
		 clienteRepositorio.deleteById(id);
		 return true;
	}


	@Override
	public Page<Cliente> listarClientes(int page, int size) {
		Pageable pages = PageRequest.of(page, size);
		return this.clienteRepositorio.findAll(pages);
	}


	@Override
	@Transactional
	public ClienteDto editarCliente(ClienteDto clienteDto) throws Exception {
		//String identificacion= clienteDto.getIdentificacion();
		//validarCedulaCliente(identificacion);
		//clienteDto.setListaPreguntas(new ArrayList<>());
		Cliente cliente = clienteMapper.toEntity(clienteDto);
		for (Iterator iterator = cliente.getListaClienteRepuestas().iterator(); iterator.hasNext();) {
			ClienteRespuestas crdto = (ClienteRespuestas) iterator.next();
			crdto.setCliente(cliente);
		}
		
		for(OdontogramaRespuestas respuesta: cliente.getListaOdontogramaRespuestas()) {
			respuesta.setCliente(cliente);
		}
		//clienteGuardar.setCategoria(cliente.getCategoria());
	    return  clienteMapper.toDto(clienteRepositorio.save(cliente));
	}


	@Override
	public void borrarClientes() {
		// TODO Auto-generated method stub
         this.clienteRepositorio.deleteAll();		
	}


	@Override
	public List<ClienteDto> findAll() {
		List<ClienteDto> listaRetorno = this.clienteRepositorio.findAll().stream().map(x -> {
			return new ClienteDto(x);
		}).collect(Collectors.toList());
		return listaRetorno;
	}


	@Override
	public ClienteDto findById(Integer idCliente) {
		Cliente cliente = this.clienteRepositorio.findById(idCliente).get();
		//cliente.getListaClienteRepuestas();
		ClienteDto clienteDto= clienteMapper.toDto(cliente);
		clienteDto = instanciarPreguntasCliente(clienteDto);
		clienteDto= instanciarOdontogramaRepsuestas(clienteDto);
		return clienteDto;
	}


	@Override
	public List<ClienteDto> findAllPorApellidoContains(String apellidos) {
		List<ClienteDto> listaRetorno = this.clienteRepositorio.findByPersonaApellidosContainingIgnoreCase(apellidos).stream().map(x -> {
			return new ClienteDto(x);
		}).collect(Collectors.toList());
		return listaRetorno;
	}
	
	
	//Preguntas Cliente
	public ClienteDto instanciarPreguntasCliente(ClienteDto clienteDto) {
		ClienteDto clienteuyhh = new ClienteDto();
		List<ClienteRespuestasDto> listRespuestaDto= new ArrayList<>();
		List<Detalle> listDetalle= new ArrayList<>();
		listDetalle.addAll(detalleRepositorio.findByMaestroCodigo("ANTF"));
		listDetalle.addAll(detalleRepositorio.findByMaestroCodigo("ANHOSP"));
		listDetalle.addAll(detalleRepositorio.findByMaestroCodigo("ASX"));
		listDetalle.addAll(detalleRepositorio.findByMaestroCodigo("PCOM"));
		
		List<Integer> preguntasExistentes=  listDetalle.stream().map(element -> element.getId()).collect(Collectors.toList());
		List<ClienteRespuestasDto> listaClientesRespuestaDto= new ArrayList<ClienteRespuestasDto>();
		if(clienteDto.getId()==null) {//preguntas no respondidas
		    clienteDto.setListaClienteRespuestasDto(this.clienteRepuestasDtoNuevas(listDetalle));
		}else {//preguntas respondidas
			//Preguntas de clientes respondida con anterioridad.
			listaClientesRespuestaDto= clienteRespuestasMapper.toDto(clienteRespuestaRepositorio.encontrarClientesRepuestas(clienteDto.getId()));
			//Preguntas de clientes respondida con anterioridad ID
			List<Integer> preguntasdelCliente=  listaClientesRespuestaDto.stream().map(element -> element.getPregunta().getId()).collect(Collectors.toList());
            
			//Si es diferente la cantidad de preguntas
			if(preguntasdelCliente.size()!= preguntasExistentes.size()) {
				//iterar sobre las preguntas del cliente y eliminar del array de preguntas existentes
			  	for (Iterator iterator = preguntasdelCliente.iterator(); iterator.hasNext();) {
					Integer integer = (Integer) iterator.next();
					//Integer preguntaEliminar= preguntasdelCliente.stream().filter(element -> element==integer).findFirst().get();
				    listDetalle.removeIf(element-> element.getId()==integer);
				}
			  	clienteDto.setListaClienteRespuestasDto(this.clienteRepuestasDtoNuevas(listDetalle));
			}
 		    //clienteDto.getListaClienteRespuestasDto().addAll(listaClientesRespuestaDto);
		}
		return clienteDto;
	}
	//Preguntas
	
	
	
	
	//Odontologia Cliente
	public ClienteDto instanciarOdontogramaRepsuestas(ClienteDto clienteDto) {
			if(clienteDto.getListaOdontogramaRespuestasDto()==null || clienteDto.getListaOdontogramaRespuestasDto().isEmpty()) {
				clienteDto.setListaOdontogramaRespuestasDto(obtenerOdontograma());	
			}
		return clienteDto;
	}
	
	
	public List<OdontogramaRespuestasDto> obtenerOdontograma(){
		OdontogramaRespuestas odontogramaRespuestas= new OdontogramaRespuestas();
		List<OdontogramaRespuestas> listadePiezas=  odontogramaRespuestas.new PiezaDental().retornarPiezas();
		return odontogramaRespuestasMapper.toDto(listadePiezas);
	}
	
	
	//Odontologia Cliente
	
	
	
	//Presupuesto Cliente
	public List<VentaResponse> listarPresupuestoCliente(Integer clienteId){
		return ventaRepositorio.fetchVentaByClientId(clienteId);
	}
	
	//Presupuesto Cliente
	
	
	private List<ClienteRespuestasDto> clienteRepuestasDtoNuevas(List<Detalle> listaPreguntasSistema) {
		List<ClienteRespuestasDto> listRespuestaDto= new ArrayList<>();
		for (Iterator iterator = listaPreguntasSistema.iterator(); iterator.hasNext();) {
			Detalle detalle = (Detalle) iterator.next();
			ClienteRespuestasDto clientRepsDto= 
			new ClienteRespuestasDto(0, null, null, null, null, 
					detalleMapper.toDto(detalle));
			listRespuestaDto.add(clientRepsDto);
		}
		return listRespuestaDto;
	}


	//Pagos de Clientes
	
	@Override
	public EntradaDto crearEntradaCliente(EntradaDto entradaDto) {
		Entrada entrada = entradaMapper.toEntity(entradaDto);
		entrada.setCreatedDate(new Date());
		return entradaMapper.toDto(entradaRepositorio.save(entrada));
	}


	@Override
	public List<EntradaDto> listarPagostClientes(Integer id) {
		//List<EntradaDto> lista=entradaMapper.toDto(entradaRepositorio.pagosCliente(id));
		return null;
	}


	@Override
	public String uploadandSaveFile(MultipartFile file, String fileName, Integer idCliente) {
		try {
			Cliente cliente = this.clienteRepositorio.findById(idCliente).get();
			fTPService.connectToFTP();
			String path="\\"+cliente.getnombreCompletosFolder()+"_"+cliente.getId();
			fTPService.uploadFileToFTP(file, path, fileName);
			Set<ImageModel> clientFiles=this.uploadImage(file, path);
			cliente.setClientFiles(clientFiles);
			this.clienteRepositorio.save(cliente);
		} catch (FTPErrors | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}


	private Set<ImageModel> uploadImage(MultipartFile file, String path) throws IOException {
		Set<ImageModel> imageModels= new HashSet<>();
			ImageModel imgm= new ImageModel(file.getName(), file.getContentType(), path);
			imageModels.add(imgm);
		return imageModels;
	}



	//Pagos de Clientes
	
	//PresupuestoClientes
	
	
	//Presupuestos Clientes

}
