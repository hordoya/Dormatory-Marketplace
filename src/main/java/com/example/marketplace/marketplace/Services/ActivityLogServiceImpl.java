package com.example.marketplace.marketplace.Services;


import com.example.marketplace.marketplace.Models.ActivityLog;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Repos.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {
    @Autowired
    private ActivityLogRepository activityLogRepository;

    public void logActivity(User user, String action, User seller) {
        ActivityLog log = new ActivityLog();
        log.setUser(user);
        log.setAction(action);
        log.setTimestamp(LocalDateTime.now());
        log.setSeller(seller);
        this.activityLogRepository.save(log);
    }


    @Override
    public List<ActivityLog> getAllLogs() {
        return this.activityLogRepository.findAll();
    }
}
