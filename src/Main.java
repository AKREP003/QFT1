
import abdulfatir.jcomplexnumber.ComplexNumber;
import processing.core.PApplet;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World, Java app");

        Fermion f = new Fermion();

        f.components[0] = new ComplexNumber(1,1);



        System.out.println(Field.sample(0,2));

    }
}