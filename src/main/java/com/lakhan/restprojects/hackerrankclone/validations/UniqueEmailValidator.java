package com.lakhan.restprojects.hackerrankclone.validations;

import com.lakhan.restprojects.hackerrankclone.annotations.UniqueEmail;
import com.lakhan.restprojects.hackerrankclone.daos.UsersRepository;
import com.lakhan.restprojects.hackerrankclone.models.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UsersRepository usersDao;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
            log.info("Validating Uniqueness for email address " + email);
            Optional<User> user = usersDao.findByEmail(email);
            return !user.isPresent();
    }
}
