
public class Polynomial {
    private double[] coefficients;

    //Constructer for zer polynomial
    public Polynomial(){
        this.coefficients=new double[]{0};
    }

    
    //Constructor for polynomial of the form P(x) = cxd
    public Polynomial(int d,double c){
        this.coefficients=new double[d+1];
        this.coefficients[d]=c;

    }

    // Constructor for polynomial from array of coefficients
    public Polynomial(double[] coefficents) {
        this.coefficients = coefficents;
    }


    public double getCoefficents(int d) {

        return coefficients[d];
    }
    public int getDegree(){

        return coefficients.length-1;
    }
 // Method to convert polynomial to string
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = this.coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) {
                if (result.length() > 0) {
                    result.append(" ");
                    if (coefficients[i] > 0)
                        result.append("+ ");
                }
                result.append(coefficients[i]);
                if (i > 0)
                    result.append("x^").append(i);
            }
        }
        return result.toString();
    }
 // Method for evaluating the polynomial at given x
    public double eval(double x){
        double res=0;
        for(int i=0;i<coefficients.length;i++){
            res+=coefficients[i]*Math.pow(x, i);
        }

        return res;
    }   
    // Method for evaluating the polynomial at given x but using Horners Method
    public double eval2(double x){
        double res=coefficients[coefficients.length-1];
        for(int i=coefficients.length-2;i>=0;i--){
            res=res*x+coefficients[i];
        }

        return res;
    }   

    
}
