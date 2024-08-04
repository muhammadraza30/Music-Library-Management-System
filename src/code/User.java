/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code;

import java.io.Serializable;
//We use Serializable because it encryptes the data in txt file
public class User implements Serializable{
    
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String fullName;
    
    //User Constructor
    public User(String userName, String password, String firstName, String lastName, String email) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    //User Constructor (OVERLOADING)
    public User(){
        
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return firstName+" "+lastName;
    }
    
    @Override
    public String toString() {
        return "{ " + "userName = " + userName + "  |  password = " + password + "  |  firstName = " + firstName +"  |  lastName = " + lastName +"  |  email = " + email + " }";
    }
    
    
}
