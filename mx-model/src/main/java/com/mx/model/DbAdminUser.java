package com.mx.model;

import lombok.Data;

import java.util.Date;

@Data
public class DbAdminUser {
    private Integer id;

    private String email;

    private String encryptedPassword;

    private String resetPasswordToken;

    private Date resetPasswordSentAt;

    private Date rememberCreatedAt;

    private Integer signInCount;

    private Date currentSignInAt;

    private Date lastSignInAt;

    private String currentSignInIp;

    private String lastSignInIp;

    private Date createdAt;

    private Date updatedAt;

    private Short locale;

    private String fullname;

    private String mobile;

    private Boolean isSupper;

    private Boolean ban;

    private String authenticationToken;
}