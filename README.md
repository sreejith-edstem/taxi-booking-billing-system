# taxi-booking-billing-system

This API allows you to manage taxi-booking-billing.

## Endpoints

### User Registration

- `POST /v1/user/signup`: User Registration.
- `GET /v1/user/login`: Login a user
- `PUT /v1/user/{userId}/update`: Update account balance.
- Implement JWT-based authentication for secure access.

### Bookings

- `POST /v1/user/booking/{userId}/create`: Book a taxi, providing pickupLocation and dropoffLocation.
- `GET /v1/user/booking/{id}`: View booking details.
- `POST /v1/user/cancel/{id}`: Cancel a booking.
- Implement logic to assign the nearest available taxi to a booking.

### Fare Calculation and Billing

- `POST /v1/user/booking/{userId}/fare`: Calculate the fare based on distance and a fixed rate per kilometer.
- After the ride is complete, deduct the fare from the user's account balance.
Handle scenarios where the user's account balance is insufficient.

### Exception Handling and Validation
Implement robust exception handling for various scenarios like invalid booking requests, payment failures, etc.
Use Spring's validation annotations for validating user inputs.

### Unit Testing
Write comprehensive unit tests for the system.
Use Mockito for mocking dependencies and JUnit for testing.

### API Documentation:
Document the API using Swagger or similar tools.

Additional Points:
Follow RESTful principles and best practices for API development.
Ensure proper transaction management for booking and payment operations.
Implement appropriate logging and error reporting.
