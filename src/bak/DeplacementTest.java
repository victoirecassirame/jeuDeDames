package bak;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeplacementTest {

    @Test
   public final void typeDeplacementPionTest(){
            int[][] plateauDepart ={ /*0 /1 / 2 / 3 / 4 / 5 / 6 / 7 /
                                /*0*/{-1, 05, -1, 05, -1, 05, -1, 05},
                                /*1*/{05, -1, 05, -1, 05, -1, 05, -1},
                                /*2*/{-1, 05, -1, 05, -1, 05, -1, 05},
                                /*3*/{11, -1, 11, -1, 11, -1, 05, -1},
                                /*4*/{-1, 11, -1, 11, -1, 11, -1, 11},
                                /*5*/{03, -1, 03, -1, 03, -1, 03, -1},
                                /*6*/{-1, 03, -1, 03, -1, 03, -1, 03},
                                /*7*/{03, -1, 03, -1, 03, -1, 03, -1},
                                };
        int[] caseDepartDeplacementSimpleNoir={2,3};
        int[] caseArriveeDeplacementSimpleNoir={4,1};

        int[] caseDepartBlanc={6 ,3};
        int[] caseArriveeDeplacementSimpleBlanc={5,4};
        int[] caseArriveeDeplacementSimpleBlancPossible={5,2};
        int[] caseArriveePriseBlanc={4,5};

        int[] caseDepartPriseNoir={3,6};
        int[] caseArriveePriseNoir={5,8};

        assertEquals(1, Deplacement.typeDeplacementPion(caseDepartDeplacementSimpleNoir, caseArriveeDeplacementSimpleNoir, plateauDepart)); // deplacement simple pion noir
        assertEquals(-1, Deplacement.typeDeplacementPion(caseDepartBlanc, caseArriveeDeplacementSimpleBlanc, plateauDepart)); // deplacement simple impossible pion blanc
        assertEquals(1, Deplacement.typeDeplacementPion(caseDepartBlanc, caseArriveeDeplacementSimpleBlancPossible, plateauDepart)); // deplacement simple possible pion blanc

        assertEquals(2, Deplacement.typeDeplacementPion(caseDepartPriseNoir, caseArriveePriseNoir, plateauDepart)); //prise possible pion noir

        assertEquals(2, Deplacement.typeDeplacementPion(caseDepartBlanc, caseArriveePriseBlanc, plateauDepart)); //prise possible pion blanc



    }
}