package bak;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class AnalyseTableauTest {

    @Test
    public final void autrePionPeutMangerTest(){

        int[][] plateauDepart ={ /*0  /1 / 2 / 3 / 4 / 5 / 6 / 7 / 8 / 9*/
                            /*0*/{ -1, 05, -1, 05, -1, 05, -1, 05, -1, 05},
                            /*1*/{ 05, -1, 05, -1, 05, -1, 05, -1, 05, -1},
                            /*2*/{ -1, 05, -1, 05, -1, 05, -1, 05, -1, 05},
                            /*3*/{ 05, -1, 05, -1, 05, -1, 05, -1, 05, -1},
                            /*4*/{ -1, 11, -1, 11, -1, 11, -1, 03, -1, 11},
                            /*5*/{ 11, -1, 11, -1, 05, -1, 11, -1, 11, -1},
                            /*6*/{ -1, 03, -1, 03, -1, 03, -1, 03, -1, 03},
                            /*7*/{ 03, -1, 03, -1, 03, -1, 03, -1, 03, -1},
                            /*8*/{ -1, 03, -1, 03, -1, 03, -1, 03, -1, 03},
                            /*9*/{ 03, -1, 03, -1, 03, -1, 03, -1, 03, -1},
                    };

        int[] caseDepartDeplacementSimpleNoir={3,2};
        int[] caseArriveeDeplacementSimpleNoir={4,1};

        int[] caseDepartBlanc={6 ,3};
        int[] caseArriveeDeplacementSimpleBlanc={5,4};
        int[] caseArriveeDeplacementSimpleBlancPossible={5,2};
        int[] caseArriveePriseBlanc={4,5};

        int[] caseDepartPriseNoir={3,6};
        int[] caseArriveePriseNoir={5,8};

        int[][] plateau ={ /*0  /1 / 2 / 3 / 4 / 5 / 6 / 7 / 8 / 9*/
                        /*0*/{ -1, 05, -1, 05, -1, 05, -1, 05, -1, 05},
                        /*1*/{ 05, -1, 05, -1, 05, -1, 05, -1, 05, -1},
                        /*2*/{ -1, 05, -1, 05, -1, 05, -1, 05, -1, 05},
                        /*3*/{ 05, -1, 05, -1, 05, -1, 05, -1, 05, -1},
                        /*4*/{ -1, 11, -1, 11, -1, 11, -1, 11, -1, 11},
                        /*5*/{ 11, -1, 11, -1, 11, -1, 11, -1, 11, -1},
                        /*6*/{ -1, 03, -1, 03, -1, 03, -1, 05, -1, 03},
                        /*7*/{ 03, -1, 03, -1, 03, -1, 11, -1, 03, -1},
                        /*8*/{ -1, 03, -1, 03, -1, 03, -1, 03, -1, 03},
                        /*9*/{ 03, -1, 03, -1, 03, -1, 03, -1, 03, -1},
                };




        assertTrue(AnalyseTableau.autrePionPeutManger(plateauDepart, caseDepartPriseNoir, caseArriveePriseNoir));
        //assertFalse(analyseTableau.autrePionPeutManger(plateau, ca));

    }

}