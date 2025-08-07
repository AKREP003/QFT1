
import abdulfatir.jcomplexnumber.ComplexNumber;
import processing.core.PApplet;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World, Java app");

        Electron electron = new Electron();

        double t = 0;

        double sum = 0;

        double delta = 0.0001;

        while (true) {

            ComplexNumber[] buffer = new ComplexNumber[4];

            for (int i = 0; i < 4; i++) {

                buffer[i] = electron.components[i].copy();

            }

            electron.components[0] = GammaMatrices.exp(new ComplexNumber(0, -electron.getEnergy() * t / Scene.planck));

            for (int i = 0; i < 4; i++) {

                buffer[i].subtract(electron.components[i]);

                buffer[i].multiply(new ComplexNumber(-1, 0));

            }

            double dense = electron.getDiracDensity(buffer, new double[]{0,0,0,0}, delta) * delta;

            sum += dense;

            if (t > 10) {
                break;
            }

            t = t + delta;

        }

        System.out.println(sum);


    }
}