package platteville_monopoly;

public abstract class Properties {
   String name;
   int price, mortgage, id;
   int player, numHouses;
   Properties () {
   }
   
   Properties (String name, int price, int mortgage, int id, int numHouses) {
      this.name = name;
      this.price = price;
      this.mortgage = mortgage;
      this.id = id;
      this.player = 10;
      this.numHouses = numHouses;
   }
   
   public String getName () {
      return name;
   }
   
   public int getPrice () {
      return price;
   }
   
   public int getMortgage () {
      return mortgage;
   }
   
   public int getId () {
      return id;
   }
   
   public int getPlayer_id () {
      return player;
   }
   
   public int getNumHouses() {
      return numHouses;
   }
   
   public void setPlayer (int player) {
      this.player = player;
   }
   
   public void setNumHouses (int numHouses) {
      this.numHouses = numHouses;
   }
   
   public abstract int getRent(int value);
}
