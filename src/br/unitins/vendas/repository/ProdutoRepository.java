package br.unitins.vendas.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.unitins.vendas.application.JPAUtil;
import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.application.Util;
import br.unitins.vendas.application.ValidationException;
import br.unitins.vendas.model.Produto;

public class ProdutoRepository {
	
	
	/**
	 * 
	 * @param produto
	 * @throws RepositoryException
	 */
	public void save(Produto produto) throws RepositoryException, ValidationException{
		
		try { 
			EntityManager em = JPAUtil.getEntityManager();
			
			// iniciando uma transacao com o banco de dados
			em.getTransaction().begin();
			em.merge(produto);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Salvar. (Erro de Conexao)");
		} catch (Exception e) {
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Salvar.");
			
		}

	}

}
