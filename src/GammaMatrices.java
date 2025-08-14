import abdulfatir.jcomplexnumber.ComplexNumber;

public class GammaMatrices {

    static final ComplexNumber ZERO = new ComplexNumber(0, 0);
    static final ComplexNumber ONE = new ComplexNumber(1, 0);
    static final ComplexNumber NEG_ONE = new ComplexNumber(-1, 0);
    static final ComplexNumber I = new ComplexNumber(0, 1);
    static final ComplexNumber NEG_I = new ComplexNumber(0, -1);

    public static final ComplexNumber[][][] gamma = {{
            { ONE,     ZERO,    ZERO,     ZERO },
            { ZERO,    ONE,     ZERO,     ZERO },
            { ZERO,    ZERO,    NEG_ONE,  ZERO },
            { ZERO,    ZERO,    ZERO,     NEG_ONE }
    },

    {
            { ZERO,    ZERO,    ZERO,     NEG_ONE },
            { ZERO,    ZERO,    NEG_ONE,  ZERO },
            { ZERO,    ONE,     ZERO,     ZERO },
            { ONE,     ZERO,    ZERO,     ZERO }
    },

    {
            { ZERO,    ZERO,    ZERO,     I },
            { ZERO,    ZERO,    NEG_I,    ZERO },
            { ZERO,    I,       ZERO,     ZERO },
            { NEG_I,   ZERO,    ZERO,     ZERO }
    },

    {
            { ZERO,    ZERO,    ONE,      ZERO },
            { ZERO,    ZERO,    ZERO,     NEG_ONE },
            { NEG_ONE, ZERO,    ZERO,     ZERO },
            { ZERO,    ONE,     ZERO,     ZERO }
    }};

    public static ComplexNumber[] getConjugate(ComplexNumber[] original) {

        ComplexNumber[] buffer = new ComplexNumber[4];

        for (int i = 0; i < 4; i++) {
            buffer[i] = original[i].conjugate();
        }

        return buffer;
    }

    public static ComplexNumber[] multiplyByMatrix(ComplexNumber[] v, ComplexNumber[][] A) {

        ComplexNumber[] buffer = new ComplexNumber[A.length];

        for (int i = 0; i < A.length; i++) {

            ComplexNumber rowBuffer = new ComplexNumber();

            for (int j = 0; j < A[i].length; j++){

                ComplexNumber addBuffer = new ComplexNumber();

                addBuffer.add(v[j]);

                addBuffer.multiply(A[i][j]);

                rowBuffer.add(addBuffer);

            }

            buffer[i] = rowBuffer;

        }

        return buffer;

    }

    public static ComplexNumber[] multiplyRowByMatrix(ComplexNumber[] row, ComplexNumber[][] matrix) {
        ComplexNumber[] result = new ComplexNumber[matrix[0].length];

        for (int j = 0; j < matrix[0].length; j++) {
            ComplexNumber sum = new ComplexNumber();
            for (int i = 0; i < row.length; i++) {
                ComplexNumber term = new ComplexNumber(row[i].getRe(), row[i].getIm());
                term.multiply(matrix[i][j]);
                sum.add(term);
            }
            result[j] = sum;
        }

        return result;
    }


    public static ComplexNumber[] getAdjoint(ComplexNumber[] psi) {

        return multiplyRowByMatrix(getConjugate(psi), gamma[0]);

    }

    public static ComplexNumber exp(ComplexNumber z) {
        double a = z.getRe();
        double b = z.getIm();
        double expA = Math.exp(a);
        return new ComplexNumber(
                expA * Math.cos(b),
                expA * Math.sin(b)
        );
    }

    public static ComplexNumber multiplyByVector(ComplexNumber[] v1, ComplexNumber[] v2) {

        ComplexNumber buffer = new ComplexNumber();

        for (int i = 0; i < v1.length; i++){

            ComplexNumber multBuffer = new ComplexNumber();

            multBuffer.add(v1[i]);

            multBuffer.multiply(v2[i]);

            buffer.add(multBuffer);

        }

        return buffer;
    }

    public static ComplexNumber[] multiplyByScalar(ComplexNumber x, ComplexNumber[] v) {

        ComplexNumber[] buffer = new ComplexNumber[v.length];

        for (int i = 0; i < v.length; i++){

            ComplexNumber multBuffer = new ComplexNumber();

            multBuffer.add(v[i]);

            multBuffer.multiply(x);

            buffer[i] = new ComplexNumber();

            buffer[i].add(multBuffer);

        }

        return buffer;

    }

    public static ComplexNumber[] complexify(double[] v1) {

        ComplexNumber[] buffer = new ComplexNumber[v1.length];

        for (int i = 0; i < v1.length; i++) {

            buffer[i] = new ComplexNumber(v1[i], 0);
        }

        return buffer;

    }

    public static ComplexNumber[] subtractFromVector(ComplexNumber[] v1, ComplexNumber[] v2) {

        ComplexNumber[] buffer = new ComplexNumber[v1.length];

        for (int i = 0; i < v1.length; i++) {

            buffer[i].add(v1[i]);

            buffer[i].subtract(v2[i]);

        }

        return buffer;
    }

}
