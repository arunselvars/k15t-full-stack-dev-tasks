# Full-Stack Developer Sample Project
1.	Enhanced the velocity forms with jquery scripts to validate the User.
	a.	A small css class is added for name field to highlight the validation error.
	b.	Simple alerts are used for any validation or notification.
2.	Frontend Validation	
	a.	Required validation for not null fields
	b.	Jquery script validation for name, name field can take only alphabets with space.
	c.	On submit encrypted password with base64.
	d.	On Success and Onfailure messages handled.
3.	Backend Developments
	a.	Rest Endpoints are created to register the User and get the list of registered User. The swagger document can be referred for further details.
	http://localhost:8080/swagger-ui.html
	b.	Spring data jpa is used for persistence.
	c.	Exceptions are handled with ExceptionHandler.
		•	An Exception is thrown if the user object is null.
		•	An Exception is thrown if the user is already registered. This is check based on email.
	d.	HTTP Status Codes
		•	ACCEPTED-202 accepted is sent as ResponseEntity status code if the received User object is a valid one.
		•	BAD_REQUEST 400 is sent as ResponseEntity status code if the received User tried to register again.

4.	Backend Integration Testing
	a.	Integration testing is performed with MockMvc
	b.	Unit testing was not required as there is no business logic is involved.
	c.	Integration testing is done for both success and failure cases.
