import java.io.File;

public class Driver {
public static void main(String [] args) {
    Polynomial p = new Polynomial();
    System.out.println(p.evaluate(3));
    double [] c1 = {6,5};
    int [] pow1 = {1,3};
    Polynomial p1 = new Polynomial(c1, pow1);
    double [] c2 = {1,-2,0,0,-9};
    int [] pow2 = {0,1,2,4,5};
    Polynomial p2 = new Polynomial(c2,pow2);
    Polynomial s = p1.add(p2);
    for (int i = 0; i < s.getLength(); i++){
        System.out.println("Coefficient: " + s.coefficients[i] + " Power: " + s.powers[i]);
    }
    System.out.println("here");
    s = p1.multiply(p2);
    for (int i = 0; i < s.getLength(); i++){
        System.out.println("Coefficient: " + s.coefficients[i] + " Power: " + s.powers[i]);
    }
    System.out.println("s(0.1) = " + s.evaluate(0.1));
    if(s.hasRoot(1))
        System.out.println("1 is a root of s");
    else
        System.out.println("1 is not a root of s");
    File f = new File("data.txt");
    Polynomial p3 = new Polynomial(f);
    for (int i = 0; i < p3.getLength(); i++){
        System.out.println("Coefficient: " + p3.coefficients[i] + " Power: " + p3.powers[i]);
    }
    p3.saveToFile("output.txt");
}
}