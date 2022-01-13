package platteville_monopoly;

public class Utility_Properties extends Properties {
   int multiplier;
   Utility_Properties () {
   }
   
   Utility_Properties (String name, int price, int mortgage, int id, int numHouses) {
      super(name, price, mortgage, id, numHouses);
      this.multiplier = 5;
   }
   
   @Override
   public int getRent(int diceRoll) {
      return multiplier * diceRoll;
   }
}
