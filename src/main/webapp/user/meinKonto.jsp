<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div>
	<div class="panel panel-primary">


		<div class="panel-heading">
			<h3><spring:message code="label.myAccountTitle"/></h3>
		</div>
		
		<div class="panel-body">
		
			<table class="table table-striped">
			<tr><td width="20%"><spring:message code="label.vorname"/></td><td>:</td><td width="20%">${userInfo.vorname}</td><td width="60%">&nbsp</td></tr>
			<tr><td><spring:message code="label.nachname"/></td><td>:</td><td>${userInfo.nachname}</td><td width="60%">&nbsp</td></tr>
			<tr><td><spring:message code="label.userName"/></td><td>:</td><td>${userInfo.username}</td><td width="60%">&nbsp</td></tr>
			<tr><td><spring:message code="label.email"/></td><td>:</td><td>${userInfo.email}</td><td width="60%">&nbsp</td></tr>
			<tr><td><spring:message code="label.password"/></td><td>:</td><td><a href="/user/password-change"><input type="button" value="<spring:message code="label.buttonChancePassword"/>" class="btn-warning"/></a></td><td width="60%">&nbsp</td></tr>
			<tr><td><spring:message code="label.arbeitsort"/></td><td>:</td><td>${userInfo.ort.ortname}</td><td width="60%">&nbsp</td></tr>
			</table>
			
		</div>
	</div>
</div>
<%@ include file="../common/footer.jspf"%>
