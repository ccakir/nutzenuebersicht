<%@ include file="../common/header.jspf"%> <%@ include
file="../common/navigation.jspf"%>




<div class="container">

	<form method="POST" name="taetigkeitForm"
		modelAttribute="taetigkeitForm" class="form-signin"
		onsubmit="return validateForm()">
		<h2 class="form-signin-heading"><spring:message code="label.nTaetigkeit"/></h2>
		<div>
			<input type="text" id="name" name="name" class="form-control"
				placeholder="<spring:message code="label.tname"/>" autofocus="true">
			<div class="error" id="nameError"></div>
		</div>

		<div>
			<input type="text" id="beschreibung" name="beschreibung"
				class="form-control" placeholder="<spring:message code="label.beschreibung"/>"> 
				
		</div>






		<input class="btn btn-lg btn-primary btn-block" type="submit"
			value="<spring:message code="label.buttonAdd"/>">
	</form>

</div>

<%@ include file="../common/footer.jspf"%>
