package zoot;

import zoot.analyse.AnalyseurLexical;
import zoot.analyse.AnalyseurSyntaxique;
import zoot.arbre.ArbreAbstrait;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.exceptions.AnalyseException;
import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.TDS;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Zoot {




    public Zoot(String nomFichier) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(nomFichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;

            //DO NOTHING IN zoot0
            arbre.verifier() ;
            if(!AccumulateurErreurSemantiques.getInstance().existSemantiqueErreur()){
                System.out.println("COMPILATION OK") ;
               //System.out.println(TDS.getInstance().toString());
                String nomSortie = nomFichier.replaceAll("[.]zoot", ".mips") ;
                PrintWriter flot = new PrintWriter(new BufferedWriter(new FileWriter(nomSortie))) ;
                flot.println(arbre.toMIPS());
                flot.close() ;
            }



        }
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + nomFichier + " inexistant") ;
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(Zoot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar zoot.jar <fichierSource.zoot>") ;
            System.exit(1) ;
        }
        new Zoot(args[0]) ;
    }
    
}
