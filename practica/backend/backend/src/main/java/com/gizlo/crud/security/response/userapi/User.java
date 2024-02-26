package com.gizlo.crud.security.response.userapi;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private Long age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private Long height;
    private Double weight;
    private String eyeColor;
    private Hair hair;
    private String domain;
    private String ip;
    private Address address;
    private String macAddress;
    private String university;
    private Bank bank;
    private Company company;
    private String ein;
    private String ssn;
    private String userAgent;
    private Crypto crypto;

    public User(Long id, String firstName, String lastName, String maidenName, Long age, String gender, String email, String phone, String username, String password, String birthDate, String image, String bloodGroup, Long height, Double weight, String eyeColor, Hair hair, String domain, String ip, Address address, String macAddress, String university, Bank bank, Company company, String ein, String ssn, String userAgent, Crypto crypto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.image = image;
        this.bloodGroup = bloodGroup;
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hair = hair;
        this.domain = domain;
        this.ip = ip;
        this.address = address;
        this.macAddress = macAddress;
        this.university = university;
        this.bank = bank;
        this.company = company;
        this.ein = ein;
        this.ssn = ssn;
        this.userAgent = userAgent;
        this.crypto = crypto;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getMaidenName() {
        return this.maidenName;
    }

    public Long getAge() {
        return this.age;
    }

    public String getGender() {
        return this.gender;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public String getImage() {
        return this.image;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public Long getHeight() {
        return this.height;
    }

    public Double getWeight() {
        return this.weight;
    }

    public String getEyeColor() {
        return this.eyeColor;
    }

    public Hair getHair() {
        return this.hair;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getIp() {
        return this.ip;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public String getUniversity() {
        return this.university;
    }

    public Bank getBank() {
        return this.bank;
    }

    public Company getCompany() {
        return this.company;
    }

    public String getEin() {
        return this.ein;
    }

    public String getSsn() {
        return this.ssn;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public Crypto getCrypto() {
        return this.crypto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHair(Hair hair) {
        this.hair = hair;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }
}
