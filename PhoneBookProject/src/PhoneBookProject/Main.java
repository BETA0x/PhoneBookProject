package PhoneBookProject;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scan = new Scanner(System.in);
        boolean x = true;
        
        while (x) {
            System.out.println("Phone Book Menu:\n1. Add Contact\n2. Search for Contacts\n3. Show Your Contacts\n4. Remove Contact\n0. Exit");
            System.out.println("Choose: ");
            int Ch = scan.nextInt();
            scan.nextLine();
            
            switch (Ch){
                case 1:
                    System.out.println("Enter Contact Name    (* Enter 0 to back to Main Menu)");
                    String name2Add = scan.nextLine();
                    if (name2Add.equalsIgnoreCase("0")){
                        break;
                    }
                    System.out.println("Enter Contact Number 5X XXX XXXX   (* Enter 0 to back to Main Menu)");
                    Integer number2Add = scan.nextInt();
                    scan.nextLine();
                    
                    if (number2Add == 0){
                        break;
                    }
                    phoneBook.addContact(number2Add, name2Add);
                    System.out.println("");
                    break;
                case 2:
                    if(phoneBook.contactsCount == 0){
                        System.out.println("    *There is no contact Added yet\n");
                        break;
                    }
                    
                    System.out.println("Enter The Contact number for Search    (* Enter 0 to back to Main Menu)");
                    Integer number2Search = scan.nextInt();
                    
                    if(number2Search == 0){
                        break;
                    }
                    phoneBook.search4Contact(number2Search);
                    break;
                case 3:
                    phoneBook.showAllContacts();
                    break;
                case 4:
                    if(phoneBook.contactsCount == 0){
                        System.out.println("    *There is no contact Added yet\n");
                        break;
                    }
                    
                    System.out.println("Enter Contact number to remove    (* Enter 0 to back to Main Menu)");
                    Integer number2R = scan.nextInt();
                    
                    if( number2R == 0){
                        break;
                    } else {
                        phoneBook.removeContact(number2R);
                    }
                    
                case 0:
                    x = false;
                    break;
            }
        }
    }
    
}
