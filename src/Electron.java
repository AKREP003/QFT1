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

    @Override
    public void multiplyComponent(int i, ComplexNumber z) {

        this.components[i].multiply(z);

    }

    @Override
    public double getMass() {return this.mass;}
    @Override
    public double getCharge() {return this.charge;}
    @Override
    public ComplexNumber[] getComponents(){return this.components;}
}
