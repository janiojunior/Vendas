package br.unitins.vendas.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.vendas.application.JPAUtil;
import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.model.Cidade;

public class CidadeRepository extends Repository<Cidade> {
	
	public CidadeRepository() {
		super();
	}
	
	public CidadeRepository(EntityManager em) {
		super(em);
	}
	
	public List<Cidade> findByNome(String nome, int maxResults) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  c ");
			jpql.append("FROM ");
			jpql.append("  Cidade c ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(c.nome) like UPPER(:nome) ");
			jpql.append("ORDER BY c.nome ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("nome", "%"+ nome + "%");
			
			query.setMaxResults(maxResults);
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<Cidade> findByNome(String nomeCidade, Integer idEstado, int maxResults) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  c ");
			jpql.append("FROM ");
			jpql.append("  Cidade c ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(c.nome) like UPPER(:nome) ");
			jpql.append("  AND c.estado.id = :idEstado ");
			jpql.append("ORDER BY c.nome ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("nome", "%"+ nomeCidade + "%");
			query.setParameter("idEstado", idEstado);
			
			query.setMaxResults(maxResults);
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}


}
