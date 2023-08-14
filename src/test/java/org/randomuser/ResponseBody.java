package org.randomuser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBody {

    private ResultModel[] results;
    private InfoModel info;

    @Getter
    @Setter
    public static class ResultModel{
        private String gender;
        private NameModel name;
        private LocationModel location;
        private String email;
        private LoginModel login;
        private DobModel dob;
        private RegisteredModel registered;
        private String phone;
        private String cell;
        private IdModel id;
        private PictureModel picture;
        private String nat;
    }

    @Getter
    @Setter
    public static class NameModel{
        String title;
        String first;
        String last;
    }

    @Getter
    @Setter
    private static class LocationModel {
        private StreetModel street;
        private String city;
        private String state;
        private String country;
        private String postcode;
        private CoordinatesModel coordinates;
        private TimezoneModel timezone;
    }

    @Getter
    @Setter
    private static class LoginModel {
        private String uuid;
        private String username;
        private String password;
        private String salt;
        private String md5;
        private String sha1;
        private String sha256;
    }

    @Getter
    @Setter
    private static class DobModel {
        private String date;
        private Integer age;
    }

    @Getter
    @Setter
    private static class RegisteredModel {
        private String date;
        private Integer age;
    }

    @Getter
    @Setter
    private static class IdModel {
        private String name;
        private String value;
    }

    @Getter
    @Setter
    private static class PictureModel {
        private String large;
        private String medium;
        private String thumbnail;
    }

    @Getter
    @Setter
    private static class InfoModel {
        private String seed;
        private Integer results;
        private Integer page;
        private String version;
    }

    @Getter
    @Setter
    private static class StreetModel {
        private Integer number;
        private String name;
    }

    @Getter
    @Setter
    private static class CoordinatesModel {
        private String latitude;
        private String longitude;
    }

    @Getter
    @Setter
    private static class TimezoneModel {
        private String offset;
        private String description;
    }
}
