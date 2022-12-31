package jeu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamierTest {

    private Damier.Case[][] cases(int lignes, int colonnes){
        Damier.Case[][]  cases = new Damier.Case[lignes][colonnes];
        for (int ligne = 0 ; ligne < lignes ; ligne++){
            for(int colonne = 0 ; colonne < colonnes ; colonne++){
                cases[ligne][colonne] = new Damier.Case(Damier.CaseType.Vide ,null, null);
            }
        }

        return cases;
    }

    @Test
    public void testNullArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Damier(null);
        });
    }

    @Test
    public void testDimensions(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Damier(cases(0,0));
        });
        assertThrows(RuntimeException.class, () -> {
            new Damier(cases(2,3));
        });
        assertThrows(RuntimeException.class, () -> {
            new Damier(cases(9,9));
        });
        new Damier(cases(8,8));
        new Damier(cases(10,10));


    }

    @Test
    public void testCaseNulle(){
        assertThrows(RuntimeException.class, () -> {

            Damier.Case[][] cases = cases(8, 8);
            cases[4][4] = null;
            new Damier(cases);

        });
    }
    
    @Test
    public void testManoury8(){
        Damier.Case[][] cases = cases(8, 8);
        cases[0][0]=new Damier.Case(Damier.CaseType.Interdit ,null, null);
        Damier damier = new Damier(cases);

        assertEquals(damier.getCase(3,2), damier.getCase(14));
        assertEquals(damier.getCase(4,5), damier.getCase(19));
        assertEquals(damier.getCase(5,0), damier.getCase(21));
        assertEquals(damier.getCase(2,7), damier.getCase(12));
    }
    @Test
    public void testManoury10(){
        Damier.Case[][] cases = cases(10, 10);
        cases[0][0]=new Damier.Case(Damier.CaseType.Interdit ,null, null);
        Damier damier = new Damier(cases);

        assertEquals(damier.getCase(3,4), damier.getCase(18));
        assertEquals(damier.getCase(6,9), damier.getCase(35));
        assertEquals(damier.getCase(0,1), damier.getCase(1));
        assertEquals(damier.getCase(1,0), damier.getCase(6));
    }

    @Test
    public void testManoury10Blanc(){
        Damier.Case[][] cases = cases(10, 10);
        Damier damier = new Damier(cases);

        assertEquals(damier.getCase(3,5), damier.getCase(18));
        assertEquals(damier.getCase(6,8), damier.getCase(35));
        assertEquals(damier.getCase(0,0), damier.getCase(1));
        assertEquals(damier.getCase(1,1), damier.getCase(6));
    }

}