package zoot.tds;

import zoot.exceptions.AccumulateurErreurSemantiques;

import java.util.HashMap;

public class Bloc {

    /* TDS du bloc */
    private HashMap<Entree,Symbole> tds  = new HashMap<>();

    /* numéro du bloc */
    int numBloc ;

    /* compteur de déplacements */
    private int cpt = 4;


    private String nomBloc;

    /*  utilse pour la sauvegarde de type de param de la fonction*/
    String[] params ;
    /**
     * constructeur d'un Bloc
     * @param bloc
     *      numero de bloc
     */
    public Bloc(int bloc) {
        this.tds = tds;
        this.numBloc = bloc;
    }

    /**
     * méthode ajouter permettant d'ajouter une variable dans la TDS
     * @param idf
     *      identifiant
     * @param symbole
     *      symbole
     * @param numLigne
     *      numéro de la ligne
     */
    public void ajouter(Entree idf,Symbole symbole,int numLigne){
        //impossible d'eviter la boucle
        for (Entree e : tds.keySet()) {
            if(e.getIdf().equals(idf.getIdf())) {

                if((idf.estEntreeFonction() && e.estEntreeVariable()) || (idf.estEntreeVariable() && e.estEntreeFonction())){
                    String nomFonction = TDS.getInstance().getNomFonctionActuel();
                    System.out.println("ERREUR SEMANTIQUE : " + numLigne + " ligne d'erreur : la Variable '"+e.getIdf()+"' et Fonction '"+e.getIdf()+"' ne peuvent pas porter le meme nom'\n");
                    AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + numLigne + " ligne d'erreur " + " : Double Declaration\n");
                    return;
                }else {
                    if(idf.estEntreeFonction()) {
                        if (idf.getNbrParam() ==  e.getNbrParam()) {
                            //System.out.println("nbr idf " + idf.getNbrParam() + " , nbr symbole : " + e.getNbrParam() + "\n");
                            String nomFonction = TDS.getInstance().getNomFonctionActuel();
                            System.out.println("ERREUR SEMANTIQUE : " + numLigne + " ligne d'erreur :  Double Declaration de la fonction  '" + e.getIdf() + "'\n");
                            AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + numLigne + " ligne d'erreur " + " : Double Declaration\n");
                            return;
                        }
                    }else{
                        String nomFonction = TDS.getInstance().getNomFonctionActuel();
                        System.out.println("ERREUR SEMANTIQUE : " + numLigne + " ligne d'erreur : " + nomFonction+", Double Declaration de la variable  '"+e.getIdf()+"'\n");
                        AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + numLigne + " ligne d'erreur " + " : Double Declaration\n");
                        return;
                    }
                }
            }
        }
        tds.put(idf,symbole);
    }


    /**
     * méthode permettant d'identifier une variable dans la TDS
     * @param idf
     *      identifiant à identifier
     * @param noLigne
     *      numero de la ligne
     * @return Symbole recherché si existant sinon null
     */
    public Symbole identifier(Entree idf,int noLigne){

        for (Entree e : tds.keySet()) {   // tester si la variable existe dans la tds
            if( e.getIdf().equals(idf.getIdf()) && idf.getNbrParam() == e.getNbrParam()) {
                return tds.get(e);
            }
        }
        int i = this.numBloc;

        if (i != 1){
            Symbole s =   TDS.getInstance().identifierSansErreur(idf,1,noLigne);
            if(s != null)
                return  s;
        }

        // sinon on ajoute une erreur semantique afin de signaler que la variable recherchée n'est pas declarée
        if(idf.estEntreeVariable()){
            //System.out.println("----->"+this.numBloc);
            String nomFonction = TDS.getInstance().identifierNomFonction(i);
            System.out.println("ERREUR SEMANTIQUE : " + noLigne + " ligne d'erreur : " +nomFonction +", la Variable '"+idf.getIdf()+"'  est non decalarée\n");
            AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + noLigne + " ligne d'erreur " + " : Variable "+idf.getIdf()+"   non decalarée\n");
        }else{
            //System.out.println("----->"+this.numBloc);
            String nomFonction = TDS.getInstance().identifierNomFonction(i);
            System.out.println("ERREUR SEMANTIQUE : " + noLigne + " ligne d'erreur : la Fonction '"+idf.getIdf()+"'  est non decalarée\n");
            AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + noLigne + " ligne d'erreur " + " : Fonction "+idf.getIdf()+"   non decalarée\n");
        }
        return null;
    }



    public Symbole identifierSansErreur(Entree idf,int noLigne){

        for (Entree e : tds.keySet()) {   // tester si la variable existe dans la tds
            if( e.getIdf().equals(idf.getIdf()) && idf.getNbrParam() == e.getNbrParam()) {
                return tds.get(e);
            }
        }
        int i = this.numBloc;

        if (i != 1){
            Symbole s =   TDS.getInstance().identifier(idf,1,noLigne);
            if(s != null)
                return  s;
        }
        return null;
    }
    /**
     * méthode permettant d'identifier un Symbole à partir du numéro de bloc
     * @param numBloc
     *      numéro du bloc
     * @return symbole
     */
    public Symbole identifierFonction(int numBloc){

        for (Entree e : tds.keySet()) {   // tester si la variable existe dans la tds
            if(e.estEntreeFonction()) {
                if(tds.get(e).getBlocRepresenter() == numBloc)
                    return tds.get(e);
            }
        }
        return null;
    }

    public String identifierNomFonction(int numBloc){

        for (Entree e : tds.keySet()) {   // tester si la variable existe dans la tds
            if(e.estEntreeFonction()) {
                if(tds.get(e).getBlocRepresenter() == numBloc)
                    return e.getIdf();
            }
        }
        return "";
    }

    /**
     * getteur TailleZoneVariable
     * @return int
     */
    public int getTailleZoneVariable(){
        int cpt = 0;
        for(Entree e : this.tds.keySet())
            if (e.estEntreeVariable())
                cpt++;
        return cpt*4;
    }

    /**
     * méthode permettant d'incrementer le compteur de deplacements
     * @return cpt
     */
    public int incrementerCpt(){
        this.cpt = this.cpt-4;
        return this.cpt;
    }

    /**
     * method toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Bloc   "+ this.numBloc+"\n");
        for (Entree e : tds.keySet()){
            Symbole b = tds.get(e);
            stringBuilder.append(e.toString() +"   ---->    "+ b.toString()+"\n");
        }

        return stringBuilder.toString();

    }

    public int getNbVariable(){
        return this.tds.size();
    }



    public void selectionerLesvarPar(){
        this.params = new String[this.getNbVariable()]; //
        for (Entree e : this.tds.keySet()){
            Symbole b = tds.get(e);
            int indice = b.getDeplacement()/-4;
            this.params[indice] = b.getType();
        }
    }


    public String[] getParams() {
        return params;
    }
}

