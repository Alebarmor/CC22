<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
		<jstl:when test="${command == 'show' && !notPublished}">
			<acme:input-select code="patron.patronage.form.label.status" path="status" readonly="${true}">
				<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
				<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
				<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
			</acme:input-select>
		</jstl:when>
	</jstl:choose>
	<jstl:choose>
    	<jstl:when test="${command == 'create'}">	
    		<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
    	</jstl:when>
    	<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
    		<acme:input-textbox code="patron.patronage.form.label.code" path="code" readonly="${true}"/>
    	</jstl:when>
    </jstl:choose>
    <acme:input-textarea code="patron.patronage.form.label.legal-stuff" path="legalStuff"/>
    <acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
    <jstl:choose>
    	<jstl:when test="${command == 'create'}">		
	    	<acme:input-textbox code="patron.patronage.form.label.inventor.username" path="username"/>
	    </jstl:when>
	    <jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
	    	<acme:input-textbox code="patron.patronage.form.label.inventor.username" path="username" readonly="${true}"/>
	    	<acme:input-textbox code="patron.patronage.form.label.inventor.name" path="fullName" readonly="${true}"/>
	    	<acme:input-email code="patron.patronage.form.label.inventor.email" path="email" readonly="${true}"/>
	    </jstl:when>
    </jstl:choose>
    <acme:input-moment code="patron.patronage.form.label.start-period" path="startPeriod"/>
    <acme:input-moment code="patron.patronage.form.label.end-period" path="endPeriod"/>
    <acme:input-url code="patron.patronage.form.label.link" path="link"/>
    
    <jstl:choose>
    	<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && notPublished}">
    		<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
    		<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
    		<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
    	</jstl:when>
    	<jstl:when test="${command == 'create'}">
    		<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
    	</jstl:when>
    </jstl:choose>
    
    
</acme:form>