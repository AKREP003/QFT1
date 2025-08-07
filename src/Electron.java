import abdulfatir.jcomplexnumber.ComplexNumber;

public class Electron extends Fermion{

    public int charge = -1;
    public int mass = 1;
    public ComplexNumber[] components = {
            new ComplexNumber(1,0),
            new ComplexNumber(0,0),
            new ComplexNumber(0,0),
            new ComplexNumber(0,0)
    };

    public double[] momentumMean = {

            this.mass * c,
            0,
            0,
            0

    };

    public double energy = mass * Math.pow(c, 2);

    @Override
    public void multiplyComponent(int i, ComplexNumber z) {

        this.components[i].multiply(z);

    }

    @Override
    public void addComponent(int i, ComplexNumber z) {

        this.components[i].add(z);

    }

    @Override
    public double getMass() {return this.mass;}
    @Override
    public double getCharge() {return this.charge;}
    @Override
    public ComplexNumber[] getComponents(){return this.components;}
    @Override
    public double[] getMomentumMean(){return this.momentumMean;}

    @Override
    public double getEnergy() {updateEnergy(); return this.energy;}

    @Override
    public double getStaticEnergy() { return getMass() * Math.pow(Field.c, 2);}

    @Override
    public void setEnergy(double e) {this.energy = e;}

    @Override
    public void setPositionMean(int i, double v){this.positionMean[i] = v;}

    @Override
    public void setMomentumMean(int i, double v){this.momentumMean[i] = v;}

}
