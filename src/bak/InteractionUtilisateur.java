package bak;

import java.util.Scanner;
public class InteractionUtilisateur {
    public static int[] saisieCaseDepart() {
        Scanner scanner = new Scanner(System.in);
        int caseDepart;

        do {
            System.out.println("Quelle est la case de la pièce que vous souhaitez déplacer ?"); // rajouter ("Joueur " + VariablesGlobales.nbTour%2 + ", quelle case...")
            caseDepart = scanner.nextInt();
        } while((caseDepart < 1) || (caseDepart > 32)); // || !VariablesGlobales.appartientAJoueur//); //TESTER APPARTENANCE DU PION//

        int[] tableauIndicesCaseDepart;
        tableauIndicesCaseDepart = new int[2];

        tableauIndicesCaseDepart = Conversion.manouryAIndice(caseDepart);
        return tableauIndicesCaseDepart;
    }

    public static int[] saisieCaseArrivee() {
        Scanner scanner = new Scanner(System.in);
        int caseArrivee;
        do {
            System.out.println("Sur quelle case souhaitez-vous arriver ?");
            caseArrivee = scanner.nextInt();
        } while ((caseArrivee< 1) || (caseArrivee > 32));
        int[] tableauIndicesCaseArrivee;
        tableauIndicesCaseArrivee = new int[2];

        tableauIndicesCaseArrivee = Conversion.manouryAIndice(caseArrivee);

        return tableauIndicesCaseArrivee;
    }


    public static void main (String[] args){
        saisieCaseDepart();
    }

}

