package platteville_monopoly;

public class Player_Stats {
   int location, cash, temp, numBars, inJail;
   boolean diceRoll;
   
   Player_Stats(){
      this.location = 0;
      this.cash = 1500;
      this.temp = 0;
      this.diceRoll = false;
      this.inJail = 0;
      this.numBars = 0;
   }
   
   Player_Stats(int location, int cash, boolean diceRoll, int inJail, int numBars) {
      this.location = location;
      this.cash = cash;
      this.diceRoll = diceRoll;
      this.inJail = inJail;
      this.numBars = numBars;
   }
   
   public int getLocation() {
      return location;
   }
   
   public int getCash() {
      return cash;
   }
   
   public boolean getDiceRoll () {
      return diceRoll;
   }
   
   public int getInJail () {
      return inJail;
   }
   
   public int getNumBars() {
      return numBars;
   }
   
   public void setLocation(int newLocation) {
      if ((location + newLocation )< 40)
         this.location = location + newLocation;
      else {
         temp = 40 - location;
         this.location = newLocation - temp;
         setCash(200);
      }
   }
   
   public void setCash (int cash) {
      this.cash += cash;
   }
   
   public void setDiceRoll(boolean diceRoll) {
      this.diceRoll = diceRoll;
   }
   
   public void setinJail(int inJail) {
      this.inJail  = inJail;
   }
   
   public void updateinJail(int inJail) {
      this.inJail  += inJail;
   }
   
   public void setNumBars(int numBars) {
      this.numBars += numBars;
   }
}
