<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>



	<div>
		<div class="header_button">
			<a type="button" class="btn btn-primary btn-md" href="/vorortbetreuung/add-vorOB"><spring:message code="label.neuenEintrag" /></a>
		</div>
		<br>
		<div class="panel panel-primary">
			<div class="panel-heading">
   				<h3><spring:message code="label.vorOrtBetreuungTitle" /></h3>
   				<h5><c:if test="${message!=null}"><spring:message code="${message}"/></c:if></h5>
   				
   				
   			</div>
   			
   			
   			<div class="panel-body">
  <form action="/vorortbetreuung/delete-vorOB" method="POST">
   <table class="table table-striped">
    <thead>
     <tr>
     <th width="5%"></th>
      <th width="20%"><strong>
                <spring:message    code="label.mitarbeiter" />
                </strong></th>
      <th width="30%"><spring:message code="label.kunde" /></th>
      <th width="20%"><spring:message code="label.ort" /></th>
      
      <th width="25%"></th>
     </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${listVorOBs}" var="listVOB">
      <tr>
      <td><input type="checkbox" name="listVOBIds" value="${listVOB.id}"/></td>
       <td>${listVOB.user.vorname} ${listVOB.user.nachname}</td>
       <td>${listVOB.kunde.name} </td>
       <td>${listVOB.ort.ortname}</td>
       
       
       
       
       <td style="text-align: right; padding-right: 30px">
      
       <a type="button" class="btn btn-success" href="/vorortbetreuung/update-vorOB?id=${listVOB.id}"><spring:message code="label.buttonUpdate" /></a>
       <a type="button" class="btn btn-warning" href="/vorortbetreuung/delete-vorOB?id=${listVOB.id}" onclick="return deleteDatensatz()"><spring:message code="label.buttonDelete" /></a></td>
      </tr>
     </c:forEach>
     
    </tbody>
   </table>
   <input type="submit" value="<spring:message code="label.buttonMehrDelete" />" class="btn btn-warning" onclick="return deleteDatensatz()"/>
     </form>
  
  </div>
		</div>
	</div>
	

<%@ include file="../common/footer.jspf"%>
