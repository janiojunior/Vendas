package br.unitins.vendas.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.vendas.application.JPAUtil;
import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.model.Estado;

public class EstadoRepository extends Repository<Estado> {
	
	public EstadoRepository() {
		super();
	}
	
	public EstadoRepository(EntityManager em) {
		super(em);
	}
	
	public List<Estado> findByNome(String nome, int maxResults) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  e ");
			jpql.append("FROM ");
			jpql.append("  Estado e ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(e.nome) like UPPER(:nome) ");
			jpql.append("ORDER BY e.nome ");
			
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


}
