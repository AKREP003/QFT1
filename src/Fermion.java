import abdulfatir.jcomplexnumber.ComplexNumber;


public class Fermion{
    
    public double[] momentumMean = new double[4];

    public double[] positionMean = new double[2];
    
    public ComplexNumber[] components = new ComplexNumber[4];
    public double charge = 0;
    public double mass = 1;
    public double energy = mass * Math.pow(Scene.c, 2);

    public double uncertainty = 1;

    public Fermion clone() {

        Fermion buffer = new Fermion();

        buffer.components = getComponents();

        buffer.mass = getMass();

        buffer.positionMean = getPositionMean();

        buffer.energy = getEnergy();

        buffer.charge = getCharge();

        return buffer;

    }

    public ComplexNumber[] getComponents(){return this.components.clone();}

    public void multiplyComponent(int i, ComplexNumber z) {

        this.components[i].multiply(z);

    }

    public void addComponent(int i, ComplexNumber z) {

        this.components[i].add(z);

    }

    public double getMass() {return this.mass;}
    public double getCharge() {return this.charge;}
    public double[] getMomentumMean(){return this.momentumMean.clone();}
    public void setMomentumMean(int i, double v){this.momentumMean[i] = v;}
    public double[] getPositionMean(){return this.positionMean.clone();}
    public void setPositionMean(int i, double v){this.positionMean[i] = v;}

    public double getEnergy() {updateEnergy(); return this.energy;}

    public double getStaticEnergy() { return getMass() * Math.pow(Scene.c, 2);}

    public void setEnergy(double e) {this.energy = e;}

    public void updateEnergy() {

        double sumBuffer = 0;

        double[] momentum = getMomentumMean();

        for (int i = 1; i < 4; i++) {

            sumBuffer += Math.pow(momentum[i], 2);

        }

        sumBuffer = sumBuffer * Math.pow(Scene.c, 2);

        sumBuffer += Math.pow(getMass(), 2) * Math.pow(Scene.c, 4);

        sumBuffer = Math.sqrt(sumBuffer);

        setEnergy(sumBuffer);

        setMomentumMean(0, sumBuffer / Scene.c);

    }



    public double getDiracDensity(ComplexNumber[] D, double[] A, double delta) {

        ComplexNumber[] psi = getComponents();
        ComplexNumber[] psiAdjoint = GammaMatrices.getAdjoint(psi);

        updateEnergy();

        ComplexNumber kinetic = new ComplexNumber(0, 0);

        for (int i = 0; i < 4; i++) {


            ComplexNumber coupledDerivative = new ComplexNumber(D[i].getRe(), D[i].getIm());
            coupledDerivative.add(new ComplexNumber(0, Scene.e * A[i]));


            ComplexNumber[] gammaPsi = GammaMatrices.multiplyByMatrix(psi, GammaMatrices.gamma[i]);


            ComplexNumber[] changeBuffer = GammaMatrices.multiplyByScalar(coupledDerivative, gammaPsi);


            changeBuffer = GammaMatrices.multiplyByScalar(new ComplexNumber(0, 1), changeBuffer);


            kinetic.add(GammaMatrices.multiplyByVector(psiAdjoint, changeBuffer));
        }

        // Mass term
        ComplexNumber matter = new ComplexNumber(getMass() * Math.pow(Scene.c, 2) * delta, 0);
        ComplexNumber adjDotPsi = GammaMatrices.multiplyByVector(psiAdjoint, psi);
        matter.multiply(adjDotPsi);

        kinetic.subtract(matter);

        return kinetic.getRe();
    }



    public void updatePosition(double time) {
        double[] momentum = getMomentumMean();
        double[] position = getPositionMean();
        updateEnergy();
        double energy = getEnergy();

        setPositionMean(0, position[0] + ((momentum[1] * Math.pow(Scene.c, 2)) / energy) * time);
        setPositionMean(1, position[1] + ((momentum[2] * Math.pow(Scene.c, 2)) / energy) * time);

    }

    public static double sample(double mu, double sigma) {
        double u1 = Math.random();
        double u2 = Math.random();

        double z = Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);

        return mu + sigma * z;
    }



    public Fermion measure() {

        Fermion buffer = this.clone();

        buffer.uncertainty = this.uncertainty;

        double positionDev = buffer.uncertainty * Scene.scale;


        buffer.positionMean[0] = sample(buffer.positionMean[0], positionDev);
        buffer.positionMean[1] = sample(buffer.positionMean[1], positionDev);

        buffer.momentumMean[1] = (this.positionMean[0] - buffer.positionMean[0]) / (Scene.scale * Scene.timePerStep);
        buffer.momentumMean[2] = (this.positionMean[1] - buffer.positionMean[1]) / (Scene.scale * Scene.timePerStep);

        return buffer;
    }





}
