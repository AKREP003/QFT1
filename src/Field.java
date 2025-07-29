import abdulfatir.jcomplexnumber.ComplexNumber;

public class Field {

    public double[] positionMean = new double[2];

    public double[] momentumMean = new double[2];

    public double uncertainty = 1;
    public static int planck = 2;

    public static int c = 2;



    public int scale = 10;

    public static double sample(double mu, double sigma) {
        double u1 = Math.random();
        double u2 = Math.random();

        double z = Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);

        return mu + sigma * z;
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

    public Field measure() {

        Field fieldBuffer = new Field();

        fieldBuffer.uncertainty = this.uncertainty;

        double positionDev = this.uncertainty * this.scale;
        double momentumDev = (Field.planck / (2.0 * this.uncertainty)) * this.scale;

        fieldBuffer.momentumMean[0] = sample(this.momentumMean[0], momentumDev);
        fieldBuffer.momentumMean[1] = sample(this.momentumMean[1], momentumDev);

        fieldBuffer.positionMean[0] = sample(this.positionMean[0], positionDev);
        fieldBuffer.positionMean[1] = sample(this.positionMean[1], positionDev);

        return fieldBuffer;
    }

    public double action(double time) {return 0;}

}
