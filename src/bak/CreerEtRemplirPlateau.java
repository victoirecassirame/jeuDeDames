package bak;

public class CreerEtRemplirPlateau {
    public static int[][] creationRemplissagePlateau() {
        int[][] plateau;
        plateau = new int[8][8];
        for(int ligne = 0 ; ligne < plateau.length ; ligne++){
            for (int colonne = 0 ; colonne < plateau[ligne].length ; colonne ++){
                if(ligne%2==0) {
                    if (ligne < 3 && colonne % 2 == 0)
                        plateau[ligne][colonne] = -1;
                    else if (ligne < 3 && colonne % 2 != 0)
                        plateau[ligne][colonne] = 05;
                    else if (ligne < 5 && colonne % 2 == 0)
                        plateau[ligne][colonne] = 11;
                    else if (ligne < 5 && colonne % 2 != 0)
                        plateau[ligne][colonne] = -1;
                    else if (colonne % 2 == 0)
                        plateau[ligne][colonne] = 11;
                    else
                        plateau[ligne][colonne] = 03;
                }else{
                    if (ligne < 3 && colonne % 2 == 0)
                        plateau[ligne][colonne] = 05;
                    else if (ligne < 3 && colonne % 2 != 0)
                        plateau[ligne][colonne] = -1;
                    else if (ligne < 5 && colonne % 2 == 0)
                        plateau[ligne][colonne] = -1;
                    else if (ligne < 5 && colonne % 2 != 0)
                        plateau[ligne][colonne] = 11;
                    else if (colonne % 2 == 0)
                        plateau[ligne][colonne] = 03;
                    else
                        plateau[ligne][colonne] = 11;
                }
            }
        }
        return plateau;
    }
}
