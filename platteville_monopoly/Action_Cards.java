package platteville_monopoly;

public class Action_Cards extends Monopoly_Cards {
   int location;
   Action_Cards() {
   }
   
   Action_Cards(int location, String prompt1, String prompt2) {
      super(prompt1, prompt2);
      this.location = location;
   }
   
   public int getLocation() {
      return location;
   }
}
