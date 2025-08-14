import java.util.ArrayList;

public class singleElectron extends Scene
{

    @Override
    public Fermion[] getFields() {
        return this.fields;
    }

    public Fermion[] fields = new Fermion[] {new Electron(), new Electron(),};

    public singleElectron() {

        this.fields[0].positionMean = new double[] {300, 200};

        this.fields[1].positionMean = new double[] {100, 200};

    }

}
