package com.book.serice;

import com.book.entity.User;
import jakarta.servlet.http.HttpSession;

public interface Userserice {
    public Boolean jiance(String username, String password, HttpSession session);
}
