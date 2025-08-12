import processing.core.PApplet;


public class Display extends PApplet{

    public int width = 1000;
    public int height = 1000;

    singleElectron scene = new singleElectron();

    public void settings() {
        size(width, height);

    }

    public void draw() {
        background(0);

        this.scene.sampleFields();

        for (int i = 0; i < this.scene.lattice.length; i ++) {

            for (int j = 0; j < this.scene.lattice[i].length; j ++) {

                if (this.scene.lattice[i][j] != null) {

                    double amplitude = Math.pow(this.scene.lattice[i][j].getRe(), 2) +  Math.pow(this.scene.lattice[i][j].getIm(), 2);


                    set(300 + i, 300 + j, color(200, 100 , 0));

                    this.scene.lattice[i][j] = null;

                }

            }

        }


    }

    public static void main(String... args) {
        PApplet.main("Display");
    }
}