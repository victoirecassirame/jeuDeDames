package bak;

public class Deplacement {


    // Méthode vérifiant si la pièce sur la case de départ est un pion ou une reine et appelle une méthode de déplacement selon le cas.
    public static int typeDeplacement (int[][] plateau, int[] caseDepart, int[] caseArrivee){
        if (plateau[caseDepart[0]][caseDepart[1]]%2 == 0)
            return Deplacement.typeDeplacementReine(InteractionUtilisateur.saisieCaseDepart(), InteractionUtilisateur.saisieCaseArrivee(), VariablesGlobales.plateau);
        else if(plateau[caseDepart[0]][caseDepart[1]]%2 != 0)
            return Deplacement.typeDeplacementPion(InteractionUtilisateur.saisieCaseDepart(), InteractionUtilisateur.saisieCaseArrivee(), VariablesGlobales.plateau);
        return -1;
    }

    //Fonction prenant en arguments les cases de départ et arrivéé demandées par le joueur
    // et qui analyse le type de mouvement. Elle regarde si la case d'arrivée est bien vide.
    // Pour une prise, elle regarde aussi la case intermédiaire qui doit être à l'adversaire.
    // La fonction retourne alors 1 si c'est un déplacement simple (autorisé),
    // 2 si c'est une prise (autorisée)
    // -1 si le mouvement n'est pas autorisé.
    public static int typeDeplacementPion(int[] caseDepart, int[] caseArrivee, int[][]plateau){

        int caseActuelle = plateau[caseDepart[0]][caseDepart[1]];

        if ((plateau[caseArrivee[0]][caseArrivee[1]]==11) && (caseArrivee[0] >=0 && caseArrivee[0] <=9) && (caseArrivee[1] >=0 && caseArrivee[1] <=9)){
            //pour un déplacement simple
            if (valAbs(caseArrivee[0], caseDepart[0])==1 && valAbs(caseDepart[1], caseArrivee[1])==1)
                return 1;
                //prise diagonale haute
           // else if (valAbs(caseArrivee[0], caseDepart[0])==2) {
            else if (caseArrivee[0] == caseDepart[0] - 2) {
            //PRISE PION BLANC
                if (caseActuelle % 3 == 0) {
                    if (((caseArrivee[1] == (caseDepart[1] - 2) )&& ((plateau[caseDepart[0] - 1][caseDepart[1] - 1] % 05) == 0)) || ((caseArrivee[1] == caseDepart[1] + 2) && (plateau[caseDepart[0] - 1][caseDepart[1] + 1] % 05 == 0)))
                        return 2;
                    //PRISE PION NOIR
                } else if (caseActuelle % 5 == 0){
                    if (((caseArrivee[1] == caseDepart[1] - 2) && ((plateau[caseDepart[0] - 1][caseDepart[0]-1] % 03) == 0)) || ((caseArrivee[1] == caseDepart[1] + 2) && (plateau[caseDepart[0] - 1][caseDepart[1] + 1] % 03 == 0)))
                        return 2;
                }
                //prise diagonale basse
            } else if (caseArrivee[0] == caseDepart[0] + 2) {
                    //PRISE PION BLANC
                if (caseActuelle % 3 == 0) {
                    if (((caseArrivee[1] == caseDepart[1] - 2) && ((plateau[caseDepart[1] + 1][caseDepart[1] - 1] % 05) == 0)) || ((caseArrivee[1] == caseDepart[1] + 2) && (plateau[caseDepart[0] + 1][caseDepart[1] + 1] % 05 == 0)))
                        return 2;
                    //PRISE PION NOIR
                }else if (caseActuelle % 5 == 0) {
                    if (((caseArrivee[1] == caseDepart[1] - 2) && ((plateau[caseDepart[1] + 1][caseDepart[1] - 1] % 03) == 0)) || ((caseArrivee[1] == caseDepart[1] + 2) && (plateau[caseDepart[0] + 1][caseDepart[1] + 1] % 03 == 0)))
                        return 2;
                }
            }
        }
        return -1;
    }

    //Fonction prenant en arguments les cases de départ et arrivéé demandées par le joueur
    // et qui analyse le type de mouvement. Elle regarde si la case d'arrivée est bien vide.
    // Pour une prise, elle regarde aussi la/les case(s) intermédiaire(s) qui doivent être à l'adversaire.
    // La fonction retourne alors 1 si c'est un déplacement simple (autorisé),
    // 2 si c'est une prise (autorisée)
    // -1 si le mouvement n'est pas autorisé.
    public static int typeDeplacementReine(int[] caseDepart, int[] caseArrivee, int[][]plateau){

        return -1;
    }



    //A DEPLACER DANS CONVERSION
    public static int valAbs(int coordonnee1, int coordonnee2){
        int somme = coordonnee1 - coordonnee2;
        if (somme < 0)
            return -somme;
        else
            return somme;
    }


//    Selon l'entier reçu (1 pour un déplacement simple, 2 pour une prise, -1 pour un déplacement autorisé),
//     On effectue le mouvement demandé ou pas.
    public static void effectuerDeplacement(int typeDeplacement){
        if (typeDeplacement == -1)
            System.out.println("Vous ne pouvez pas effectuer ce déplacement. Il n'est pas autorisé");
//        else if (typeDeplacement == 1 && !analyseTableau.autrePionPeutManger())
//            Affichage.MAJPlateau();
//            if(analyseTableau.arriveePeutManger()) {
//                System.out.println("Vous avez encore la possibilité de manger.");
//                typeDeplacement(VariablesGlobales.plateau, InteractionUtilisateur.saisieCaseDepart(), InteractionUtilisateur.saisieCaseArrivee());
//                effectuerDeplacement();
//            }
//        else if(typeDeplacement == 1 && analyseTableau.autrePionPeutManger())
//            System.out.println("Vous ne pouvez pas effectuer ce déplacement. Vous avez la possibilité de manger un pion adverse et devez le faire.");
//        else
//            Affichage.MAJPlateau();
//            if (analyseTableau.arriveePeutManger()) {
//                System.out.println("Vous avez encore la possibilité de manger.");
//                typeDeplacement(VariablesGlobales.plateau, InteractionUtilisateur.saisieCaseDepart(), InteractionUtilisateur.saisieCaseArrivee());
//                effectuerDeplacement();
//            }
    }

}
