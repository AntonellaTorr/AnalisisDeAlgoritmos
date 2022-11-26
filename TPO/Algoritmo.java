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

    private void esElMejor(){
        
    }
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
    public static void main(String[] args) 
    {
        System.out.println("Gale Shapley Marriage Algorithm\n");
        /** list of men **/
        String[] m = {"M1", "M2", "M3", "M4", "M5"};
        /** list of women **/
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
    }
}