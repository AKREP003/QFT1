
import abdulfatir.jcomplexnumber.ComplexNumber;
import processing.core.PApplet;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World, Java app");

        Electron electron = new Electron();



        while (true) {

            double dense = electron.getDiracDensity(new ComplexNumber[] {new ComplexNumber(0, -electron.getEnergy()), new ComplexNumber(), new ComplexNumber(),new ComplexNumber()}, new double[]{0,0,0,0});

            System.out.println(dense);

            System.out.println(Arrays.toString(electron.components));


        }



    }
}