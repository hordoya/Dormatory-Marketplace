package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.ActivityLog;
import com.example.marketplace.marketplace.Services.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityLogController {
    @Autowired
    private ActivityLogService activityLogService;

    // Display all activity logs
    @GetMapping("/all")
    public String viewAllLogs(Model model) {
        List<ActivityLog> logs = this.activityLogService.getAllLogs();
        model.addAttribute("logs", logs);
        return "activity-log-view";
    }

    // Display logs for a specific user
//    @GetMapping("/user/{userId}")
//    public String viewLogsForUser(@PathVariable Long userId, Model model) {
//        User user = // retrieve user by userId from UserService or repository
//                List < ActivityLog > userLogs = this.activityLogService.getLogsForUser(user);
//        model.addAttribute("logs", userLogs);
//        return "user-activity-log-view";
//    }
}

