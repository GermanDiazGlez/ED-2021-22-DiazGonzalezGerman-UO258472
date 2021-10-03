package p2Grafos;

import java.text.DecimalFormat;

public class Graph <T>{   

    protected T[] nodes; // Vector de nodos
    protected boolean[][] edges; // matriz de aristas
    protected double[][] weights; // matriz de pesos
    protected int numNodes; // número de elementos en un momento dado
    

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
        	edges[numNodes][numNodes] = false;
        	weights[numNodes][numNodes] = 0;
        	numNodes++;
    	}
    	
    	return res;
    }
    
    /**
    * Obtiene el índice de un nodo en el vector de nodos, y –1 si no existe
    * ¡¡¡ OJO que no es público porque depende de la implementación !!!
    */
    protected int getNode(T node) {
    	for(int i=0; i<numNodes; i++){
    		if(node.equals(nodes[i])){
    			return i;
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
        	}
        	if(edgeWeight > 0.0) {
	        	edges[sourcePos][targetPos] = true;
	        	weights[sourcePos][targetPos] = edgeWeight;
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
    		T nodeFinal = nodes[numNodes-1];
    		
    		for(int i=0; i<numNodes; i++) {
    			edges[i][nodePos] = false;
    			edges[nodePos][i] = false;
    		}
    		
    		for(int i=0; i<numNodes; i++) {
    			if(edges[i][numNodes-1] != false) {
    				edges[i][nodePos] = true;
    				weights[i][nodePos] = getEdge(nodes[i], nodeFinal);
    				removeEdge(nodes[i], nodes[numNodes-1]);
    			}
    			if(edges[numNodes-1][i] != false) {
    				edges[nodePos][i] = true;
    				weights[nodePos][i] = getEdge(nodeFinal, nodes[i]);
    				removeEdge(nodes[numNodes-1], nodes[i]);
    			}
    		}
    		
    		nodes[nodePos] = nodeFinal;
    		numNodes--;
    		
    		return 0;
    	}
    	return -1;
    }
    
    
    /**
      * Devuelve un String con la informacion del grafo  
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
         return cadena;  
    } 
    
   
}   