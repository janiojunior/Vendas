package br.unitins.vendas.test;

import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.controller.Departamento;
import br.unitins.vendas.model.Marca;
import br.unitins.vendas.model.Produto;
import br.unitins.vendas.repository.MarcaRepository;
import br.unitins.vendas.repository.ProdutoRepository;

public class TestJPA {

	public static void main(String[] args) throws RepositoryException {
		Marca marca = new Marca();
		marca.setNome("Intel 3");
		MarcaRepository repoMarca = new MarcaRepository();
		repoMarca.beginTransaction();
		marca = repoMarca.save(marca);
//		repoMarca.commitTransaction();
		
		Produto produto = new Produto();
		produto.setNome("Processador i7");
		produto.setDescricao("Possui 8 nucleos");
		produto.setDepartamento(Departamento.TELEFONIA);
		produto.setMarca(marca);
		ProdutoRepository repoProduto = 
				new ProdutoRepository(repoMarca.getEntityManager());
//		repoProduto.beginTransaction();
		repoProduto.save(produto);
		repoProduto.commitTransaction();
				
	}

}
