<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="authenticated.announcement.list.label.creation" path="creation" width="30%"/>
    <acme:list-column code="authenticated.announcement.list.label.title" path="title" width="50%"/>
    <acme:list-column code="authenticated.announcement.list.label.status" path="status" width="20%"/>
   
</acme:list>