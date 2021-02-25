package model;
/**
 * Tag object that include Tag value and Tag name
 */

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Tags {
    private SimpleStringProperty name;
    private SimpleStringProperty value;

    /**
     * default constructor for tag
     * @param name name of tag
     * @param value value of tag
     */
    public Tags(String name,String value){
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleStringProperty(value);
    }

    /**
     * setter for tag name
     * @param name name String
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * setter for tag value
     * @param value value String
     */
    public void setValues(String value) {
        this.value.set(value) ;
    }

    /**
     * getter for tag name
     * @return name String
     */
    public String getName() {
        return name.get();
    }

    /**
     * getter for tag value
     * @return tag value
     */
    public String getValue(){
        return value.get();
    }
}
