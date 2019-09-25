package gohil.aru.noteroomdatabase.modal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

import gohil.aru.noteroomdatabase.utils.TimestampConverter;

@Entity
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Firstname")
    private String Firstname;

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    @ColumnInfo(name = "Lastname")
    private String Lastname;
    @ColumnInfo(name = "Age")
    private String Age;
    @ColumnInfo(name = "City")
    private String City;
    @ColumnInfo(name = "Gender")
    private String Gender;
    @ColumnInfo(name = "MobileNumber")
    private String MobileNumber;
    @ColumnInfo(name = "Identity")
    private String Identity;
    @ColumnInfo(name = "MarriedStatus")
    private String MarriedStatus;
    @ColumnInfo(name = "created_at")
    @TypeConverters({TimestampConverter.class})
    private Date Date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public String getMarriedStatus() {
        return MarriedStatus;
    }

    public void setMarriedStatus(String marriedStatus) {
        MarriedStatus = marriedStatus;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }
}
