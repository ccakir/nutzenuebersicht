<%@ include file="../common/header.jspf"%> <%@ include
file="../common/navigation.jspf"%>

<div class="container">

	<form:form method="POST" modelAttribute="vorOBForm" class="form-signin"
		name="vorOBForm" onsubmit="return vorOBFormKontrolle()">
		<h2 class="form-signin-heading" style="font-size: 20pt">
		<spring:message code="label.neueVOBTitle"/></h2>
		<div>

			<select  name="kunde" class="form-control">
				<option value="0"><spring:message code="label.selectKunde"/></option>
				<c:forEach items="${allKunden}" var="kunden">
					<option value="${kunden.kundeId}" ${kunden.kundeId==selectedKunde ? 'selected' : ''}>
					${kunden.name} / ${kunden.land}</option>
				</c:forEach>
				<select>

					<div class="error" id="kundeError"></div>
		</div>
		<br>
		<div>

			<select path="ort" name="ort" class="form-control">
				<option value="0"><spring:message code="label.selectLocation"/></option>
				<c:forEach items="${allOrte}" var="ort">
					<option value="${ort.ortId}" ${ort.ortId==selectedOrt ? 'selected' : ''}>${ort.ortname} / ${ort.land}</option>
				</c:forEach>
			</select>
			<div class="error" id="ortError"></div>


		</div>
		<br>
		<div>
			<select path="user" name="user" class="form-control">
				<option value="0"><spring:message code="label.selectUser"/></option>
				<c:forEach items="${allUsers}" var="user">
					<option value="${user.id}" ${user.id==selectedUser ? 'selected' : ''}>${user.vorname}
						${user.nachname}</option>
				</c:forEach>
			</select>
			<div class="error" id="userError"></div>

<div class="error" >${fehler}</div>

		</div>












		<input class="btn btn-lg btn-primary btn-block" type="submit"
			value="<spring:message code="label.buttonAdd"/>">
	</form:form>

</div>

<%@ include file="../common/footer.jspf"%>
