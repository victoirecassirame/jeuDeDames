import jeu.JeuDeDame;
import jeu.impl.JeuDeDamesImpl;
import ui.AfficherDamier;

public class Main {
     public static void main (String[] args){
         JeuDeDame jeu = new JeuDeDamesImpl(JeuDeDamesImpl.Internationale);
         AfficherDamier.afficherDamier(jeu.getDamier());
         jeu.joue(31, 27); // les blancs commencent
         jeu.joue (18,22);


         AfficherDamier.afficherDamier(jeu.getDamier());
     }
}
