package bak;

public class GestionDame {

    public static int pionDevientDames(int[][] plateau, int[] caseArrivee){
        int valeurCase = plateau[caseArrivee[0]][caseArrivee[1]];
        if ((plateau[caseArrivee[0]][caseArrivee[1]]%5  == 0 && caseArrivee[1] == 7 && AnalyseTableau.arriveePeutManger(plateau, caseArrivee)== false) ||
        (plateau[caseArrivee[0]][caseArrivee[1]]%3  == 0 && caseArrivee[1] == 0 && AnalyseTableau.arriveePeutManger(plateau, caseArrivee)== false))
            valeurCase = (valeurCase*2);
        return valeurCase;
    }
}
