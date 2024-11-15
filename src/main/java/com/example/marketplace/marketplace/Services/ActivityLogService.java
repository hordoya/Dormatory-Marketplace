package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.ActivityLog;
import com.example.marketplace.marketplace.Models.User;

import java.util.List;

public interface ActivityLogService {
    public void logActivity(User user, String action, User seller);

    List<ActivityLog> getAllLogs();
}
