package com.daniele.mydatabase.userProfile.model;

import com.daniele.mydatabase.shared.model.SlicedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SOCIAL_PROFILE")
public class SocialProfileDetails extends SlicedEntity {

    @Column(name = "PROFILE_NAME")
    private String profileName;

    @Column(name = "PROFILE_URL")
    private String profileUrl;

    public SocialProfileDetails() {
    }

    private SocialProfileDetails(SocialProfileDetailsBuilder builder) {
        this.profileName = builder.socialProfileDetails.profileName;
        this.profileUrl = builder.socialProfileDetails.profileUrl;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public static class SocialProfileDetailsBuilder {

        protected SocialProfileDetails socialProfileDetails;

        public static SocialProfileDetailsBuilder forUpdate(SocialProfileDetails profileDetails) {
            return new SocialProfileDetailsBuilder(profileDetails);
        }

        public static SocialProfileDetailsBuilder forCreation() {
            return new SocialProfileDetailsBuilder(new SocialProfileDetails());
        }

        private SocialProfileDetailsBuilder(SocialProfileDetails socialProfileDetails) {
            this.socialProfileDetails = socialProfileDetails;
        }

        public SocialProfileDetailsBuilder withProfileName(String profileName) {
            this.socialProfileDetails.profileName = profileName;
            return this;
        }

        public SocialProfileDetailsBuilder withProfileUrl(String profileUrl) {
            this.socialProfileDetails.profileUrl = profileUrl;
            return this;
        }

        public SocialProfileDetails build() {
            return new SocialProfileDetails(this);
        }
    }
}
