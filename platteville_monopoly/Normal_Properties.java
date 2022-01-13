package platteville_monopoly;

public class Normal_Properties extends Properties {
   String color;
   int numHouses, rent, house1, house2, house3, house4, hotel;
   Normal_Properties () {
   }
   
   Normal_Properties (String name, int price, int mortgage, int id, int numHouses, String color, 
      int rent, int h1, int h2, int h3, int h4, int hotel) {
      super(name, price, mortgage, id, numHouses);
      this.numHouses = 0;
      this.color = color;
      this.rent = rent;
      this.house1 = h1;
      this.house2 = h2;
      this.house3 = h3;
      this.house4 = h4;
      this.hotel = hotel;
   }
   
   public int getNumHouses() {
      return numHouses;
   }   
   public String getColor() {
      return color;
   }
   
   @Override
   public int getRent(int houses) {
      if (houses == 0)
         return rent;
      else if (houses == 1)
         return house1;
      else if (houses == 2)
         return house2;
      else if (houses == 3)
         return house3;
      else if (houses == 4)
         return house4;
      else
         return hotel;
   }
   
   public int getHouse1() {
      return house1;
   }
   
   public int getHouse2() {
      return house2;
   }
   
   public int getHouse3() {
      return house3;
   }
   
   public int getHouse4() {
      return house4;
   }
   
   public int getHotel () {
      return hotel;
   }
}
