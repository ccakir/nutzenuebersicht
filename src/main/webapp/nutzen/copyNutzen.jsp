<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>




    <div class="container">

        <form method="POST"  name="nutzenForm" modelAttribute="nutzenForm" class="form-signin" onsubmit="return validateNutzenForm()" >
            <h2 class="form-signin-heading"><spring:message code="label.nutzen.addTitle"/></h2>
            
            	<input type="hidden" name="user" id="user" value="${nutzenForm.user.id}">
            
            <div>
            	<input type="date" id="datum" name="datum" class="form-control" value="${nutzenForm.datum}" autofocus="true">
            	<div class="error" id="datumError"></div>
            </div>
           <br>
           
           <div>
            	<select name="kunde" class="form-control">
            	
            	<c:forEach items="${kundeList}" var="kunden">
            	<option value="${kunden.kundeId}" ${nutzenForm.kunde.kundeId==kunden.kundeId ? 'selected="selected"' : ''} >${kunden.name}</option>
            	</c:forEach>
            	</select>
            	<div class="error" id="kundeError"></div>
           	</div>
           	<br>
           	<div>
            	<select name="ort" class="form-control">
            	
            	<c:forEach items="${ortList}" var="ort">
            	<option value="${ort.ortId}" ${nutzenForm.ort.ortId==ort.ortId ? 'selected="selected"' : ''}>${ort.ortname}</option>
            	</c:forEach>
            	</select>
            	<div class="error" id="ortError"></div>
           	</div>
           	<br>
           	<div>
            	<select name="taetigkeit" class="form-control">
            	
            	<c:forEach items="${taetigkeitList}" var="taetigkeit">
            	<option value="${taetigkeit.id}" ${nutzenForm.taetigkeit.id==taetigkeit.id ? 'selected="selected"' : ''}>${taetigkeit.name}</option>
            	</c:forEach>
            	</select>
            	<div class="error" id="taetigkeitError"></div>
           	</div>
           	<br>
            <div>
            	
            	<textarea id="details" name="details" rows="10" style="width: 500px" class="form-control" placeholder="<spring:message code="label.nutzen.detail"/>">${nutzenForm.details}</textarea>
           		
            </div>
            <br>
             <div>
             <textarea  rows="10"  style="width: 500px" class="form-control" id="sondernleistung" name="sondernleistung"  placeholder="<spring:message code="label.nutzen.specialPerformance"/>">${nutzenForm.sondernleistung}</textarea>
            	
           	</div>
              <br>      
            <div>
            	<input type="text" id="stunde" style="width: 120px" name="stunde" value="${nutzenForm.stunde}"  class="form-control" placeholder="<spring:message code="label.nutzen.hour"/>">
           		<div class="error" id="stundeError"></div>
            </div>
            <br>       
            <div>
            <label style="padding-right: 10px; vertical-align: middle;" for="freigabe"><spring:message code="label.nutzen.release"/></label>
            
            <c:choose>
            	<c:when test="${nutzenForm.freigabe==true}">
            		<input type="checkbox" id="freigabe" name="freigabe"  checked="checked" >
           		</c:when>
           		<c:otherwise>
           			<input type="checkbox" id="freigabe" name="freigabe" >
            	</c:otherwise>
            </c:choose>
            	
            	
           	</div>
            
           
            

            <input class="btn btn-lg btn-primary btn-block" type="submit" value="<spring:message code="label.buttonAdd"/>">
        </form>

    </div>

<%@ include file="../common/footer.jspf"%>