import abdulfatir.jcomplexnumber.ComplexNumber;

import java.util.Arrays;

public class Fermion extends Field{

    public ComplexNumber[] components = new ComplexNumber[4];
    public double charge = 0;
    public double mass = 1;
    public double energy = mass * Math.pow(c, 2);

    public ComplexNumber[] getComponents(){return this.components;}

    public void multiplyComponent(int i, ComplexNumber z) {

        this.components[i].multiply(z);

    }

    public void addComponent(int i, ComplexNumber z) {

        this.components[i].add(z);

    }

    public double getMass() {return this.mass;}
    public double getCharge() {return this.charge;}
    public double[] getMomentumMean(){return this.momentumMean;}
    public void setMomentumMean(int i, double v){this.momentumMean[i] = v;}
    public double[] getPositionMean(){return this.positionMean;}
    public void setPositionMean(int i, double v){this.positionMean[i] = v;}

    public double getEnergy() {return this.energy;}

    public void setEnergy(double e) {this.energy = e;}

    public void updateEnergy() {

        double sumBuffer = 0;

        double[] momentum = getMomentumMean();

        for (int i = 1; i < 4; i++) {

            sumBuffer += Math.pow(momentum[i], 2);

        }

        sumBuffer = sumBuffer * Math.pow(Field.c, 2);

        sumBuffer += Math.pow(getMass(), 2) * Math.pow(Field.c, 4);

        sumBuffer = Math.sqrt(sumBuffer);

        setEnergy(sumBuffer);

        setMomentumMean(0, sumBuffer / Field.c);

    }



    public double getDiracDensity(ComplexNumber[] D, double[] A) {

        ComplexNumber[] psi = getComponents();
        ComplexNumber[] psiAdjoint = GammaMatrices.getAdjoint(psi);

        updateEnergy();

        ComplexNumber kinetic = new ComplexNumber(0,0);

        for (int i = 0; i < 4; i++) {

            ComplexNumber[] changeBuffer = GammaMatrices.multiplyByMatrix(psi, GammaMatrices.gamma[i]);

            ComplexNumber total = new ComplexNumber();

            total.add(D[i]);

            total.add(new ComplexNumber(0, Field.e * A[i]));

            changeBuffer = GammaMatrices.multiplyByScalar(total, changeBuffer);

            changeBuffer = GammaMatrices.multiplyByScalar(new ComplexNumber(0,1), changeBuffer);

            kinetic.add(GammaMatrices.multiplyByVector(psiAdjoint, changeBuffer));

        }


        ComplexNumber matter = new ComplexNumber(getMass() * Math.pow(c, 2), 0);
        ComplexNumber adjDotPsi = GammaMatrices.multiplyByVector(psiAdjoint, psi);
        matter.multiply(adjDotPsi);

        kinetic.subtract(matter);

        return kinetic.getRe();
    }


    public void updatePosition(double time) {

        double[] momentum = getMomentumMean();
        double[] position = getPositionMean();

        updateEnergy();

        double energy = getEnergy();

        setPositionMean(0, position[0] + ((momentum[1] * Math.pow(c, 2)) / energy) * time);
        setPositionMean(1, position[1] + ((momentum[2] * Math.pow(c, 2)) / energy) * time);

    }

}
