package PhoneBookProject;

import java.util.LinkedHashMap;

public class PhoneBook {
    private LinkedHashMap<Integer,String> contacts = new LinkedHashMap<>();
    public int contactsCount = 0;
    
    public void addContact (Integer number, String name){
        if (contacts.containsKey(number)){
            System.out.println("The number is already exist!");
        } else{
            contactsCount++;
            contacts.put(number,name);
            System.out.println("Contact Added: " + name + " | " + number);
        }
    }
    
    public void removeContact (Integer number){
        if (contacts.containsKey(number)){
            contacts.remove(number);
            contactsCount--;
            System.out.println("Contact Removed");
        } else {
            System.out.println("There is no contact with "+ number + " number, or wrong Input!");
        }
    }
    
    public void search4Contact(Integer number){
        if (contacts.containsKey(number)){
            System.out.println("Contact Found !\nName: " + contacts.get(number) + " - " + number);
        } else {
            System.out.println("There is no contact with "+ number + " number, or wrong Input!");
        }
    }
    
    public void showAllContacts (){
        if(contacts.isEmpty()){
            System.out.println("No Contact Added Yet");
        } else {
            System.out.println("\n========= Your Contacts: =========");
            for (Integer number : contacts.keySet()){
                System.out.println(" > " + contacts.get(number) + " - " + number);
            }
        }
    }
    
}
