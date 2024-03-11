package com.rently.rentlyAPI.auth.entity;

import com.rently.rentlyAPI.auth.entity.enums.TokenType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true, nullable = false, columnDefinition = "varchar(1000)")
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @OneToOne
    @JoinColumn(name = "token_id")
    @NotNull
    public AccessToken accessToken;


}
