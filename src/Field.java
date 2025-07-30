import abdulfatir.jcomplexnumber.ComplexNumber;

public class Field {

    public double[] positionMean = new double[2];

    public double[] momentumMean = new double[4];

    public double uncertainty = 1;
    public static int planck = 2;

    public static int c = 2;

    public double energy = 0;

    public int scale = 10;

    public static double sample(double mu, double sigma) {
        double u1 = Math.random();
        double u2 = Math.random();

        double z = Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);

        return mu + sigma * z;
    }



    public Field measure() {

        Field fieldBuffer = new Field();

        fieldBuffer.uncertainty = this.uncertainty;

        double positionDev = this.uncertainty * this.scale;
        double momentumDev = (Field.planck / (2.0 * this.uncertainty)) * this.scale;

        fieldBuffer.momentumMean[0] = sample(this.momentumMean[0], momentumDev);
        fieldBuffer.momentumMean[1] = sample(this.momentumMean[1], momentumDev);
        fieldBuffer.momentumMean[2] = sample(this.momentumMean[2], momentumDev);
        fieldBuffer.momentumMean[3] = sample(this.momentumMean[3], momentumDev);

        fieldBuffer.positionMean[0] = sample(this.positionMean[0], positionDev);
        fieldBuffer.positionMean[1] = sample(this.positionMean[1], positionDev);


        return fieldBuffer;
    }

    public double action(double time) {return 0;}

}
