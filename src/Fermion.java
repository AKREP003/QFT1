import abdulfatir.jcomplexnumber.ComplexNumber;

public class Fermion extends Field{


    public ComplexNumber[] components = new ComplexNumber[4];
    public int charge = 0;
    public int mass = 0;
    public ComplexNumber phase = new ComplexNumber(0,0);
}
