package com.lakhan.restprojects.hackerrankclone.controllers;

import com.lakhan.restprojects.hackerrankclone.dtos.ProfileInformationResponseDto;
import com.lakhan.restprojects.hackerrankclone.dtos.UpdableProfileInfo;
import com.lakhan.restprojects.hackerrankclone.services.ProfileService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/get")
    public ProfileInformationResponseDto getProfile(@RequestParam(value = "email", required = false) String email) {
        if(email != null)
            return profileService.getProfile(email);
        return profileService.getProfile();
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody UpdableProfileInfo profileInfo) {
        profileService.saveProfile(profileInfo);
        return new ResponseEntity<>("{\"message\" : \"Profile was updated\"}", OK);
    }

    @SneakyThrows
    @PostMapping("upload/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam("profile-picture") MultipartFile image) {
       if(!validate(image))
            return ResponseEntity.badRequest().body("{\"message\" : \"Uploaded file is not of valid type\"}");
       profileService.uploadProfilePicture(image);
       return new ResponseEntity<>("{\"message\" : \"Profile picture uploaded\"}", OK);
    }

    private boolean validate(MultipartFile image) {
        List<String> allowedContentTypes = Arrays.asList("image/jpeg", "image/png");
        return allowedContentTypes.contains(image.getContentType());
    }
}
