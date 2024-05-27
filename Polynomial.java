import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.Scanner;

class Polynomial{
    double[] coefficients;
    int[] powers;
    public Polynomial(){
        coefficients = new double[1];
        coefficients[0] = 0;
        powers = new int[1];
        powers[0] = 0;
    }
    public Polynomial(double[] coeff, int[] pow){
        coefficients = coeff.clone();
        powers = pow.clone();
    }
    public Polynomial(File text){
        try{
        Scanner sc = new Scanner(text);
        String line = sc.nextLine();
        String[] parts = line.split("(?=\\+)|(?=-)");
        coefficients = new double[parts.length];
        powers = new int[parts.length];
        String[] tmp = new String[2];
        for (int i = 0; i < parts.length; i++){
            if (parts[i].length() == 1){
                coefficients[i] = Double.parseDouble(parts[i]);
                powers[i] = 0; 
                System.out.println(coefficients[i] + " " + powers[i]);               
            }
            else{
                tmp = parts[i].split("x");
                coefficients[i] = Double.parseDouble(tmp[0]);
                powers[i] = Integer.parseInt(tmp[1]);
                System.out.println(coefficients[i] + " " + powers[i]);
            }
        }
        sc.close();
    }
        
        
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    public void saveToFile(String filename){
        try{
        FileWriter writer = new FileWriter(filename);
        String line = "";
        for (int i = 0; i < coefficients.length; i++){
            if (powers[i] == 0){
                line = line+coefficients[i] + "+";
            }
            else{
                line = line+coefficients[i] + "x" + powers[i] + "+";
            }
        }
        line = line.replace("+-", "-");
        writer.write(line.substring(0, line.length()-1));

        writer.close();
        }
        catch (IOException e){
            System.out.println("File not found");
    }
    }
    public int getLength(){
        return coefficients.length;
    }
    public int comPow(Polynomial poly){
        int count = 0;
        for (int i = 0; i < this.powers.length; i++){
            for (int j = 0; j < poly.powers.length; j++){
                if (this.powers[i] == poly.powers[j]) count++;
            }
        }
        return count;
    }
    public Polynomial toOldPoly (Polynomial poly){
        double coeff[] = new double[poly.powers[poly.powers.length-1]+1];
        int[] power = new int[poly.powers[poly.powers.length-1]+1];
        Polynomial old = new Polynomial(coeff, power);
        int idx = 0;
        for (int i = 0; i < poly.powers.length; i++){
            while (poly.powers[i] != idx){
                old.coefficients[idx] = 0;
                old.powers[idx] = idx;
                idx++;
            }
            if (poly.powers[i] == idx){
                old.coefficients[idx] = poly.coefficients[i];
                old.powers[idx] = poly.powers[i];
                idx++;
            }
        }
        return old;
    }
    public Polynomial toNewPoly(Polynomial poly){
        int count = 0;
        for (int i = 0; i < poly.coefficients.length; i++){
            if (poly.coefficients[i] == 0){
                count++;
            }
        }
        double coeff[] = new double[poly.coefficients.length - count];
        int[] power = new int[poly.coefficients.length - count];
        Polynomial newPoly = new Polynomial(coeff, power);
        int idx = 0;
        for (int i = 0; i < poly.coefficients.length; i++){
            if (poly.coefficients[i] != 0){
                newPoly.coefficients[idx] = poly.coefficients[i];
                newPoly.powers[idx] = i;
                idx++;
            }
        }
        return newPoly;
    }
    public Polynomial add(Polynomial poly){
        Polynomial p1 = this.toOldPoly(this);
        Polynomial p2 = this.toOldPoly(poly);
        if (p1.coefficients.length > p2.getLength()){
            for (int i = 0; i < p2.getLength(); i++){
                p2.coefficients[i] += p1.coefficients[i];
            }
        }
        else{
            for (int i = 0; i < p1.coefficients.length; i++){
                p2.coefficients[i] += p1.coefficients[i];
            }
        }
        return p2.toNewPoly(p2);    
    }
    public double evaluate(double xVal){
        double value = 0.0;
        for (int i = 0; i < coefficients.length; i++){
            value+= Math.pow(coefficients[i], powers[i]);
        }
        return value;
    }
    public boolean hasRoot(double xVal){
        if (Math.abs(this.evaluate(xVal)) < 0.000000000001) return true;
        return false;
    }
    public Polynomial multiply(Polynomial poly){
        Polynomial p1 = this.toOldPoly(this);
        Polynomial p2 = this.toOldPoly(poly);
        int max_power = p1.powers[p1.powers.length-1] + p2.powers[p2.powers.length-1];
        double[] coeff = new double[max_power+1];
        int[] power = new int[max_power+1];
        Polynomial product = new Polynomial(coeff, power);
        for (int i = 0; i < max_power+1; i++){
            product.coefficients[i] = 0;
            product.powers[i] = i;
        }

        for (int i = 0; i < p2.coefficients.length; i++){
            for (int j = 0; j < p1.coefficients.length; j++){
                product.coefficients[p1.powers[j] + p2.powers[i]] += p1.coefficients[j] * p2.coefficients[i];
                }
            }
        return this.toNewPoly(product);
        }
    }



