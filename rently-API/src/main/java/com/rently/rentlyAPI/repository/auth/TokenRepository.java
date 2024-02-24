package com.rently.rentlyAPI.repository.auth;

import com.rently.rentlyAPI.entity.auth.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<AccessToken, Integer> {

  @Query(value = """
      select t from AccessToken t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<AccessToken> findAllValidTokenByUser(Integer id);

  Optional<AccessToken> findByToken(String accessToken);
}
