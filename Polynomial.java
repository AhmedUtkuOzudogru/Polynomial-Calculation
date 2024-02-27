public class Polynomial {
    private double[] coefficients;

    // Constructer for zer polynomial
    public Polynomial() {
        this.coefficients = new double[] { 0 };
    }

    // Constructor for polynomial of the form P(x) = cxd
    public Polynomial(int d, double c) {
        this.coefficients = new double[d + 1];
        this.coefficients[d] = c;

    }

    // Constructor for polynomial from array of coefficients
    public Polynomial(double[] coefficents) {
        this.coefficients = coefficents;
    }

    public double getCoefficents(int d) {

        return this.coefficients[d];
    }

    public int getDegree() {

        return this.coefficients.length - 1;
    }

    // Method to convert polynomial to string
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = this.coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) {
                if (result.length() > 0) {
                    result.append(" ");
                    if (coefficients[i] > 0) {
                        result.append("+ ");
                    }

                }
                result.append(coefficients[i]);
                if (i > 0)
                    result.append("x^").append(i);
            }
        }
        return result.toString();
    }

    // Method for evaluating the polynomial at given x
    public double eval(double x) {
        double res = 0;
        for (int i = 0; i < coefficients.length; i++) {
            res += coefficients[i] * Math.pow(x, i);
        }

        return res;
    }

    // Method for evaluating the polynomial at given x but using Horners Method
    public double eval2(double x) {
        double res = coefficients[coefficients.length - 1];
        for (int i = coefficients.length - 2; i >= 0; i--) {
            res = res * x + coefficients[i];
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

        for (int i = 0; i <= maxDegree; i++) {
            resultCoefficients[i] = this.getCoefficents(i) + p2.getCoefficents(i);
        }

        return new Polynomial(resultCoefficients);
    }
    /*
     * Same as add method but substract
     */
    public Polynomial sub(Polynomial p2) {
        int maxDegree = Math.max(this.getDegree(), p2.getDegree());
        double[] resultCoefficients = new double[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            resultCoefficients[i] = this.getCoefficents(i) - p2.getCoefficents(i);
        }
        return new Polynomial(resultCoefficients);
    }
    public Polynomial mul(Polynomial p2) {
        int resultDegree = this.getDegree() + p2.getDegree();
        double[] resultCoefficients = new double[resultDegree + 1];
    
        for (int i = 0; i <= this.getDegree(); i++) {
            for (int j = 0; j <= p2.getDegree(); j++) {
                resultCoefficients[i + j] += this.getCoefficents(i) * p2.getCoefficents(j);
            }
        }
    
        return new Polynomial(resultCoefficients);
    }
    public Polynomial compose(Polynomial p2) {
        int resultDegree = this.getDegree() * p2.getDegree();
        double[] resultCoefficients = new double[resultDegree + 1];
    
        for (int i = 0; i <= this.getDegree(); i++) {
            double coeff = this.getCoefficents(i);
            Polynomial temp = new Polynomial(i, coeff); // Create polynomial c*x^i
            for (int j = 0; j <= p2.getDegree(); j++) {
                double p2Coeff = p2.getCoefficents(j);
                temp = temp.mul(p2); // Multiply by p2
                temp = temp.mul(new Polynomial(new double[]{p2Coeff, 0})); // Multiply by p2Coeff*x^0
            }
            // Add temp's coefficients to resultCoefficients
            int len = Math.max(resultCoefficients.length, temp.coefficients.length);
            double[] newResultCoefficients = new double[len];
            for (int k = 0; k < len; k++) {
                double val1;
                if (k < resultCoefficients.length) {
                    val1 = resultCoefficients[k];
                } else {
                    val1 = 0;
                }
                
                double val2;
                if (k < temp.coefficients.length) {
                    val2 = temp.coefficients[k];
                } else {
                    val2 = 0;
                }
                
                newResultCoefficients[k] = val1 + val2;
            }
            resultCoefficients = newResultCoefficients;
        }
    
        return new Polynomial(resultCoefficients);
    }
}
