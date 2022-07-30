package com.eva.sessionManagement.repository;

import com.eva.sessionManagement.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionsRepository extends JpaRepository<UserSession,Long> {
}
