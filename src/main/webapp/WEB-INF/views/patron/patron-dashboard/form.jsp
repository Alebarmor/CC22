<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2><acme:message code="administrator.dashboard.form.title.patronages"/></h2>

<table class="table table-sm">
	<caption><acme:message code="administrator.dashboard.form.title.patronages"/></caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.proposed-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProposedPatronages}"/>
		</td>
		
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.accepted-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfAcceptedPatronages}"/>
		</td>
		
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.denied-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfDeniedPatronages}"/>
		</td>
	</tr>
</table>

<h3><acme:message code="administrator.dashboard.form.title.stats"/></h3>

<table class="table table-sm">
	<caption><acme:message code="administrator.dashboard.form.title.stats"/></caption>
	<jstl:forEach var="entry" items="${statsBudgetOfStatusPatronages}">
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.label.status"/>
			</th>
			<td>
				<acme:print value="${entry.key.first}"/>
			</td>
		
			<th scope="row">
				<acme:message code="administrator.dashboard.form.label.currency"/>
			</th>
			<td>
				<acme:print value="${entry.key.second}"/>
			</td>
		
			<th scope="row">
				<acme:message code="administrator.dashboard.form.label.average-value"/>
			</th>
			<td>
				<acme:print value="${entry.value.average}"/>
			</td>
			
			<th scope="row">
				<acme:message code="administrator.dashboard.form.label.deviation-value"/>
			</th>
			<td>
				<acme:print value="${entry.value.deviation}"/>
			</td>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.label.minimum-value"/>
			</th>
			<td>
				<acme:print value="${entry.value.minumun}"/>
			</td>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.label.maximum-value"/>
			</th>
			<td>
				<acme:print value="${entry.value.maximun}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>
