package p1Algoritmia;

import java.security.InvalidParameterException;

public class Algorithms {
	private static final long SLEEP_TIME = 1;

	public static void doNothing() {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Calcula el factorial de n>=0 de forma recursiva
	 * @param n
	 * @return valor factorial(n)
	 */
	public long factorial (int n) { 
		doNothing();
		if (n<0){
			throw new InvalidParameterException("No permitidos parámetros negativos como "+ n); 
		}
		if (n == 0){
			return 1;
		}
		
		return n * factorial(n-1);
		} 
	
	/**
	 * Calcula el termino n-esimo teniendo en cuenta que el primer término se considera fib(0)=0; y fib(1)=1 
	 * @param n
	 * @return valor fibonacci(n)
	 */
	public int fibonacci (int n) {
		doNothing();
		if (n<0){
			throw new InvalidParameterException("No permitidos parámetros negativos como "+ n);
		}

		if(n == 0){
			return 0;
		}
		
		if(n == 1){
			return 1;
		}

		return fibonacci(n-1) + fibonacci(n-2);
		} 
	
	/**
	 * Calcula 2 elevado a n de forma recursiva 2^n = 2*2^(n-1) 
	 * @param n
	 * @return valor 2^n
	 */
	public long pow2Rec1(int n) {
		doNothing();
		if (n<0) {
			throw new InvalidParameterException("No permitidos parámetros negativos como "+ n); 
		}
		
		if(n == 0){
			return 1;
		}
		
		return 2 * pow2Rec1(n-1);
	} 
	
	/**
	 * Calcula 2 elevado a n de forma recursiva 2^n = 2^(n-1) + 2^(n-1) 
	 * @param n
	 * @return valor 2^n
	 */
	public long pow2Rec2(int n) {
		doNothing();
		if (n<0) {
			throw new InvalidParameterException("No permitidos parámetros negativos como "+ n); 
		}
		
		if(n == 0){
			return 1;
		}
		
		return pow2Rec2(n-1) + pow2Rec2(n-1);
	}
	
	/**
	 * Calcula 2 elevado a n de forma recursiva 2^n = 2^(n/2) * 2^(n/2)
	 * @param n
	 * @return valor 2^n
	 */
	public long pow2Rec3(int n) {
		doNothing();
		if (n<0) {
			throw new InvalidParameterException("No permitidos parámetros negativos como "+ n); 
		}
		
		if(n == 0){
			return 1;
		}
		
		if(n % 2 == 0){
			return pow2Rec3(n/2) * pow2Rec3(n/2);
		} else {
			return pow2Rec3(n/2) * pow2Rec3(n/2) * 2;
		}
	}
	
	/**
	 * Calcula 2 elevado a n de forma recursiva 2^n = como pow2Rec3 pero sin repetir llamada
	 * @param n
	 * @return valor 2^n
	 */
	public long pow2Rec4(int n) {
		doNothing();
		if (n<0) {
			throw new InvalidParameterException("No permitidos parámetros negativos como "+ n); 
		}
		
		if(n == 0){
			return 1;
		}
		
		long res = pow2Rec4(n/2);
		
		if(n % 2 == 0){
			return res * res;
		} else {
			return res * res * 2;
		}
	}
	
	/**
	 * Calcula 2 elevado a n de forma iterativa 
	 * @param n
	 * @return valor 2^n
	 */
	public long pow2Iter (int n) {
	    if (n<0){
	    	throw new InvalidParameterException("No permitidos exponentes negativos como "+ n); 
	    }
	    
	    long res = 2;
	    
	    if(n == 0){
	    	return 1;
	    } 
	    if(n == 1) {
	    	return res;
	    }
	    else {
	    	for (int i = n-1; i > 0; i--) {
	    		doNothing();
	 			res = res * 2;
	 		}
	    }
	    return res;
	}
	
	/**
	 * Calcula a elevado a b de forma recursiva por potencias sucesivas
	 * @param a
	 * @param b
	 * @return valor a^b
	 */
	public int potenciaRec (int a, int b){
		doNothing();
		if (b<0 || a<0){
			throw new InvalidParameterException("No permitido alguno de los parámetros introducidos");
		}

		if(a == 0){
			return 0;
		}
		
		if(b == 0){
			return 1;
		}
		
		return a * potenciaRec(a, b-1);
	}
	
	/**
	 * Calcula el resto de la división a/b de forma recursiva
	 * @param a
	 * @param b
	 * @return resto a/b
	 */
	public int restoDivRec (int a, int b){
		doNothing();
		if (b<0 || a<0){
			throw new InvalidParameterException("No permitido alguno de los parámetros introducidos");
		}
		
		if(a == b){
			return 0;
		}
		
		if(a < b){
			return a;
		}
		
		return restoDivRec(a-b, b);
	}
	
	/**
	 * Calcula la división entera a/b de forma recursiva
	 * @param a
	 * @param b
	 * @return cociente a/b
	 */
	public int divEntRec (int a, int b){
		doNothing();
		if (b<0 || a<0){
			throw new InvalidParameterException("No permitido alguno de los parámetros introducidos");
		}
		
		if(a < b){
			return 0;
		}
		
		if(a == b){
			return 1;
		}
		
		return 1 + divEntRec(a-b, b);
	}
	
	/**
	 * Algoritmo con una complejidad temporal lineal
	 * @param n
	 */
	public void linear (int n) {
		if (n<0){
			throw new InvalidParameterException("No permitidos parámetros negativos como "+ n); 
		}

		for (int i = 0; i <= n; i++) {
			doNothing();
		}
	}
	
	/**
	 * Algoritmo con una complejidad temporal cuadrática 
	 * @param n
	 */
	public void quadratic(int n) {
		if (n<0){
			throw new InvalidParameterException("No permitidos parámetros negativos como "+ n); 
		}
		
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				doNothing();
			}
		}
	} 
	
	/**
	 * Algoritmo con una complejidad temporal cúbica
	 * @param n
	 */
	public void cubic(int n) { 
		if (n<0){
			throw new InvalidParameterException("No permitidos parámetros negativos como "+ n); 
		}

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				for (int k = 0; k <= n; k++) {
					doNothing();
				}
			}
		}
	} 
	
	/**
	 * Algoritmo con una complejidad temporal logarítmica en cualquier base 
	 * @param n
	 */
	public void logarithmic(int n) {
		if (n<0){
			throw new InvalidParameterException("No permitidos parámetros negativos como "+ n); 
		}

		for (int i = 1; i < n; i = i * 2){
		    doNothing();
		}
	} 
	
	/**
	 * Invierte un entero de forma recursiva
	 * @param num
	 * @return String del numero invertido
	 */
	public String invertirEnteroRec(int num) {
        if(num<10){
            return String.valueOf(num);
        } else {
            return String.valueOf(num%10) + invertirEnteroRec(num/10);
        }
    } 
	
}
