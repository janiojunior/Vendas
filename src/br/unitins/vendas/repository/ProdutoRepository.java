package br.unitins.vendas.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.vendas.application.JPAUtil;
import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.model.Marca;
import br.unitins.vendas.model.Produto;

public class ProdutoRepository extends Repository<Produto> {

	public ProdutoRepository() {
		super();
	}

	public ProdutoRepository(EntityManager em) {
		super(em);
	}

	public List<Produto> findAll() throws RepositoryException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT p FROM Produto p ORDER BY p.nome");

			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}

	}

	public List<Produto> findByNome(String nome) throws RepositoryException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  p ");
			jpql.append("FROM ");
			jpql.append("  Produto p ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(p.nome) like UPPER(:nome) ");
			jpql.append("ORDER BY p.nome ");

			Query query = em.createQuery(jpql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}

	}

}
