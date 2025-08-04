package com.project.Capstone.repository;

import com.project.Capstone.model.Token;
//import ch.qos.logback.core.subst.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}
