<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

	<div>
		<div class="header_button">
			<a type="button" class="btn btn-primary btn-md" href="/taetigkeit/add-taetigkeit"><spring:message code="label.neueTaetigkeit"/></a>
		</div>
		<br>
		<div class="panel panel-primary">
			<div class="panel-heading">
   				<h3><spring:message code="label.taetigkeitTitle"/></h3>
   				<h5>${message}</h5>
   				
   				
   			</div>
   			
   			<div class="panel-body">
  <form action="/taetigkeit/delete-taetigkeiten" method="POST">
   <table class="table table-striped">
    <thead>
     <tr>
     <th width="5%"></th>
      <th width="30%"><spring:message code="label.tname"/></th>
      <th width="30%"><spring:message code="label.beschreibung"/></th>
      <th width="35%"></th>
     </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${taetigkeiten}" var="taetigkeit">
      <tr>
      <td><input type="checkbox" name="taetigkeitIds" value="${taetigkeit.id}"/></td>
       <td>${taetigkeit.name}</td>
       <td>${taetigkeit.beschreibung}</td>
       
       
       <td style="text-align: right; padding-right: 30px"><a type="button" class="btn btn-success"
        href="/taetigkeit/update-taetigkeit?id=${taetigkeit.id}"><spring:message code="label.buttonUpdate"/></a>
       <a type="button" class="btn btn-warning"
        href="/taetigkeit/delete-taetigkeit?id=${taetigkeit.id}" onclick="return deleteTaetigkeit()"><spring:message code="label.buttonDelete"/></a></td>
      </tr>
     </c:forEach>
     
    </tbody>
   </table>
   <spring:message code="label.buttonMehrDelete" var="labelbuttonMehrDelete"></spring:message>
   <input type="submit" value="${labelbuttonMehrDelete}" class="btn btn-warning" onclick="return deleteTaetigkeit()"/>
     </form>
  </div>
		</div>
	</div>
	

<%@ include file="../common/footer.jspf"%>
