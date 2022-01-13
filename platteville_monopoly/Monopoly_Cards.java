package platteville_monopoly;

public class Monopoly_Cards{
   String prompt1, prompt2;
   
   Monopoly_Cards() {
   }
   
   Monopoly_Cards(String prompt1, String prompt2) {
      this.prompt1 = prompt1;
      this.prompt2 = prompt2;
   }
   
   public String getPrompt1() {
      return prompt1;
   }
   
   public String getPrompt2() {
      return prompt2;
   }
   //public void setType(char type) {
   //   this.type = type;
   //}
   
   //public void setPrompt(String prompt) {
   //   this.prompt = prompt;
   //}
}
