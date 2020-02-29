<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">

        <form:form method="POST" modelAttribute="kunde" class="form-signin">
            <h2 class="form-signin-heading">Create your account</h2>
            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="name" class="form-control" placeholder="name"
                                autofocus="true"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="adresse">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="adresse" class="form-control" placeholder="adresse"
                                autofocus="true"></form:input>
                    <form:errors path="adresse"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="plz">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="plz" class="form-control" placeholder="plz"
                                autofocus="true"></form:input>
                    <form:errors path="plz"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="ort">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="ort" class="form-control" placeholder="ort"
                                autofocus="true"></form:input>
                    <form:errors path="ort"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="land">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="land" class="form-control" placeholder="land"
                                autofocus="true"></form:input>
                    <form:errors path="land"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="telefon">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="telefon" class="form-control" placeholder="telefon"
                                autofocus="true"></form:input>
                    <form:errors path="telefon"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="fax">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="fax" class="form-control" placeholder="fax"
                                autofocus="true"></form:input>
                    <form:errors path="fax"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="web">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="web" class="form-control" placeholder="web"
                                autofocus="true"></form:input>
                    <form:errors path="web"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="email" class="form-control" placeholder="email"
                                autofocus="true"></form:input>
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind>

            

            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>

    </div>
<%@ include file="common/footer.jspf"%>