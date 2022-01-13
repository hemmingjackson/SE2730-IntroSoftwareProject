package platteville_monopoly;

public class Gameboard_Layout {
   int xCoordinate, yCoordinate, location_id, property_id;
   char type;
   Gameboard_Layout (){
   }
   
   Gameboard_Layout (char type, int location_id, int property_id, int xCoordinate, int yCoordinate){
      this.type = type;
      this.location_id = location_id;
      this.property_id = property_id;
      this.xCoordinate = xCoordinate;
      this.yCoordinate = yCoordinate;
   }
   
   public char getType () {
      return type;
   }
   
   public int getPropertyId () {
      return property_id;
   }
   
   public int getLocationId () {
      return location_id;
   }
   
   public int getxCoordinate () {
      return xCoordinate;
   }
   
   public int getyCoordinate () {
      return yCoordinate;
   }
}
