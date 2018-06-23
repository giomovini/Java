//O vetor deve ser ordenado crescente ate x e decrescente a partir de x, a busca retorna x, que no caso e o maior elemento

public class Main {

	public static void main(String[] args) {
		int v2[] = {0,1,6,7,8,9,10,9,8};
		
		imprimir(v2, v2.length);
		
		int r = busca(v2, 0, v2.length-1,0);
		
		System.out.println(r);
	}
	public static int busca(int v[], int ini, int n,int verifica) {

		if(ini == n ) return v[n];
		
		if(verifica == 0) {
			if(n>1 && v[0] > v[1]) {
				return -1;
			}
			if(n>1 && v[n] > v[n-1]) { 
				return -1;
			}
			verifica = 1;
		}
		
		int meio = ini+((n-ini)/2);
	
		if(v[meio]>v[meio+1]) {
			return busca(v,ini,meio,verifica);
		}else {
			return busca(v,meio+1,n,verifica);
		}
	}
	
	
	

	public static void imprimir(int v[], int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(v[i] + " ");
		}
		System.out.println();
	}

}
