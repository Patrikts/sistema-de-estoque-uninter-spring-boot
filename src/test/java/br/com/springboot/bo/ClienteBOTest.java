package br.com.springboot.bo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.springboot.BO.ClienteBO;
import br.com.springboot.model.Cliente;
import br.com.springboot.model.Sexo;


public class ClienteBOTest {
	
	@Autowired
	private ClienteBO bo;
	
	public void insere() {
		Cliente cliente = new Cliente();
		cliente.setNome("José da Silva");
		cliente.setCpf("01234567890");
		cliente.setDataDeNascimento(LocalDate.of(2000, 1, 8));
		cliente.setSexo(Sexo.MASCULINO);
		cliente.setTelefone("0123456789");
		cliente.setCelular("01234567890");
		cliente.setAtivo(true);
		bo.insere(cliente);
	}

	public void pesquisaPeloId() {
		Cliente cliente = bo.pesquisaPeloId(1L);
		System.out.println(cliente);
	}

	public void atualiza() {
		Cliente cliente = bo.pesquisaPeloId(1L);
		cliente.setCpf("98765432100");
		bo.atualiza(cliente);
	}
}
