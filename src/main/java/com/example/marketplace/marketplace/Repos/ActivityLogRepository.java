package com.example.marketplace.marketplace.Repos;

import com.example.marketplace.marketplace.Models.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
}

