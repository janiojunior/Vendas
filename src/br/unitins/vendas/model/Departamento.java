package br.unitins.vendas.model;

public enum Departamento {
	TELEFONIA(1, "Telefonia"), 
	INFORMATICA(2, "Informática"), 
	MOVEIS(3, "Móveis");
	
	private int id;
	private String label;
	
	Departamento(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	

	
	public static Departamento valueOf(Integer id) {
		if (id == null)
			return null;
		
		for (Departamento dep : Departamento.values()) {
			if (dep.getId() == id)
				return dep;
		}
		return null;
	}
	
}
