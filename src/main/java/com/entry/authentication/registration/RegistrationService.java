package com.entry.authentication.registration;

import com.entry.authentication.appuser.AppUser;
import com.entry.authentication.appuser.AppUserRole;
import com.entry.authentication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidation emailValidation;
    private final AppUserService appUserService;
    public String register(RegistrationRequest request) {

        boolean isValidEmail = emailValidation.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        return appUserService.signUp(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
