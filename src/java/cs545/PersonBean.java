/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs545;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 *
 * @author levi
 */
@Named("personBn") // or @ManagedBean(name="form")
@SessionScoped
public class PersonBean implements Serializable{
    private String first;
    private String last;
    private List<Person> nameList = new ArrayList<Person>();

    public PersonBean(){
        nameList.add(new Person("a1","a2"));
        nameList.add(new Person("b1","b2"));
        nameList.add(new Person("c1","c2"));
        
    }
    public String addToList() {
        System.out.println("adding to name list:  " + first + "##" + last + "$$");
        nameList.add(new Person(first, last));
        return "index";        
    }
    public List<Person> getNameList() {
        return nameList;
    }

    public void setNameList(List<Person> nameList) {
        this.nameList = nameList;
    }
    

    /* this is the initial version of the change listener 
     * It does not work with the inputText field 
     * because this change will be overwritten by the updateModel phase.
     * In update model, JSF will take the submitted/local value for the last name
     * component and write it into the this.last field of this bean, thereby overwriting
     * the effect of the value change listener.
     * 
     * It will work with the outputText field because outputText elements do not
     * produce updates to the model in the Update Model phase.
     * 
     * If there are both input and output fields, then the input field will update
     * the bean field in Update Model, and this will then be rendered to both 
     * the output and input fields in Render Response.
     */
    public void fillLast(ValueChangeEvent e) {
        String enteredFirst = (String) e.getNewValue();
        for (Person pers: nameList) {
            if (pers.getFirst().equals(enteredFirst)) {
                String found = pers.getLast();
                System.out.println("found the name, about to set last:  " + found);
                setLast(found);
                break;
            }
        }              
    }

    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }
    
}
