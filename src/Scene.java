import abdulfatir.jcomplexnumber.ComplexNumber;

public class Scene {

    public Fermion[] fields = new Fermion[0];

    public static double timePerStep = 1;
    public static double scale = 10;

    public static int planck = 2;

    public static int c = 1;

    public static int e = 1;

    public static double samplePerStep = 2;

    public static double stepPerFrame = 10;

    Measurement[] fieldsBuffer = new Measurement[0];

    public ComplexNumber[][] lattice = new ComplexNumber[500][500];

    public Fermion[] getFields() {
        return this.fields;
    }



    public void evolveSpin(double time, Fermion f) {

        double energy = f.getEnergy();
        double phaseArg = energy * time / Scene.planck;

        ComplexNumber phase;

        if (f.getCharge() < 0) {

            phase = new ComplexNumber(Math.cos(-phaseArg), Math.sin(-phaseArg));
            f.multiplyComponent(0, phase);
            f.multiplyComponent(1, phase);

        } else {

            phase = new ComplexNumber(Math.cos(phaseArg), Math.sin(phaseArg));
            f.multiplyComponent(2, phase);
            f.multiplyComponent(3, phase);
        }
    }

    double density(Fermion f) {

        ComplexNumber[] buffer = new ComplexNumber[4];

        for (int i = 0; i < 4; i++) {

            buffer[i] = f.components[i].copy();

        }

        evolveSpin(this.timePerStep, f);

        for (int i = 0; i < 4; i++) {

            buffer[i].subtract(f.components[i]);

            buffer[i].multiply(new ComplexNumber(-1, 0));

        }

        return f.getDiracDensity(buffer, new double[]{0,0,0,0}, this.timePerStep);



    }

    ComplexNumber feynman(double S) {return GammaMatrices.exp(new ComplexNumber(S, 0));}

    void actionSum(double current, Fermion f, int bufferIndex){

        Fermion next = f.measure();

        double nextDensity = this.density(next);

        double action = current + (Math.min(current, nextDensity) * timePerStep) + (Math.abs(nextDensity - nextDensity) / 2);

        ComplexNumber amplitude = feynman(action);

        if (this.lattice[(int)next.positionMean[0]][(int) next.positionMean[1]] == null) {

            this.lattice[(int)next.positionMean[0]][(int) next.positionMean[1]] = new ComplexNumber();

        }

        this.lattice[(int)next.positionMean[0]][(int) next.positionMean[1]].add(amplitude);

        this.fieldsBuffer[bufferIndex] = new Measurement();

        this.fieldsBuffer[bufferIndex].action = action;

        this.fieldsBuffer[bufferIndex].record = next;
    }

    public void sampleFields() {

        Fermion[] fieldList = getFields();

        fieldsBuffer = new Measurement[this.getFields().length];

        Measurement[] stepBuffer = new Measurement[(int)(fieldList.length)];

        for (int i = 0; i < fieldList.length; i++) {

            stepBuffer[i] = new Measurement();

            stepBuffer[i].record = fieldList[i].clone();

        }

        for (int sample = 0; sample < (stepPerFrame + 1); sample++) {

            this.fieldsBuffer = new Measurement[(int)(this.fieldsBuffer.length * samplePerStep)];

            int indexBuffer = 0;

            for (int fermiIndex = 0; fermiIndex < stepBuffer.length; fermiIndex++) {

                for (int i = 0; i < samplePerStep; i++) {

                    this.actionSum(stepBuffer[fermiIndex].action, stepBuffer[fermiIndex].record, indexBuffer);

                    indexBuffer++;

                }

            }


            stepBuffer = new Measurement[(int)(fieldsBuffer.length)];

            for (int i = 0; i < fieldsBuffer.length; i++) {

                stepBuffer[i] = fieldsBuffer[i].clone();

            }

        }


    }


}
