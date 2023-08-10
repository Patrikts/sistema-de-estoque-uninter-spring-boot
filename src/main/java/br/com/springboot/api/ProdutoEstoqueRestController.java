package br.com.springboot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.bo.ProdutoBO;
import br.com.springboot.bo.ProdutoEstoqueBO;
import br.com.springboot.model.Produto;
import br.com.springboot.model.ProdutoEstoque;

@RestController
public class ProdutoEstoqueRestController {

	@Autowired
	private ProdutoEstoqueBO produtoEstoqueBO;
	
	@Autowired
	private ProdutoBO produtoBO;
	
	@RequestMapping(value="/api/estoque", method = RequestMethod.GET)
	public List<ProdutoEstoque> listaTodos() {
		return produtoEstoqueBO.listaTodos();
	}
	
	@RequestMapping(value="/api/estoque/{id}", method = RequestMethod.GET)
	public ProdutoEstoque pesquisaPeloId(@PathVariable Long id) {
		return produtoEstoqueBO.pesquisaPeloId(id);
	}
	
	@RequestMapping(value="/api/estoque", method = RequestMethod.POST)
	public ProdutoEstoque insere(@RequestBody ProdutoEstoque produtoEstoque) {
		Produto produto = produtoBO.pesquisaPeloId(produtoEstoque.getProduto().getId());
		produtoEstoque.setProduto(produto);
		produtoEstoqueBO.insere(produtoEstoque);
		return produtoEstoque;
	}
	
	@RequestMapping(value="/api/estoque/{id}", method = RequestMethod.PUT)
	public ProdutoEstoque atualiza(@PathVariable Long id, @RequestBody ProdutoEstoque produtoEstoque) {
		produtoEstoque.setId(id);
		produtoEstoque.setProduto(produtoBO.pesquisaPeloId(produtoEstoque.getProduto().getId()));
		produtoEstoqueBO.atualiza(produtoEstoque);
		return produtoEstoque;
	}
	
	@RequestMapping(value="/api/estoque/{id}", method = RequestMethod.DELETE)
	public ProdutoEstoque remove(@PathVariable Long id) {
		ProdutoEstoque produtoEstoque = produtoEstoqueBO.pesquisaPeloId(id);
		produtoEstoqueBO.remove(produtoEstoque);
		return produtoEstoque;
	}
}
