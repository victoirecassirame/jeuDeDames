package bak;

import java.util.Scanner;

    public class essaiChatGPT {
        // Le plateau de jeu est représenté par un tableau à deux dimensions
        // de caractères. Un '.' représente une case vide, un 'b' une pièce
        // noire et un 'w' une pièce blanche.
        private static char[][] plateau;

        // Le joueur actuel est représenté par un caractère 'b' ou 'w'.
        private static char joueurActuel;

        public static void main(String[] args) {
            // Initialisation du plateau de jeu et du joueur actuel.
            plateau = new char[8][8];
            joueurActuel = 'b';

            // Remplissage du plateau avec des cases vides.
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    plateau[i][j] = '.';
                }
            }

            // Placement des pièces noires et blanches sur le plateau.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) {
                        plateau[i][j] = 'b';
                    }
                }
            }
            for (int i = 5; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) {
                        plateau[i][j] = 'w';
                    }
                }
            }

            // Boucle de jeu.
            while (true) {
                // Affichage du plateau de jeu.
                afficherPlateau();

                // Demande du prochain coup au joueur actuel.
                jouerCoup();

                // Changement de joueur.
                if (joueurActuel == 'b') {
                    joueurActuel = 'w';
                } else {
                    joueurActuel = 'b';
                }
            }
        }

        // Affiche le plateau de jeu sur la sortie standard.
        private static void afficherPlateau() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(plateau[i][j] + " ");
                }
                System.out.println();
            }
        }

        // Demande au joueur actuel de saisir son prochain coup et le joue sur
        // le plateau de jeu.
        private static void jouerCoup() {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Entrez la ligne de départ du coup (0-7) : ");
                int ligneDepart = scanner.nextInt();
                System.out.print("Entrez la colonne de départ du coup (0-7) : ");
                int colonneDepart = scanner.nextInt();
                System.out.print("Entrez la ligne d'arrivée du coup (0-7) : ");
                int ligneArrivee = scanner.nextInt();
                System.out.print("Entrez la colonne d'arrivée du coup (0-7) : ");
                int colonneArrivee = scanner.nextInt();

                // Vérification de la validité du coup.
                if (plateau[ligneDepart][colonneDepart] == joueurActuel && plateau[ligneArrivee][colonneArrivee] == '.') {
                    // Déplacement de la pièce.
                    plateau[ligneArrivee][colonneArrivee] = joueurActuel;
                    plateau[ligneDepart][colonneDepart] = '.';
                    break;
                } else {
                    System.out.println("Coup non valide, réessayez.");
                }
            }
        }

    }



