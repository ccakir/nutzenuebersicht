<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>



	<div>
		<div class="header_button">
			<a type="button" class="btn btn-primary btn-md" href="/vertrag/add-vertrag"><spring:message code="label.buttonNewContract" /></a>
		</div>
		<br>
		<div class="panel panel-primary">
			<div class="panel-heading">
   				<h3><spring:message code="navigation.contract" /></h3>
   				<h5><c:if test="${message!=null}"><spring:message code="${message}"/></c:if></h5>
   				
   				
   			</div>
   			
   			
   			<div class="panel-body">
  <form action="/vertrag/delete-vertrags/" method="POST">
   <table class="table table-striped">
    <thead>
     <tr>
     <th width="2%"></th>
      
      <th width="11%"><spring:message code="label.kunde" /></th>
      <th width="10%"><spring:message code="label.ort" /></th>
      <th width="10%"><spring:message code="label.file" /></th>
      <th width="15%"><spring:message code="label.content" /></th>
      
      <th width="20%"></th>
     </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${listVertrag}" var="listVertrag">
     
      <tr>
    	<td><input type="checkbox" name="listIds" value="${listVertrag.id}"/></td>
		
       	<td>${listVertrag.kunde.name} </td>
       	<td>${listVertrag.ort.ortname}</td>
      	
       <td>
       <c:set var="i" value="5" />
       <c:forEach items="${listFile}" var="files">
       		<c:choose>
       			<c:when test="${listVertrag.id == files.vertrag.id}">
       				<a href="downloadFile/${files.fileName}">[<spring:message code="label.file"/>-${files.fileName}]</a><br>
       			</c:when>
       		</c:choose>
       <c:set var="i" value="${i+1}" />
       </c:forEach> 
       </td>
       <td>${listVertrag.inhalt}</td>
       
       
       
       
       
       <td style="text-align: right; padding-right: 30px">
      
       <a type="button" class="btn btn-success" href="/vertrag/update-vertrag/${listVertrag.id}"><spring:message code="label.buttonUpdate" /></a>
       <a type="button" class="btn btn-warning" href="/vertrag/delete-vertrag/${listVertrag.id}" ><spring:message code="label.buttonDelete" /></a></td>
      </tr><c:set var="i" value="1" />
     </c:forEach>
     
    </tbody>
   </table>
   <input type="submit" value="<spring:message code="label.buttonMehrDelete" />" class="btn btn-warning" onclick="return deleteDatensatz()"/>
     </form>
  
  </div>
		</div>
	</div>
	

<%@ include file="../common/footer.jspf"%>
