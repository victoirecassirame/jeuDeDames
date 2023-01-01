package jeu.impl;

import jeu.Damier;
import jeu.JeuDeDame;

import java.util.ArrayList;
import java.util.List;

import static jeu.Damier.CaseType.Piece;
import static jeu.Damier.Couleur.Blanc;
import static jeu.Damier.Couleur.Noir;

import java.util.Scanner;

public class JeuDeDamesImpl implements JeuDeDame {

    public static class JeuDeDamesOption {

        // nom de variable ? de constructeur ? de classe ? enum ?
        private Damier.Taille taille;
        private Damier.Couleur casesUtilisees;
        private Damier.Couleur premierJoueur;

        private boolean priseArrierePossible;

        private boolean priseObligatoire;

        // constructeur avec param et attributs qui sont les param.
        public JeuDeDamesOption(Damier.Taille taille, Damier.Couleur casesUtilisees, Damier.Couleur premierJoueur, boolean priseArrierePossible, boolean priseObligatoire) {
            this.taille = taille;
            this.casesUtilisees = casesUtilisees;
            this.premierJoueur = premierJoueur;
            this.priseArrierePossible = priseArrierePossible;
            this.priseObligatoire = priseObligatoire;
        }
    }

    // nom de la classe + nom spécifique = new + nom de la classe(paramètres/Attributs avec leurs valeurs spécifiques qui ici ont été définies dans enums)
    public static JeuDeDamesOption Anglaise = new JeuDeDamesOption(Damier.Taille.x8, Noir, Noir, false, true);
    public static JeuDeDamesOption Internationale = new JeuDeDamesOption(Damier.Taille.x10, Noir, Blanc, true, true);


    private Damier.Couleur joueurActuel;
    private Damier.Couleur gagnant = null;
    private final Damier damier;

    private final JeuDeDamesOption options;

    public JeuDeDamesImpl(JeuDeDamesOption options, Damier damier) {
        this.options = options;
        joueurActuel = options.premierJoueur;
        this.damier = damier;
    }

    public JeuDeDamesImpl(JeuDeDamesOption options) {

        this.options = options;
        joueurActuel = options.premierJoueur;

        int w = options.taille == Damier.Taille.x8 ? 8 : 10;
        this.damier = new Damier(w, options.casesUtilisees);
        Damier.Case[][] cases = new Damier.Case[w][w];
        int nbLignesParCouleur = options.taille == Damier.Taille.x8 ? 3 : 4;

        for (int ligne = 0; ligne < cases.length; ligne++) {
            for (int colonne = 0; colonne < cases[ligne].length; colonne++) {

                Damier.Couleur couleur = (ligne + colonne) % 2 == 0 ? Blanc : Damier.Couleur.Noir;

                if (couleur == options.casesUtilisees) {
                    if (ligne < nbLignesParCouleur) {
                        this.damier.setCase(ligne, colonne, new Damier.Case(Piece, couleur, new Damier.Piece(Damier.TypePiece.Pion, Damier.Couleur.Noir)));
                    } else if (ligne >= (w - nbLignesParCouleur)) {
                        this.damier.setCase(ligne, colonne, new Damier.Case(Piece, couleur, new Damier.Piece(Damier.TypePiece.Pion, Blanc)));
                    }
                }

            }
        }

        //Ce damier là est un nouvel objet de type Damier (cases ??)
    }

    @Override
    public Damier.Couleur getJoueurActuel() {
        return joueurActuel;
    }


    @Override
    public boolean isPartieTerminee() {
        return gagnant != null;
    }

    //Le joueur a perdu la partie lorsqu'il ne lui reste plus aucune pièce en jeu, ou bien, si c'est à lui de jouer,
    // lorsque toutes ses pièces sont bloquées, c'est-à-dire dans l'impossibilité de prendre ou de se déplacer.
    // gagnant initialisé à null
    @Override
    public Damier.Couleur getGagnant() {
        return gagnant;
    }

    @Override
    public Damier getDamier() {
        return damier;
    }

    @Override
    public void joue(int depart, int arrivee) {

        if (gagnant != null) {
            throw new IllegalStateException("La partie est déjà terminée!");
        }


        Damier.Case caseDepart = damier.getCase(depart);
        if (caseDepart.getType() != Piece) {
            throw new IllegalStateException("La case de départ n'est pas une pièce");
        }


        if (caseDepart.getPiece().getCouleur() != joueurActuel) {
            throw new IllegalStateException("Il semble que la pièce choisie appartienne à votre adversaire");
        }


        int[][] possibilites = getDeplacementsPossibles(depart);

        int[] possibilite = null;
        for (int i = 0; i < possibilites.length && possibilite == null; i++) {
            if (possibilites[i][0] == arrivee) {
                possibilite = possibilites[i];
            }
        }
        if (possibilite == null) {
            throw new IllegalStateException("Le déplacement demandé n'est pas autorisé."); // au lieu de pas de déplacement possible
        }

        if (possibilite.length == 1) {
            //pas de prise
            for (int i = 1; i <= damier.getNbCasesJouables(); i++) {
                Damier.Case c = getDamier().getCase(i);
                if (c.getType() == Piece && c.getPiece().getCouleur() == joueurActuel && i != depart) {
                    int[][] deplacementsPossibles = getDeplacementsPossibles(i);
                    if (deplacementsPossibles.length > 0 && deplacementsPossibles[0].length > 1) {
                        throw new IllegalStateException("Vous devez privilégier la prise.");
                    }
                }
            }
        }

        Damier.Case caseArrivee = getDamier().getCase(arrivee);
        getDamier().setCase(depart, caseArrivee);
        getDamier().setCase(arrivee, caseDepart);

        if (possibilite.length > 1) {
            getDamier().setCase(possibilite[1], caseArrivee);
        }

        //Pion devient Dame
        //TODO: dame en passant INTERDIT
        int[] coordArrivee = getDamier().conversionManouryATableau(arrivee);
        if (caseDepart.getPiece().getType() == Damier.TypePiece.Pion && coordArrivee[0] == (caseDepart.getPiece().getCouleur() == Blanc ? 0 : getDamier().getLignes() - 1)) {
            int[][] deplacementsPossibles = getDeplacementsPossibles(arrivee);
            if (deplacementsPossibles.length == 0 || deplacementsPossibles[0].length == 1) {
                getDamier().setPiece(arrivee, caseDepart.getPiece().getCouleur() == Blanc ? Damier.Piece.DameBLanche : Damier.Piece.DameNoire);
            }
        }

        //TODO: si joueur peut encore manger alors joue encore
        int[][] d1 = getDeplacementsPossibles(arrivee);
        if (d1.length == 0 || d1[0].length == 1) {
            this.joueurActuel = this.joueurActuel == Blanc ? Noir : Blanc;
        }

        boolean peutBouger = false;
        for (int ligne = 0; ligne < getDamier().getLignes() && !peutBouger; ligne++) {
            for (int colonne = 0; colonne < getDamier().getColonnes() && !peutBouger; colonne++) {
                Damier.Case caseActuelle = getDamier().getCase(ligne, colonne);
                // ou si le joueur ne peut effectuer aucun déplacement
                if (caseActuelle.getType() == Piece && caseActuelle.getPiece().getCouleur() == joueurActuel) {
                    // REGARDER S'IL PEUT BOUGER : aucun déplacement = perdant
                    int indiceCase = getDamier().conversionTableauAManoury(ligne, colonne);
                    peutBouger = getDeplacementsPossibles(indiceCase).length > 0;
                }
            }
        }
        if (!peutBouger) {
            gagnant = joueurActuel == Noir ? Blanc : Noir;
        }

    }

    //    public int[] getDeplacementsPossibles(int numeroCase) {
//
//        List<Integer> res = new ArrayList<>();
//
//        Damier.Case c = getDamier().getCase(numeroCase);
//        if (c.getType() == Damier.CaseType.Piece) {
//            int sens = c.getPiece().getCouleur() == Damier.Couleur.Noir ? 1 : -1;
//            Damier.Couleur couleurAdverse = c.getPiece().getCouleur() == Damier.Couleur.Noir ? Blanc : Noir;
//            int x = numeroCase % (damier.getColonnes() / 2);
//            if (x > 1) {
//                int caseNo = numeroCase + sens * (damier.getColonnes() + 1);
//                if (caseNo > 0) {
//                    Damier.Case diagonaleGauche2 = damier.getCase(caseNo);
//                    if (diagonaleGauche2.getType() == Damier.CaseType.Vide) {
//                        Damier.Case diagonaleGauche1 = damier.getCase(numeroCase + sens * (damier.getColonnes() / 2 + 1));
//                        if (diagonaleGauche1.getType() == Damier.CaseType.Piece && diagonaleGauche1.getPiece().getCouleur() == couleurAdverse) {
//                            res.add(caseNo);
//
//
//                        }
//                    }
//
//                }
//            }
//        }
//
//        int[] resArray = new int[res.size()];
//        for (int i = 0; i < resArray.length; i++) {
//            resArray[i] = res.get(i);
//        }
//        return resArray;
//    }
    //TODO: faire renvoyer la case d'arrivée et la case mangee (pour interdire de repasser sur cette case)
    public int[][] getDeplacementsPossibles(int numeroCase) {

        // création d'une liste qui va contenir les cases d'arrivée possibles
        List<int[]> listeCasesArrivee = new ArrayList<>();

        // on attribue la case de départ (numeroCase) à la variable c
        Damier.Case c = getDamier().getCase(numeroCase);
        // on vérifie que c est une pièce, sinon, ce n'est pas une case correcte
        if (c.getType() == Piece) {
            // création d'un tableau de coordonnées x,y de l'indice manoury de la case de départ
            int[] coord = getDamier().conversionManouryATableau(numeroCase);
            //si seulement déplacement avant des pions : les pions noirs vers le bas, les blancs vers le haut
            int sensVer = c.getPiece().getCouleur() == Noir ? 1 : -1;
            // je regarde la couleur adverse
            Damier.Couleur couleurAdverse = c.getPiece().getCouleur() == Damier.Couleur.Noir ? Blanc : Noir;
            for (int sensH = -1; sensH <= 1; sensH += 2) {
                for (int sensV = -1; sensV <= 1; sensV += 2) {
                    if (this.options.priseArrierePossible || sensV == sensVer) {
                        int l1 = coord[0] + sensV, c1 = coord[1] + sensH;
                        //j'élimine déplacement hors limites (out of bounds) des colonnes/lignes 0 et 7/9
                        if (l1 >= 0 && c1 >= 0 && l1 < damier.getLignes() && c1 < damier.getColonnes()) {
                            //case +1 prend les coordonnées (l1,c1)
                            Damier.Case caseIntermediaire1 = getDamier().getCase(l1, c1);
                            //je check que cette case est bien une pièce adverse
                            if (caseIntermediaire1.getType() == Piece && caseIntermediaire1.getPiece().getCouleur() == couleurAdverse) {
                                // je checke case+2
                                int l2 = coord[0] + sensV * 2, c2 = coord[1] + sensH * 2;
                                //j'élimine déplacement hors limites (out of bounds) des colonnes/lignes 0 et 7/9
                                if (l2 >= 0 && c2 >= 0 && l2 < damier.getLignes() && c2 < damier.getColonnes()) {
                                    //c2/arrivée prend (l2, c2)
                                    Damier.Case caseArrivee = getDamier().getCase(l2, c2);
                                    // je checke que c'est bien une case vide
                                    if (caseArrivee.getType() == Damier.CaseType.Vide) {
                                        //Si tout est bon, alors j'ajoute caseArrivee à la liste des arrivees possibles
                                        listeCasesArrivee.add(new int[]{getDamier().conversionTableauAManoury(l2, c2), getDamier().conversionTableauAManoury(l1, c1)});
                                    }
                                }
                            }
                        }
                    }
                }

                // si la liste ne contient rien ou que la prise n'est pas obligatoire, alors on peut faire un déplacement simple

            }

            if (listeCasesArrivee.size() == 0 || !this.options.priseObligatoire) {
                for (int sensH = -1; sensH <= 1; sensH += 2) {
                    int ligne = coord[0] + sensVer, colonne = coord[1] + sensH;
                    if (ligne >= 0 && colonne >= 0 && ligne < getDamier().getLignes() && colonne < getDamier().getColonnes()) {
                        Damier.Case caseArrivee = getDamier().getCase(ligne, colonne);
                        if (caseArrivee.getType() == Damier.CaseType.Vide) {
                            listeCasesArrivee.add(new int[]{getDamier().conversionTableauAManoury(ligne, colonne)});
                        }
                    }
                }
            }
        }

        int[][] resArray = new int[listeCasesArrivee.size()][];
        for (int i = 0; i < resArray.length; i++) {
            resArray[i] = listeCasesArrivee.get(i);
        }
        return resArray;
    }


    @Override
    public void setGagnant(Damier.Couleur gagnant) {
        this.gagnant = gagnant;
    }
}
