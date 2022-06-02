<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="inventor.patronage.list.label.code" path="code" width="35%"/>
    <acme:list-column code="inventor.patronage.list.label.start-period" path="startPeriod" width="25%"/>
    <acme:list-column code="inventor.patronage.list.label.end-period" path="endPeriod" width="25%"/>
    <acme:list-column code="inventor.patronage.list.label.status" path="status" width="15%"/>
</acme:list>