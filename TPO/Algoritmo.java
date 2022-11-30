import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Algoritmo

{
  
    private int N, cantEmparejados;
    private String[][] proponentePref;
    private String[][] propuestoPref;
    private String[] proponente;
    private String[] propuesto;
    private String[] propuestoPareja;
    private boolean[] proponenteEmparejado;
    private ArrayList propuestosPerfectos;

   
    public Algoritmo(String[] m, String[] w, String[][] mp, String[][] wp) {
        N = mp.length;
        cantEmparejados = 0;
        proponente = m;
        propuesto = w;
        proponentePref = mp;
        propuestoPref = wp;
        proponenteEmparejado = new boolean[N];
        propuestoPareja = new String[N];
        propuestosPerfectos= new ArrayList<>();
        calcMatches();
    }

  
    private void verificarParejaPerfecta(String proponente,String propuesto, int indice){
        //si el propuesto tiene en primer lugar al proponente es decir es el primero que prefiere lo agrega a la lista
        System.out.println("VERIFICANDO PAREJA PERFECTA");
        System.out.println(" Propuesto "+ propuesto);
        System.out.println(" Proponente "+ proponente);
        System.out.println(" Primero en la lista de propeusto "+ propuestoPref[indice][0]);
        if (propuestoPref[indice][0].equals(proponente) ){
            System.out.println(propuesto + " acepto propuesta y es pareja perfecta ");
            propuestosPerfectos.add(propuesto);
        }
       

    }
    private void calcMatches() {
        while (cantEmparejados < N) {
            int j;
            for (j = 0; j < N; j++)
                if (!proponenteEmparejado[j])
                    break;
                System.out.println("PROPONENTE "+ proponente[j]);
            for (int i = 0; i < N && !proponenteEmparejado[j]; i++) {
                System.out.println("Le pregunta a "+ proponentePref[j][i]);

                int indice = propuestoObtenerIndice(proponentePref[j][i]);
                //si el propuesto no tiene a su pareja perfecta entonces pregunta 
                if (!propuestosPerfectos.contains(propuesto)){

                    if (propuestoPareja[indice] == null) {
                        propuestoPareja[indice] = proponente[j];
                        proponenteEmparejado[j] = true;
                        cantEmparejados++;
                        System.out.println(proponentePref[j][i] + " acepto propuesta ");
                        this.verificarParejaPerfecta(proponente[j], proponentePref[j][i], indice);
    
                    } else {
                        String parejaActual = propuestoPareja[indice];
                        if (mayorPreferencia(parejaActual, proponente[j], indice)) {
                            propuestoPareja[indice] = proponente[j];
                            proponenteEmparejado[j] = true;
                            proponenteEmparejado[proponenteObtenerIndice(parejaActual)] = false;
                            System.out.println(proponentePref[j][i] + " acepto propuesta ");
                            this.verificarParejaPerfecta(proponente[j], proponentePref[j][i], indice);

                        }
                    }
                }
               
            }
        }
        mostrarEmparejamiento();
    }

    /**
     * funcion para verificar si el propuesto prefiere al proponente actual o al
     * anterior
     **/
    private boolean mayorPreferencia(String proponenteActual, String nuevoProponente, int index) {
        for (int i = 0; i < N; i++) {
            if (propuestoPref[index][i].equals(nuevoProponente))
                return true;
            if (propuestoPref[index][i].equals(proponenteActual))
                return false;
        }
        return false;
    }

    /** get proponente index **/
    private int proponenteObtenerIndice(String str) {
        for (int i = 0; i < N; i++) {
            if (proponente[i].equals(str))
                return i;
        }
        return -1;
    }

    /** get propuesto index **/
    private int propuestoObtenerIndice(String str) {
        for (int i = 0; i < N; i++) {

            if (propuesto[i].equals(str))
                return i;
        }
        return -1;
    }

    /** imprimir parejas **/
    public void mostrarEmparejamiento() {
        System.out.println("Emparejados : ");
        for (int i = 0; i < N; i++) {
            System.out.println(propuestoPareja[i] + " " + propuesto[i]);
        }
    }

    /** main function **/

    public static String[] generarRubros(int n) {
        String[] r = new String[n];
        for (int i = 0; i < n; i++) {
            r[i] = "R" + (i);
        }
        return r;
    }

    public static String[] generarEmpleados(int n) {
        String[] emp = new String[n];
        for (int i = 0; i < n; i++) {
            emp[i] = "E" + (i);
        }
        return emp;
    }

    public static String[][] rubrosPreferencia(int n) {

        String[][] rPref = new String[n][n];
        for (int i = 0; i < n; i++) {
            System.out.println("Rub " + i);
            ArrayList a = generarSinRepetidos(n, i);
            for (int j = 0; j < n; j++) {

                rPref[i][j] = "E" + a.get(j).toString();
                System.out.print(rPref[i][j].toString() + " ");
            }
            System.out.println("");

        }
        
        System.out.println("---------------------------------------------------------------------------");
        return rPref;
    }

    public static String[][] empleadosPreferencia(int n) {

        String[][] empPref = new String[n][n];
        for (int i = 0; i < n; i++) {
            System.out.println("Emp " + i);
            ArrayList a = generarSinRepetidos(n, i);
            for (int j = 0; j < n; j++) {
                empPref[i][j] = "R" + a.get(j).toString();
                System.out.print(empPref[i][j].toString() + " ");
            }
            System.out.println("");

        }
        
        System.out.println("---------------------------------------------------------------------------");
        return empPref;
    }

    public static ArrayList generarSinRepetidos(int n, int p) {
        ArrayList a = new ArrayList<>();
        Random r = new Random();
        while (a.size() < n) {
            int j = r.nextInt(n);
            while (a.contains(j)) {
                j = r.nextInt(n);
            }
            a.add(j);
        }
        return a;

    }

    public static void main2 (){
        System.out.println("Gale Shapley Marriage Algorithm\n");
        /** proponentes **/
        String[] m = {"M1", "M2", "M3", "M4", "M5"};
        /*propuestos **/
        String[] w = {"W1", "W2", "W3", "W4", "W5"};
 
        /** men preference **/
        String[][] mp = {{"W5", "W2", "W3", "W4", "W1"}, 
                         {"W2", "W5", "W1", "W3", "W4"}, 
                         {"W4", "W3", "W2", "W1", "W5"}, 
                         {"W1", "W2", "W3", "W4", "W5"},
                         {"W5", "W2", "W3", "W4", "W1"}};
        /** women preference **/                      
        String[][] wp = {{"M5", "M3", "M4", "M1", "M2"}, 
                         {"M1", "M2", "M3", "M5", "M4"}, 
                         {"M4", "M5", "M3", "M2", "M1"},
                         {"M5", "M2", "M1", "M4", "M3"}, 
                         {"M2", "M1", "M4", "M3", "M5"}};
 
        Algoritmo gs = new Algoritmo(m, w, mp, wp);     
        System.out.println(" Respuesta Esperada M4 W1, M2 W2, M5 W3, M3 W4, M1 W5 ");
          
    }
    public static void main(String[] args) {
        main2();

        /*

        Scanner leer = new Scanner(System.in);
        System.out.println(" Ingrese el n");
        int n = leer.nextInt();
        System.out.println(
                "Ingrese 1 sea desea priorizar la motivacion de los empleados o 2 si prefiere priorizar la distribucion de recursos humanos");
        int i = leer.nextInt();
        // motivacion proponentes empleados
        // distribucion proponentes rubbros
        String[] proponente = new String[n];
        String[] propuesto = new String[n];
        String[][] propPref = new String[n][n];
        String[][] propuestoPref = new String[n][n];

        if (i == 1) {
            proponente = generarEmpleados(n);
            propuesto = generarRubros(n);
            propPref = empleadosPreferencia(n);

            propuestoPref = rubrosPreferencia(n);

        } else {
            proponente = generarRubros(n);
            propuesto = generarEmpleados(n);
            propPref = rubrosPreferencia(n);
            propuestoPref = empleadosPreferencia(n);

        }

        Algoritmo gs = new Algoritmo(proponente, propuesto, propPref, propuestoPref);*/
    }
}