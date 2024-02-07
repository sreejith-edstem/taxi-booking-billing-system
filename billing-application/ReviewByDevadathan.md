
## Model class (Booking):

#### 1)" private long id; "

#### Mentioned id with datatype as "long". I think it's better to use "Double". As "double" is a primitive datatype whereas the "Double" class wraps a value of the primitive type double in an object.

#### 2)" private double fare; "

#### Mentioned fare with datatype as "double". As "long" is a primitive datatype whereas the "Long" class wraps a value of the primitive type long in an object.

## Model class (User):

#### " private double accountBalance; "

#### Same here too better to use "Double".

## Repository:

#### In all repositories he had mentioned datatype as "Long" whereas in model class it's "long".

## Booking Service:

#### In line: 62,  For finding the nearest taxi location. He wrote a method for finding nearest taxi location and used an exception in the method. In line: 38, Called that method and again wrote same exception.

### Cancel Booking (BookingService):

#### For cancelling the booking, userId have to be added as a parameter. If we don't add the userId everyone can cancel the booking.

## Unit Testing:

#### He had tested model class, request, response. I think testing service and controller class is enough.
