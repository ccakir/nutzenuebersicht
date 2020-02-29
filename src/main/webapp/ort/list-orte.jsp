<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div>
 <div class="header_button">
  <a type="button" class="btn btn-primary btn-md" href="/ort/addOrt"><spring:message code="label.newLocation"/></a>
 </div>
 <br>
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3><spring:message code="label.allLocation"/></h3>
  
  </div>
  <div class="panel-body">
  <form action="/ort/delete-orts" method="POST">
   <table class="table table-striped">
    <thead>
     <tr>
     <th width="3%"></th>
      <th width="20%"><spring:message code="label.locationName"/></th>
      <th width="20%"><spring:message code="label.Address"/></th>
      <th width="15%"><spring:message code="label.postcode"/></th>
      <th width="15%"><spring:message code="label.country"/></th>
      
      
      <th width="20%"></th>
     </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${listOrt}" var="ort">
      <tr>
      <td><input type="checkbox" name="listOrtIds" value="${ort.ortId}"/></td>
       <td>${ort.ortname}</td>
       <td>${ort.adresse}</td>
       <td>${ort.plz}</td>
       <td>${ort.land}</td>
       
       
       <td><a type="button" class="btn btn-success"
        href="/ort/update-ort?id=${ort.ortId}"><spring:message code="label.buttonUpdate"/></a>
       <a type="button" class="btn btn-warning"
        href="/ort/delete-ort?id=${ort.ortId}"><spring:message code="label.buttonDelete"/></a></td>
      </tr>
     </c:forEach>
     
    </tbody>
   </table>
   <input type="submit" value="<spring:message code="label.buttonMehrDelete"/>" class="btn btn-warning"/>
     </form>
  </div>
 </div>

</div>
<%@ include file="../common/footer.jspf"%>