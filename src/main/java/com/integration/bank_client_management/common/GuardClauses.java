package com.integration.bank_client_management.common;

import com.integration.bank_client_management.constants.Constants;
import com.integration.bank_client_management.errors.AlreadyExistsException;
import com.integration.bank_client_management.errors.ResourceNotFoundException;
import com.integration.bank_client_management.model.Client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class GuardClauses {
    public static void guardAgainstClientNotFound(Optional<Client> client) throws ResourceNotFoundException {
        if (client.isEmpty()) throw new ResourceNotFoundException(Constants.ERROR_NOT_FOUND);
    }
    public static void guardAgainstClientAlreadyExists(Optional<Client> client) throws AlreadyExistsException {
        if (client.isPresent()) throw new AlreadyExistsException(Constants.ERROR_ALREADY_EXIST);
    }
}

