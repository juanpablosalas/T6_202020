package model.data_structures;

public class VertexExistsException extends Exception {
	
	public VertexExistsException(String id) {
		super("El vértice  con ID:"+id+ "ya existe");
	}

}
