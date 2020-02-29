<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

    <div class="container">

        <form:form method="POST" modelAttribute="ortForm" class="form-signin">
            <h2 class="form-signin-heading"><spring:message code="label.newLocationTitle"/></h2>
            <spring:message code="label.locationName" var="labelLocationName"></spring:message>
            <spring:bind path="ortname">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    
                                <form:input type="text" id="ortname" path="ortname" class="form-control" autofocus="true" placeholder="${labelLocationName}"></form:input>
                    <form:errors path="ortname"></form:errors>
                </div>
            </spring:bind>
            <spring:message code="label.Address" var="labelAddress"></spring:message>
            <spring:bind path="adresse">
                
                    <form:input type="text" path="adresse" class="form-control" placeholder="${labelAddress}"
                                autofocus="true"></form:input>
                    
            </spring:bind>
            <spring:message code="label.postcode" var="labelPostcode"></spring:message>
            <spring:bind path="plz">
                
                    <form:input type="text" path="plz" class="form-control" placeholder="${labelPostcode}"
                                autofocus="true"></form:input>
                 
            </spring:bind>

            
            
            
               
                    <input name="land" placeholder="<spring:message code="label.country"/>" id="land" type="text" class="form-control"/>
                    
            
            
           
            

            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="label.buttonAdd"/></button>
        </form:form>

    </div>

<%@ include file="../common/footer.jspf"%>