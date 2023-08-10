package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.ProdutoEstoqueDAO;
import br.com.springboot.model.ProdutoEstoque;

@Service
public class ProdutoEstoqueBO implements CRUD<ProdutoEstoque, Long> {

	@Autowired
	private ProdutoEstoqueDAO dao;
	
	@Override
	public ProdutoEstoque pesquisaPeloId(Long id) {
		return dao.pesquisaPeloId(id);
	}

	@Override
	public List<ProdutoEstoque> listaTodos() {
		return dao.listaTodos();
	}

	@Override
	public void insere(ProdutoEstoque pe) {
		dao.insere(pe);
	}

	@Override
	public void atualiza(ProdutoEstoque pe) {
		dao.atualiza(pe);
	}

	@Override
	public void remove(ProdutoEstoque pe) {
		dao.remove(pe);
	}

}
