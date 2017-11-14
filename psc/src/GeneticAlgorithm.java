
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

    public String[] kromosom;

    public GeneticAlgorithm(int input) {

    }

    public void soutKromosom() {
        for (int i = 0; i < kromosom.length; i++) {
            System.out.println(kromosom[i]);
        }
    }

    public static String bacaFile(String namaFile) {
        BufferedReader br = null;
        String stringHasil = "src/";

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(namaFile));
            while ((sCurrentLine = br.readLine()) != null) {
                stringHasil = stringHasil + sCurrentLine + "\n";
            }

        } catch (IOException e) {
            System.out.println("Gagal membaca file " + namaFile);
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stringHasil;
    }
    
    public static void tulisFile(String teks, String namaFile) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new FileWriter(namaFile, true)));
            out.println(teks);
            out.close();
        } catch (IOException e) {
            System.out.println("Gagal menulis ke file " + namaFile);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm(10);

        BridgeCrossing bc = new BridgeCrossing(5);
        bc.soutPpl();
        System.out.println(bc.solve());
        ga.kromosom = bc.kromosom.split(";");
        for (int i = 0; i < ga.kromosom.length; i++) {
            System.out.println(ga.kromosom[i]);
        }
    }

}
