import processing.core.PApplet;


public class Display extends PApplet{
    public int a = 5;
    public int b = 5;

    public double inca = 1;
    public double incb = 3;

    public int width = 1000;
    public int height = 1000;

    public int rectwidth = 100;
    public int rectheigth = 150;

    public void settings() {
        size(width, height);

    }

    public void draw() {
        background(0);




        fill(204, 102, 0);

        rect(a, b, rectwidth, rectheigth);

        if ( a <= 1 || a >= (width - rectwidth)) {
            inca = - inca;
        }
        if ( b <= 1 || b >= (height - rectheigth)) {
            incb = - incb;
        }

        a = (int) ((double) a  + inca);
        b = (int) ((double) b + incb);

    }

    public static void main(String... args) {
        PApplet.main("Display");
    }
}