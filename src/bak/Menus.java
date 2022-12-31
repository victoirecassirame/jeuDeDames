package bak;

import java.util.Scanner;
public class Menus {

    public static void main(String[] args) {

        introduction();
    }

    //1er affichage vu par l'utilisateur.
    //Fonction renvoyant un int : 1 pour lire le réglement ou 2 pour commencer une partie ou 3 pour quitter
    public static void introduction() {
        String menu = "Bonjour, que souhaitez-vous faire ? \n 1 : Lire le réglement \n 2 : Commencer une partie \n 3 : Quitter \n Tapez 1, 2 ou 3 selon votre choix.";
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println(menu);
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("REGLEMENT");
                    break;
                case 2:
                    System.out.println("Très bien, vous allez commencer une partie de dames. Voici le plateau de jeu :");
                    System.out.println();
                    Affichage.afficherPlateau(CreerEtRemplirPlateau.creationRemplissagePlateau());
                    //choixOrdreJoueur();
                    System.out.println("Le joueur 1 jouera les noirs et le joueur 2 jouera les blancs");
                    menuPartie();
                case 3 :
                    default: System.out.println("Au revoir !");
                }
        } while (choix != 3);
    }

    //Methode décidant aléatoirement quel joueur va être blanc ou noir
    //Si le nombre donné est inférieur à 1, alors le jour 1 est blanc, sinon il est noir.
    // et le joueur 2 prend l'autre couleur
    public static void choixOrdreJoueur() {

        double nbAléatoire = Math.random();
        if (nbAléatoire < 1)
            System.out.println("Le joueur 1 jouera les blancs et le joueur 2 jouera les noirs");
        else
            System.out.println("Le joueur 1 jouera les noirs et le joueur 2 jouera les blancs");

    }

// Menu proposé à chaque tour. Permet de bouger un pièce (1) ou d'abandonner (2).
    public static void menuPartie() {
        String menu = "Que souhaitez-vous faire ? \n 1 : Bouger un pion\n 2 : Abandonner \n Tapez 1 ou 2 selon votre choix.";
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println(menu);
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    Deplacement.effectuerDeplacement(Deplacement.typeDeplacement(VariablesGlobales.plateau, InteractionUtilisateur.saisieCaseDepart(), InteractionUtilisateur.saisieCaseArrivee()));
//                    System.out.println(InteractionUtilisateur.saisieCaseDepart());
//                    System.out.println(InteractionUtilisateur.saisieCaseArrivee());
                    break;
                case 2:
                 //   System.out.println("Vous avez abandonné, le joueur " + nbTours%2 + "a gagné !");
            }
        } while (choix != 3);

    }

}
