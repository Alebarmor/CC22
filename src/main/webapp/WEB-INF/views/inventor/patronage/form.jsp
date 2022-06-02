<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-select code="inventor.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
    <acme:input-textbox code="inventor.patronage.form.label.code" path="code" readonly="${true}"/>
    <acme:input-textarea code="inventor.patronage.form.label.legal-stuff" path="legalStuff" readonly="${true}"/>
    <acme:input-money code="inventor.patronage.form.label.budget" path="budget" readonly="${true}"/>
    <acme:input-textbox code="inventor.patronage.form.label.patron.name" path="fullName" readonly="${true}"/>
    <acme:input-email code="inventor.patronage.form.label.patron.email" path="email" readonly="${true}"/>
    <acme:input-moment code="inventor.patronage.form.label.start-period" path="startPeriod" readonly="${true}"/>
    <acme:input-moment code="inventor.patronage.form.label.end-period" path="endPeriod" readonly="${true}"/>
    <acme:input-url code="inventor.patronage.form.label.link" path="link" readonly="${true}"/>
    
    <jstl:choose>
    	<jstl:when test="${acme:anyOf(command, 'show, update') && proposed}">
    		<acme:submit code="inventor.patronage.form.button.update" action="/inventor/patronage/accept-or-deny"/>
    	</jstl:when>
    </jstl:choose>
</acme:form>