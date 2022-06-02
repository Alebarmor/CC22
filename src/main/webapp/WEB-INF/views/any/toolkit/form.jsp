<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	
    <acme:input-textbox code="any.toolkit.form.label.code" path="code"/>
    <acme:input-textbox code="any.toolkit.form.label.title" path="title"/>
    <acme:input-textarea code="any.toolkit.form.label.description" path="description"/>
    <acme:input-textarea code="any.toolkit.form.label.assemblyNote" path="assemblyNote"/>
    <acme:input-textarea code="any.toolkit.form.label.retailPrice" path="retailPrice"/>
    <acme:input-url code="any.toolkit.form.label.link" path="optionalLink"/>
    <acme:input-textbox code="any.toolkit.form.label.inventor" path="fullname"/>
    <acme:button code="any.toolkit.form.button.items" action="/any/item/list-toolkit?id=${id}"/>	
	
</acme:form>
