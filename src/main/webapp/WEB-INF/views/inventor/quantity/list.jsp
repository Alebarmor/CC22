<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="inventor.item.list.label.number" path="number" width="20%"/>
    <acme:list-column code="inventor.item.list.label.item-type" path="item.itemType" width="20%"/>
    <acme:list-column code="inventor.item.list.label.item-name" path="item.name" width="20%"/>
    <acme:list-column code="inventor.item.list.label.item-technology" path="item.technology" width="20%"/>
    <acme:list-column code="inventor.item.list.label.item-retailPrice" path="item.retailPrice" width="20%"/>
</acme:list>
<acme:button test="${showCreate}" code="inventor.item-toolkit.list.button.create" action="/inventor/quantity/create?masterId=${masterId}"/>