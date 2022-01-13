/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platteville_monopoly;

public class Property_List {
   String name;
   int price, id, listId;
   Property_List () {
   }
   
   Property_List (String name, int price, int id, int listId) {
      this.name = name;
      this.price = price;
      this.id = id;
      this.listId = listId;
   }
   
   public String getName () {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public int getPrice () {
      return price;
   }
   
   public void setPrice(int price) {
      this.price = price;
   }
   
   public int getId() {
      return id;
   }
   
   public void setId(int id) {
      this.id = id;
   }
   
   public int getListId() {
      return listId;
   }
   
   public void setListId(int listId) {
      this.listId = listId;
   }
}
