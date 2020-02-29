<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div class="container" style="width: 500px">

	<form:form method="POST" modelAttribute="vorOBForm" class="form"
		name="vorOBForm" onsubmit="return vorOBFormKontrolle()" >
		<h2 class="form-signin-heading" style="font-size: 20pt" >
		<spring:message code="label.locationSupportUpdate"/></h2>
		<div>
		<input type="hidden" value="${vorOBForm.id}" />

			<select  name="kunde" class="form-control">
				<option value="0"><spring:message code="label.selectKunde"/></option>
				<c:forEach items="${allKunden}" var="kunden">
					<option value="${kunden.kundeId}" ${kunden.kundeId==vorOBForm.kunde.kundeId ? 'selected' : ''}>
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
					<option value="${ort.ortId}" ${ort.ortId==vorOBForm.ort.ortId ? 'selected' : ''}>${ort.ortname} / ${ort.land}</option>
				</c:forEach>
			</select>
			<div class="error" id="ortError"></div>


		</div>
		<br>
		<div>
			<select path="user" name="user" class="form-control">
				<option value="0"><spring:message code="label.selectUser"/></option>
				<c:forEach items="${allUsers}" var="user">
					<option value="${user.id}" ${user.id==vorOBForm.user.id ? 'selected' : ''}>${user.vorname}
						${user.nachname}</option>
				</c:forEach>
			</select>
			<div class="error" id="userError"></div>

<div class="error" ><c:if test="${message!=null}"><spring:message code="${message}"/></c:if></div>

		</div>

<br>










		<input class="btn btn-lg btn-primary btn-block" type="submit"
			value="<spring:message code="label.buttonAdd"/>"><input class="btn btn-lg btn-primary btn-block" type="button"
			value="<spring:message code="label.buttonBack"/>" onclick="window.location.href='${contextPath}/vorortbetreuung/list-vorobs'">
	</form:form>

</div>

<%@ include file="../common/footer.jspf"%>
