
import abdulfatir.jcomplexnumber.ComplexNumber;
import processing.core.PApplet;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World, Java app");

        Electron electron = new Electron();


        while (true) {

            electron.evolveSpin(0.1);

            System.out.println(Arrays.toString(electron.components));

            System.out.println(electron.getDiracDensity());

        }



    }
}