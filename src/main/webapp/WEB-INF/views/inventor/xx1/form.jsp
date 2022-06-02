<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-textbox code="inventor.xx1.form.label.code" path="code"/>
			<acme:input-select code="inventor.xx1.form.label.item" path="items">
            	<jstl:forEach items="${items}" var="items">
                	<acme:input-option code="${items}" value="${items}" selected="${items}"/>
            	</jstl:forEach>
        	</acme:input-select>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox code="inventor.xx1.form.label.code" path="code" readonly="true"/>
			<acme:input-textbox code="inventor.xx1.form.label.item" path="item" readonly="true"/>
			<acme:input-moment code="inventor.xx1.form.label.xx2" path="xx2" readonly="true"/>
		</jstl:when>
	</jstl:choose>
    <acme:input-textbox code="inventor.xx1.form.label.xx3" path="xx3"/>
    <acme:input-textarea code="inventor.xx1.form.label.xx4" path="xx4"/>
    <acme:input-moment code="inventor.xx1.form.label.xx51" path="xx51"/>
    <acme:input-moment code="inventor.xx1.form.label.xx52" path="xx52"/>
    <acme:input-money code="inventor.xx1.form.label.xx6" path="xx6"/>
    <acme:input-url code="inventor.xx1.form.label.xx7" path="xx7"/>
    
    	<jstl:choose>
			<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
				<acme:submit code="inventor.xx1.form.button.update" action="/inventor/xx1/update"/>
				<acme:submit code="inventor.xx1.form.button.delete" action="/inventor/xx1/delete"/>
			</jstl:when>	
		
			<jstl:when test="${command == 'create'}">
				<acme:submit code="inventor.xx1.form.button.create" action="/inventor/xx1/create"/>
			</jstl:when>
		</jstl:choose>
    
</acme:form>