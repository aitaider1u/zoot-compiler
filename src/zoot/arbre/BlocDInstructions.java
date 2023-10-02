package zoot.arbre;

import zoot.arbre.instructions.Instruction;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.tds.TDS;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    /**
     * liste des instructions formant le programme
     */
    protected ArrayList<Instruction> programme ;

    /**
     * taille zoneMemoireVariable
     */
    protected int zoneMemoireVariable;

    protected int nbrDeParamDeLafonction = 0 ;
    protected int numBloc;

    /**
     * constructeur d'un bloc d'instruction
     * @param n
     *      numero de la ligne
     */
    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
        this.numBloc = TDS.getInstance().getCptBloc();
    }

    /**
     * methode ajoutant une instruction au programme
     * @param i
     *      instruction à ajouter
     */
    public void ajouter(Instruction i) {
        programme.add(i) ;
    }

    /**
     * methode verifier
     */
    @Override
    public void verifier()
    {
        //throw new UnsupportedOperationException("fonction verifier non définie ") ;


        zoneMemoireVariable = TDS.getInstance().getTailleBlocIndex(this.numBloc);
        this.nbrDeParamDeLafonction = TDS.getInstance().getNbParams(this.numBloc);
        for (Instruction inst : programme) {
                inst.verifier();
        }

    }


    /**
     * methode toMIPS
     * @return code MIPS
     */
    @Override
    public String toMIPS() {
        StringBuilder strBuilder = new StringBuilder();
        //


        for (Instruction inst : programme) {
            strBuilder.append(inst.toMIPS()+"\n");
        }
        return strBuilder.toString();
    }

    /**
     * method toString
     * @return String
     */
    @Override
    public String toString() {
        return programme.toString();
    }

    public int getZoneMemoireVariable() {
        return zoneMemoireVariable;
    }




    public int getNumBloc() {
        return numBloc;
    }

    public void setNumBloc(int numBloc) {
        this.numBloc = numBloc;
    }

    public ArrayList<Instruction> getInstructions() {
        return programme;
    }

    @Override
    public boolean hasRetourne() {
        boolean a = false;
        for (Instruction i: this.programme) {
            if (i.hasRetourne())
                return true;
        }
        return false;
    }
}
