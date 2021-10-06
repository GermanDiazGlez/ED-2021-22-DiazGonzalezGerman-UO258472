//  NO MODIFICAR NOMBRE DE ESTE PAQUETE, crear (si no existe ya) un paquete nuevo en vuestro proyecto que se llame así: "evalNestor"
//  y meted en él esta clase
package evalNestor;

import p2Grafos.Graph;

public class EvalGraph<T> extends Graph<T> {
	
	Alumno alum=new Alumno();

	public EvalGraph(int i) {
		super(i);
	}

	public EvalGraph(int i,T initialNodes[], boolean[][] initialEdges, double [][] initialWeights){
		super(i,initialNodes,initialEdges,initialWeights);
	}

	public String getNombreFicheroAlumno(){
		return alum.getNombreFicheroAlumno();
	}
}


/*
	//  debe estar incluido este constructor en la clase Graph 
	public Graph (int tam, T initialNodes[], boolean[][] initialEdges, double [][] initialWeights) {
		// Llama al constructor original
		this(tam); 
		
		// Pero modifica los atributos con los parametros para hacer pruebas...
		numNodes = initialNodes.length;
		
		for (int i=0;i<numNodes;i++) {
			// Si el vector de nodos se llama de otra forma (distinto de "nodes"), refactorizar para que se llama "nodes"
			nodes[i]=initialNodes[i];
			for (int j=0;j<numNodes;j++){
				// Si la matriz de aristas se llama de otra forma (distinto de "edges"), refactorizar para que se llama "edges"
				edges[i][j]=initialEdges[i][j];
				// Si la matriz de pesos se llama de otra forma (distinto de "weights"), refactorizar para que se llama "weights"
				weights[i][j]=initialWeights[i][j];
			}
		}

	} 
 */
