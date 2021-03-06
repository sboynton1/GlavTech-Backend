package tech.GlavTech.SD2022.model;

import tech.GlavTech.SD2022.model.post.Post;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // IDENTITY VS AUTO
    @Column(nullable = false, updatable = false)
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String username;
    private String password;
    @ElementCollection
    private List<String> following = new ArrayList<>();
    @ElementCollection
    private List<String> followers = new ArrayList<>();

    private Boolean isAdmin;

    @OneToMany
    List<Post> posts = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private String userCode;

    public User() {}

    public User(long id, String firstName, String lastName, String email, String phone, String address, String city, String state, String zip, String username, String password, List<String> following,
                List<String> followers, String userCode, Boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.username = username;
        this.password = password;
        this.following = following;
        this.followers = followers;
        this.userCode = userCode;
        this.isAdmin = isAdmin;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> worshippingUsers) {
        this.followers = worshippingUsers;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> admiredUsers) {
        this.following = admiredUsers;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userCode='" + userCode + '\'' +
                '}';
    }
}
