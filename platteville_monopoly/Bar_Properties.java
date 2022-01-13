package platteville_monopoly;

public class Bar_Properties extends Properties{
   int oneBar, twoBars, threeBars, fourBars;
   Bar_Properties () {
   }
   
   Bar_Properties (String name, int price, int mortgage, int id, int numHouses) {
      super(name, price, mortgage, id, numHouses);
      this.oneBar = 25;
      this.twoBars = 50;
      this.threeBars = 100;
      this.fourBars = 200;
   }
   
   @Override
   public int getRent(int numBars) {
      if (numBars == 1)
         return oneBar;
      else if (numBars == 2)
         return twoBars;
      else if (numBars == 3)
         return threeBars;
      else
         return fourBars;
   }
}
