<%@ include file="../common/header.jspf"%> <%@ include
file="../common/navigation.jspf"%>
<div class="container">

	<form:form method="POST" modelAttribute="kennwortChange" class="form-signin">
		<h2 class="form-signin-heading"><spring:message code="label.chancePasswordTitle"/></h2>
		<spring:message code="label.password" var="labelPassword"></spring:message>
		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="password" id="password" path="password"
					class="form-control" placeholder="${labelPassword}"></form:input>
				<form:errors path="password"></form:errors>

			</div>
		</spring:bind>
		<spring:message code="label.newPassword" var="labelNewPassword"></spring:message>
		<spring:bind path="newPassword">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="password" id="newPassword" path="newPassword"
					class="form-control" placeholder="${labelNewPassword}"></form:input>
				<form:errors path="newPassword"></form:errors>

			</div>
		</spring:bind>
		<spring:message code="label.newPasswordConfirm" var="labelNewPasswordConfirm"></spring:message>
		<spring:bind path="matchingNewPassword">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="password" id="matchingNewPassword" path="matchingNewPassword"
					class="form-control" placeholder="${labelNewPasswordConfirm}"></form:input>
				<form:errors path="matchingNewPassword"></form:errors>

			</div>
		</spring:bind>
		<button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="label.buttonUpdate"/></button>
        

	</form:form>

</div>
<%@ include file="../common/footer.jspf"%>
