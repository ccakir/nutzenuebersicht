<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div>
 <div class="header_button">
  <a type="button" class="btn btn-primary btn-md" href="/kunde/addKunde"><spring:message code="label.newCustomerTitle"/></a>
 </div>
 <br>
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3 ></h3>
   ${message}
   ${message1}
  </div>
  <div class="panel-body">
  <form action="/kunde/delete-kunde" method="POST">
   <table class="table table-striped">
    <thead>
     <tr>
     <th width="3%"></th>
      <th width="15%"><spring:message code="label.tname"/></th>
      <th width="10%"><spring:message code="label.Address"/></th>
      <th width="10%"><spring:message code="label.ort"/></th>
      <th width="5%"><spring:message code="label.postcode"/></th>
      <th width="5%"><spring:message code="label.country"/></th>
      <th width="10%"><spring:message code="label.web"/></th>
      <th width="8%"><spring:message code="label.customerActiveLocation"/></th>
      <th width="10%"><spring:message code="label.email"/></th>
      <th width="5%"><spring:message code="label.phone"/></th>
      <th width="5%"><spring:message code="label.fax"/></th>
      <th width="15%"></th>
     </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${listKunden}" var="kunde">
      <tr>
      <td><input type="checkbox" name="listKundeIds" value="${kunde.kundeId}"/></td>
       <td>${kunde.name}</td>
       <td>${kunde.adresse}</td>
       <td>${kunde.ortsname}</td>
       <td>${kunde.plz}</td>
       <td>${kunde.land}</td>
       <td>${kunde.web}</td>
       <td style="text-align: left;"><a type="button" class="btn btn-success"
        href="/kunde/view-customer-active-location/${kunde.kundeId}"><spring:message code="label.nutzen.buttonView"/></a></td>
       <td>${kunde.email}</td>
       <td>${kunde.telefon}</td>
       <td>${kunde.fax}</td>
       
       <td><a type="button" class="btn btn-success"
        href="/kunde/update-kunde?id=${kunde.kundeId}"><spring:message code="label.buttonUpdate"/></a>
       <a type="button" class="btn btn-warning"
        href="/kunde/delete-kunde?id=${kunde.kundeId}"><spring:message code="label.buttonDelete"/></a></td>
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