import java.util.ArrayList;
public class PolynomialTester {
 public static void main(String[] args) {
   ArrayList<Polynomial> pols=new ArrayList<Polynomial>();
   double[] array={1,-2,-3,4,5,6,7,8,9};
    Polynomial pol1 = new Polynomial();
    Polynomial pol2 = new Polynomial(5,5);
    Polynomial pol3 = new Polynomial(array);
    pols.add(pol1);
    pols.add(pol2);
    pols.add(pol3);

   for(Polynomial pol:pols){
      System.out.println("***********************************");
      System.out.println(pol.getCoefficents(0));
      System.out.println(pol.getDegree());
      System.out.println(pol);
      System.out.println(pol.eval(5));
      System.out.println(pol.eval2(5));
      System.out.println("***********************************");

   }
    
 }    
}
