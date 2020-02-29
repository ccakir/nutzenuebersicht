<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

    <div class="container">

        <form:form method="POST" modelAttribute="kundeForm" class="form-signin">
            <h2 class="form-signin-heading"><spring:message code="label.newCustomerTitle"/></h2>
            <spring:message code="label.tname" var="labelName"></spring:message>
            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    
                                <form:input type="text" id="name" path="name" class="form-control" autofocus="true"
                                 placeholder="${labelName}"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </spring:bind>
            <spring:message code="label.Address" var="labelAddress"></spring:message>
            <input name="adresse" placeholder="${labelAddress}" id="adresse" type="text" class="form-control"/>
            <spring:message code="label.ort" var="labelort"></spring:message>
            <input name="ortsname" placeholder="${labelort}" id="ortsname" type="text" class="form-control"/>
            <spring:message code="label.postcode" var="labelPostcode"></spring:message>
            <input name="plz" placeholder="${labelPostcode}" id="plz" type="text" class="form-control"/>
            <spring:message code="label.country" var="labelCountry"></spring:message>
            <input name="land" placeholder="${labelCountry}" id="land" type="text" class="form-control"/>
            <spring:message code="label.web" var="labelWeb"></spring:message>
            <input name="web" placeholder="${labelWeb}" id="web" type="text" class="form-control"/>
            <spring:message code="label.email" var="labelEmail"></spring:message>
            <input name="email" placeholder="${labelEmail}" id="email" type="text" class="form-control"/>
            <spring:message code="label.phone" var="labelPhone"></spring:message>
            <input name="telefon" placeholder="${labelPhone}" id="telefon" type="text" class="form-control"/>
            <spring:message code="label.fax" var="labelFax"></spring:message>
            <input name="fax" placeholder="${labelFax}" id="fax" type="text" class="form-control"/>

            
            
            
               
                    
                    
            
            
           
            

            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="label.buttonAdd"/></button>
        </form:form>

    </div>

<%@ include file="../common/footer.jspf"%>