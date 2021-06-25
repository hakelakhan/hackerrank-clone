package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.daos.UsersRepository;
import com.lakhan.restprojects.hackerrankclone.dtos.ProfileInformationResponseDto;
import com.lakhan.restprojects.hackerrankclone.dtos.UpdableProfileInfo;
import com.lakhan.restprojects.hackerrankclone.enums.Badge;
import com.lakhan.restprojects.hackerrankclone.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileService {

    private final UsersRepository usersDao;

    private final UserDetailsServiceImpl userDetailsService;

    private final CodeSubmissionDetailsService submissionDetailsService;

    private final StorageService storageService;

    public ProfileInformationResponseDto getProfile(String email) {
        ProfileInformationResponseDto.ProfileInformationResponseDtoBuilder profileBuilder = ProfileInformationResponseDto.
                builder();
        UpdableProfileInfo updatableProfileInfo = new UpdableProfileInfo();
        Optional<User> userOptional = usersDao.findByEmail(email);
        userOptional.ifPresent(user -> {
            updatableProfileInfo.setFullName(user.getFullName());
            updatableProfileInfo.setMobile(user.getMobile());
            updatableProfileInfo.setQualification(user.getQualification());
            updatableProfileInfo.setPassedOutYear(user.getPassedOutYear());
            updatableProfileInfo.setCgpa(user.getCgpa());
            updatableProfileInfo.setProfession(user.getProfession());
            updatableProfileInfo.setExperienceInYears(user.getExperinceInYears());
            updatableProfileInfo.setOrganization(user.getOrganization());
            updatableProfileInfo.setAdditionalDetails(user.getAdditionalDetails());
            updatableProfileInfo.setUniversity(user.getUniversity());

            profileBuilder.updatable(updatableProfileInfo);
            profileBuilder.email(user.getEmail());
            profileBuilder.badge(Badge.getBadgeByScore(user.getCurrentScore()));
            profileBuilder.score(user.getCurrentScore());
            profileBuilder.solvedProblems(submissionDetailsService.getSolvedProblemsCount(user));
            profileBuilder.programmingSkills(submissionDetailsService.getProgrammingSkills(user));
            if(user.getProfilePictureFilename() != null) {
                profileBuilder.profilePictureUri(
                        ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/profile-pictures/")
                                .path(user.getProfilePictureFilename())
                                .toUriString());
            }
        });
        return profileBuilder.build();
    }

    public void saveProfile(UpdableProfileInfo profileInfo) {
        Optional<User> userOptional = userDetailsService.getActiveUser();
        userOptional.ifPresent( user -> {
            user.setFullName(profileInfo.getFullName());
            user.setMobile(profileInfo.getMobile());
            user.setQualification(profileInfo.getQualification());
            user.setUniversity(profileInfo.getUniversity());
            user.setPassedOutYear(profileInfo.getPassedOutYear());
            user.setCgpa(profileInfo.getCgpa());
            user.setProfession(profileInfo.getProfession());
            user.setExperinceInYears(profileInfo.getExperienceInYears());
            user.setOrganization(profileInfo.getOrganization());
            user.setAdditionalDetails(profileInfo.getAdditionalDetails());
            usersDao.saveAndFlush(user);
        });
    }

    public ProfileInformationResponseDto getProfile() {
        return getProfile(userDetailsService.getActiveUser().get().getEmail());
    }

    public void uploadProfilePicture(MultipartFile image) throws IOException {
        Optional<User> userOptional = userDetailsService.getActiveUser();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String targetFilename = UUID.randomUUID().toString() + image.getOriginalFilename();
            storageService.store(image, targetFilename);
            user.setProfilePictureFilename(targetFilename);
            usersDao.save(user);
        }
    }
}