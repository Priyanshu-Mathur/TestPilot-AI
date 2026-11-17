package com.testpilot.ai.repository;

import com.testpilot.ai.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepository extends JpaRepository<Requirement, Long> {

}
