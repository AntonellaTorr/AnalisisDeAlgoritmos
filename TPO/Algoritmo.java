import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Algoritmo

{
   //proponente HOMBRE
   //propuesto MUJER
    private int N, cantEmparejados;
    private String[][] proponentePref;
    private String[][] propuestoPref;
    private String[] proponente;
    private String[] propuesto;
    private String[] propuestoPareja;
    private boolean[] proponenteEmparejado;
 
    /** Constructor **/
    public Algoritmo(String[] m, String[] w, String[][] mp, String[][] wp)
    {
        N = mp.length;
        cantEmparejados = 0;
        proponente = m;
        propuesto = w;
        proponentePref = mp;
        propuestoPref = wp;
        proponenteEmparejado = new boolean[N];
        propuestoPareja = new String[N];
        calcMatches();
    }
    /** function to calculate all matches **/

  
    private void calcMatches()
    {
        while (cantEmparejados < N)
        {
            int j;
            for (j = 0; j < N; j++)
                if (!proponenteEmparejado[j])
                    break;
 
            for (int i = 0; i < N && !proponenteEmparejado[j]; i++)
            {
                int indice = propuestoObtenerIndice(proponentePref[j][i]);
                if (propuestoPareja[indice] == null)
                {
                    propuestoPareja[indice] = proponente[j];
                    proponenteEmparejado[j] = true;
                    cantEmparejados++;
                    
                }
                else
                {
                    String parejaActual = propuestoPareja[indice];
                    if (mayorPreferencia(parejaActual, proponente[j], indice))
                    {
                        propuestoPareja[indice] = proponente[j];
                        proponenteEmparejado[j] = true;
                        proponenteEmparejado[proponenteObtenerIndice(parejaActual)] = false;
                    }
                }
            }            
        }
        printCouples();
    }
    /** funcion para verificar si el propuesto prefiere al proponente actual o al anterior **/
    private boolean mayorPreferencia(String proponenteActual, String nuevoProponente, int index)
    {
        for (int i = 0; i < N; i++)
        {
            if (propuestoPref[index][i].equals(nuevoProponente))
                return true;
            if (propuestoPref[index][i].equals(proponenteActual))
                return false;
        }
        return false;
    }
    /** get men index **/
    private int proponenteObtenerIndice(String str)
    {
        for (int i = 0; i < N; i++)
            if (proponente[i].equals(str))
                return i;
        return -1;
    }
    /** get women index **/
    private int propuestoObtenerIndice(String str)
    {
       
        for (int i = 0; i < N; i++)
            if (propuesto[i].equals(str))
                return i;
        return -1;
    }
    /** print couples **/
    public void printCouples()
    {
        System.out.println("Couples are : ");
        for (int i = 0; i < N; i++)
        {
            System.out.println(propuestoPareja[i] +" "+ propuesto[i]);
        }
    }

    /** main function **/

    public static String [] generarRubros(int n){
        String[] r= new String[n];
        for (int i=0;i<n;i++){
            
            r[i]= "R" +(i+1);
        }
        return r;
    }
    public static String [] generarEmpleados(int n){
        String[] emp= new String[n];
        for (int i=0;i<n;i++){
            
            emp[i]= "E" +(i+1);
        }
        return emp;
    }
    public static String [][] rubrosPreferencia (int n){
       
        String [][] rPref= new String [n][n];
        for (int i=0;i<n;i++){
            System.out.println("Rub "+i);
            ArrayList a=generarSinRepetidos(n, i);
            for (int j=0;j<n;j++){
             
                rPref[i][j]="E" + a.get(j).toString();
                System.out.print(rPref[i][j].toString()+ " ");
            }
            System.out.println("");
           

        }
        return rPref;
    }
    public static String [][] empleadosPreferencia (int n){
       
        String [][] empPref= new String [n][n];
        for (int i=0;i<n;i++){
            System.out.println("Emp "+i);
            ArrayList a=generarSinRepetidos(n, i);
            for (int j=0;j<n;j++){
             
                empPref[i][j]="R" +a.get(j).toString();
                System.out.print(empPref[i][j].toString() +" ");
            }
            System.out.println("");
           

        }
        System.out.println("---------------------------------------------------------------------------");
        return  empPref;
    }
    public static ArrayList generarSinRepetidos(int n, int p){
        ArrayList a= new ArrayList<>();
        Random r= new Random();
        while (a.size()<n){
            int j=r.nextInt(n+1);
            //controla que no se ponga a si mismo 
            while (a.contains(j) && j==p){
                j=r.nextInt(n+1);
            }
            a.add(j);
        }
        return a;

    }
    
    public static void main(String[] args) 
    {
       
        Scanner leer= new Scanner(System.in);
        System.out.println(" Ingrese el n");
        int n=leer.nextInt();
        System.out.println("Ingrese 1 sea desea priorizar la motivacion de los empleados o 2 si prefiere priorizar la distribucion de recursos humanos");
        int i=leer.nextInt();
        //motivacion proponentes empleados
        //distribucion proponentes rubbros
        String [] proponente= new String [n];
        String [] propuesto= new String [n];
        String [][]propPref= new String [n][n];
        String [][]propuestoPref= new String [n][n];

        if (i==1){
            proponente= generarEmpleados(n);
             propuesto=generarRubros(n);
             propPref=empleadosPreferencia(n);
          
             propuestoPref= rubrosPreferencia(n);

        }
        else{
            proponente= generarRubros(n);
            propuesto=generarEmpleados(n);
            propPref= rubrosPreferencia(n);
            propuestoPref=empleadosPreferencia(n);
       

        }
       

 
        Algoritmo gs = new Algoritmo(proponente, propuesto, propPref, propuestoPref);                        
    }
}