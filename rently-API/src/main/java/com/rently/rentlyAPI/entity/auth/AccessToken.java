package com.rently.rentlyAPI.entity.auth;

import com.rently.rentlyAPI.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccessToken {

  @Id
  @GeneratedValue
  public Integer id;

  @Column(unique = true, nullable = false, columnDefinition = "varchar(1000)")
  public String token;

  @Enumerated(EnumType.STRING)
  public TokenType tokenType = TokenType.BEARER;

  public boolean revoked;

  public boolean expired;

  @OneToOne(mappedBy= "accessToken", cascade = CascadeType.REMOVE, orphanRemoval = true)
  public RefreshToken refreshToken;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  public User user;
}
