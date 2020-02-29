<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
  

    <div class="container">

        <form:form method="POST" modelAttribute="userForm" class="form-signin">
            <h2 class="form-signin-heading">Update Benutzer</h2>
            <form:hidden path="id" />
            <spring:bind path="vorname">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="vorname" class="form-control" placeholder="Vorname"
                                autofocus="true"></form:input>
                    <form:errors path="vorname"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="nachname">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="nachname" class="form-control" placeholder="Nachname"
                                autofocus="true"></form:input>
                    <form:errors path="nachname"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control2" placeholder="Username"
                                autofocus="true" ></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" id="password" path="password" class="form-control2" placeholder="Kennwort"></form:input>
                    <form:errors path="password"></form:errors>
                    
                </div>
            </spring:bind>

            <spring:bind path="matchingNewPassword">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="matchingNewPassword"  class="form-control2"
                                placeholder="Kennwort wiederholen"></form:input>
                    <form:errors path="matchingNewPassword"></form:errors>
                </div>
            </spring:bind>

            
            
            <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="email" class="form-control" placeholder="Email"
                                autofocus="true"></form:input>
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind>
            
            
            <div>
            
            <form:select path="ort" name="ort" class="form-control">
            <c:forEach items="${ortList}" var="ort">
        <option value="${ort.ortId}" ${ort.ortId == ortId.ortId ? 'selected="selected"' : ''}>${ort.ortname}</option>
    </c:forEach>
            </form:select>
            
            </div>
                
                <c:choose>
                	<c:when test="${formRole == '[ROLE_ADMIN]'}">
                		<div>
                			<input type="radio" name="role"  checked="checked" value="1"/>ADMIN
                  			<input type="radio" name="role"  value="2"/>USER
               			</div>
                	</c:when>
                	<c:otherwise>
                		<div>
                			<input type="radio" name="role"  value="1"/>ADMIN
                  			<input type="radio" name="role"  checked="checked"  value="2"/>USER
               			</div>
                	</c:otherwise>
                </c:choose>
                   
           
            
            
            

            <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
        </form:form>

    </div>

<%@ include file="../common/footer.jspf"%>