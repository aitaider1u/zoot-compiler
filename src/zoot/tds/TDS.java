package zoot.tds;

import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.AccumulateurErreurSemantiques;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Table des symboles
 */
public class TDS {

    /* instance de la classe TDS */
    private static TDS instance = new TDS();

    /* la table des symbole se présente sous la forme d'une HashMap associant une Entree à un Symbole */
    //private HashMap<Entree,Symbole> tds  = new HashMap<>();
    //private ArrayList<Bloc> blocs = new ArrayList<>();
    private HashMap<Integer,Bloc> blocs = new HashMap<>();

    private String nomFonctionActuel = "Dans le Programme Principal"; // utile pour rendre le message de la double decaleration plus claire ,


    /**
     * numéro du bloc courant
     */
    private int blocActuel = 0 ;

    /* compteur de déplacements */
    //private int cpt = 4;

    private int cptBloc = 0 ;
    //private int blocActuel = -1;


    /**
     * constructeur vide
     * */
    private TDS() {
        //this.blocs.put( this.blocActuel , new Bloc(this.blocActuel));
    }

    /**
     * méthode permettant d'ajouter une variable dans la TDS
     * @param idf
     *      identifiant de la variable
     * @param symbole
     *      Symbole de la variable
     * @param numLigne
     *      numéro de la ligne dans laquelle est déclarée la variable
     */
    public void ajouter(Entree idf,Symbole symbole,int numLigne){
        this.blocs.get(blocActuel).ajouter(idf,symbole,numLigne);
    }

    /**
     * méthode permettant d'identifier une variable dans la TDS
     * @param idf
     *      identifiant à identifier
     * @param noLigne
     *      numero de la ligne
     * @return Symbole recherché si existant sinon null
     */
    // utlise si identifier est appeler pendant l'analyse lexical et syntaxique
    public Symbole identifier(Entree idf,int noLigne){
        return this.blocs.get(blocActuel).identifier(idf,noLigne);

    }

    // utilise si la focntion est appeler pendant de l'analyse semantique
    public Symbole identifier(Entree idf,int numBloc ,int noLigne){
        Symbole s = this.blocs.get(numBloc).identifier(idf,noLigne);
        return s;
    }
    public Symbole identifierSansErreur(Entree idf,int numBloc ,int noLigne){
        Symbole s = this.blocs.get(numBloc).identifierSansErreur(idf,noLigne);
        return s;
    }

    /**
     * méthode permettant d'incrementer le compteur de deplacements
     * @return cpt
     */
    public int incrementerCpt(){
        return this.blocs.get(blocActuel).incrementerCpt();
    }

    /**
     * getteur TailleZoneVariable
     * @return int
     */

    public int getTailleBlocActeul(){
        //return this.blocs.get(0);
        return this.blocs.get(blocActuel).getTailleZoneVariable();
    }


   public int getTailleBlocIndex(int index){
        return this.blocs.get(index).getTailleZoneVariable();
    }



    /**
     * getteur de l'instance TDS
     * @return instance
     */
    public static TDS getInstance() {
        return instance;
    }

    /**
     * entrée d'un bloc
     */
    public void entreBloc(){
        this.cptBloc++;
        this.blocActuel = this.cptBloc;
        //System.out.println("--------------> "+ this.blocActuel);
        this.blocs.put( this.blocActuel , new Bloc(this.blocActuel));
    }

    /**
     * sortie d'un bloc
     */
    public void sortieBloc(){
        this.blocActuel = 1;
    }

    /**
     * method toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Bloc e : this.blocs.values()) {
            stringBuilder.append(e.toString());
            stringBuilder.append("___________________________________________________________________\n");
        }
        return stringBuilder.toString();
    }

    /**
     * getteur du compteur de bloc
     * @return cptBloc
     */
    public int getCptBloc() {
        return cptBloc;
    }

    /**
     * getteur du bloc actuel
     * @return blocActuel
     */
    public int getBlocActuel (){
        return this.blocActuel;
    }

    /**
     * getteur de bloc par son index
     * @param index
     *      index du bloc
     * @return Bloc
     */
    public Bloc getBlocIndex(int index ){
        return  this.blocs.get(index);
    }



    /**
     * identifier une fonction dans le bloc zeo
     * @param numBloc
     *      numéro du bloc
     * @return Symbole
     */
    public Symbole identifierFonction(int numBloc){
        return this.blocs.get(1).identifierFonction(numBloc);
    }
    /**
     * identifier le nom de la fonction dans le bloc zero utile pour les message d'erreur;
     * @param numBloc
     *      numéro du bloc
     * @return Symbole
     */
    public String identifierNomFonction(int numBloc){
        if (numBloc == 1)
            return "Dans le Programme Principal";
        return "Dans la Fonction "+ this.blocs.get(1).identifierNomFonction(numBloc);
    }





    public int getNbVariable()
    {
        return this.blocs.get(blocActuel).getNbVariable();
    }

    public void selectionerLesvarPar(){
        this.blocs.get(blocActuel).selectionerLesvarPar();
    }

    public String[] getParams(int index) {
        return this.blocs.get(index).getParams();
    }

    public int getNbParams(int index) {
        if (this.blocs.get(index).getParams() == null)
            return 0;
        return this.blocs.get(index).getParams().length;
    }

    public int getZoneMemoireVariable(int index) {
        return this.blocs.get(index).getNbVariable();
    }


    public String getNomFonctionActuel() {
        return nomFonctionActuel;
    }

    public void setNomFonctionActuel(String nomFonctionActuel) {
        this.nomFonctionActuel = "Dans la fonction "+nomFonctionActuel;
    }
}
