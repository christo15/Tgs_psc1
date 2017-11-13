/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RickyWahyudi
 */
public class GeneticAlgorithm {

    public BridgeCrossing[] bc;
    public String[] kromosom;

    public GeneticAlgorithm(int input) {
        bc = new BridgeCrossing[input];
        kromosom = new String[input];
        int banyak = (int) (Math.random() * 6 + 4);
        for (int i = 0; i < bc.length; i++) {
            bc[i] = new BridgeCrossing(banyak);
            kromosom[i] = bc[i].solve();
        }
    }
    
    public void soutKromosom(){
        for (int i = 0; i < kromosom.length; i++) {
            System.out.println(kromosom[i]);
        }
    }
    
    public static void main(String[] args){
        //GeneticAlgorithm ga=new GeneticAlgorithm(10);
        //ga.soutKromosom();
        BridgeCrossing bc = new BridgeCrossing(5);
        bc.soutPpl();
        System.out.println(bc.solve());
    }

}
