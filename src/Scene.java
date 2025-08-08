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

    public ComplexNumber[][] lattice = new ComplexNumber[500][500];

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

    double density(Fermion f) {return 0;}

    ComplexNumber feynman(double S) {return GammaMatrices.exp(new ComplexNumber(S, 0));}

    void actionSum(double current, Fermion f){

        Fermion next = f.measure();

        double nextDensity = this.density(next);

        ComplexNumber act = feynman(current + (Math.min(current, nextDensity) * this.timePerStep) + (Math.abs(nextDensity - nextDensity) / 2));

        this.lattice[(int)next.positionMean[0]][(int) next.positionMean[1]] = act;

    }

    public void sampleFields() {

        Fermion[] fieldList = getFields();

        for (Fermion f : fieldList) {



        }

    }


}
