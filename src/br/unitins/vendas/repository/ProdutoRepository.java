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
	
	public List<Produto> findByMarca(Integer idMarca) throws RepositoryException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  p ");
			jpql.append("FROM ");
			jpql.append("  Produto p ");
			jpql.append("WHERE ");
			jpql.append("  p.marca.id = :marca ");
			jpql.append("ORDER BY p.nome ");

			Query query = em.createQuery(jpql.toString());
			query.setParameter("marca", idMarca);

			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}

	}	
	
	public List<Object[]> findByNomeSQL(String nome) throws RepositoryException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  p.id, p.nome, m.nome as nomeMarca ");
			sql.append("FROM ");
			sql.append("  produto p, ");
			sql.append("  marca m ");
			sql.append("WHERE ");
			sql.append("  p.marca_id = m.id ");
			sql.append("  AND UPPER(p.nome) like UPPER(?) ");
			sql.append("ORDER BY p.nome ");

			Query query = em.createNativeQuery(sql.toString());
			query.setParameter(1, "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}

	}

}
