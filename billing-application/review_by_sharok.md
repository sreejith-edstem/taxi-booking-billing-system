# UserService.Class

1. In line no.50 in the **authenticate()** method, Its good to throw custom exception and use where ever needed.


# BookingService.class

1. In line no.38 in the createBooking() method, If condition and exception is not needed.
2. In the createBooking() method, taxis are assigned for booking based on the pickup location given in the request. However, if more than one taxi is present in the searched location, only the first taxi is automatically assigned.
3. In line no.86 in the fareCalculation() method, user not present exception is not handled.
4. In fareCalculation()) method, when the fare exceeds the account balance, an exception should be thrown instead of using System.out.println().

# UpdateAccountRequest.class

1. Validation is not added for accountBalance.

# SignUpResponse.class

1. Should not return hashed password.

# BookingServiceTest

1. In testCreateBooking() method, UserNotFoundException() is not tested.
2. In testViewBookingDetailsById() method, UserNotFoundException() is not tested.
3. In testCancelBooking() method, BookingNotFoundException() is not tested.




