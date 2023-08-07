package br.com.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Produto;

@Repository
@Transactional
public class ProdutoDAO implements CRUD<Produto, Long> {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Produto pesquisaPeloId(Long id) {
		return em.find(Produto.class, id);
	}

	@Override
	public List<Produto> listaTodos() {
		Query query = em.createQuery("select p from Produto p");
		return query.getResultList();
	}

	@Override
	public void insere(Produto produto) {
		em.persist(produto);
	}

	@Override
	public void atualiza(Produto produto) {
		em.merge(produto);
	}

	@Override
	public void remove(Produto produto) {
		em.remove(produto);
	}

}
