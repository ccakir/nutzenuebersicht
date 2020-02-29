<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<% 

String size = request.getParameter("size").toString();

String kunde = request.getParameter("kunde").toString();




%>
	<div>
		<div class="header_button">
			<a type="button" class="btn btn-primary btn-md" href="/nutzen/add-nutzen"><spring:message code="label.nutzen.buttonNewNutzen"/></a>
		</div>
		<br>
		<div class="panel panel-primary">
			<div class="panel-heading">
   				<h3><spring:message code="label.nutzen.Title"/></h3>
   				<h5>${message}</h5>
   				
   				
   			</div>
   			<div class="headerPageSize">
   			<form method="get" action="/nutzen/list-nutzen" modelAttribute="vonbisForm" name="vonbisForm" onsubmit="return vonbisKontrolle()">
   			<div class="vonbiserror" id="vonbisError"></div>
   			<input type="submit" value="<spring:message code="label.nutzen.buttonSearchByDate"/>" class="btn btn-suche">
   			<input type="hidden" name="kunde" value="0">
   			<input type="hidden" name="sucheMit" value="datum">
   			<input type="hidden" name="size" value="10">
   			<input type="hidden" name="mitarbeiter" value="0">
   			<label for="von"> <spring:message code="label.nutzen.from"/> : </label> <input type="date" name="von">
   			<label for="bis"> <spring:message code="label.nutzen.to"/> : </label> <input type="date" name="bis">
   			
   			
   			
   			</form>
   			<p><spring:message code="label.nutzen.searchByUser"/></p>
   			
   			<c:choose>
   				<c:when test="${authorized==true}">
   					<select name="allUser" onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
   						<option style="font-weight: bold;" value="/nutzen/list-nutzen?size=<%= size %>&kunde=0&mitarbeiter=0&kunde=0&sucheMit=0">--<spring:message code="label.nutzen.selectAllUser"/>--</option>
   							<c:forEach items="${userList}" var="users">
   								<option value="/nutzen/list-nutzen?sucheMit=mitarbeiter&size=<%= size %>&mitarbeiter=${users.id}&kunde=0"
   					 			${users.id==mitarbeiter ? 'selected="selected"' : ''} >
   								${users.vorname} ${users.nachname} ${users.role}
   								</option>
   							</c:forEach>
   					</select>
   				</c:when>
   				<c:otherwise>
   				<select name="allUser" onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
   						
   								<option disabled="disabled" value="/nutzen/list-nutzen?sucheMit=mitarbeiter&size=<%= size %>&mitarbeiter=${users.id}&kunde=0"	selected="selected">
   								${user.vorname} ${user.nachname} 
   								</option>
   					</select>
   				</c:otherwise>
   			</c:choose>
   			
   			<p><spring:message code="label.nutzen.searchByCustomer"/> : </p>
   			<select name="kunde" onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
   			<option style="font-weight: bold;" value="/nutzen/list-nutzen?size=<%= size %>&kunde=0&mitarbeiter=0&sucheMit=0">--<spring:message code="label.selectKunde"/>--</option>
   				<c:forEach items="${kundenList}" var="kunden">
   				
   					<option value="/nutzen/list-nutzen?sucheMit=kunde&size=<%= size %>&kunde=${kunden.kundeId}&mitarbeiter=0"
   					 ${kunden.kundeId==sucheNachKunde ? 'selected="selected"' : ''}>
   					${kunden.name}
   					</option>
   				
   				</c:forEach>
   			
   			</select>
   			
   			
   			<p><spring:message code="label.nutzen.entryPerPage"/></p>
   			
   			<select onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
   				<option value="/nutzen/list-nutzen?kunde=<%=kunde%>&size=5&sucheMit=${sucheMit}&mitarbeiter=${mitarbeiter}" <%= size.equals("5")?"selected":""  %>>5</option>
   				<option value="/nutzen/list-nutzen?kunde=<%=kunde%>&size=10&sucheMit=${sucheMit}&mitarbeiter=${mitarbeiter}" <%= size.equals("10")?"selected":""  %>>10</option>
   				<option value="/nutzen/list-nutzen?kunde=<%=kunde%>&size=20&sucheMit=${sucheMit}&mitarbeiter=${mitarbeiter}" <%= size.equals("20")?"selected":""  %>>20</option>
   			</select>
   			</div>
   			
   			<div class="panel-body">
  <form action="/nutzen/delete-nutzen" method="POST">
   <table class="table table-striped">
    <thead>
     <tr>
     <th width="2%"></th>
      <th width="10%"><spring:message code="label.nutzen.date"/></th>
      <th width="10%"><spring:message code="label.mitarbeiter"/></th>
      <th width="25%"><spring:message code="label.kunde"/></th>
      <th width="25%"><spring:message code="label.nTaetigkeit"/></th>
      <th width="5%"><spring:message code="label.nutzen.hour"/></th>
      <th width="3%" ><spring:message code="label.nutzen.release"/></th>
      <th width="20%"></th>
     </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${nutzenPage.content}" var="nutzen">
      <tr>
      <td><input type="checkbox" name="nutzenIds" value="${nutzen.id}"/></td>
       <td>${nutzen.datum}</td>
       <td>${nutzen.user.vorname} ${nutzen.user.nachname}</td>
       <td>${nutzen.kunde.name}</td>
       <td>${nutzen.taetigkeit.name}</td>
       <td>${nutzen.stunde}</td>
       <td style="vertical-align: middle; text-align: center;">
       <c:choose>
       <c:when test="${nutzen.freigabe==true}">
       <span class="dotgreen"></span>
       </c:when>
       <c:otherwise>
       <span class="dotred"></span>
       </c:otherwise>
       </c:choose>
       </td>
       
       
       <td style="text-align: right; padding-right: 30px">
       <a type="button" class="btn btn-info" href="/nutzen/ansehen-nutzen?id=${nutzen.id}"><spring:message code="label.nutzen.buttonView"/></a>
       <a type="button" class="btn btn-success" href="/nutzen/update-nutzen?id=${nutzen.id}"><spring:message code="label.buttonUpdate"/></a>
       <a type="button" class="btn btn-warning" href="/nutzen/delete-nutzen?id=${nutzen.id}" onclick="return deleteNutzen()"><spring:message code="label.buttonDelete"/></a></td>
      </tr>
     </c:forEach>
     
    </tbody>
   </table>
   <input type="submit" value="<spring:message code="label.buttonMehrDelete"/>" class="btn btn-warning" onclick="return deleteNutzen()"/>
     </form>
     <div class="pager">
	<c:choose>
		<c:when test="${nutzenPage.totalPages > 0}">
		<ul>
		<c:forEach items="${pageNumbers}" var="number">
		
		<li>
		<a href="/nutzen/list-nutzen?kunde=<%=kunde%>&size=${nutzenPage.size}&page=${number}&mitarbeiter=${mitarbeiter}&sucheMit=${sucheMit}">${number}</a>
		</li>
		</c:forEach>
		</ul>
		</c:when>
	</c:choose>
	
</div>
  </div>
		</div>
	</div>
	

<%@ include file="../common/footer.jspf"%>
