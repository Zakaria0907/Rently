//package com.rently.rentlyAPI.dto;
//
//import com.rently.rentlyAPI.entity.User.User;
//import com.rently.rentlyAPI.security.Role;
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@Builder
//public class UserDto {
//
//    private Integer id;
//
//    @NotBlank(message = "The first name is required")
//    private String firstname;
//
//    @NotBlank(message = "The last name is required")
//    private String lastname;
//
//    @NotBlank(message = "The phone number is required")
//    private String phoneNumber;
//
//    private String bio;
//
//    @NotBlank(message = "The email is required")
//    private String email;
//
//    @Builder.Default
//    private String role = Role.USER.name();
//
//    public static UserDto fromEntity(User user) {
//        return UserDto.builder()
//            .id(user.getId())
//            .firstname(user.getFirstName())
//            .lastname(user.getLastname())
//            .email(user.getEmail())
//            .role(user.getRole().name())
//            .phoneNumber(user.getPhoneNumber())
//            .bio(user.getBio())
//            .build();
//    }
//
//    public static User toEntity(UserDto userDTO) {
//        return User.builder()
//            .id(userDTO.getId())
//            .firstname(userDTO.getFirstname())
//            .lastname(userDTO.getLastname())
//            .phoneNumber(userDTO.getPhoneNumber())
//            .bio(userDTO.getBio())
//            .email(userDTO.getEmail())
//            .role(Role.valueOf(userDTO.getRole()))
//            .build();
//    }
//
//}
