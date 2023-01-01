package jeu.impl;

import jeu.Damier;
import jeu.JeuDeDame;
import org.junit.jupiter.api.Test;
import ui.AfficherDamier;

import static jeu.Damier.Couleur.Noir;
import static jeu.Damier.Piece.*;
import static org.junit.jupiter.api.Assertions.*;

public class JeuDeDamesImplTest {

    @Test
    public void TestTourBlancs() {

        //Test de l'interface JeuDeDame
        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale);
        Damier.Case caseDepartAVant = jeu.getDamier().getCase(35);
        assertEquals(caseDepartAVant.getType(), Damier.CaseType.Piece);
        assertEquals(Damier.Couleur.Blanc, caseDepartAVant.getPiece().getCouleur());
        Damier.Case caseArriveeAvant = jeu.getDamier().getCase(30);
        assertEquals(caseArriveeAvant.getType(), Damier.CaseType.Vide);

        jeu.joue(35, 30);

        Damier.Case caseDepartApres = jeu.getDamier().getCase(30);
        assertEquals(caseDepartApres.getType(), Damier.CaseType.Piece);
        assertEquals(Damier.Couleur.Blanc, caseDepartApres.getPiece().getCouleur());
        Damier.Case caseArriveeApres = jeu.getDamier().getCase(35);
        assertEquals(caseArriveeApres.getType(), Damier.CaseType.Vide);

    }

    @Test
    public void TestMouvementCaseVideInterdit() {
        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale);
        Damier.Case caseDepartAvant = jeu.getDamier().getCase(21);
        assertEquals(caseDepartAvant.getType(), Damier.CaseType.Vide);

        assertThrows(IllegalStateException.class, () -> {

            jeu.joue(21, 17);
        });
    }


    @Test
    public void TestNoirJoueEnPremierInterdit() {
        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale);
        Damier.Case caseDepartAvant = jeu.getDamier().getCase(17);
        assertEquals(Damier.CaseType.Piece, caseDepartAvant.getType());
        assertEquals(Noir, caseDepartAvant.getPiece().getCouleur());

        assertThrows(IllegalStateException.class, () -> {
            jeu.joue(17, 22);

        });
    }

    @Test
    public void TestCaseArriveeOccupeeInterdit() {
        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale);
        Damier.Case caseDepart = jeu.getDamier().getCase(37);
        assertEquals(Damier.CaseType.Piece, caseDepart.getType());
        Damier.Case caseArrivee = jeu.getDamier().getCase(31);
        assertEquals(Damier.CaseType.Piece, caseArrivee.getType());

        assertThrows(IllegalStateException.class, () -> {
            jeu.joue(37, 31);
        });
    }

    @Test
    public void TestPionBougeDe2CasesAvecCaseMilieuVideInterdit() {
        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale);
        Damier.Case caseDepart = jeu.getDamier().getCase(33);
        assertEquals(Damier.CaseType.Piece, caseDepart.getType());
        Damier.Case caseMilieu = jeu.getDamier().getCase(29);
        assertEquals(Damier.CaseType.Vide, caseMilieu.getType());
        Damier.Case caseArrivee = jeu.getDamier().getCase(24);
        assertEquals(Damier.CaseType.Vide, caseArrivee.getType());

        assertThrows(IllegalStateException.class, () -> {
            jeu.joue(33, 24);
        });

    }


    @Test
    public void TestPionMangeUnPionAdverseAutorise() {
        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale);

        jeu.joue(31, 26); // les blancs commencent
        jeu.joue(17, 22);// noir se déplace de 1 case
        jeu.joue(35, 30); //blanc jouent

        //déplacement noir de 22 à 28
        Damier.Case caseDepartNoir = jeu.getDamier().getCase(22);
        assertEquals(Damier.CaseType.Piece, caseDepartNoir.getType());
        assertEquals(Noir, caseDepartNoir.getPiece().getCouleur()); //? est ce que ça expect bien la couleur de la pièce ?

        Damier.Case caseArriveeNoir = jeu.getDamier().getCase(28);
        assertEquals(Damier.CaseType.Vide, caseArriveeNoir.getType());
        jeu.joue(22, 28);


        //Prise Blanc de 32 à 23 au dessus du pion  noir en 28
        Damier.Case caseDepartBlanc = jeu.getDamier().getCase(32);
        assertEquals(Damier.CaseType.Piece, caseDepartBlanc.getType());
        Damier.Case caseArrivee = jeu.getDamier().getCase(23);
        assertEquals(Damier.CaseType.Vide, caseArrivee.getType());

        jeu.joue(33, 22);

    }

//TODO:corriger le test
//    @Test
//    public void TestPionNoirPriseArriereInterdit(){
//        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Anglaise);
//
//        jeu.joue(23, 19); // déplacement blanc utile
//        jeu.joue(11, 16); // déplacement noir utile
//        jeu.joue(21, 17 ); // déplacement blanc
//        jeu.joue(16, 23); //déplacement noir utile
//        jeu.joue(22, 15); // déplacement blanc utile
//
//        Damier.Case caseDepart = jeu.getDamier().getCase(20);
//        assertEquals(Damier.CaseType.Piece, caseDepart.getType());
//        assertEquals(Damier.Couleur.Noir, caseDepart.getCouleur());
//
//        Damier.Case caseMilieu = jeu.getDamier().getCase(16);
//        assertEquals(Damier.CaseType.Piece, caseMilieu.getType());
//        assertEquals(Damier.Couleur.Noir, caseMilieu.getCouleur());
//
//        Damier.Case caseArrivee = jeu.getDamier().getCase(11);
//        assertEquals(Damier.CaseType.Vide, caseArrivee.getType());
//
//
//        assertThrows(IllegalStateException.class, () -> {
//            jeu.joue(20, 11);
//        });
//
//    }

    @Test
    public void TestPriseObligatoire() {
        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale);

        jeu.joue(31, 27); // les blancs commencent
        jeu.joue(18, 22);// noir se déplace de 1 case

        AfficherDamier.afficherDamier(jeu.getDamier());

        //blanc jouent et essayent de faire un déplacement simple alors que pion en position 27 peut manger
        assertThrows(IllegalStateException.class, () -> {
            jeu.joue(33, 29);
        });
    }

    @Test
    public void TestPriseNoirMangeBlancAutorise() {
        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Anglaise);

        jeu.joue(9, 13);
        jeu.joue(22, 17);
        AfficherDamier.afficherDamier(jeu.getDamier());
        jeu.joue(13, 22);
    }


    @Test
    public void TestPionDevientDame() {

        Damier d = new Damier(10, Noir);
        d.setPiece(6, PionBlanc);
        d.setPiece(45, PionNoir);

        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale, d);

        jeu.joue(6, 1);
        jeu.joue(45, 50);

        AfficherDamier.afficherDamier(d);
        assertEquals(Damier.TypePiece.Dame, d.getCase(1).getPiece().getType());
        assertEquals(Damier.Couleur.Blanc, d.getCase(1).getPiece().getCouleur());

        assertEquals(Damier.TypePiece.Dame, d.getCase(50).getPiece().getType());
        assertEquals(Noir, d.getCase(50).getPiece().getCouleur());
    }

    @Test
    public void TestCouleurPionNeDevientPasDame() {
        Damier d = new Damier(10, Noir);
        d.setPiece(36, PionBlanc);
        d.setPiece(41, PionNoir);
        d.setPiece(15, PionNoir);
        d.setPiece(10, PionBlanc);

        AfficherDamier.afficherDamier(d);
        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale, d);

        jeu.joue(36, 47);
        jeu.joue(15, 4);


        AfficherDamier.afficherDamier(d);
        assertEquals(Damier.TypePiece.Pion, d.getCase(47).getPiece().getType());
        assertEquals(Damier.Couleur.Blanc, d.getCase(47).getPiece().getCouleur());

        assertEquals(Damier.TypePiece.Pion, d.getCase(4).getPiece().getType());
        assertEquals(Noir, d.getCase(4).getPiece().getCouleur());
    }


    @Test
    public void TestDameEnPassantInterdit() {
        Damier d = new Damier(10, Noir);
        d.setPiece(16, PionBlanc);
        d.setPiece(36, PionNoir);
        d.setPiece(41, PionBlanc);
        d.setPiece(42, PionBlanc);

        AfficherDamier.afficherDamier(d);

        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale, d);
        jeu.joue(16, 11);

        assertEquals(Noir, jeu.getJoueurActuel());
        jeu.joue(36, 47);
        AfficherDamier.afficherDamier(d);
        assertEquals(Noir, jeu.getJoueurActuel());
        assertEquals(Damier.TypePiece.Pion, d.getCase(47).getPiece().getType());
        AfficherDamier.afficherDamier(d);
        jeu.joue(47, 38);
        AfficherDamier.afficherDamier(d);

    }

    @Test
    public void TestGagnantAucunPion() {
        Damier d = new Damier(10, Noir);
        d.setPiece(1, 0, PionBlanc);
        d.setPiece(2, 1, PionNoir);

        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale, d);
        assertNull(jeu.getGagnant());
        jeu.joue(6, 17);


        assertEquals(Damier.Couleur.Blanc, jeu.getGagnant());
    }


    @Test
    public void TestGagnantAucunDeplacementPossible() {
        Damier d = new Damier(10, Noir);
        d.setPiece(5, PionNoir);
        d.setPiece(10, PionBlanc);
        d.setPiece(20, PionBlanc);
        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale, d);
        assertNull(jeu.getGagnant());
        AfficherDamier.afficherDamier(d);
        jeu.joue(20, 14);
        AfficherDamier.afficherDamier(d);


        assertEquals(Damier.Couleur.Blanc, jeu.getGagnant());
    }


    @Test
    public void TestDeplacementLongSimpleDameAutorise(){
        Damier d = new Damier(10, Noir);

        d.setPiece(15, DameBlanche);
        d.setPiece(16, PionBlanc);
        d.setPiece(1, PionNoir);

        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale, d);
        AfficherDamier.afficherDamier(d);

        jeu.joue(15, 29);
        AfficherDamier.afficherDamier(d);
    }

    @Test
    public void TestDeplacementLongPriseDameAutorise(){
        Damier d = new Damier(10, Noir);

        d.setPiece(15, DameBlanche);
        d.setPiece(29, PionNoir);

        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale, d);
        AfficherDamier.afficherDamier(d);

        jeu.joue(15, 33);
        AfficherDamier.afficherDamier(d);
    }

    @Test
    public void TestDeplacementLongPriseDameAutoriseSaut2PionsInterdits(){
        Damier d = new Damier(10, Noir);

        d.setPiece(15, DameBlanche);
        d.setPiece(29, PionNoir);
        d.setPiece(24, PionNoir);

        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale, d);
        AfficherDamier.afficherDamier(d);

        assertThrows(IllegalStateException.class, () -> {
            jeu.joue(15, 33);
        });
        AfficherDamier.afficherDamier(d);
    }



    @Test
    public void TestPriseObligatoireDame(){
        Damier d = new Damier(10, Noir);

        d.setPiece(15, DameBlanche);
        d.setPiece(29, PionNoir);

        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale, d);
        AfficherDamier.afficherDamier(d);

        assertThrows(IllegalStateException.class, () -> {
            //Dame Blanche censée manger pion en 29
            jeu.joue(15, 4);
        });
        AfficherDamier.afficherDamier(d);
    }


    @Test
    public void TestDeplacementLongInterditReglesAnglaises(){
        Damier d = new Damier(8, Noir);

        d.setPiece(1, DameNoire);
        d.setPiece(15, DameBlanche);

        JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Anglaise, d);
        AfficherDamier.afficherDamier(d);


        assertThrows(IllegalStateException.class, () -> {
            //Dame Blanche censée manger pion en 29
            jeu.joue(1, 19);
        });


    }


}
