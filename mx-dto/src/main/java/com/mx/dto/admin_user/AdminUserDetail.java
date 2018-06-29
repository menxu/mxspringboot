package com.mx.dto.admin_user;

import lombok.Data;

import java.util.Date;

/**
 * Created by menxu on 18/6/29.
 */
@Data
public class AdminUserDetail {
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
