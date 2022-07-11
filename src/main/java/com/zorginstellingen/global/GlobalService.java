package com.zorginstellingen.global;

import com.zorginstellingen.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class GlobalService {
    public static User getUserFromToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return user;
    }
}
