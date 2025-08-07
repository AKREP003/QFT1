import abdulfatir.jcomplexnumber.ComplexNumber;

import java.util.ArrayList;

public class Scene {

    public Fermion[] fields = new Fermion[0];

    public double timePerStep = 1;
    public static double scale = 10;

    public static int planck = 2;

    public static int c = 1;

    public static int e = 1;

    public double samplePerStep = 2;

    public double[][] lattice = new double[500][500];

    public Fermion[] getFields() {
        return fields;
    }



    public void evolveSpin(double time, Fermion f) {

        double energy = f.getEnergy();
        double phaseArg = energy * time / Scene.planck;

        ComplexNumber phase;

        if (f.getCharge() < 0) {

            phase = new ComplexNumber(Math.cos(-phaseArg), Math.sin(-phaseArg));
            f.multiplyComponent(0, phase);
            f.multiplyComponent(1, phase);

        } else {

            phase = new ComplexNumber(Math.cos(phaseArg), Math.sin(phaseArg));
            f.multiplyComponent(2, phase);
            f.multiplyComponent(3, phase);
        }
    }

    ComplexNumber[] getStepChange(Fermion f){

        double[] positionBuffer = f.positionMean.clone();

        double[] nextPosition = f.measure();

        ComplexNumber[] path = GammaMatrices.subtractFromVector(GammaMatrices.complexify(nextPosition), GammaMatrices.complexify(positionBuffer));

        ComplexNumber divisor = new ComplexNumber(1.0 / this.timePerStep, 0);

        return GammaMatrices.multiplyByScalar(divisor, path);

    }

    public void sampleFields() {

        Fermion[] fieldList = getFields();

        for (Fermion f : fieldList) {



        }

    }


}
