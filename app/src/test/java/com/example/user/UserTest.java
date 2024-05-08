package com.example.user;

import com.example.myapplication.data.model.User;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    @Test
    public void testUserSetup() {
        String username = "EthanIsAwesome";
        String password = "$TSLA_FTW9000!"; // no seriously don't sign into my bank of america

        User user = new User(username, password);

        Assert.assertEquals(username, user.getUsername());
        Assert.assertEquals(password, user.getPassword());
        Assert.assertFalse(user.isAdmin());
    }

    @Test
    public void testIsAdmin() {
        User user = new User();

        Assert.assertFalse(user.isAdmin());

        user.promote();
        Assert.assertTrue(user.isAdmin());

        user.demote();
        Assert.assertFalse(user.isAdmin());
    }
}