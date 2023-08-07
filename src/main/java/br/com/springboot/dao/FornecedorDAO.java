package br.com.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Fornecedor;

@Repository
@Transactional
public class FornecedorDAO implements CRUD<Fornecedor, Long> {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Fornecedor pesquisaPeloId(Long id) {
		return em.find(Fornecedor.class, id);
	}

	@Override
	public List<Fornecedor> listaTodos() {
		Query query = em.createQuery("select f from Fornecedor f");
		return query.getResultList();
	}

	@Override
	public void insere(Fornecedor fornecedor) {
		em.persist(fornecedor);
	}

	@Override
	public void atualiza(Fornecedor fornecedor) {
		em.merge(fornecedor);
	}

	@Override
	public void remove(Fornecedor fornecedor) {
		em.remove(fornecedor);
	}
}
