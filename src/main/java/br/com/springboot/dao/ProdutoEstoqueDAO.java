package br.com.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.ProdutoEstoque;

@Repository
@Transactional
public class ProdutoEstoqueDAO implements CRUD<ProdutoEstoque, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ProdutoEstoque pesquisaPeloId(Long id) {
		return entityManager.find(ProdutoEstoque.class, id);
	}

	@Override
	public List<ProdutoEstoque> listaTodos() {
		Query query = entityManager.createQuery("select pe from ProdutoEstoque pe");
		return query.getResultList();
	}

	@Override
	public void insere(ProdutoEstoque pe) {
		entityManager.persist(pe);
	}

	@Override
	public void atualiza(ProdutoEstoque pe) {
		entityManager.merge(pe);
	}

	@Override
	public void remove(ProdutoEstoque pe) {
		entityManager.remove(pe);
	}
	
}
