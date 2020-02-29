<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div>
 <div class="header_button">
  <a type="button" class="btn btn-primary btn-md" href="/user/addUser"><spring:message code="label.neuenBenutzerAnlegen"/></a>
 </div>
 <br>
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3><spring:message code="label.alleBenutzer" /></h3>
   <h5><c:if test="${message!=null}"><spring:message code="${message}"/></c:if></h5>
   
  </div>
  <div class="panel-body">
  <form action="/user/delete-users" method="POST">
   <table class="table table-striped">
    <thead>
     <tr>
     <th width="3%"></th>
      <th width="10%"><spring:message code="label.vorname" /></th>
      <th width="10%"><spring:message code="label.nachname" /></th>
      <th width="11%"><spring:message code="label.userName" /></th>
      <th width="21%"><spring:message code="label.email" /></th>
      <th width="10%"><spring:message code="label.role" /></th>
      <th width="15%"><spring:message code="label.arbeitsort" /></th>
      
      <th width="20%"></th>
     </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${users}" var="user">
      <tr>
      <td><input type="checkbox" name="listUserIds" value="${user.id}"/></td>
       <td>${user.vorname}</td>
       <td>${user.nachname}</td>
       <td>${user.username}</td>
       <td>${user.email}</td>
       <td>${user.role}</td>
       <td>${user.ort.ortname} / ${user.ort.land}</td>
       
       <td><a type="button" class="btn btn-success"
        href="/user/update-user?id=${user.id}"><spring:message code="label.buttonUpdate" /></a>
       <a type="button" class="btn btn-warning"
        href="/user/delete-user?id=${user.id}"><spring:message code="label.buttonDelete" /></a></td>
      </tr>
     </c:forEach>
     
    </tbody>
   </table>
   <spring:message code="label.buttonMehrDelete" var="labelbuttonMehrDelete"></spring:message>
   <input type="submit" value="${labelbuttonMehrDelete}" class="btn btn-warning"/>
     </form>
  </div>
 </div>

</div>
<%@ include file="../common/footer.jspf"%>