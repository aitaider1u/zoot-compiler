package zoot.outils;

import java.util.LinkedList;

public class Registres {
    private LinkedList<String> regs = new LinkedList<>();

    public Registres(int nbr){
        this.init(nbr);
    }

    public Registres(){}



    public void init(int i){
        this.regs.add("$v0");
        for (int j = 0; j < i-1; j++) {
            this.regs.add("$t"+j);
        }
    }

    public String getFirtRegistre(){
        return this.regs.getFirst();
    }

    public String extractFirtRegistre(){
        return this.regs.removeFirst();
    }

    public boolean isEmpty(){
        return this.regs.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < regs.size(); i++) {
            str.append(regs.get(i)+"  -  " );
        }
        return str.toString();
    }

    public Registres getCopy(){
        Registres copy = new Registres();
        for (int i = 0; i < this.getSize(); i++) {
            copy.addRegiste(this.getRegIndex(i));
        }
        return copy;
    }

    public String getRegIndex(int i ){
        return this.regs.get(i);
    }

    public void addRegiste(String r){
        this.regs.add(r);
    }

    public int getSize(){
        return this.regs.size();
    }
}
