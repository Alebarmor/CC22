<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
		<jstl:when test="${command == 'show'}">
    		<acme:input-textbox code="inventor.patronage-report.form.label.sequence-number" path="sequenceNumber" readonly="${true}"/>
		    <acme:input-moment code="inventor.patronage-report.form.label.creation-moment" path="creationMoment" readonly="${true}"/>
		    <acme:input-textarea code="inventor.patronage-report.form.label.memorandum" path="memorandum" readonly="${true}"/>
		    <acme:input-url code="inventor.patronage-report.form.label.link" path="link" readonly="${true}"/>
    	</jstl:when>
    	<jstl:when test="${command == 'create'}">
    		<acme:input-textbox code="inventor.patronage-report.form.label.code" path="code"/>
    		<acme:input-textarea code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	    	<acme:input-url code="inventor.patronage-report.form.label.link" path="link"/>
	    	<acme:input-checkbox code="inventor.patronage-report.form.label.confirmation" path="confirmation"/>
	    	<acme:submit code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create"/>
    	</jstl:when>
    </jstl:choose>
</acme:form>