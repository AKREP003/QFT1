import abdulfatir.jcomplexnumber.ComplexNumber;

import java.util.Arrays;

public class Fermion extends Field{

    public ComplexNumber[] components = new ComplexNumber[4];
    public double charge = 0;
    public double mass = 0;





    public ComplexNumber[] getComponents(){return this.components;}

    public void multiplyComponent(int i, ComplexNumber z) {

        this.components[i].multiply(z);

    }

    public double getMass() {return this.mass;}
    public double getCharge() {return this.charge;}

    public void evolveSpin(double time) {

        double energy = getMass() * Math.pow(Field.c, 2);
        double phaseArg = energy * time / Field.planck;

        ComplexNumber phase;

        if (getCharge() < 0) {

            phase = new ComplexNumber(Math.cos(-phaseArg), Math.sin(-phaseArg));
            multiplyComponent(0, phase);
            multiplyComponent(1, phase);

        } else {

            phase = new ComplexNumber(Math.cos(phaseArg), Math.sin(phaseArg));
            multiplyComponent(2, phase);
            multiplyComponent(3, phase);
        }
    }

}
