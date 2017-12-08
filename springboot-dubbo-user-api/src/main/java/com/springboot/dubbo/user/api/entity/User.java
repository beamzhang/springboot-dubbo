package com.springboot.dubbo.user.api.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 8681641069959169520L;

	private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String nickname;

    private String realName;

    private String sex;

    private LocalDate birthday;

    private LocalDateTime regTime;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String phone;
    
    private String userStatus;
    
    private String avatar;
    
    public User(Long id) {
        this.id = id;
    }
}