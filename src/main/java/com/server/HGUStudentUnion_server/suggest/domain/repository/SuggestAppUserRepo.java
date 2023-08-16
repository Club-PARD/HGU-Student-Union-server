package com.server.HGUStudentUnion_server.suggest.domain.repository;

import com.server.HGUStudentUnion_server.suggest.domain.SuggestAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SuggestAppUserRepo extends JpaRepository<SuggestAppUser, Long> {

    @Query("select s from SuggestAppUser s where s.appUser.id = :userId and s.suggest.id = :suggestId")
    public Optional<SuggestAppUser> findByUserIdAndSuggestId(Long userId, Long suggestId);

    @Query("select s from SuggestAppUser s where s.suggest.id = :id")
    public List<SuggestAppUser> findBySId(Long id);
}
