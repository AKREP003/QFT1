public class Measurement {

    public Fermion record;

    public double action = 0;

    public Measurement clone() {

        Measurement buffer = new Measurement();

        buffer.record = this.record.clone();

        buffer.action = this.action;

        return buffer;

    }

}
