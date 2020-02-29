<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>



	<div>
		
		<br>
		<div class="panel panel-primary">
			<div class="panel-heading">
   				<h3><spring:message code="label.CustomerLocations" /></h3>
   				
   				
   				
   			</div>
   			
   			
   			<div class="panel-body">
 
   <table class="table table-striped">
    <thead>
     <tr>
      <th width="20%" style="padding-left: 30px"><spring:message code="label.kunde" /></th>
      <th width="30%"><spring:message code="label.ort" /></th>
      <th width="20%"><spring:message code="label.country" /></th>
      <th width="20%"><spring:message code="label.supervised" /></th>
     
     </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${listLocations}" var="listLocations">
      <tr>
      	<td style="padding-left: 30px">${listLocations.kunde.name}</td>
      	<td>${listLocations.ort.ortname}</td>
      	<td>${listLocations.ort.land}</td>
      	<td>${listLocations.user.vorname}  ${listLocations.user.nachname}</td>
      	
       	    	
      </tr>
     </c:forEach>
     
    </tbody>
   </table>
   
  
  </div>
		</div>
	</div>
	

<%@ include file="../common/footer.jspf"%>
