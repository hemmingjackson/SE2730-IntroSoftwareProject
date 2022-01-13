package platteville_monopoly;

public class Monetary_Cards extends Monopoly_Cards {
   int value;
   Monetary_Cards () {
   }
   
   Monetary_Cards(int value, String prompt1, String prompt2) {
      super(prompt1, prompt2);
      this.value = value;
   }
   
   public int getValue() {
      return value;
   }
}
