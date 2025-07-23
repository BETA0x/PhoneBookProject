package PhoneBookProject;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PhoneBook {
    private ArrayList<Contact> contacts = new ArrayList<>();
    public int contactsCount = 0;
    private Scanner scan = new Scanner(System.in);
    private static final Pattern phoneNumberPattern = Pattern.compile("5\\d{8}");
    
    public void addContact (){
        System.out.println("Enter Contact Name     *Or 0 to back to Main Menu\n");
        String contactName = scan.nextLine();
        if (contactName.equalsIgnoreCase("0")){
            return;
        } else if(!isValidContactName(contactName)){
            return;
            } else{
                System.out.println("Enter Contact Number 5XXXXXXXX (9 Digits)     *Or 0 to back to Main Menu\n");
                String phoneNumber = scan.nextLine();
                if (phoneNumber.contentEquals("0")){
                    return;
                } else if (isPhoneNumberExists(phoneNumber)){
                    System.out.println("\nThe number is Already On!");
                    return;
                } else if (!isValidPhoneNumber(phoneNumber)){
                    System.out.println("\nSorry, Number must be 9 digits, and starts by 5..");
                    return;
                } else {
                    contactsCount++;
                    contacts.add(new Contact(phoneNumber,contactName));
                    System.out.println("\nContact Added: " + contactName + "\t-\t" + phoneNumber);
                    return;
                }
        }
    }
    
    
    public void removeContact() {
        if (contacts.isEmpty()){
            System.out.println("\nThere is no contact added yet\n");
            return;
        } else {
            showAllContacts();
            System.out.println("\nWitch conatct you Want to Remove? Enter Phone number 'Or' Contact Name      *Or 0 to back to Main Menu\n");
            String chooseContact = scan.nextLine();
            if (chooseContact.equalsIgnoreCase("0")){
                return;
            } else {
                Contact selected = selectContactByInput(chooseContact);
                if (selected == null){
                    return;
                } else {
                    contacts.remove(selected);
                    contactsCount--;
                    System.out.println("\nContact Removed Successfull");
                }
            }
        }
    }
    
    
    public void updateContact (){
        if (contacts.isEmpty()){
            System.out.println("\nThere is no contact added yet\n");
            return;
        } else {
            showAllContacts();
            System.out.println("\nWitch conatct you Want to Update on? Enter Phone number 'Or' Contact Name      *Or 0 to back to Main Menu\n");
            String chooseContact = scan.nextLine();
            
            if (chooseContact.equalsIgnoreCase("0")){
                return;
            } else {
                Contact selected = selectContactByInput(chooseContact);
                if(selected == null){
                    return;
                } else{
                System.out.println("What do you want to Update?\n> 1. Name\n> 2. Phone number      *Or 0 to back to Main Menu\n");
                String choice = scan.nextLine();
                if (choice.equalsIgnoreCase("0")){
                    return;
                } else {
                    switch(choice){
                        case "1":
                            System.out.println("\nEnter the new Name of contact:      *Or 0 to back to Main Menu\n");
                            String newContactName = scan.nextLine();
                            if (newContactName.equalsIgnoreCase("0")){
                                return;
                            } else {
                                if (!isValidContactNameUpdate(newContactName)){
                                    return;
                                } else {
                                    selected.setName(newContactName);
                                    System.out.println("\nContact Name Updated successfull");
                                    return;
                                }
                            }
                        case "2":
                            System.out.println("\nEnter the new Contact Number of contact:      *Or 0 to back to Main Menu\n");
                            String newPhoneNumber = scan.nextLine();
                            if (newPhoneNumber.equalsIgnoreCase("0")){
                                return;
                            } else {
                                if (isPhoneNumberExists(newPhoneNumber)){
                                    System.out.println("\nSorry, This number is already exists for another contact");
                                    return;
                                } else if(isValidPhoneNumber(newPhoneNumber)){
                                    System.out.println("\nSorry, Number must be 9 digits, and starts by 5..");
                                    return;
                                } else {
                                    selected.setNumber(newPhoneNumber);
                                    System.out.println("\nPhone number Upbdated successfull");
                                }
                            }
                        case "0":
                            return;
                        default:
                            System.out.println("\nInvaild Choice");
                            break;
                        }
                    }
                }   
            }    
        }
    }
    
    
    public void showAllContacts(){
        if(contacts.isEmpty()){
            System.out.println("\nNo Contact Added Yet");
            return;
        } else {
            contacts.sort((a,b) -> a.getName().compareToIgnoreCase(b.getName()));
            
            System.out.println("\n========= Your Contacts =========");
            System.out.println("\nAll contacots: " + contactsCount + "\n");
            for (int i = 0; i < contacts.size(); i++){
                Contact c = contacts.get(i);
                System.out.printf("%2d. Name: %-20s | Number: %s\n", i + 1, c.getName(), c.getNumber());
                System.out.println();
            }
        return;
        }
    }
    
    
    
    
    private boolean isPhoneNumberExists(String phoneNumber){
        for (Contact contact : contacts){
            if (contact.getNumber().equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }
    
    
    private boolean isValidPhoneNumber (String phoneNumber){
        return phoneNumberPattern.matcher(phoneNumber).matches();
    }
    
    private boolean isValidContactName(String contactName){
        ArrayList<Contact> matchesContacts = getContactsByName(contactName);
        if (!matchesContacts.isEmpty()){
            System.out.println("\nThere is a contact/s with the same name --\n");
            for (Contact c : matchesContacts){
                System.out.println(c);
            }
            System.out.println("\nAre you sure for Adding another Contact with the same name?\n> 1. Keep Adding\n> 0. Cansel");
            String choice = scan.nextLine();
            return choice.equalsIgnoreCase("1");
        }
        return true;
    }
    
    
    private boolean isValidContactNameUpdate(String contactName){
        ArrayList<Contact> matchesContacts = getContactsByName(contactName);
        if (!matchesContacts.isEmpty()){
            System.out.println("\nThere is a contact/s with the same name --\n");
            for (Contact c : matchesContacts){
                System.out.println(c);
            }
            System.out.println("\nAre you sure for Update Contact Name?\n> 1. Keep Updating\n> 0. Cansel");
            String choice = scan.nextLine();
            return choice.equalsIgnoreCase("1");
        }
        return true;
    }
    
    
    private ArrayList<Contact> getContactsByName(String contactName){
        ArrayList<Contact> matchesContacts = new ArrayList<>();
        for (Contact contact : contacts){
            if (contact.getName().equalsIgnoreCase(contactName)) {
                matchesContacts.add(contact);
            }
        }
        return matchesContacts;
    }

    
    private Contact selectContactByInput(String input){
        ArrayList<Contact> matchesContacts = new ArrayList<>();
        for (Contact c : contacts){
            if (c.getName().equalsIgnoreCase(input) || c.getNumber().equalsIgnoreCase(input)){
                matchesContacts.add(c);
            }
        }
        
        if (matchesContacts.isEmpty()){
            System.out.println("\nContact not Found");
            return null;
        } else if (matchesContacts.size() == 1){
            return matchesContacts.get(0);
        } else {
            System.out.println("\nThere are More then a Contacts with the same name: \n");
            for (Contact c : matchesContacts){
                System.out.println("> " + c);
            }
            System.out.println("\nEnter the phone number of the contact you want remove      *Or 0 to back to Main Menu\n");
            String selectedNumber = scan.nextLine();
            if (selectedNumber.equalsIgnoreCase("0")){
                return null;
            } else {
                for (Contact c : matchesContacts){
                    if (c.getNumber().equalsIgnoreCase(selectedNumber)){
                        return c;
                    }
                }
                System.out.println("\nSorry, Number must be 9 digits, and starts by 5..");
                return null;
            }
        }
    }
    
    
    
    public void run(){
        boolean x = true;
        while (x) {
            System.out.println("\nPhone Book Menu:\n\n1. Add Contact\n2. Show All Contacts\n3. Edit Contact \n4. Remove Contact\n0. Exit\n");
            System.out.println("Choose: \n");
            int Ch = scan.nextInt();
            scan.nextLine();
            
            switch (Ch){
                case 1:
                    addContact();
                    break;
                case 2:
                    showAllContacts();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 0:
                    x = false;
                    break;
                default:
                    System.out.println("\nInvaild Choice");
                    break;
            }
        }
    }
}
