import abdulfatir.jcomplexnumber.ComplexNumber;

import java.util.ArrayList;

public class Scene {

    public ArrayList<Field> fields = new ArrayList<Field>();

    public double timePerStep = 1;
    public double scale = 10;

    //implement additive amplitude over grid logic

    public void evolveSpin(double time, Fermion f) {

        double energy = f.getEnergy();
        double phaseArg = energy * time / Field.planck;

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

}
