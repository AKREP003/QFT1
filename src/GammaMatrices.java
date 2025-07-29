import abdulfatir.jcomplexnumber.ComplexNumber;

public class GammaMatrices {

    static final ComplexNumber ZERO = new ComplexNumber(0, 0);
    static final ComplexNumber ONE = new ComplexNumber(1, 0);
    static final ComplexNumber NEG_ONE = new ComplexNumber(-1, 0);
    static final ComplexNumber I = new ComplexNumber(0, 1);
    static final ComplexNumber NEG_I = new ComplexNumber(0, -1);

    public static final ComplexNumber[][] gamma0 = {
            { ONE,     ZERO,    ZERO,     ZERO },
            { ZERO,    ONE,     ZERO,     ZERO },
            { ZERO,    ZERO,    NEG_ONE,  ZERO },
            { ZERO,    ZERO,    ZERO,     NEG_ONE }
    };

    public static final ComplexNumber[][] gamma1 = {
            { ZERO,    ZERO,    ZERO,     NEG_ONE },
            { ZERO,    ZERO,    NEG_ONE,  ZERO },
            { ZERO,    ONE,     ZERO,     ZERO },
            { ONE,     ZERO,    ZERO,     ZERO }
    };

    public static final ComplexNumber[][] gamma2 = {
            { ZERO,    ZERO,    ZERO,     I },
            { ZERO,    ZERO,    NEG_I,    ZERO },
            { ZERO,    I,       ZERO,     ZERO },
            { NEG_I,   ZERO,    ZERO,     ZERO }
    };

    public static final ComplexNumber[][] gamma3 = {
            { ZERO,    ZERO,    ONE,      ZERO },
            { ZERO,    ZERO,    ZERO,     NEG_ONE },
            { NEG_ONE, ZERO,    ZERO,     ZERO },
            { ZERO,    ONE,     ZERO,     ZERO }
    };

    public static ComplexNumber[] getConjugateComponents(ComplexNumber[] original) {

        ComplexNumber[] buffer = new ComplexNumber[4];

        for (int i = 0; i < 4; i++) {
            buffer[i] = original[i].conjugate();
        }

        buffer[2].multiply(new ComplexNumber(-1, 0));
        buffer[3].multiply(new ComplexNumber(-1, 0));

        return buffer;
    }



}
