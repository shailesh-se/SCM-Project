package com.example.demo.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Provider;
import com.example.demo.entities.User;
import com.example.demo.helpers.AppConstants;
import com.example.demo.repository.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenicationSuccessHandler");
        
        
        var oauth2AuthenicationToken = (OAuth2AuthenticationToken) authentication;

        String authorizedClientRegistrationId = oauth2AuthenicationToken.getAuthorizedClientRegistrationId();

        logger.info(authorizedClientRegistrationId);

        var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

        oauthUser.getAttributes().forEach((key, value) -> {
            logger.info(key + " : " + value);
        });

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {

            // google
            // google attributes

            user.setEmail(oauthUser.getAttribute("email").toString());
            user.setProfilePic(oauthUser.getAttribute("picture").toString());
            user.setName(oauthUser.getAttribute("name").toString());
            user.setProviderUserId(oauthUser.getName());
            user.setProvider(Provider.GOOGLE);
            user.setAbout("This account is created using google.");

        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {

            // github
            // github attributes
            String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString()
                    : oauthUser.getAttribute("login").toString() + "@githubuser.com";
            String picture = oauthUser.getAttribute("avatar_url").toString();
            String name = oauthUser.getAttribute("login").toString();
            String providerUserId = oauthUser.getName();

            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderUserId(providerUserId);
            user.setProvider(Provider.GIT);

            user.setAbout("This account is created using github");
        }

        else if (authorizedClientRegistrationId.equalsIgnoreCase("linkedin")) {

        }

        else {
            logger.info("OAuthAuthenicationSuccessHandler: Unknown provider");
        }
        
        
        
        
/*
         DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        // logger.info(user.getName());

        // user.getAttributes().forEach((key, value) -> {
        //     logger.info("{} => {}", key, value);
        // });

        // logger.info(user.getAuthorities().toString());

        // data database save:

        @SuppressWarnings("null")
		String email = user.getAttribute("email").toString();
        @SuppressWarnings("null")
		String name = user.getAttribute("name").toString();
        @SuppressWarnings("null")
		String picture = user.getAttribute("picture").toString();

        // Create User and save it in database
        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setProfilePic(picture);
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Provider.GOOGLE);
        user1.setEnabled(true);
        
        user1.setEmailVerified(true);
        user1.setProviderUserId(user.getName());
        user1.setRoleList(List.of(AppConstants.ROLE_USER));
        user1.setAbout("This account is created using google..");
*/
        User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
        if (user2 == null) {

            userRepo.save(user);
            System.err.println("User saved:" + user.getEmail());
        }
        

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
