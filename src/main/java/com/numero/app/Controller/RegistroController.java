package com.numero.app.Controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.numero.app.Model.RegistroModel;
import com.numero.app.Repository.RegistroRepository;

@RestController
@RequestMapping(value="/api")
public class RegistroController {

	@Autowired
	private RegistroRepository registroRepository;

	@RequestMapping(value="/obterResultado", method=RequestMethod.POST)
	public String obterResultado(@RequestBody RegistroModel registro) throws Exception{
		
		String resultado = "";
		
		if(validaFiltro(registro)) {
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String data = formatter.format(new Date());
			registro.setDataRegistro(data);
			resultado = resultado(registro.getNumeroChamado());
			registro.setResultado(resultado);
			registroRepository.save(registro);
		}
		
		return resultado;
	}
	
	@RequestMapping(value="/registros", method=RequestMethod.GET)
	public List<RegistroModel> registros(@RequestBody RegistroModel registro) throws Exception{
		
		
		List<RegistroModel> lista = registroRepository.findRegistros(registro.getDataRegistro(), registro.getEmail(), registro.getNumeroChamado(), registro.getResultado());
		
		return lista;
	}
	
	public String resultado(int numero) {
		
		String resultado = "";
		boolean verificador = false;
		
		if(numero % 3 == 0) {
			resultado += "Fizz";
			verificador = true;
		}
		
		if(numero % 5 == 0) {
			resultado += "Buzz";
			verificador = true;
		}
		
		return (verificador ? resultado : String.valueOf(numero));
	} 
	
	
	
	public boolean validaFiltro(RegistroModel registro) throws Exception {
		
		if(registro.getNumeroChamado() == null) {
			throw new Exception("É necessário informar o campo numeroChamado");
		}
		
		if(registro.getEmail() == null) {
			throw new Exception("É necessário informar o campo email");
		}
		
		if(registro.getNumeroChamado() < 1 || registro.getNumeroChamado() > 100) {
			throw new Exception("É necessário informar um número entre 1 e 100.");
		}
		
		return true;
	}
	
	
	
}