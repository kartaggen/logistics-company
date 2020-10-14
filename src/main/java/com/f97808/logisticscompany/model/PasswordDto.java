package com.f97808.logisticscompany.model;

import com.f97808.logisticscompany.validation.PasswordConfirmedUpdate;
import com.f97808.logisticscompany.validation.PasswordMatch;
import com.f97808.logisticscompany.validation.PasswordPolicy;

import javax.validation.constraints.NotEmpty;

@PasswordConfirmedUpdate
public class PasswordDto {
    @PasswordMatch
    @NotEmpty(message = "Please enter in your old password")
    private String oldPass;
    @NotEmpty(message = "Please enter in a new password")
    @PasswordPolicy
    private String newPass;
    @NotEmpty(message = "Please confirm your new password")
    private String newPassConfirm;

    public PasswordDto() {
    }

    public PasswordDto(@NotEmpty(message = "Please enter in your old password") String oldPass,
                       @NotEmpty(message = "Please enter in a new password") String newPass,
                       @NotEmpty(message = "Please confirm your new password") String newPassConfirm) {
        this.oldPass = oldPass;
        this.newPass = newPass;
        this.newPassConfirm = newPassConfirm;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getNewPassConfirm() {
        return newPassConfirm;
    }

    public void setNewPassConfirm(String newPassConfirm) {
        this.newPassConfirm = newPassConfirm;
    }
}
