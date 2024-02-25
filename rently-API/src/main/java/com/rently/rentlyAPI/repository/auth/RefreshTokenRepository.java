package com.rently.rentlyAPI.repository.auth;

import com.rently.rentlyAPI.entity.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    @Query(value = """
        select t from RefreshToken t inner join AccessToken tk
        on t.accessToken.id = tk.id
        where tk.user.id = :id and (t.expired = false or t.revoked = false)
        """)
    List<RefreshToken> findAllValidRefreshTokenByUser(Integer id);
    
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

}
