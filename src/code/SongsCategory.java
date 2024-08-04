/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code;

import java.util.ArrayList;
import java.util.List;

//We create enum which is kinda class but we can use it as we want to select different items 
public enum SongsCategory {
    
    //These all are categories which we are using in Songs Recommendation
    Sad,
    Happy,
    Party,
    Pop,
    Romantic;
    
    //This Function get All Songs Categories 
    public static List<String> getAllSongsCategory(){
        List<String> categories = new ArrayList<>();
        //value:We use it when we have to select items from enum
        for (SongsCategory category : values()) {
            categories.add(category.name());
        }
        return categories;
    }
    
    
}
