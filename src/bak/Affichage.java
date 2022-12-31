package bak;

public class Affichage {

    public static void afficherPlateau(int[][] plateau) {
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
               // System.out.print(" | ");
                if (plateau[ligne][colonne] == -1)
                    System.out.print(". ");
                else if (plateau[ligne][colonne] == 11)
                    System.out.print(". ");
                else if (plateau[ligne][colonne] == 03)
                    System.out.print("b ");
                else if (plateau[ligne][colonne] == 05)
                    System.out.print("n ");
                else if (plateau[ligne][colonne] == 06)
                    System.out.print("B ");
                else
                    System.out.print("N ");
            }

            System.out.println();
        }
    }

    public static int[][] MAJPlateau(int[][] plateau, int[] caseDepart, int[] caseArrivee) {

        if (caseArrivee[0] == caseDepart[0]-1 || caseArrivee[0] == caseDepart[0]+1){
            for (int ligne = 0; ligne < plateau.length; ligne++) {
                for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                    if (plateau[caseDepart[0]][caseDepart[1]] == 3) {
                        // Déplacement de la pièce.
                        plateau[caseArrivee[0]][caseArrivee[1]] = 3;
                        plateau[caseDepart[0]][caseDepart[1]] = 11;
                    } else if (plateau[caseDepart[0]][caseDepart[1]] == 5) {
                        // Déplacement de la pièce.
                        plateau[caseArrivee[0]][caseArrivee[1]] = 5;
                        plateau[caseDepart[0]][caseDepart[1]] = 11;
                    }
                }
            }
        }
        else if (caseArrivee[0] == caseDepart[0]-2){
            for (int ligne = 0; ligne < plateau.length; ligne++) {
                for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                    if (plateau[caseDepart[0]][caseDepart[1]] == 3) {
                        // Déplacement de la pièce.
                        plateau[caseArrivee[0]][caseArrivee[1]] = 3;
                        plateau[caseDepart[0]][caseDepart[1]] = 11;
                        if (caseArrivee[1] == caseDepart[1]-2)
                            plateau[caseDepart[0]-1][caseDepart[1]-1] = 11;
                        else if (caseArrivee[1] == caseDepart[1]+2)
                            plateau[caseDepart[0]-1][caseDepart[1]+1] = 11;
                    } else if (plateau[caseDepart[0]][caseDepart[1]] == 5) {
                        // Déplacement de la pièce.
                        plateau[caseArrivee[0]][caseArrivee[1]] = 5;
                        plateau[caseDepart[0]][caseDepart[1]] = 11;
                        if (caseArrivee[1] == caseDepart[1]-2)
                            plateau[caseDepart[0]-1][caseDepart[1]-1] = 11;
                        else if (caseArrivee[1] == caseDepart[1]+2)
                            plateau[caseDepart[0]-1][caseDepart[1]+1] = 11;
                    }
                }
            }
        }
        else if (caseArrivee[0] == caseDepart[0]+2){
            for (int ligne = 0; ligne < plateau.length; ligne++) {
                for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                    if (plateau[caseDepart[0]][caseDepart[1]] == 3) {
                        // Déplacement de la pièce.
                        plateau[caseArrivee[0]][caseArrivee[1]] = 3;
                        plateau[caseDepart[0]][caseDepart[1]] = 11;
                        if (caseArrivee[1] == caseDepart[1]-2)
                            plateau[caseDepart[0]+1][caseDepart[1]-1] = 11;
                        else if (caseArrivee[1] == caseDepart[1]+2)
                            plateau[caseDepart[0]+1][caseDepart[1]+1] = 11;
                    } else if (plateau[caseDepart[0]][caseDepart[1]] == 5) {
                        // Déplacement de la pièce.
                        plateau[caseArrivee[0]][caseArrivee[1]] = 5;
                        plateau[caseDepart[0]][caseDepart[1]] = 11;
                        if (caseArrivee[1] == caseDepart[1]-2)
                            plateau[caseDepart[0]+1][caseDepart[1]-1] = 11;
                        else if (caseArrivee[1] == caseDepart[1]+2)
                            plateau[caseDepart[0]+1][caseDepart[1]+1] = 11;
                    }
                }
            }
        }
        return plateau;
    }

        public static void main (String[]args){

            int[][] plateauTest = {{-1, 05, -1, 05, -1, 05, -1, 05},
                                    {05, -1, 05, -1, 05, -1, 05, -1},
                                    {-1, 05, -1, 05, -1, 05, -1, 05},
                                    {11, -1, 11, -1, 11, -1, 11, -1},
                                    {-1, 11, -1, 11, -1, 05, -1, 11},
                                    {03, -1, 03, -1, 03, -1, 03, -1},
                                    {-1, 03, -1, 03, -1, 03, -1, 03},
                                    {03, -1, 03, -1, 03, -1, 03, -1},
                            };

            int[] caseDepartDeplacementSimpleNoir={2,3};
            int[] caseArriveeDeplacementSimpleNoir={3,4};

            int[] caseDepartBlanc={6 ,3};
            int[] caseArriveeDeplacementSimpleBlanc={5,4};
            int[] caseArriveeDeplacementSimpleBlancPossible={5,2};
            //int[] caseArriveePriseBlanc={4,5};

            int[] caseDepartPriseNoir={3,6};
            int[] caseArriveePriseNoir={5,8};

            int[] caseDepartPriseBlanc={5,4};
            int[] caseArriveePriseBlanc={3,6};

           // afficherPlateau(CreerEtRemplirPlateau.creationRemplissagePlateau());
            afficherPlateau(MAJPlateau(plateauTest, caseDepartDeplacementSimpleNoir, caseArriveeDeplacementSimpleNoir)); // déplacement simple
            System.out.println("---------------------------------------");
            afficherPlateau(MAJPlateau(plateauTest,caseDepartPriseBlanc, caseArriveePriseBlanc)); // déplacement avec prise
        }

}