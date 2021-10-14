package p2Grafos;

import java.text.DecimalFormat;
import java.util.Iterator;

public class Graph <T>{   

    protected T[] nodes; // Vector de nodos
    protected boolean[][] edges; // matriz de aristas
    protected double[][] weights; // matriz de pesos
    protected int numNodes; // número de elementos en un momento dado
    
    
    
    protected boolean[] dijkstraS;
    
    //Vector D es el vector de costes minimos
    //se inicializa con el tamaño numNodes al llamar al metodo de dijkstra 
    //Para ir desde el nodo origen así mismo el coste es 0
    protected double[] dijkstraD;
    
    //Vector P(path) es el vector de Rutas de coste minimo
    //nodos intermedios por los que tengo que dar un salto, y sin no hay nodos intermedios no se pone nada
    protected int[] dijkstraP; //se inicializa con el tamaño numNodes al llamar al metodo de dijkstra con -1 para que java no lo inicialice a 0

    //Matriz A de Floyd
    protected double aFloyd[][]; 
    //Matriz P de Floyd
    protected int pFloyd[][]; 
    
	// Nuevo constructor en vuestro Graph, hay que mantener tambien los anteriores... 
    public Graph (int tam, T initialNodes[], boolean[][] initialEdges, double [][] initialWeights, double [][] initialAfloyd, int [][] initialPfloyd) { 
        // Llama al constructor anterior de inicialización 
        this(tam, initialNodes,initialEdges,initialWeights);  
         
        // Pero modifica los atributos que almacenan las matrices de Floyd con los parámetros para hacer pruebas... 
         
        if (initialAfloyd!=null && initialPfloyd!=null){ 
            // Si la matriz A de floyd se llama de otra forma (distinto de "aFloyd"), refactorizar para que se llame "aFloyd"
            aFloyd=initialAfloyd; 
            // Si la matriz P de floyd se llama de otra forma (distinto de "pFloyd"), refactorizar para que se llame "pFloyd"
            pFloyd=initialPfloyd; 
        } 
  
    } 
    
    //  debe estar incluido este constructor en la clase Graph 
	public Graph (int tam, T initialNodes[], boolean[][] initialEdges, double [][] initialWeights) {
		// Llama al constructor original
		this(tam); 
		
		// Pero modifica los atributos con los parametros para hacer pruebas...
		numNodes = initialNodes.length;
		
		for (int i=0;i<numNodes;i++) {
			// Si el vector de nodos se llama de otra forma (distinto de "nodes"), cambiad el nombre en la linea de abajo
			nodes[i]=initialNodes[i];
			for (int j=0;j<numNodes;j++){
				// Si la matriz de aristas se llama de otra forma (distinto de "edges"), cambiad el nombre en la linea de abajo
				edges[i][j]=initialEdges[i][j];
				// Si la matriz de pesos se llama de otra forma (distinto de "weights"), cambiad el nombre en la linea de abajo
				weights[i][j]=initialWeights[i][j];
			}
		}

	} 
    
    /**   
    * Se le pasa el numero maximo de nodos del grafo   
    */
    @SuppressWarnings("unchecked")   

    public Graph(int tam) {   
    	numNodes = 0;
        nodes = (T[])new Object[tam]; 
        edges = new boolean[tam][tam];
        weights = new double[tam][tam];
        
    }   

    /** 
    * Inserta un nuevo nodo que se le pasa como parámetro  
    * si lo inserta devuelve 0.  
    * Error 1: si ya existe, valor -1 
    * Error 2: no cabe, valor -2,  
    * Error 3: el nodo a insertar no es válido, valor –4 
    * Se suman los errores en caso de que se den varios, 
    * por ejemplo: si además de no ser válido no cabe, devuelve -6 (suma errores 2 y 3)
    */
    public int addNode(T node){
    	int res = 0;
    	
    	if(getNode(node)!=-1){
    		res += -1;
    	}

    	if(numNodes + 1 > nodes.length){
    		res += -2;
    	}
    	
    	if(node == null){
    		res += -4;
    	}
    	
    	if(res == 0){
    		nodes[numNodes] = node;
    		for(int i=0; i< numNodes; i++) {
    			edges[numNodes][i] = false;
    			edges[i][numNodes] = false;
            	weights[numNodes][i] = 0;
            	weights[i][numNodes] = 0;
    		}
        	numNodes++;
        	aFloyd = null;
        	pFloyd = null;
    	}
    	
    	return res;
    }
    
    /**
    * Obtiene el índice de un nodo en el vector de nodos, y –1 si no existe
    * ¡¡¡ OJO que no es público porque depende de la implementación !!!
    */
    protected int getNode(T node) {
    	if(node != null) {
        	for(int i=0; i<numNodes; i++){
        		if(node.equals(nodes[i])){
        			return i;
        		}
        	}
    	}
    	return -1;
    }  
    
    /**
    * Indica si existe o no el nodo en el grafo
    */
    public boolean existsNode(T node) {
    	for(int i=0; i<numNodes; i++){
    		if(node.equals(nodes[i])){
    			return true;
    		}
    	}
    	return false;
    }  
    
    /**
    * Inserta una arista con el peso indicado (> 0) entre dos nodos, uno origen y  
    * otro destino. Si la arista existe, no la inserta. 
    * Devuelve 0 si la inserta 
    * Se suman los valores de los errores si se dan varios simultaneamente, 
    *   por ejemplo si se dan errores 1 y 2 el resultado es la suma de los valores correspondientes (-3) 
    * Error 1: No existe nodo origen, valor -1 
    * Error 2: No existe nodo destino, valor -2 
    * Error 3: Ya existe la arista, valor -4 
    * Error 4: El peso no es válido, valor -8 
    * 
    */
    public int addEdge(T source, T target, double edgeWeight) {
    	int res = 0;
    	int sourcePos = getNode(source);
    	int targetPos = getNode(target);
    	
    	if(sourcePos==-1){
    		res += -1;
    	}
    	
    	if(targetPos==-1){
    		res += -2;
    	}
    	
    	if(edgeWeight <= 0.0){
    		res += -8;
    	}
    	
    	if(sourcePos != -1 && targetPos != -1){
        	if(edges[sourcePos][targetPos] != false){
        		res += -4;
        	} else if(edgeWeight > 0.0) {
	        	edges[sourcePos][targetPos] = true;
	        	weights[sourcePos][targetPos] = edgeWeight;
	        	aFloyd = null;
	        	pFloyd = null;
        	}
    	}
    	return res;
    }  

    /**
    * Devuelve el peso de la arista que conecta dos nodos
    * Se suman los valores de los errores si se dan varios simultaneamente
    * Error 1: No existe nodo origen, valor –1,
    * Error 2: No existe nodo destino, valor -2
    * Error 3: No existe la arista pero sí los nodos origen y destino, valor –4
    */
    public double getEdge(T source, T target) {
    	int res = 0;
    	int sourcePos = getNode(source);
    	int targetPos = getNode(target);
    	
    	if(sourcePos != -1 && targetPos != -1){
    		if(edges[sourcePos][targetPos] == true){
    			return weights[sourcePos][targetPos];
    		}
    	}
    	
    	if(sourcePos == -1){
    		res += -1;
    	}
    	
    	if(targetPos == -1){
    		res += -2;
    	}
    	
    	if(res == 0){
    		return -4;
    	}
		
    	return res;
    }  

    /**
    * Comprueba si existe una arista entre dos nodos que se pasan como parámetro
    * si alguno de los nodos no existe, no existe la arista evidentemente
    */
    public boolean existsEdge(T source, T target) {
    	int sourcePos = getNode(source);
    	int targetPos = getNode(target);
    	if(sourcePos != -1 && targetPos != -1) {
    		if(edges[sourcePos][targetPos] == true){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
    * Borra la arista del grafo que conecta dos nodos
    * Se suman los valores de los errores si se dan varios simultaneamente
    * Error 1: No existe nodo origen, valor –1,
    * Error 2: No existe nodo destino, valor -2
    * Error 3: No existe la arista pero sí los nodos origen y destino, valor –4
    * devuelve 0 si la borra
    */  
    public int removeEdge(T source, T target) {
    	int res = 0;
    	int sourcePos = getNode(source);
    	int targetPos = getNode(target);
    	
    	if(sourcePos != -1 && targetPos != -1){
    		if(edges[sourcePos][targetPos] == true){
    			edges[sourcePos][targetPos] = false;
            	aFloyd = null;
            	pFloyd = null;
    		} else {
    			res += -4;
    		}
    	}
    	
    	if(sourcePos == -1){
    		res += -1;
    	}
    	
    	if(targetPos == -1){
    		res += -2;
    	}		
    	return res;
    }  
    
    /**
    * Borra el nodo deseado del vector de nodos así como las aristas de las que
    * forma parte, devolviendo 0 si lo hace y –1 si no lo hace
    * En el removeNode cogemos el ultimo y lo ponemos en la posición del borrado.
    */
    public int removeNode(T node) {
    	int nodePos = getNode(node);
    	if(nodePos != -1) {
    		if(nodePos == numNodes -1) {
    			numNodes--;
    			return 0;
    		}
    		nodes[nodePos] = nodes[numNodes -1];
    		for(int i=0; i<numNodes;i++) {
    			edges[i][nodePos] = edges[i][numNodes-1];
    			edges[nodePos][i] = edges[numNodes-1][i];
    			weights[i][nodePos] = weights[i][numNodes-1];
    			weights[nodePos][i] = weights[numNodes-1][i];
    		}
    		edges[nodePos][nodePos] = edges[numNodes-1][numNodes-1];
    		weights[nodePos][nodePos] = weights[numNodes-1][numNodes-1];
    		numNodes--;
    		
        	aFloyd = null;
        	pFloyd = null;
    		return 0;
    	}
    	return -1;
    }
    
    
//    /**
//      * Devuelve un String con la informacion del grafo  
//      */
//    public String toString() {  
//         DecimalFormat df = new DecimalFormat("#.##");  
//         String cadena = "";
//         cadena += "NODES\n";
//
//         for (int i = 0; i < numNodes; i++) {  
//             cadena += nodes[i].toString() + "\t";  
//         }  
//         cadena += "\n\nEDGES\n";
//         
//         for (int i = 0; i < numNodes; i++) {  
//             for (int j = 0; j < numNodes; j++) {  
//                 if (edges[i][j])  
//                     cadena += "T\t";  
//                 else  
//                     cadena += "F\t";  
//             }  
//             cadena += "\n";  
//         }
//         cadena += "\nWEIGHTS\n";  
//
//         for (int i = 0; i < numNodes; i++) {  
//             for (int j = 0; j < numNodes; j++) {  
//                 cadena += (edges[i][j]?df.format(weights[i][j]):"-") + "\t";  
//             }
//             cadena += "\n";  
//         }  
//         return cadena;  
//    } 
    
    
    
    //--------------------DIJKSTRA------------------//
    
    /**
    *  Algoritmo de Dijkstra para encontrar el camino de coste mínimo desde nodoOrigen
    *  hasta el resto de nodos. Devuelve el vector D de Dijkstra
    * Si no existe el nodoOrigen (o es inválido como parámetro) devuelve null
    */
    public double[] dijkstra(T nodoOrigen) {
    	if(nodoOrigen == null || getNode(nodoOrigen) == -1){
        	return null;
        }
    	
        dijkstraS = new boolean[numNodes];        
        initializeDijkstraD(nodoOrigen);
        initializeDijkstraP(nodoOrigen);
        
        while(minCost(dijkstraD, dijkstraS) != -1){
	        int minCostNodePos = minCost(dijkstraD, dijkstraS);
	        dijkstraS[minCostNodePos] = true;
	        
	        for(int i=0; i<numNodes; i++){
	        	if(!dijkstraS[i] && edges[minCostNodePos][i]){
		        	if(dijkstraD[minCostNodePos] + weights[minCostNodePos][i] < dijkstraD[i]){
		        		dijkstraD[i] = dijkstraD[minCostNodePos] + weights[minCostNodePos][i];
		        		dijkstraP[i] = minCostNodePos;
		        	}
	        	}
	        }
        }        
        return dijkstraD;
    }
    
    /**
    * Busca el nodo con distancia minima en D al resto de nodos, se le pasa el vector D de dijkstra y
    * el conjunto de visitados (como un vector de booleanos) y devuelve el indice del nodo buscado
    * Si hay varios con mismo coste devuelve el que tenga índica más bajo en el vector de nodos
    * o -1 si el grafo es no conexo o no quedan nodos sin visitar
    */
    private int minCost(double[] vectorD, boolean[] visited) {
    	double minCost = Double.POSITIVE_INFINITY;
    	int nodePos = -1;
    	for(int i=0; i<vectorD.length; i++){
    		if(!visited[i]){
    			if(vectorD[i] < minCost){
    				minCost = vectorD[i];
    				nodePos = i;
    			}
    		}
    	}
    	return nodePos;
    }
    
    /**
     * Método que inicializa la matriz D de dijkstra
     */
	private void initializeDijkstraD(T node) {
		dijkstraD = new double[numNodes];
		int nodePos = getNode(node);
		for (int i = 0; i < numNodes; i++) {
			if (nodePos == i) {
				dijkstraD[i] = 0;
			} else if (edges[nodePos][i]) {
				dijkstraD[i] = new Double(weights[nodePos][i]);
			} else {
				dijkstraD[i] = Double.POSITIVE_INFINITY;
			}
		}
	}
	
	/**
     * Método que inicializa la matriz P de dijkstra
     */
	private void initializeDijkstraP(T node) {
		dijkstraP = new int[numNodes];
		int nodePos = getNode(node);
		for (int i = 0; i < numNodes; i++) {
			if (edges[nodePos][i] || nodePos == i) {
				dijkstraP[i] = nodePos;
			} else {
				dijkstraP[i] = -1;
			}
		}
	}   
	
	
	//-----------------------Floyd---------------------------\\
	
	
	/**
	* Aplica el algoritmo de Floyd al grafo y devuelve 0 si lo aplica y genera matrices A y P;
	* y –1 si no lo hace
	*/
	public int floyd() {
		if(numNodes == 0) {
			return -1;
		}

		this.aFloyd = inicializarAFloyd();
		this.pFloyd = inicializarPFloyd();
		
		for(int k=0; k<numNodes; k++) {
			for(int i=0; i<numNodes; i++) {
				for(int j=0; j<numNodes; j++) {
					if(aFloyd[i][k] + aFloyd[k][j] < aFloyd[i][j]) {
						aFloyd[i][j] = aFloyd[i][k] + aFloyd[k][j];
						pFloyd[i][j] = k + 1;
					}
				}
			}
		}
		//En todas las pos que no haya arista habrá un infinito
		//En la diagonal principal habra 0
		//Matriz P es la ruta que hay que seguir para obtener el coste minimo, se inicializa con guiones por ejemplo -1
		return 0;
	}  
	
	
	private double[][] inicializarAFloyd() {
		this.aFloyd = new double[numNodes][numNodes];
		for(int i=0; i<numNodes; i++) {
			for(int j=0; j<numNodes; j++) {
				if(weights[i][j] != 0) {
					this.aFloyd[i][j] = weights[i][j];
				}
				else if(i == j) {
					this.aFloyd[i][j] = 0;
				}
				else {
					this.aFloyd[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		
		return aFloyd;
	}
	
	
	private int[][] inicializarPFloyd() {
		this.pFloyd = new int[numNodes][numNodes];
		for(int i=0; i<numNodes; i++) {
			for(int j=0; j<numNodes; j++) {
				this.pFloyd[i][j] = -1;
			}
		}
		return pFloyd;
	}
	
	
	/**
	* Devuelve la matriz A de Floyd, con infinito si no hay camino
	* Si no se ha invocado a Floyd debe devolver null  (OJO que no lo invoca)
	*/
	protected double[][] getAFloyd() { 
		if(aFloyd == null) {
			return null;
		}
		return aFloyd;
	} 
	
	
	/**
	* Devuelve la matriz P de Floyd, con -1 en las posiciones en las que no hay nodo intermedio
	* Si no se ha invocado a Floyd debe devolver null  (OJO que no lo invoca)
	*/
	protected int[][] getPFloyd() {
		if(pFloyd == null) {
			return null;
		}
		return pFloyd;
	}
	
	
	/**
	* Devuelve el coste del camino de coste mínimo entre origen y destino según Floyd
	* Si no están generadas las matrices de Floyd, las genera.
	* Si no puede obtener el valor por alguna razón, devuelve –1 (OJO que es distinto de infinito)
	**/
	public double minCostPath(T nodeInicial, T nodeFinal) {
		if(pFloyd == null) {
			inicializarPFloyd();
		}
		if(aFloyd == null) {
			inicializarAFloyd();
		}
		int floyd = floyd();
		int nodeInicialPos = getNode(nodeInicial);
		int nodeFinalPos = getNode(nodeFinal);
		if(floyd != 0 || nodeInicialPos == -1 || nodeFinalPos == -1) {
			return -1;
		}
		double coste = 0.0;
		coste = aFloyd[nodeInicialPos][nodeFinalPos];
		return coste;
	}
	
	//A partir de un nodo recorrer en profundidad hasta donde se llegue. 
	//Empezando por el que se encuentra en posicion mas baja en el vector de nodos.
	//Siempre que se haga un tostring de un nodo se mete un \t detras
	
	
	/**
    * Devuelve un String con la informacion del grafo (incluyendo matrices de Floyd)
    * Si se ha usado para pruebas el toString anterior, sería conveniente renombrarlo antes (mediante refactorizacion)
	* antes de sustituirlo por este, para que sigan pasando las pruebas anteriores sin problemas.
    */
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");  
        String cadena = "";  
   
        cadena += "NODES\n";  
        for (int i = 0; i < numNodes; i++) {  
            cadena += nodes[i].toString() + "\t";  
        }
        cadena += "\n\nEDGES\n";  
        for (int i = 0; i < numNodes; i++) {  
            for (int j = 0; j < numNodes; j++) {  
                if (edges[i][j])  
                    cadena += "T\t";  
                else  
                    cadena += "F\t";  
            }  
            cadena += "\n";  
        }  
        cadena += "\nWEIGHTS\n";  
        for (int i = 0; i < numNodes; i++) {  
            for (int j = 0; j < numNodes; j++) {  
                cadena += (edges[i][j]?df.format(weights[i][j]):"-") + "\t";  
            }  
            cadena += "\n";  
        }

        double[][] aFloyd = getAFloyd();  
        if (aFloyd != null) {
            cadena += "\nAFloyd\n";
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    cadena += df.format(aFloyd[i][j]) + "\t";
                }
                cadena += "\n";  
            }  
        }  

        int[][] pFloyd = getPFloyd();
        if (pFloyd != null) {
                cadena += "\nPFloyd\n";
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    cadena += (pFloyd[i][j]>=0?df.format(pFloyd[i][j]):"-") + "\t";
                }
                cadena += "\n";
            }
        }
        return cadena;
    }
    
    
}   