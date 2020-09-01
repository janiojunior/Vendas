package br.unitins.vendas.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.unitins.vendas.model.Produto;

public class TestJPA {

	public static void main(String[] args) {
		Produto p1 = new Produto();
		p1.setId(1);
		p1.setNome("Processador i9");
		p1.setDescricao("Intel");
		
		// Responsavel por criar os entitys managers
		EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("Vendas");
		// gerenciador do jpa (responsavel por fazer transacoes ou selecoes no banco)
		EntityManager em = emf.createEntityManager();
		
		// iniciando uma transacao com o banco de dados
		em.getTransaction().begin();
		em.persist(p1);
		em.getTransaction().commit();
		
		System.out.println("Operacao realizada.");
		
	}

}
