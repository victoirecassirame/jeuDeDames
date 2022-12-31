package ui;

import bak.*;
import jeu.Damier;
import jeu.JeuDeDame;
import jeu.impl.JeuDeDamesImpl;

import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        introduction();
    }

    public static void introduction() {
        String menu = "Bonjour, que souhaitez-vous faire ? \n 1 : Commencer une partie \n 2 : Quitter \n Tapez 1 ou 2 selon votre choix.";
        Scanner scanner = new Scanner(System.in);
        int choix;

        System.out.println(menu);
        choix = scanner.nextInt();

        if (choix == 1) {
            JeuDeDame jeuDeDame = choixTypePartie();
            if (jeuDeDame != null) {
                executerPartie(jeuDeDame);
            }
        } else if (choix == 2) {
            System.out.println("Au revoir !");
        }
    }

    public static JeuDeDame choixTypePartie() {
        String menu = "Souhaitez-vous jouer une partie de : \n 1 : Dames anglaises \n 2 : Dames internationales \n 3 : Quitter \n Tapez 1, 2 ou 3 selon votre choix.";
        Scanner scanner = new Scanner(System.in);
        int choix;
        System.out.println(menu);
        choix = scanner.nextInt();

        if (choix == 1) {
            System.out.println("Vous allez commencer une partie de dames anglaises. " +
                    "\n Le joueur 1 jouera les noirs et le joueur 2 jouera les blancs. " +
                    "\n Voici le plateau de jeu.");
            return new JeuDeDamesImpl(JeuDeDamesImpl.Anglaise);
        } else if (choix == 2) {
            System.out.println("Vous allez commencer une partie de dames internationales. " +
                    "\n Ce jeu ne prend pas en compte la prise majoritaire. " +
                    "\n Le joueur 1 jouera les blancs et le joueur 2 jouera les noirs" +
                    "\n Voici le plateau de jeu. ");
            return new JeuDeDamesImpl(JeuDeDamesImpl.Internationale);
        } else if (choix == 3) {
            System.out.println("Au revoir !");
        }
        return null;
    }

    public static void executerPartie(JeuDeDame jeu) {
        AfficherDamier.afficherDamier(jeu.getDamier());
        String menu = "Que souhaitez-vous faire ? \n 1 : Bouger un pion \n 2 : Abandonner \n Tapez 1 ou 2 selon votre choix.";
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("C'est au tour du joueur " + jeu.getJoueurActuel());
            System.out.println(menu);
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("Saisissez votre case de départ : ");
                    int caseDepart = scanner.nextInt();
                    System.out.println("Saisissez votre case d'arrivée : ");
                    int caseArrivee = scanner.nextInt();
                    try {
                        jeu.joue(caseDepart, caseArrivee);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());

                    }
                    AfficherDamier.afficherDamier(jeu.getDamier());



                    break;
                case 2:
                    Damier.Couleur gagnant = jeu.getJoueurActuel() == Damier.Couleur.Noir ? Damier.Couleur.Blanc : Damier.Couleur.Noir;
                    jeu.setGagnant(gagnant);
                    System.out.println("Vous avez abandonné.");

            }
        } while (jeu.getGagnant() == null || choix != 2);
        if (jeu.isPartieTerminee()) {
            System.out.println("le joueur " + jeu.getGagnant() + " a gagné !");
        }
    }

}
