package bak;

public class AnalyseTableau {

    public static boolean autrePionPeutManger(int[][] plateau, int[] caseDepart, int[] caseArrivee) {

        //A ENLEVER PLUS TARD
        int caseActuelle = plateau[caseDepart[0]][caseDepart[1]];

        boolean autrePionPeutManger = false;
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne] != plateau[caseDepart[0]][caseDepart[1]]) {
                    if (caseArrivee[0] == caseDepart[0] - 2) {
                        //PRISE PION BLANC
                        if (caseActuelle % 3 == 0) {
                            if (((caseArrivee[1] == (caseDepart[1] - 2)) && ((plateau[caseDepart[0] - 1][caseDepart[1] - 1] % 05) == 0)) || ((caseArrivee[1] == caseDepart[1] + 2) && (plateau[caseDepart[0] - 1][caseDepart[1] + 1] % 05 == 0)))
                                autrePionPeutManger = true;
                            //PRISE PION NOIR
                        } else if (caseActuelle % 5 == 0) {
                            if (((caseArrivee[1] == caseDepart[1] - 2) && ((plateau[caseDepart[0] - 1][caseDepart[0] - 1] % 03) == 0)) || ((caseArrivee[1] == caseDepart[1] + 2) && (plateau[caseDepart[0] - 1][caseDepart[1] + 1] % 03 == 0)))
                                autrePionPeutManger = true;
                        }
                        //prise diagonale basse
                    } else if (caseArrivee[0] == caseDepart[0] + 2) {
                        //PRISE PION BLANC
                        if (caseActuelle % 3 == 0) {
                            if (((caseArrivee[1] == caseDepart[1] - 2) && ((plateau[caseDepart[1] + 1][caseDepart[1] - 1] % 05) == 0)) || ((caseArrivee[1] == caseDepart[1] + 2) && (plateau[caseDepart[0] + 1][caseDepart[1] + 1] % 05 == 0)))
                                autrePionPeutManger = true;
                            //PRISE PION NOIR
                        } else if (caseActuelle % 5 == 0) {
                            if (((caseArrivee[1] == caseDepart[1] - 2) && ((plateau[caseDepart[1] + 1][caseDepart[1] - 1] % 03) == 0)) || ((caseArrivee[1] == caseDepart[1] + 2) && (plateau[caseDepart[0] + 1][caseDepart[1] + 1] % 03 == 0)))
                                autrePionPeutManger = true;
                        }
                    }
                }
            }
        }
        return autrePionPeutManger;
    }


    public static int[] autrePionPeutMangerV2(int[][] plateau, int[] caseDepart) {

        //A ENLEVER PLUS TARD
        int caseActuelle = plateau[caseDepart[0]][caseDepart[1]];
        int[] coordonneesCaseQuiMange = new int[2];

        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne] != plateau[caseDepart[0]][caseDepart[1]]) {
                    if (plateau[ligne][colonne] % 3 == 0) {
                        if (((plateau[ligne - 2][colonne - 2] == 11) && (plateau[ligne - 1][colonne - 1] % 5 == 0)) ||
                                ((plateau[ligne - 2][colonne + 2] == 11) && (plateau[ligne - 1][colonne + 1] % 5 == 0)) ||
                                ((plateau[ligne + 2][colonne - 2] == 11) && (plateau[ligne + 1][colonne - 1] % 5 == 0)) ||
                                ((plateau[ligne + 2][colonne + 2] == 11) && (plateau[ligne + 1][colonne + 1] % 5 == 0))) {
                            coordonneesCaseQuiMange[0] = plateau[ligne][colonne];
                        }
                        //PRISE PION NOIR
                    } else if (plateau[ligne][colonne] % 5 == 0) {
                        if (((plateau[ligne - 2][colonne - 2] == 11) && (plateau[ligne - 1][colonne - 1] % 3 == 0)) ||
                                ((plateau[ligne - 2][colonne + 2] == 11) && (plateau[ligne - 1][colonne + 1] % 3 == 0)) ||
                                ((plateau[ligne + 2][colonne - 2] == 11) && (plateau[ligne + 1][colonne - 1] % 3 == 0)) ||
                                ((plateau[ligne + 2][colonne + 2] == 11) && (plateau[ligne + 1][colonne + 1] % 3 == 0))) {
                            coordonneesCaseQuiMange[0] = plateau[ligne][colonne];
                        }
                    }
                }
            }
        }
        return coordonneesCaseQuiMange;
    }


    public static int[] autrePionBlancPeutManger(int[][] plateau, int[] caseDepart) {

        //A ENLEVER PLUS TARD
        int caseActuelle = plateau[caseDepart[0]][caseDepart[1]];
        int[] coordonneesCaseQuiMange = new int[2];

        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne] != plateau[caseDepart[0]][caseDepart[1]]) {
                    if (plateau[ligne][colonne] % 3 == 0) {
                        if (((plateau[ligne - 2][colonne - 2] == 11) && (plateau[ligne - 1][colonne - 1] % 5 == 0)) ||
                            ((plateau[ligne - 2][colonne + 2] == 11) && (plateau[ligne - 1][colonne + 1] % 5 == 0)) ||
                            ((plateau[ligne + 2][colonne - 2] == 11) && (plateau[ligne + 1][colonne - 1] % 5 == 0)) ||
                            ((plateau[ligne + 2][colonne + 2] == 11) && (plateau[ligne + 1][colonne + 1] % 5 == 0))) {
                            coordonneesCaseQuiMange[0] = plateau[ligne][colonne];
                        }
                    }
                }
            }
        }
        return coordonneesCaseQuiMange;
    }


    public static int[] autrePionNoirPeutManger(int[][] plateau, int[] caseDepart) {

        //A ENLEVER PLUS TARD
        int caseActuelle = plateau[caseDepart[0]][caseDepart[1]];
        int[] coordonneesCaseQuiMange = new int[2];

        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne] != plateau[caseDepart[0]][caseDepart[1]]) {
                    if (plateau[ligne][colonne] % 5 == 0) {
                        if (((plateau[ligne - 2][colonne - 2] == 11) && (plateau[ligne - 1][colonne - 1] % 3 == 0)) ||
                            ((plateau[ligne - 2][colonne + 2] == 11) && (plateau[ligne - 1][colonne + 1] % 3 == 0)) ||
                            ((plateau[ligne + 2][colonne - 2] == 11) && (plateau[ligne + 1][colonne - 1] % 3 == 0)) ||
                            ((plateau[ligne + 2][colonne + 2] == 11) && (plateau[ligne + 1][colonne + 1] % 3 == 0))) {
                            coordonneesCaseQuiMange[0] = plateau[ligne][colonne];
                        }
                    }
                }
            }
        }
        return coordonneesCaseQuiMange;
    }

    public static boolean arriveePeutManger(int[][] plateau, int[] caseArrivee){
        if (plateau[caseArrivee[0]][caseArrivee[1]] % 3 == 0){
            if ((plateau[caseArrivee[0]-2][caseArrivee[1]-2] ==11 && plateau[caseArrivee[0]-1][caseArrivee[1]-1]%5 ==0) ||
                (plateau[caseArrivee[0]-2][caseArrivee[1]+2] ==11 && plateau[caseArrivee[0]-1][caseArrivee[1]+1]%5 ==0) ||
                (plateau[caseArrivee[0]+2][caseArrivee[1]-2] ==11 && plateau[caseArrivee[0]+1][caseArrivee[1]-1]%5 ==0)||
                (plateau[caseArrivee[0]+2][caseArrivee[1]+2] ==11 && plateau[caseArrivee[0]+1][caseArrivee[1]+1]%5 ==0))
                return true;
        }else if (plateau[caseArrivee[0]][caseArrivee[1]]%5==0){
            if ((plateau[caseArrivee[0]-2][caseArrivee[1]-2] ==11 && plateau[caseArrivee[0]-1][caseArrivee[1]-1]%3 ==0) ||
                    (plateau[caseArrivee[0]-2][caseArrivee[1]+2] ==11 && plateau[caseArrivee[0]-1][caseArrivee[1]+1]%3 ==0) ||
                    (plateau[caseArrivee[0]+2][caseArrivee[1]-2] ==11 && plateau[caseArrivee[0]+1][caseArrivee[1]-1]%3 ==0)||
                    (plateau[caseArrivee[0]+2][caseArrivee[1]+2] ==11 && plateau[caseArrivee[0]+1][caseArrivee[1]+1]%3 ==0))
                return true;
        }
        return false;
    }
}