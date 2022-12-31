package bak;

public class Conversion {

    public static int[] manouryAIndice(int caseManoury) {
        int[] indice = new int[2];
        indice[0] = ((caseManoury-1) * 2 / 10);
        if (indice[0] % 2 == 0) {
            indice[1] = (caseManoury%10-1)*2 +1;
        }
        if (indice[0] % 2 != 0)
            for (int i = 0 ; i < 9 ; i++)
                indice[1] = caseManoury%10;
        return indice;
    }

    public static void main (String[]args){
        int[] position = manouryAIndice(5);
        System.out.println("Ligne : " + position[0] + ", colonne : " + position[1]);
    }


    public static int valAbs(int coordonnee1, int coordonnee2){
        int somme = coordonnee1 - coordonnee2;
        if (somme < 0)
            return -somme;
        else
            return somme;
    }
}



//        .  1  .  2  .  3  .  4  .  5
//        6  .  7  .  8  .  9  . 10  .
//        . 11  . 12  . 13  . 14  . 15
//        16  . 17  . 18  . 19  . 20  .
//        . 21  . 22  . 23  . 24  . 25
//        26  . 27  . 28  . 29  . 30  .
//        . 31  . 32  . 33  . 34  . 35
//        36  . 37  . 38  . 39  . 40  .
//        . 41  . 42  . 43  . 44  . 45
//        46  . 47  . 48  . 49  . 50  .

