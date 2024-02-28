import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

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
    public Polynomial(double[] anArrayOfCoefficents) {
        coefficients=new double[anArrayOfCoefficents.length];
        for(int a=0;a<anArrayOfCoefficents.length;a++)
        {
            this.coefficients[a] = anArrayOfCoefficents[a];
        }
        //  coefficients=Arrays.copyOf(anArrayOfCoefficents,anArrayOfCoefficents.length);
    }


    public double getCoefficents(int d) {
        if(this.coefficients.length>d&&d>-1){
            return this.coefficients[d];

        }else{
            System.out.println(" Pls give int thats in bounds of the array");
        }
        return 0;
    }
    public int getDegree(){
        for(int i=this.coefficients.length-1;i>=0;i--)
        {
            if(this.coefficients[i]!=0){
                return i;
            }
        }

        return 0;
    }
 // Method to convert polynomial to string
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = this.coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) { // if zero skips the  cofficents and x
                if (result.length() > 0) { // skips + sign for the first value
                    result.append(" ");
                    if (coefficients[i] > 0)
                    {
                        result.append("+ ");
                    }
                }
                result.append(coefficients[i]);

                if (i >1){
                    result.append("x^").append(i);
                }else if(i==1){
                    result.append("x");
                }
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

    /*
     * creates a new array by getting the max degree from both of them
     * then iterates both by adding their coefficents
     * 
     */
    public Polynomial add(Polynomial p2) {
        int maxDegree = Math.max(this.getDegree(), p2.getDegree());
        double[] resultCoefficients = new double[maxDegree + 1];
    
        for (int i = 0; i <= this.getDegree(); i++) {
            resultCoefficients[i] += this.getCoefficents(i);
        }
    
        for (int i = 0; i <= p2.getDegree(); i++) {
            resultCoefficients[i] += p2.getCoefficents(i);
        }
    
        return new Polynomial(resultCoefficients);
    }
    /*
     * Same as add method but substract
     */
    public Polynomial sub(Polynomial p2) {
        int maxDegree = Math.max(this.getDegree(), p2.getDegree());
        double[] resultCoefficients = new double[maxDegree + 1];
    
        for (int i = 0; i <= this.getDegree(); i++) {
            resultCoefficients[i] += this.getCoefficents(i);
        }
    
        for (int i = 0; i <= p2.getDegree(); i++) {
            resultCoefficients[i] -= p2.getCoefficents(i);
        }
    
        return new Polynomial(resultCoefficients);
    }
    public Polynomial mul(Polynomial p2) {
        int resultDegree = this.getDegree() + p2.getDegree();
        double[] resultCoefficients = new double[resultDegree + 1];
    
        // Initialize resultCoefficients array with zeros
        for (int i = 0; i <= resultDegree; i++) {
            resultCoefficients[i] = 0;
        }
    
        for (int i = 0; i <= this.getDegree(); i++) {
            for (int j = 0; j <= p2.getDegree(); j++) {
                // Multiply the coefficients and add to the corresponding index
                resultCoefficients[i + j] += this.getCoefficents(i) * p2.getCoefficents(j);
            }
        }
    
        return new Polynomial(resultCoefficients);
    }
    
    public Polynomial compose(Polynomial p2) {
        int resultDegree = this.getDegree() * p2.getDegree();
        double[] resultCoefficients = new double[resultDegree + 1];
    
        for (int i = 0; i <= this.getDegree(); i++) {
            double coeff1 = this.getCoefficents(i);
            if (coeff1 != 0) {
                Polynomial term = p2.power(i);
                for (int j = 0; j <= term.getDegree(); j++) {
                    resultCoefficients[j] += coeff1 * term.getCoefficents(j);
                }
            }
        }
    
        return new Polynomial(resultCoefficients);
    }
    
    public Polynomial power(int exponent) {
        Polynomial result = new Polynomial(0, 1);
        for (int i = 0; i < exponent; i++) {
            result = result.mul(this);
        }
        return result;    
    }
    public Polynomial div(Polynomial p2) {
        Polynomial divisor = new Polynomial(p2.coefficients); // Make a copy of the current polynomial
        Polynomial dividend = new Polynomial(this.coefficients); // Make a copy of the current polynomial
        double[] resCoff = new double[dividend.getDegree() - p2.getDegree() + 1];
        Polynomial res = new Polynomial(resCoff);
        
        System.out.println("Dividend (P(x)): " + dividend);
        System.out.println("Divisor (Q(x)): " + p2);
        
        while (dividend.getDegree() >= divisor.getDegree()) {
            // Step 1: Find leading terms
            int leadP = dividend.getDegree();
            int leadQ = divisor.getDegree();
            
            // Step 2: Find T(x)
            double leadPVal = dividend.getCoefficents(leadP);
            double leadQVal = divisor.getCoefficents(leadQ);
            double[] tCoefficients = new double[leadP - leadQ + 1];
            tCoefficients[leadP - leadQ] = leadPVal / leadQVal;
            Polynomial t = new Polynomial(tCoefficients);

            
            System.out.println("Dividend degree: " + dividend.getDegree() + ", Divisor degree: " + divisor.getDegree());
            System.out.println("T(x): " + t);
            
            // Step 3: Subtract T(x) * Q(x) from P(x)
            Polynomial subtract = p2.mul(t);
            System.out.println("T(x) * Q(x): " + subtract);
            dividend = dividend.sub(subtract);
            System.out.println("After subtraction: " + dividend);
            
            // Step 4: Update quotient by adding T(x)
            res = res.add(t);

        }
        
        return res;
    }
    public int[] findEqual(Polynomial p2) {
        ArrayList<Integer> solutions = new ArrayList<>();

        for (int x = 1; x <= 200; x++) {

            double eval1 = this.eval(x);
            double eval2 = p2.eval(x);

            // Check if the evaluations are equal (using some tolerance for double comparison)
            if (Math.abs(eval1 - eval2) < 1) {
                solutions.add(x);
            }
        }

        // Convert ArrayList to int array
        int[] result = new int[solutions.size()];
        for (int i = 0; i < solutions.size(); i++) {
            result[i] = solutions.get(i);
        }

        return result;
    }

    
    
    
}