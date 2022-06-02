<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="administrator.configuration.form.label.default-currency" path="defaultCurrency"/>
    <acme:input-textbox code="administrator.configuration.form.label.accepted-currencies" path="acceptedCurrencies"/>
    <acme:input-textbox code="administrator.configuration.form.label.strong-spam-terms" path="strongSpamTerms"/>
    <acme:input-double code="administrator.configuration.form.label.strong-spam-threshold" path="strongSpamThreshold"/>
    <acme:input-textbox code="administrator.configuration.form.label.weak-spam-words" path="weakSpamTerms"/>
    <acme:input-double code="administrator.configuration.form.label.weak-spam-threshold" path="weakSpamThreshold"/>
	<acme:submit code="administrator.configuration.form.button.update" action="/administrator/configuration/update"/>
</acme:form>
