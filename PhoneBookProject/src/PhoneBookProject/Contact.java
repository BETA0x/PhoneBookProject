package PhoneBookProject;

public class Contact {
    private String phoneNumber,name;
    
    public Contact(String phoneNumber, String name){
        this.phoneNumber = phoneNumber;
        this.name = name;
    }
    
    public String getNumber(){
        return phoneNumber;
    }
    
    public void setNumber(String newPhoneNumber){
        this.phoneNumber = newPhoneNumber;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        this.name = newName;
    }
    
    @Override
    public String toString(){
        return name + ": " + phoneNumber;
    }
}
