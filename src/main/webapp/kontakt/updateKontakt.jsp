<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

    <div class="container">

        <form:form method="POST" modelAttribute="updateKontakt" class="form-signin">
            <h2 class="form-signin-heading"><spring:message code="label.contact.contactUpdateTitel"/></h2>
            <form:hidden path="kontaktId"/>
             <spring:message code="label.contact.anrede.herr" var="labelAnredeHerr"></spring:message>
             <spring:message code="label.contact.anrede.frau" var="labelAnredeFrau"></spring:message>
            <form:select id="anrede" path="anrede" class="form-control" autofocus="true" >
            <option value=""><spring:message code="label.contact.anrede"/></option>
            <option value="${labelAnredeHerr}" ${updateKontakt.anrede == labelAnredeHerr ?'selected="selected"' : ''}><spring:message code="label.contact.anrede.herr"/></option>
            <option value="${labelAnredeFrau}" ${updateKontakt.anrede == labelAnredeFrau ?'selected="selected"' : ''}><spring:message code="label.contact.anrede.frau"/></option>
            </form:select>
            <spring:message code="label.contact.titel" var="labelTitel"></spring:message>
            <form:input path="titel" placeholder="${labelTitel}" id="titel" type="text" class="form-control" autofocus="true"></form:input>
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
            <spring:message code="label.phone" var="labelPhone"></spring:message>
            <div><form:input path="telefon" placeholder="${labelPhone}" id="telefon" type="text" class="form-control"></form:input></div>
            <spring:message code="label.contact.mobil" var="labelMobil"></spring:message>
            <div><form:input path="mobil" placeholder="${labelMobil}" id="mobil" type="text" class="form-control"></form:input></div>
            <spring:message code="label.fax" var="labelFax"></spring:message>
            <div><form:input path="fax" placeholder="${labelFax}" id="fax" type="text" class="form-control"></form:input></div>
            <spring:message code="label.email" var="labelEmail"></spring:message>
            <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    
                                <form:input type="text" id="email" path="email" class="form-control" autofocus="true"
                                 placeholder="${labelEmail}"></form:input>
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind><br>
           <div><spring:bind path="kunde"> 
            <form:select path="kunde" name="kunde" class="form-control">
            <option value = "0"><spring:message code="label.selectKunde"/></option>
            <c:forEach items="${kundeList}" var="kunden">
        <option value="${kunden.kundeId}" ${updateKontakt.kunde.kundeId == kunden.kundeId ? 'selected="selected"' : ''}>${kunden.name} / ${kunden.land}</option>
    </c:forEach>
            </form:select>
            <form:errors path="kunde"></form:errors>
            
            </spring:bind> </div>
            
            

            
            
            
               
                    
                    
            
            
           
            

            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="label.buttonUpdate"/></button>
        </form:form>

    </div>

<%@ include file="../common/footer.jspf"%>