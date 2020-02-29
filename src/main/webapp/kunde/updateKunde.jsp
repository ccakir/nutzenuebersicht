<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

    <div class="container">

        <form:form method="POST" modelAttribute="updateKundeForm" class="form-signin">
            <h2 class="form-signin-heading"><spring:message code="label.customerUpdateTitle"/></h2>
            <form:hidden path="kundeId" />
            <spring:message code="label.tname" var="labelName"></spring:message>
            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    
                                <form:input type="text" id="name" path="name" class="form-control" autofocus="true" placeholder="${labelName}"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </spring:bind>
            
             <spring:message code="label.Address" var="labelAddress"></spring:message>
             <form:input name="adresse" placeholder="${labelAddress}" path="adresse" type="text" class="form-control"></form:input>
             <spring:message code="label.ort" var="labelort"></spring:message>
             <form:input name="ortsname" placeholder="${labelort}" path="ortsname" type="text" class="form-control"></form:input>
             <spring:message code="label.postcode" var="labelPostcode"></spring:message>
             <form:input name="plz" placeholder="${labelPostcode}" path="plz" type="text" class="form-control"></form:input>
             <spring:message code="label.country" var="labelCountry"></spring:message>
             <form:input  name="land" placeholder="${labelCountry}" path="land" type="text" class="form-control"></form:input>
             <spring:message code="label.web" var="labelWeb"></spring:message>
             <form:input  name="web" placeholder="${labelWeb}" path="web" type="text" class="form-control"></form:input>
             <spring:message code="label.email" var="labelEmail"></spring:message>
             <form:input  name="email" placeholder="${labelEmail}" path="email" type="text" class="form-control"></form:input>
             <spring:message code="label.phone" var="labelPhone"></spring:message>
             <form:input  name="telefon" placeholder="${labelPhone}" path="telefon" type="text" class="form-control"></form:input>
             <spring:message code="label.fax" var="labelFax"></spring:message>
             <form:input  name="fax" placeholder="${labelFax}" path="fax" type="text" class="form-control"></form:input>
            
           
            

            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="label.buttonUpdate"/></button>
        </form:form>

    </div>

<%@ include file="../common/footer.jspf"%>