import java.lang.Math;
class Polynomial{
    double[] terms;
    public Polynomial(){
        this.terms = new double[1];
        this.terms[0] = 0;
    }
    public Polynomial(double[] arr){
        this.terms = arr.clone();
    }
    public int getLength(){
        return this.terms.length;
    }
    public Polynomial add(Polynomial poly){
        if (this.terms.length > poly.getLength()){
            for (int i = 0; i < poly.getLength(); i++){
                poly.terms[i] += this.terms[i];
            }
        }
        else{
            for (int i = 0; i < this.terms.length; i++){
                poly.terms[i] += this.terms[i];
            }
        }
        return poly;
    }
    public double evaluate(double xVal){
        double sum = 0.0;
        double power = 0.0;
        for (int i = 0; i < this.terms.length; i++){
            power = Math.pow(xVal, i);
            sum += (power)*(this.terms[i]);
        }
        return sum;
    }
    public boolean hasRoot(double xVal){
        if (this.evaluate(xVal) == 0.0) return true;
        return false;
    }
}