<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

    <div class="container">

        <form:form method="POST" modelAttribute="kontaktForm" class="form-signin">
            <h2 class="form-signin-heading"><spring:message code="label.contact.newContactTitle"/></h2>
            <select id="anrede" name="anrede" path="anrede" class="form-control" autofocus="true" >
            <option value=""><spring:message code="label.contact.anrede"/></option>
            <option value="<spring:message code="label.contact.anrede.herr"/>"><spring:message code="label.contact.anrede.herr"/></option>
            <option value="<spring:message code="label.contact.anrede.frau"/>"><spring:message code="label.contact.anrede.frau"/></option>
            </select>
            <input name="titel" placeholder="<spring:message code="label.contact.titel"/>" id="titel" type="text" class="form-control" autofocus="true"/>
            <spring:message code="label.vorname" var="labelVorname"></spring:message>
            <spring:bind path="vorname">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    
                                <form:input type="text" id="vorname" path="vorname" class="form-control" autofocus="true"
                                 placeholder="${labelVorname}"></form:input>
                    <form:errors path="vorname"></form:errors>
                </div>
            </spring:bind>
            <spring:message code="label.nachname" var="labelNachname"></spring:message>
            <spring:bind path="nachname">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    
                                <form:input type="text" id="nachname" path="nachname" class="form-control" autofocus="true"
                                 placeholder="${labelNachname}"></form:input>
                    <form:errors path="nachname"></form:errors>
                </div>
            </spring:bind>
            <div><input name="telefon" placeholder="<spring:message code="label.phone"/>" id="telefon" type="text" class="form-control"/></div>
            <div><input name="mobil" placeholder="<spring:message code="label.contact.mobil"/>" id="mobil" type="text" class="form-control"/></div>
            <div><input name="fax" placeholder="<spring:message code="label.fax"/>" id="fax" type="text" class="form-control"/></div>
            <spring:message code="label.email" var="labelEmail"></spring:message>
            <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    
                                <form:input type="text" id="email" path="email" class="form-control" autofocus="true"
                                 placeholder="${labelEmail}"></form:input>
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind><br>
           <spring:bind path="kunde">
           <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:select path="kunde" name="kunde" class="form-control">
            <option value = "0"><spring:message code="label.selectKunde"/></option>
            <c:forEach items="${kundeList}" var="kunden">
        <option value="${kunden.kundeId}" ${kunden.kundeId==selectedKunde ? 'selected' :''}>${kunden.name} / ${kunden.land}</option>
    </c:forEach>
            </form:select>
            <form:errors path="kunde"></form:errors>
            
            </spring:bind> </div>
            
            

            
            
            
               
                    
                    
            
            
           
            

            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="label.buttonAdd"/></button>
        </form:form>

    </div>

<%@ include file="../common/footer.jspf"%>