<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
    <acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>
    <acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
    <acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>
    <acme:input-textarea code="inventor.toolkit.form.label.assemblyNote" path="assemblyNote"/>
    <acme:input-textbox code="inventor.toolkit.form.label.retailPrice" path="retailPrice" readonly="true"/>
    <acme:input-url code="inventor.toolkit.form.label.link" path="optionalLink"/>
	
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && draft == true}">
			<acme:submit code="inventor.toolkit.form.button.update" action="/inventor/toolkit/update"/>
			<acme:submit code="inventor.toolkit.form.button.delete" action="/inventor/toolkit/delete"/>
			<acme:submit code="inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish"/>
		</jstl:when>	
			
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
		</jstl:when>
	</jstl:choose>
	
	<acme:button code="inventor.toolkit.form.button.items" action="/inventor/quantity/list-toolkit?masterId=${id}"/>
	
</acme:form>
