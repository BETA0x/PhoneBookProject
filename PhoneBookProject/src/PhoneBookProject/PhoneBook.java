package PhoneBookProject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class PhoneBook {
    private LinkedHashMap<Integer,String> contacts = new LinkedHashMap<>();
    public int contactsCount = 0;
    private Scanner scan = new Scanner(System.in);
    private static final Pattern numberPattern = Pattern.compile("\\d{8,}");
    
    public boolean isValidNumber (String numStr){
        return numberPattern.matcher(numStr).matches();
    }
    public void addContact (){
        System.out.println("Enter Contact Name     *Or 0 to back to Main Menu\n");
        String name = scan.nextLine();
        if (name.equalsIgnoreCase("0")){
            return;
        } else {
            System.out.println("Enter Contact Number 5XXXXXXXX (9 Digits)     *Or 0 to back to Main Menu\n");
            Integer number = scan.nextInt();
            scan.nextLine();
            if (number == 0){
                return;
            } else if (contacts.containsKey(number)){
                System.out.println("\nThe number is Already On!");
                return;
            } else if (!isValidNumber(String.valueOf(number))){
                System.out.println("\nERROR: Number must be 9 digits");
                return;
            } else {
                contactsCount++;
                contacts.put(number,name);
                System.out.println("\nContact Added: " + name + " | " + number);
                return;
            }
        }
    }
    
    public void removeContact (){
        if (!contacts.isEmpty()){
            System.out.println("Enter Contact number to remove    *Or 0 to back to Main Menu\n");
            Integer number = scan.nextInt();
            if( number == 0){
                return;
            }
            if (contacts.containsKey(number)){
                contacts.remove(number);
                contactsCount--;
                System.out.println("");
                return;
            } else {
                System.out.println("\nThere is no contact with "+ number + " number");
                return;
            }
        } else {
            System.out.println("\nThere is no contact Added yet\n");
            return;
        }
    }
    
    public void changeContact(Integer oldNumber, Integer newNumber, String newName){
        contacts.remove(oldNumber);
        contactsCount--;
        contacts.put(newNumber, newName);
        System.out.println("\nContact Changed!\n");
    }
    
    public void editContact (){
        showAllContacts();
        if (contacts.isEmpty()){
            return;
        } else {
            System.out.println("\nWitch conatct you Want to Edit on? 'BY PHONE NUMBER'      *Or 0 to back to Main Menu\n");
            Integer number = scan.nextInt();
            scan.nextLine();
            if (number == 0){
                return;
            } else if (!contacts.containsKey(number)){
                System.out.println("\nNumber is not Found");
                return;
            } else if (!isValidNumber(String.valueOf(number))){
                System.out.println("\nNumber must be 9 Digits"); 
                return;
            } else {
                System.out.println("\nEnter the new name of contact:      *Or 0 to back to Main Menu\n");
                String newName = scan.nextLine();
                if (newName.equalsIgnoreCase("0")){
                    return;
                } else {
                    System.out.println("\nEnter the new number of contact:      *Or 0 to back to Main Menu\n");
                    Integer newNum = scan.nextInt();
                    if (newNum == 0){
                        return;
                    } else {
                        scan.nextLine();
                        changeContact(number, newNum, newName);
                        return;
                    }
                }
            }
        }
    }
    
    public void search4Contact(){
        if(contacts.isEmpty()){
            System.out.println("\nThere is no contact Added yet\n");
            return;
        } else {
            System.out.println("Enter The Contact number for Search    *Or 0 to back to Main Menu\n");
            Integer number = scan.nextInt();
            
            if(number == 0){
                return;
            } else if (!isValidNumber(String.valueOf(number))){
                System.out.println("\nERROR: Number must be 9 digits");
                return;
            } else if (contacts.containsKey(number)){
                System.out.println("\nContact Found !\n\nName: " + contacts.get(number) + " - " + number);
                return;
            } else {
                System.out.println("\nThere is no contact with "+ number + " number");
                return;
            }
        }
    }

    public void showAllContacts (){
        if(contacts.isEmpty()){
            System.out.println("\nNo Contact Added Yet");
            return;
        } else {
            ArrayList<Map.Entry<Integer,String>> contactsList = new ArrayList<>(contacts.entrySet());
            Collections.sort(contactsList, (a,b) -> a.getValue().compareToIgnoreCase(b.getValue()));
            System.out.println("\n========= Your Contacts =========");
            System.out.println("\nAll contacots: " + contactsCount + "\n");
            for (Map.Entry<Integer, String> e : contactsList){
                System.out.println(" > " + e.getValue() + " - " + e.getKey() + "\n");
            }
        return;
        }
    }
    
    public void run(){
        boolean x = true;
        while (x) {
            System.out.println("\nPhone Book Menu:\n\n1. Add Contact\n2. Search for Contacts"
                    + "\n3. Show Your Contacts\n4. Edit Contact \n5. Remove Contact\n0. Exit\n");
            System.out.println("Choose: \n");
            int Ch = scan.nextInt();
            scan.nextLine();
            
            switch (Ch){
                case 1:
                    addContact();
                    break;
                case 2:
                    search4Contact();
                    break;
                case 3:
                    showAllContacts();
                    break;
                case 4:
                    editContact();
                    break;
                case 5:
                    removeContact();
                    break;
                case 0:
                    x = false;
                    break;
            }
        }
    }
}
