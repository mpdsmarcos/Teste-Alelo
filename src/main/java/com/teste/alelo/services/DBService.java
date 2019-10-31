package com.teste.alelo.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teste.alelo.domain.Cidade;
import com.teste.alelo.domain.Cliente;
import com.teste.alelo.domain.Endereco;
import com.teste.alelo.domain.Estado;
import com.teste.alelo.domain.enums.Perfil;
import com.teste.alelo.domain.enums.TipoCliente;
import com.teste.alelo.repositories.CidadeRepository;
import com.teste.alelo.repositories.ClienteRepository;
import com.teste.alelo.repositories.EnderecoRepository;
import com.teste.alelo.repositories.EstadoRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
												
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est1.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Bruno Pereira", "brunobarrospereira@cuvox.de", "792.347.446-08", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Cliente cli2 = new Cliente(null, "Leonor Castro", "leonorrodriguescastro@rhyta.com", "31628382740", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("93883321","34252625"));
		cli2.addPerfil(Perfil.ADMIN);
		
		Endereco  e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco  e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco  e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli1.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.save(Arrays.asList(cli1, cli2));
		enderecoRepository.save(Arrays.asList(e1, e2, e3));
	}
}