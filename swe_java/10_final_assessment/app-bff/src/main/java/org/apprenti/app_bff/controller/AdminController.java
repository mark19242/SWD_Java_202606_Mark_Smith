package org.apprenti.app_bff.controller;

import java.security.Principal;
import java.util.Map;

import org.apprenti.app_bff.dto.UpdateUserEnabledRequest;
import org.apprenti.app_bff.service.AdminUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminUserService adminUserService;

    public AdminController(
            AdminUserService adminUserService
    ) {
        this.adminUserService = adminUserService;
    }

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

    @PutMapping("/users/{username}/enabled")
    public ResponseEntity<Void> updateUserEnabled(
            Principal principal,
            @PathVariable String username,
            @RequestBody UpdateUserEnabledRequest request
    ) {
        boolean updated
                = adminUserService.updateEnabledStatus(
                        principal.getName(),
                        username,
                        request.enabled()
                );

        if (updated) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
