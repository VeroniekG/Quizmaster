package service;

import model.Session;

/**
 * Services can extend this class to obtain access to the user session and implement input
 * validation.
 *
 * @author Daniel Leertouwer
 * @version 1.0.1
 * @see Session
 * @see InputValidationService
 * @since 1.0
 */
abstract class Service {

    final Session session;
    final InputValidationService inputValidationService;

    Service() {
        session = Session.getInstance();
        inputValidationService = new InputValidationService();
    }

}