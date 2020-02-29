<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>




    <div class="container">

        <form method="POST"  name="nutzenForm" modelAttribute="nutzenForm" class="form-signin" onsubmit="return validateNutzenForm()" >
            <h2 class="form-signin-heading"><spring:message code="label.nutzen.addTitle"/></h2>
            
            	<input type="hidden" name="user" id="user" value="${userId}">
            
            <div>
            	<input type="date" id="datum" name="datum" class="form-control" placeholder="<spring:message code="label.nutzen.date"/>" autofocus="true">
            	<div class="error" id="datumError"></div>
            </div>
           <br>
           
           <div>
            	<select name="kunde" class="form-control">
            	<option value="0">---- <spring:message code="label.selectKunde"/> ----</option>
            	<c:forEach items="${kundeList}" var="kunden">
            	<option value="${kunden.kundeId}">${kunden.name}</option>
            	</c:forEach>
            	</select>
            	<div class="error" id="kundeError"></div>
           	</div>
           	<br>
           	<div>
            	<select name="ort" class="form-control">
            	<option value="0">---- <spring:message code="label.selectLocation"/> ----</option>
            	<c:forEach items="${ortList}" var="ort">
            	<option value="${ort.ortId}">${ort.ortname}</option>
            	</c:forEach>
            	</select>
            	<div class="error" id="ortError"></div>
           	</div>
           	<br>
           	<div>
            	<select name="taetigkeit" class="form-control">
            	<option value="0">---- <spring:message code="label.nutzen.selectActivity"/> ----</option>
            	<c:forEach items="${taetigkeitList}" var="taetigkeit">
            	<option value="${taetigkeit.id}">${taetigkeit.name}</option>
            	</c:forEach>
            	</select>
            	<div class="error" id="taetigkeitError"></div>
           	</div>
           	<br>
            <div>
            	
            	<textarea id="details" name="details" rows="10" style="width: 500px" class="form-control" placeholder="<spring:message code="label.nutzen.detail"/>"></textarea>
           		
            </div>
            <br>
             <div>
             <textarea  rows="10" style="width: 500px" class="form-control" id="sondernleistung" name="sondernleistung"  placeholder="<spring:message code="label.nutzen.specialPerformance"/>"></textarea>
            	
           	</div>
              <br>      
            <div>
            	<input type="text" id="stunde" style="width: 120px" name="stunde" class="form-control" placeholder="<spring:message code="label.nutzen.hour"/>">
           		<div class="error" id="stundeError"></div>
            </div>
            <br>       
            <div>
            <label style="padding-right: 10px; vertical-align: middle;" for="freigabe"><spring:message code="label.nutzen.release"/></label>
            	<input type="checkbox" id="freigabe" name="freigabe"  checked="checked" >
            	
           	</div>
            
           
            

            <input class="btn btn-lg btn-primary btn-block" type="submit" value="<spring:message code="label.buttonAdd"/>">
        </form>

    </div>

<%@ include file="../common/footer.jspf"%>