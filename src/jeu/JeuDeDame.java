package jeu;

public interface JeuDeDame {

    Damier.Couleur getJoueurActuel();

    boolean isPartieTerminee();

    Damier.Couleur getGagnant();

    Damier getDamier();

    void joue(int depart, int arrivee);
//    void joue();

    int[][] getDeplacementsPossibles(int numeroCase);

    void setGagnant(Damier.Couleur gagnant);

}
