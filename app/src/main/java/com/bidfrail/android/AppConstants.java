package com.bidfrail.android;

import java.util.HashMap;

public class AppConstants {

    static {
        System.loadLibrary("keys");
    }

    public static native String getDevBaseUrl();
    public static native String getDevApiKey();
    public static native String getDevBearerAuthenticationToken();

    public static native String getProdBaseUrl();
    public static native String getProdApiKey();
    public static native String getProdBearerAuthenticationToken();

    public static final class AppConfig {
        public static final String APP_NAME                 = "BidFrail";
        public static final String PACKAGE_NAME             = BuildConfig.APPLICATION_ID;
        public static final int VERSION_CODE                = BuildConfig.VERSION_CODE;
        public static final String VERSION_NAME             = BuildConfig.VERSION_NAME;
        public static final boolean IS_DEBUG                = BuildConfig.DEBUG;
    }

    public static final class AppSupport {
        public static final String LOCATION                 = "Indore";
        public static final String MAIL                     = "support@gmail.com";
        public static final String MOBILE                   = "7898680304";
    }

    public static final class ApiInfo {
        public static final class Development {
            public static final String BASE_URL                     = getDevBaseUrl();
            public static final String API_KEY                      = getDevApiKey();
            public static final String BEARER_AUTHENTICATION_TOKEN  = getDevBearerAuthenticationToken();
        }

        public static final class Production {
            public static final String BASE_URL                     = getProdBaseUrl();
            public static final String API_KEY                      = getProdApiKey();
            public static final String BEARER_AUTHENTICATION_TOKEN  = getProdBearerAuthenticationToken();
        }
    }

    public static final class Delay {
        public static final int SPLASH                          = 1000 * 2;
        public static final int API_CALL                        = 1000 * 5; /* api call time, every 5 seconds api call*/
    }

    public static final class PermissionRequestCode {
        public static final int CAPTURE_IMAGE                       = 1010;
        public static final int CAPTURE_VIDEO                       = 1020;
    }

    public static final class Extras {
        public static final String EXTRA_FROM                       = "FROM";
        public static final String EXTRA_SENT_OTP                   = "SENT_OTP";

        public static final String EXTRA_NAME                       = "NAME";
        public static final String EXTRA_EMAIL                      = "EMAIL";
        public static final String EXTRA_PHONE_NUMBER               = "PHONE_NUMBER";
        public static final String EXTRA_REFERRAL_CODE              = "REFERRAL_CODE";
    }

    public static final class SharedPreferences {
        public static final String SHARED_PREFERENCES_FILE_NAME = AppConfig.APP_NAME + "SharedPrefs";

        public static final String CURRENT_THEME                = "current_theme";

        public static final String IS_LANGUAGE_SELECT           = "is_language_select";
        public static final String CURRENT_LANGUAGE             = "current_language";

        public static final String IS_SHOW_APP_INTRO            = "is_show_app_intro";

        public static final String REMEMBER_ME                  = "remember_me";

        public static final String USER_ID                      = "user_id";
        public static final String PICTURE                      = "picture";
        public static final String NAME                         = "name";
        public static final String EMAIL                        = "email";
        public static final String PHONE_NUMBER                 = "phone_number";
        public static final String FLAT_NUMBER                  = "flat_number";
        public static final String STREET_NAME                  = "street_name";
        public static final String SOCIETY_NAME                 = "society_name";
        public static final String LOCALITY                     = "locality";
        public static final String REFERRAL_CODE                = "referral_code";
        public static final String REFERENCE_BY                 = "reference_by";
        public static final String IS_ACTIVE                    = "is_active";
        public static final String FCM_TOKEN                    = "fcm_token";
        public static final String CREATED_AT                   = "created_at";
        public static final String UPDATED_AT                   = "updated_at";
    }

    public static final class Database {
        public static final String SQLITE_DATABASE_FILE_NAME    = AppConfig.APP_NAME + "SQLite.db";
        public static final int SQLITE_VERSION                  = 1;

        public static final String ROOM_DATABASE_FILE_NAME      = AppConfig.APP_NAME + "Room.db";
        public static final int ROOM_VERSION                    = 1;
    }

    public static final class Firebase {
        /**
         * FCM Endpoint for sending messages.
         */
        public static final String FCM                          = "https://fcm.googleapis.com/fcm/send";

        public static final String AUTHORIZATION_KEY            = "Authorization";
        public static final String FIREBASE_SERVER_KEY      = BuildConfig.FIREBASE_SERVER_KEY;
        public static final String AUTHORIZATION_KEY_VALUE      = "key=" + FIREBASE_SERVER_KEY;

        public static final String CONTENT_TYPE_KEY             = "Content-Type";
        public static final String CONTENT_TYPE_VALUE_JSON      = "application/json";

        public static HashMap<String,String> FCMHeader() {
            HashMap<String,String> headers=new HashMap<>();
            headers.put(AUTHORIZATION_KEY, AUTHORIZATION_KEY_VALUE);
            headers.put(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE_JSON);
            return headers;
        }

        public static final String KYE_TO                   = "to";
        public static final String KYE_REGISTRATION_IDS     = "registration_ids";
        public static final String KYE_NOTIFICATION_PAYLOAD = "notification";
        public static final String KYE_DATA_PAYLOAD         = "data";
    }

    public static final class Chat {
        public static final String SENDER_ID                = "sender_id";
        public static final String SENDER_NAME              = "sender_name";
        public static final String SENDER_PROFILE_PICTURE   = "sender_profile_picture";
        public static final String MESSAGE                  = "message";
        public static final String MESSAGE_TYPE             = "message_type";
        public static final String DATE                     = "date";
        public static final String TIME                     = "time";
        public static final String SEEN                     = "seen";
    }

    public static final class Call {
    }

    public static final class AssetsPath {
        public static final String LOTTIE_PATH                  = "assets/lottie/";
    }
}