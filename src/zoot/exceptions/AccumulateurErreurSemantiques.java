package zoot.exceptions;

import java.util.ArrayList;

public class AccumulateurErreurSemantiques {
    private static AccumulateurErreurSemantiques instance = new AccumulateurErreurSemantiques();

    private ArrayList <String>  erreurSementiques = new ArrayList<>();

    private ArrayList <Integer> nbrDeRetouneBloc = new ArrayList<>();  //utile pour verifier que les fonction ont tous au moins une instruction retourne
    private int indexBloc = -1; //utile pour le array nbrDeRetouneBloc

    private boolean onEstDansLeProgrammePrincipal = false;
    private boolean isSi = false;

    private AccumulateurErreurSemantiques() {  }


    public static AccumulateurErreurSemantiques getInstance() {
        return instance;
    }


    public void  ajouter(String error){
        erreurSementiques.add(error);
    }


    public int nbErreurSementique(){
        return this.erreurSementiques.size();
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String s : erreurSementiques ){
            str.append(s);
        }
        return  str.toString();
    }

    public void incrementerLeNumDeBloc(){
        this.indexBloc++;
        this.nbrDeRetouneBloc.add(0);
    }

    public void incrementerNbrDeRetourneDuBlocindex(){
        this.nbrDeRetouneBloc.set(indexBloc,this.nbrDeRetouneBloc.get(indexBloc)+1);
    }

    public int nbrDeRetourneDuBlocIndex(){
        return this.nbrDeRetouneBloc.get(this.indexBloc);
    }

    public void clear(){
        this.erreurSementiques.clear();
    }


    public boolean onEstLeProgrammePrincipal(){
        return this.onEstDansLeProgrammePrincipal;
    }

    public void setOnEstLeProgrammePrincipal( Boolean b){
        this.onEstDansLeProgrammePrincipal = b;
    }

    public boolean existSemantiqueErreur(){
        return  this.erreurSementiques.size()>0;
    }

    public int size(){
        return this.nbrDeRetouneBloc.size();
    }

    public boolean isSi() {
        return isSi;
    }

    public void setSi(boolean si) {
        isSi = si;
    }
}

