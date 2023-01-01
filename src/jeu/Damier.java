package jeu;

import static jeu.Damier.CaseType.Piece;
import static jeu.Damier.Couleur.Blanc;
import static jeu.Damier.Couleur.Noir;

public class Damier {

    //Je créé les possibilités des arguments de la Piece
    public enum Couleur {
        Blanc,
        Noir
    }

    public enum TypePiece {
        Pion,
        Dame
    }

    //Je créé la classe qui représente l'objet "pièce" et je créé 2 variables/attributs de type TypePiece et Couleur
    public static class Piece {
        private TypePiece type;
        private Couleur couleur;

        public static final Piece PionBlanc = new Piece(TypePiece.Pion, Blanc);
        public static final Piece PionNoir = new Piece (TypePiece.Pion, Noir);
        public static final Piece DameBlanche = new Piece(TypePiece.Dame, Blanc);
        public static final Piece DameNoire = new Piece (TypePiece.Dame, Noir);

        // Je créé un constructeur Piece qui prend 2 attributs qui vont avoir des valeurs    (--> instance = new)
        public Piece(TypePiece type, Couleur couleur) {
            this.type = type;
            this.couleur = couleur;
        }

        // la fonction getType est un accesseur/getter et renvoie une donnée de type TypePiece.
        // Ici : permet d'avoir le type -de la piece-.
        public TypePiece getType() {
            return type;
        }

        public Couleur getCouleur() {
            return couleur;
        }
    }

    public enum CaseType {
        Interdit,
        Vide,
        Piece
    }


    // Je créé la classe CASE qui correspond à l'OBJET CASE
    public static class Case {
        private final CaseType type;
        private final Piece piece;

        private final Couleur couleur;

        public Case(CaseType type, Couleur couleur, Piece piece) {
            this.type = type;
            this.piece = piece;
            this.couleur = couleur;
        }

        public CaseType getType() {
            return type;
        }

        public Piece getPiece() {
            return piece;
        }

        public Couleur getCouleur() {
            return couleur;
        }
    }

    public enum Taille {
        x8,
        x10
    }

    private final int colonnes, lignes;
    // Case[][] tableau à 2 dimensions de case
    private final Case[][] cases;
    private final Taille taille;
    private final int nbCasesJouables;

    private static Case[][] generateDamier(int taille, Couleur casesUtilisees){
        Damier.Case[][] cases = new Damier.Case[taille][taille];

        for (int ligne = 0; ligne < cases.length; ligne++) {
            for (int colonne = 0; colonne < cases[ligne].length; colonne++) {

                Damier.Couleur couleur = (ligne + colonne) % 2 == 0 ? Blanc : Damier.Couleur.Noir;

                if (couleur != casesUtilisees) {
                    cases[ligne][colonne] = new Damier.Case(Damier.CaseType.Interdit, couleur, null);
                } else {
                        cases[ligne][colonne] = new Damier.Case(Damier.CaseType.Vide, couleur, null);
                }

            }
        }
        return cases;
    }

    public Damier(int taille, Couleur caseUtilisees){
        this(generateDamier(taille, caseUtilisees));
    }



    // Ma classe s'appelle Damier.
    //Ici je créé un constructeur de l'objet de ma classe qui prend en paramètre un tableau à 2 dimensions de Cases
    // Un constructeur est une "méthode/classe --> constructeur" qui ne renvoie rien (même pas void)
    public Damier(Case[][] cases) {

        if (cases == null) {
            throw new IllegalArgumentException("Cases : null");
        }

        if (cases.length == 0) {
            throw new IllegalArgumentException("Pas de ligne.");
        }

        this.lignes = cases.length;
        this.colonnes = cases[0].length;
        // Si cases est un tableau. pq ce n'est pas : = cases[lignes][colonnes] ?
        this.cases = cases;
        //Ternaire : si condition (?) vraie alors ... sinon ... (:)
        this.taille = colonnes == 8 ? Taille.x8 : Taille.x10;
        this.nbCasesJouables = this.colonnes*this.lignes/2;

        if (colonnes != lignes) {
            throw new RuntimeException("Le damier n'est pas carré.");
        }
        if (colonnes != 8 && colonnes != 10) {
            throw new RuntimeException("Dimensions incorrectes");
        }
        for (int ligne = 0; ligne < lignes; ligne++) {
            // A quoi ça sert ici ?
            // cases[ligne] n'est pas la même chose que colonnes ?
            if (cases[ligne].length != colonnes) {
                throw new RuntimeException("Mauvais plateau.");
            }
            for (int colonne = 0; colonne < colonnes; colonne++) {
                if (cases[ligne][colonne] == null) {
                    throw new RuntimeException("Les cases sont vides");
                }
            }
        }
    }

    public Case getCase(int ligne, int colonne) {

        return this.cases[ligne][colonne];
    }

    public Case getCase(int index) {

        int[] coordonnees = conversionManouryATableau(index);
        return this.getCase(coordonnees[0], coordonnees[1]);
    }

    public void setCase(int index, Case c) {
        int[] coord = conversionManouryATableau(index);
        this.setCase(coord[0], coord[1], c);
    }

    public void setCase(int ligne, int colonne, Case c) {
        //TODO: contrôler la case
        this.cases[ligne][colonne] = c;
    }

    public void setPiece(int index, Piece piece){
        Case c = getCase(index);
        setCase(index, new Case(Piece, c.getCouleur(), piece));
    }

    public void setPiece(int ligne, int colonne, Piece piece){
        Case c = getCase(ligne, colonne);
        setCase(ligne, colonne, new Case(Piece, c.getCouleur(), piece));
    }

    public int[] conversionManouryATableau(int index) {
        int utilisables = this.colonnes / 2;
        int ligne = (index - 1) / utilisables;
        int colonne = (index - utilisables * ligne - 1) * 2 + (ligne % 2 == (cases[0][0].getType() == CaseType.Interdit ? 0 : 1) ? 1 : 0);

        return new int[]{ligne, colonne};
    }

    public int conversionTableauAManoury(int ligne, int colonne) {
        int manoury = (ligne * this.colonnes + colonne) / 2 + 1;
        return manoury;
    }

    public int getColonnes() {
        return this.colonnes;
    }

    public int getLignes() {
        return this.lignes;
    }

    public Taille getTaille() {
        return this.taille;
    }

    public int getNbCasesJouables() {
        return nbCasesJouables;
    }
}
