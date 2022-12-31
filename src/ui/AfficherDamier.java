package ui;

import jeu.Damier;

public class AfficherDamier {
    private static void afficherDamierCase(Damier.Case c){
        {
            String couleurFond = c.getCouleur() == Damier.Couleur.Blanc ? "\033[47m" : "\033[40m";
            String couleurPiece = c.getCouleur() == Damier.Couleur.Blanc ? "\033[1;30m" : "\033[1;37m";
            System.out.print(couleurFond + couleurPiece);
            if (c.getType() == Damier.CaseType.Vide){
                System.out.print("   ");
            } else if (c.getType() == Damier.CaseType.Interdit){
                System.out.print("   ");
            }
            else if(c.getType()==Damier.CaseType.Piece){
                // pq on doit mettre get piece et pas juste get type ?
                if(c.getPiece().getType()== Damier.TypePiece.Pion){
                    // même question
                    if(c.getPiece().getCouleur()== Damier.Couleur.Blanc){
                        System.out.print(" b ");
                    }else {
                        System.out.print(" n ");
                    }
                }else{
                    if(c.getPiece().getCouleur()== Damier.Couleur.Blanc){
                        System.out.print(" B ");
                    }else {
                        System.out.print(" N ");
                    }
                }
            }
        }
    }
    private static void afficherDamierLigne(Damier damier, int ligne){
        for (int colonne = 0; colonne < damier.getColonnes(); colonne++) {
           afficherDamierCase(damier.getCase(ligne, colonne));
        }
    }
    public static void afficherDamier(jeu.Damier damier){
        if(damier.getCase(0,0).getType() == Damier.CaseType.Interdit){
            System.out.print("   ");
        }
        System.out.print("    1     2     3     4" );
        if(damier.getColonnes()== 10) {
            System.out.print("     5");
        }
        System.out.println();
        for (int ligne = 0; ligne < damier.getLignes(); ligne++) {
            if(damier.getCase(ligne, 0).getType() != Damier.CaseType.Interdit) {
                int value = (ligne * damier.getColonnes() / 2 + 1);
                System.out.print(value + " ");
                if(value < 10){
                    System.out.print(" ");
                }
            }
            else{
                System.out.print("   ");
            }
            afficherDamierLigne(damier,ligne);
            System.out.print("\033[0m");
            if(damier.getCase(ligne, 0).getType() == Damier.CaseType.Interdit) {
                System.out.print(" "+((ligne+1)*damier.getColonnes()/2));
            }
            System.out.println();
        }
        if(damier.getCase(0,0).getType() != Damier.CaseType.Interdit){
            System.out.print("   ");
        }
        if(damier.getColonnes()== 10) {
            System.out.print("    46    47    48    49    50");
        }else{
            System.out.print("    29    30    31    32");


        }
        System.out.println();
    }
}
