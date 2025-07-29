import processing.core.PApplet;


public class Display extends PApplet{

    public int width = 1000;
    public int height = 1000;



    public void settings() {
        size(width, height);

    }

    public void draw() {
        background(0);

        Field p = new Field();

        p.positionMean[0] = 500;
        p.positionMean[1] = 500;

        p.scale = 20;

        Field pM = p.measure();


        circle((float)(pM.positionMean[0]), (float)(pM.positionMean[1]), 10);


    }

    public static void main(String... args) {
        PApplet.main("Display");
    }
}