<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

	<div>
		<div class="header_button">
			<a type="button" class="btn btn-primary btn-md" href="/contact/add-kontakt"><spring:message code="label.contact.buttonNewContact"/></a>
		</div>
		<br>
		<div class="panel panel-primary">
			<div class="panel-heading">
   				<h3><spring:message code="label.contact.allContactTitle"/></h3>
   				<h5><c:if test="${message!=null}"><spring:message code="${message}"/></c:if></h5>
   				
   				
   				
   			</div>
   			
		<div style="padding: 5px 10px 5px 30px">
			<div style="display: inline; font-size: 15px; font-weight: bold; padding-right: 30px"><spring:message code="label.searchByLastname"/> :</div>
			
		<c:set var="alphabet" value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" />
			<c:forTokens var="letter" items="${alphabet}" delims=",">
				
   			 	<a href="/contact/list-kontakte/${letter}" style="font-size: 19px; padding-left: 10px">${letter}</a>
   			 	<c:choose><c:when test="${letter!='Z'}"><span style="font-size: 19px; padding-left: 10px"> - </span></c:when></c:choose>
			</c:forTokens>

				<span><a href="/contact/list-kontakte" style="font-size: 19px; padding-left: 30px;">
				<c:set var="labelAll"><spring:message code="label.all"/></c:set>
				<c:set var="labelAllUpper" value="${fn:toUpperCase(labelAll)}"/>
				${labelAllUpper}
				</a></span>
		</div>
		
		
		<div class="panel-body">
  <form action="/contact/delete-kontakte" method="POST">
   <table class="table table-striped">
    <thead>
     <tr>
     <th width="3%"></th>
      <th width="5%"><spring:message code="label.contact.anrede"/></th>
      <th width="5%"><spring:message code="label.contact.titel"/></th>
      <th width="5%"><spring:message code="label.vorname"/></th>
      <th width="5%"><spring:message code="label.nachname"/></th>
      <th width="7%"><spring:message code="label.phone"/></th>
      <th width="7%"><spring:message code="label.contact.mobil"/></th>
      <th width="7%"><spring:message code="label.fax"/></th>
      <th width="9%"><spring:message code="label.email"/></th>
      <th width="27%"><spring:message code="label.kunde"/></th>
      
      <th width="20%"></th>
     </tr>
    </thead>
    
    
    	<tbody>
    	<c:choose>
    	<c:when test="${kontakte!='[]'}">
     <c:forEach items="${kontakte}" var="kontakt">
      <tr>
      <td><input type="checkbox" name="kontaktIds" value="${kontakt.kontaktId}"/></td>
       <td>${kontakt.anrede}</td>
       <td>${kontakt.titel}</td>
       <td>${kontakt.vorname}</td>
       <td>${kontakt.nachname}</td>
       <td>${kontakt.telefon}</td>
       <td>${kontakt.mobil}</td>
       <td>${kontakt.fax}</td>
       <td>${kontakt.email}</td>
        <td>${kontakt.kunde.name}</td>
       
       <td><a type="button" class="btn btn-success"
        href="/contact/update-kontakt/${kontakt.kontaktId}"><spring:message code="label.buttonUpdate"/></a>
       <a type="button" class="btn btn-warning"
        href="/contact/delete-kontakt/${kontakt.kontaktId}"><spring:message code="label.buttonDelete"/></a></td>
      </tr>
     </c:forEach>
     
    
    </c:when>
    <c:otherwise><tr><td colspan="11" style="text-align: center;font-size: 20px; color: red;padding-top: 20px"><spring:message code="label.noResult"/></td></tr></c:otherwise>
    </c:choose>
    </tbody>
   </table>
   <input type="submit" value="<spring:message code="label.buttonMehrDelete"/>" class="btn btn-warning"/>
     </form>
  </div>
		</div>
	</div>
	

<%@ include file="../common/footer.jspf"%>
