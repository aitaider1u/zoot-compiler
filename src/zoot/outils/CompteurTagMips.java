package zoot.outils;

public class CompteurTagMips {


    private int nbrSi = 0;
    private int nbrNon = 0;
    private int nbrRepeter = 0;
    private int nbrSup = 0;
    private int nbrEgale = 0;
    private int nbrSiSinon = 0;


    private static CompteurTagMips instance = new CompteurTagMips();

    private CompteurTagMips() {
        //this.blocs.put( this.blocActuel , new Bloc(this.blocActuel));
    }


    public static CompteurTagMips getInstance() {
        return instance;
    }

    public int incrementerNbrSi(){
        this.nbrSi++;
        return this.nbrSi;
    }

    public int incrementerNbrSup(){
        this.nbrSup++;
        return this.nbrSup;
    }
    public int incrementerNbrNon(){
        this.nbrNon++;
        return this.nbrNon;
    }

    public int incrementerNbrEgale(){
        this.nbrEgale++;
        return this.nbrEgale;
    }

    public int incrementerNbrRepeter(){
        this.nbrRepeter++;
        return this.nbrRepeter;
    }

    public int incrementerNbrSiSinon(){
        this.nbrSiSinon++;
        return this.nbrSiSinon;
    }


    public int getNbrSi() {
        return nbrSi;
    }
    public int getNbrNon() {
        return nbrNon;
    }
    public int getNbrRepeter() {
        return nbrRepeter;
    }
    public int getNbrSup() {
        return nbrSup;
    }
    public int getNbrEgale() {
        return nbrEgale;
    }

    public int getNbrSiSinon() {
        return nbrSiSinon;
    }
}
