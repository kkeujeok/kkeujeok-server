package com.kkeujeok.domain.rollingPaper.repository;

import com.kkeujeok.domain.rollingPaper.domain.RollingPaper;
import com.kkeujeok.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RollingPaperRepository extends JpaRepository<RollingPaper,Long> {
    List<RollingPaper> findByUser(User user);
}
