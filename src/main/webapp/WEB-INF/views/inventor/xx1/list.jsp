<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="inventor.xx1.list.label.code" path="code" width="35%"/>
    <acme:list-column code="inventor.xx1.list.label.xx3" path="xx3" width="30%"/>
    <acme:list-column code="inventor.xx1.list.label.item" path="item" width="35%"/>
</acme:list>

<acme:button code="inventor.xx1.list.button.create" action="/inventor/xx1/create"/>