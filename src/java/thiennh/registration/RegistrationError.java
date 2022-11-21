/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.registration;

/**
 *
 * @author Thien's
 */
public class RegistrationError {
    private String usernameLength;
    private String passwordLength;
    private String confirmNotMatch;
    private String fullNameLength;
    private String usernameIsExisted;

    public RegistrationError() {
    }

    public RegistrationError(String usernameLength, String passwordLength, String confirmNotMatch, String fullNameLength, String usernameIsExisted) {
        this.usernameLength = usernameLength;
        this.passwordLength = passwordLength;
        this.confirmNotMatch = confirmNotMatch;
        this.fullNameLength = fullNameLength;
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getUsernameLength() {
        return usernameLength;
    }

    public void setUsernameLength(String usernameLength) {
        this.usernameLength = usernameLength;
    }

    public String getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(String passwordLength) {
        this.passwordLength = passwordLength;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getFullNameLength() {
        return fullNameLength;
    }

    public void setFullNameLength(String fullNameLength) {
        this.fullNameLength = fullNameLength;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
}
