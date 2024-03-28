package com.oaxaca.waiter_service.exception;

public class WaiterCreationFailedException extends RuntimeException {
  /**
   * Constructs a new WaiterCreationFailedException with the specified error message.
   * 
   * @param message The detail message of the exception.
   */
    public WaiterCreationFailedException(String message) {
        super(message);
    }
    
}
