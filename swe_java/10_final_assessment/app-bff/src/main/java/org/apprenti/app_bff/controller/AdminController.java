package org.apprenti.app_bff.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/status")
    public Map<String, String> getAdminStatus(
            Principal principal
    ) {
        return Map.of(
                "message",
                "ReelVibe admin access granted.",
                "username",
                principal.getName()
        );
    }
}
