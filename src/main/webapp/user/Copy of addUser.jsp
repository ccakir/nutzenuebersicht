<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>


<div class="container">
	<form  modelAttribute="userForm" name="userForm" class="form-signin" onsubmit="return userFormValidate()" method="POST">
		<h2 class="form-signin-heading">
			<spring:message code="label.user.newUserTitle" />
		</h2>
		
		<input type="text" path="vorname" name="vorname" id="vorname" maxlength="20"  class="form-control" required placeholder="<spring:message code="label.vorname" />" >
		
		<div id="firstname_message">
			<p id="firstname_length" class="invalid"><spring:message code="label.name.max" /></p>
		</div>
		<input  type="text" name="nachname" id="nachname" maxlength="20" class="form-control" required placeholder="<spring:message code="label.nachname" />" >
		<div id="lastname_message">
			<p id="lastname_length" class="invalid"><spring:message code="label.name.max" /></p>
		</div>
		<input type="text" id="username" name="username" minlength="6" maxlength="10" class="form-control" required placeholder="<spring:message code="label.userName"/>">
		<div id="username_message">
		<p id="username_length" class="invalid"><spring:message code="label.error.username.lengt"/></p>
		
		</div>
		
		<input  type="text" id="password" name="password" class="form-control"
			pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
			 required
		placeholder="<spring:message code="label.password" />">
		<div id="message">
			<h6>
				<spring:message code="label.password.must" />
			</h6>
			<p id="letter" class="invalid">
				<spring:message code="label.password.lowercase" />
			</p>
			<p id="capital" class="invalid">
				<spring:message code="label.password.uppercase" />
			</p>
			<p id="number" class="invalid">
				<spring:message code="label.password.number" />
			</p>
			<p id="length" class="invalid">
				<spring:message code="label.password.min" />
			</p>
		</div>

		<input  type="text" class="form-control" name="matchingNewPassword" id="matchingNewPassword" placeholder="<spring:message code="label.newPasswordConfirm" />">
		<div id="confirm_message">
		<p id="repeat" class="invalid"><spring:message code="value.Diff.userForm.passwordConfirm"/></p>
		</div>
		
		<input type="text" id="email"  name="email" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2, 4}$"  required placeholder="Email">
		<div id="email_message">
		<p id="email_pattern" class="invalid"><spring:message code="value.Invalid.Email"/></p>
		</div>
		<br>
		<select name="ort" class="form-control" id="ort">
			<option value="null"><spring:message code="label.arbeitsort"/></option>
				<c:forEach items="${locationList}" var="location">
					<option value="${location.ortId}" ${location.ortId==selectedLocation.ortId ? 'selected="selected"' :''}>${location.ortname} / ${location.land}</option>
				</c:forEach>
		</select>
		<div class="error" id="location_error"></div>
		<div id="location_message">
		<p id="select" class="invalid"><spring:message code="label.error.selectLocation"/></p>
		</div>
		<br>
		<div>
		<select id="role" name="role" class="form-control">
			<option value="1">ADMIN</option>
			<option value="2" selected="selected">USER</option>
		</select>
		</div>
		<input type="submit" class="btn btn-lg btn-primary btn-block" value="<spring:message code="label.buttonAdd"/>">
	</form>

</div>


<script>
	
	
	var email = document.getElementById("email");
	var email_pattern = document.getElementById("email_pattern");
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var email_error = true;

	var username = document.getElementById("username");
	var username_length = document.getElementById("username_length");
	
	
	var passwordConfirm = document.getElementById("matchingNewPassword");
	var repeat = document.getElementById("repeat");
	var password_error = true;

	var firstname = document.getElementById("vorname");
	var firstname_length = document.getElementById("firstname_length");
	
	var lastname = document.getElementById("nachname");
	var lastname_length = document.getElementById("lastname_length");
	
	var myInput = document.getElementById("password");
	var letter = document.getElementById("letter");
	var capital = document.getElementById("capital");
	var number = document.getElementById("number");
	var length = document.getElementById("length");
	
	//Email validation
	email.onfocus = function() {
		document.getElementById("email_message").style.display = "block";	
	}
	
	email.onblur = function() {
		document.getElementById("email_message").style.display = "none";
	}
	
	email.onkeyup = function() {
		if( email.value.match(mailformat)) {
			
			email_pattern.classList.remove("invalid");
			email_pattern.classList.add("valid");
			email_error = false;
			
			
		} else {
			email_pattern.classList.remove("valid");
			email_pattern.classList.add("invalid");
			email_error = true;
		}
		
	}
	
	//username validation
	username.onfocus = function() {
		document.getElementById("username_message").style.display = "block";	
	}
	
	username.onblur = function() {
		document.getElementById("username_message").style.display = "none";
	}
	
	username.onkeyup = function() {
		if( username.value.length > 5 && username.value.length < 11) {
			
				username_length.classList.remove("invalid");
				username_length.classList.add("valid");
			
			
		} else {
			username_length.classList.remove("valid");
			username_length.classList.add("invalid");
		}
		
	}
	
	//Password match
	passwordConfirm.onfocus = function() {
		document.getElementById("confirm_message").style.display = "block";	
	}
	
	passwordConfirm.onblur = function() {
		document.getElementById("confirm_message").style.display = "none";
	}
	
	passwordConfirm.onkeyup = function() {
		if(passwordConfirm.value === myInput.value) {
			repeat.classList.remove("invalid");
			repeat.classList.add("valid");
			password_error = false;
		} else {
			repeat.classList.remove("valid");
			repeat.classList.add("invalid");
			password_error = true;
		}
		
	}
	
	//firstname validation
	firstname.onfocus = function() {
		document.getElementById("firstname_message").style.display = "block";	
	}
	
	
	firstname.onblur = function() {
		document.getElementById("firstname_message").style.display = "none";
	}
	
	firstname.onkeyup = function() {
		if(firstname.value.length < 21) {
			firstname_length.classList.remove("invalid");
			firstname_length.classList.add("valid");
		} else {
			firstname_length.classList.remove("valid");
			firstname_length.classList.add("invalid");
		}
	}
	
	//lastname validation
	
	lastname.onfocus = function() {
		document.getElementById("lastname_message").style.display = "block";	
	}
	
	lastname.onblur = function() {
		document.getElementById("lastname_message").style.display = "none";
	}
	
	lastname.onkeyup = function() {
		if(lastname.value.length < 21) {
			lastname_length.classList.remove("invalid");
			lastname_length.classList.add("valid");
		} else {
			lastname_length.classList.remove("valid");
			lastname_length.classList.add("invalid");
		}
	}

	// When the user clicks on the password field, show the message box
	myInput.onfocus = function() {
		document.getElementById("message").style.display = "block";
	}

	// When the user clicks outside of the password field, hide the message box
	myInput.onblur = function() {
		document.getElementById("message").style.display = "none";
	}

	// When the user starts to type something inside the password field
	myInput.onkeyup = function() {
		// Validate lowercase letters
		var lowerCaseLetters = /[a-z]/g;
		if (myInput.value.match(lowerCaseLetters)) {
			letter.classList.remove("invalid");
			letter.classList.add("valid");
		} else {
			letter.classList.remove("valid");
			letter.classList.add("invalid");
		}

		// Validate capital letters
		var upperCaseLetters = /[A-Z]/g;
		if (myInput.value.match(upperCaseLetters)) {
			capital.classList.remove("invalid");
			capital.classList.add("valid");
		} else {
			capital.classList.remove("valid");
			capital.classList.add("invalid");
		}

		// Validate numbers
		var numbers = /[0-9]/g;
		if (myInput.value.match(numbers)) {
			number.classList.remove("invalid");
			number.classList.add("valid");
		} else {
			number.classList.remove("valid");
			number.classList.add("invalid");
		}

		// Validate length
		if (myInput.value.length >= 8) {
			length.classList.remove("invalid");
			length.classList.add("valid");
		} else {
			length.classList.remove("valid");
			length.classList.add("invalid");
		}
	}
	
	

	function printError(elemId, hintMsg) {
	    document.getElementById(elemId).innerHTML = hintMsg;
	}
	
	
	function userFormValidate() {
		
		var ort = document.getElementById("ort");
		var ort_select = document.getElementById("select");
		
		var ort_error;
		
		if(password_error==true) {
			repeat.classList.remove("valid");
			repeat.classList.add("invalid");
			document.getElementById("confirm_message").style.display = "block";
		}
		
		
		if(email_error==true) {
			
			email_pattern.classList.remove("valid");
			email_pattern.classList.add("invalid");
			document.getElementById("email_message").style.display = "block";
		}
		
		
		if(ort.value == "null") {
			document.getElementById("location_message").style.display = "block";	
			
			ort_error = true;
			
		} else {
			document.getElementById("location_message").style.display = "none";
			
			ort_error = false;
		}
		
		if(password_error==true || email_error==true || ort_error==true) {return false;}else {return true;}
		
	}
</script>
<%@ include file="../common/footer.jspf"%>
