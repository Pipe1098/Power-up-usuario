package com.pragma.powerup.usermicroservice.configuration;

public class Constants {



    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Long CLIENT_ROLE_ID = 1L;
    public static final Long EMPLOYEE_ROLE_ID = 2L;

    public static final Long OWNER_ROLE_ID = 3L;
    public static final int MAX_PAGE_SIZE = 2;
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String PERSON_CREATED_MESSAGE = "Person created successfully";
    public static final String USER_CREATED_MESSAGE = "User created successfully";
    public static final String USER_DELETED_MESSAGE = "User deleted successfully";
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String WRONG_CREDENTIALS_MESSAGE = "Wrong credentials";
    public static final String NO_DATA_FOUND_MESSAGE = "No data found for the requested petition";
    public static final String PERSON_ALREADY_EXISTS_MESSAGE = "A person already exists with the DNI number provided";
    public static final String MAIL_ALREADY_EXISTS_MESSAGE = "A person with that mail already exists";
    public static final String PERSON_NOT_FOUND_MESSAGE = "No person found with the id provided";
    public static final String ROLE_NOT_FOUND_MESSAGE = "No role found with the id provided";
    public static final String ROLE_NOT_ALLOWED_MESSAGE = "No permission granted to create users with this role";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "A user already exists with the role provided";
    public static final String USER_NOT_FOUND_MESSAGE = "No user found with the role provided";
    public static final String SWAGGER_TITLE_MESSAGE = "User API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "User microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
    public static final String MALFORMED_EMAIL_EXCEPTION = "Malformed email, please enter a valid email.";
    public static final String MALFORMED_DOCUMENT_EXCEPTION = "The document number is not in a valid format.";
    public static final String MALFORMED_PHONE_NUMBER_EXCEPTION = "The cell phone number is not correct.";
    public static final String INCORRECT_PHONE_NUMBER_LENGTH_EXCEPTION = "The cell phone number must have between 8 and 13 characters including the '+' character.";
    public static final String MALFORMED_BIRTHDATE_EXCEPTION = "The birth date is not in the correct format.";
    public static final String OWNER_NOT_OF_LEGAL_AGE_EXCEPTION = "The owner must be of legal age.";
    public static final String OWNER_CREATED_SUCCESS_MESSAGE = "The owner was successfully created.";


}
