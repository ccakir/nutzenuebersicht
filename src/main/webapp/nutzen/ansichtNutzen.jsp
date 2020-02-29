<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>




    <div class="container">

        
            <h2 class="form-signin-heading"><spring:message code="label.nutzen.viewTitle"/></h2>
            
            	
            <table class="table table-striped">
            	<tr>
            		<td><spring:message code="label.nutzen.date"/></td>
            		<td>:</td>
            		<td>${nutzenForm.datum}</td>
            	</tr>
            	<tr>
            		<td><spring:message code="label.kunde"/></td>
            		<td>:</td>
            		<td>${nutzenForm.kunde.name}</td>
            	</tr>
            	<tr>
            		<td><spring:message code="label.ort"/></td>
            		<td>:</td>
            		<td>${nutzenForm.ort.ortname}</td>
            	</tr>
            	<tr>
            		<td><spring:message code="label.mitarbeiter"/></td>
            		<td>:</td>
            		<td>${nutzenForm.user.vorname} ${nutzenForm.user.nachname}</td>
            	</tr>
            	<tr>
            		<td><spring:message code="label.nTaetigkeit"/></td>
            		<td>:</td>
            		<td>${nutzenForm.taetigkeit.name}</td>
            	</tr>
            	<tr>
            		<td><spring:message code="label.nutzen.detail"/></td>
            		<td>:</td>
            		<td>${nutzenForm.details}</td>
            	</tr>
            	<tr>
            		<td><spring:message code="label.nutzen.specialPerformance"/></td>
            		<td>:</td>
            		<td>${nutzenForm.sondernleistung}</td>
            	</tr>
            	<tr>
            		<td><spring:message code="label.nutzen.hour"/></td>
            		<td>:</td>
            		<td>${nutzenForm.stunde}</td>
            	</tr>
            	<tr>
            		<td><spring:message code="label.nutzen.release"/></td>
            		<td>:</td>
            		<td>
            		<c:choose>
            			<c:when test="${nutzenForm.freigabe==true}">
            				<span class="dotgreen"></span> 
            			</c:when>
            			
            			<c:otherwise>
            				<span class="dotred"></span>
            			</c:otherwise>
            		</c:choose>
            		
            		</td>
            	</tr>
            </table>
            
            <a type="button" class="btn btn-success" href="/nutzen/update-nutzen?id=${nutzenForm.id}"><spring:message code="label.buttonUpdate"/></a>
            <a type="button" class="btn btn-info" href="/nutzen/copy-nutzen?id=${nutzenForm.id}"><spring:message code="label.nutzen.buttonCopy"/></a>
            <a type="button" class="btn btn-warning" href="/nutzen/add-nutzen"><spring:message code="label.nutzen.buttonNewEntry"/></a>
            <a type="button" class="btn btn-danger" href="/nutzen/delete-nutzen?id=${nutzenForm.id}"><spring:message code="label.buttonDelete"/></a>
           

           

    </div>

<%@ include file="../common/footer.jspf"%>