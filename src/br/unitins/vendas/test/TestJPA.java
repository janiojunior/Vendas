package br.unitins.vendas.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.unitins.vendas.model.Produto;

public class TestJPA {

	public static void main(String[] args) {
		Produto p1 = new Produto();
//		p1.setId(5);
		p1.setNome("Processador celeron");
		p1.setDescricao("Intel");
			
		// Responsavel por criar os entitys managers
		EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("Vendas");
		// gerenciador do jpa (responsavel por fazer transacoes ou selecoes no banco)
		EntityManager em = emf.createEntityManager();
		
//		p1 = em.find(Produto.class, 1);
		
//		p1.setDescricao("Novo processador Intel");
		
		// iniciando uma transacao com o banco de dados
		em.getTransaction().begin();
		System.out.println("Id Antes do merge: "+ p1.getId());
		p1 = em.merge(p1);
		System.out.println("Id Depois do merge: "+ p1.getId());
		em.getTransaction().commit();
		
		System.out.println("Operacao realizada.");
		
	}

}
