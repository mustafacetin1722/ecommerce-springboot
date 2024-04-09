package com.mustafa.ecommercespringboot.dto;

import com.mustafa.ecommercespringboot.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String uuid;
    private String userName;
    private String email;
    private String password;
    private String telephone;
    private String address;
    private UserRole userRole;
    private Date createdAt;
    private Date updatedAt;
}
