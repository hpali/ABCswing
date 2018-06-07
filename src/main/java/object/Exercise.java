/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Admin
 */
@Entity
@XmlRootElement
@XmlType(propOrder = {"age", "year", "language", "number", "answer", "url"})
public class Exercise implements Serializable {

    private String age;
    private int year;
    private String language;
    private int number;
    private String answer;
    private String url;
    

    public Exercise() {
    }

    public Exercise(String age, int year, String language, int number, String answer, String url) {
        this.age = age;
        this.year = year;
        this.language = language;
        this.number = number;
        this.answer = answer;
        this.url = url;
    }

    public Exercise(Integer id, String age, int year, String language, int number, String answer, String url) {
        this.id = id;
        this.age = age;
        this.year = year;
        this.language = language;
        this.number = number;
        this.answer = answer;
        this.url = url;
    }

    @Id
    private int id;
    
    public int getId() {
        return id;
    }


    @XmlTransient() // ezt akkor teszed be ha nem akarod ,hogy figyelembe vegye 
    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    @XmlElement(name = "AGE")
    public void setAge(String age) {
        this.age = age;
    }

    public int getYear() {
        return year;
    }

    @XmlElement(name = "YEAR")
    public void setYear(int year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    @XmlElement(name = "LANGUAGE")
    public void setLanguage(String language) {
        this.language = language;
    }

    public int getNumber() {
        return number;
    }

    @XmlElement(name = "NUMBER")
    public void setNumber(int number) {
        this.number = number;
    }

    public String getAnswer() {
        return answer;
    }

    @XmlElement(name = "ANSWER")
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUrl() {
        return url;
    }

    @XmlElement(name = "URL")
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Exercise{" + "age=" + age + ", year=" + year + ", language=" + language + ", number=" + number + ", answer=" + answer + ", url=" + url + '}';
    }

    @Override
    public int hashCode() { // erre szükségem lesz?
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Exercise other = (Exercise) obj;
        if (this.year != other.year
                && this.number != other.number
                && !Objects.equals(this.age, other.age)
                && !Objects.equals(this.language, other.language)) {
            return false;
        }

        return true;
    }

}
