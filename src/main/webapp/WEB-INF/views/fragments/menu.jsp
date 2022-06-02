<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Provider,acme.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="15414656X: Salgado Bravo, Laura" action="https://www.youtube.com"/>
			<acme:menu-suboption code="49133636R: Barba Moreno, Alejandro" action="https://www.youtube.com/watch?v=GQt1-1oPrOM"/>
			<acme:menu-suboption code="15427850W: Sanchez Hossdorf, Alexander" action="https://www.youtube.com/watch?v=eSZFIXbqmhs&list=PLNQtd15ZC-ZyZKruTodo-4I3_kfoGaDEq&index=2&t=3241s"/>
			<acme:menu-suboption code="47563095S: Pardo Pastor, Carlos" action="https://www.youtube.com/watch?v=fJ9rUzIMcZQ"/>
			<acme:menu-suboption code="29539112M: Silva Leon, Fernando" action="https://www.powerpyx.com"/>
			<acme:menu-suboption code="29533279Z: Garcia Lergo, Horacio" action="https://www.youtube.com/watch?v=K3Qzzggn--s"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.chirp.list-recent" action="/any/chirp/list-recent"/>
      		<acme:menu-suboption code="master.menu.anonymous.item.list-all" action="/any/item/list"/>
      		<acme:menu-suboption code="master.menu.anonymous.toolkit.list-all" action="/any/toolkit/list"/>
			<acme:menu-suboption code="master.menu.any.user-account.list" action="/any/user-account/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="hasRole('Authenticated')">
			<acme:menu-suboption code="master.menu.authenticated.chirp.list-recent" action="/any/chirp/list-recent"/>
			<acme:menu-suboption code="master.menu.authenticated.chirp.list-recent" action="/any/chirp/list-recent"/>
	    	<acme:menu-suboption code="master.menu.authenticated.item.list-all" action="/any/item/list"/>
	    	<acme:menu-suboption code="master.menu.authenticated.toolkit.list-all" action="/any/toolkit/list"/>
	      	<acme:menu-suboption code="master.menu.any.user-account.list" action="/any/user-account/list"/>
	      	<acme:menu-suboption code="master.menu.authenticated.announcement.list" action="/authenticated/announcement/list"/>
	      	<acme:menu-suboption code="master.menu.authenticated.configuration" action="/authenticated/configuration/show"/>
	      	<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.money-exchage" action="/authenticated/money-exchange/perform"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>	
			<acme:menu-suboption code="master.menu.administrator.configuration" action="/administrator/configuration/show"/>		
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.chirp.list-recent" action="/any/chirp/list-recent"/>
      		<acme:menu-suboption code="master.menu.administrator.item.list-all" action="/any/item/list"/>
      		<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.announcement.create-announcement" action="/administrator/announcement/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
      		<acme:menu-suboption code="master.menu.patron.chirp.list-recent" action="/any/chirp/list-recent"/>
      		<acme:menu-suboption code="master.menu.patron.patronage.list-mine" action="/patron/patronage/list-mine"/>
      		<acme:menu-suboption code="master.menu.patron.patronage-report.list-mine" action="/patron/patronage-report/list-mine"/>
      		<acme:menu-suboption code="master.menu.patron.patron-dashboard" action="/patron/patron-dashboard/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
		  	<acme:menu-suboption code="master.menu.inventor.chirp.list-recent" action="/any/chirp/list-recent"/>
		  	<acme:menu-suboption code="master.menu.inventor.patronage.list-mine" action="/inventor/patronage/list-mine"/>
		  	<acme:menu-suboption code="master.menu.inventor.patronage-report.list-mine" action="/inventor/patronage-report/list-mine"/>
     		<acme:menu-suboption code="master.menu.inventor.item.list-mine" action="/inventor/item/list"/>
			<acme:menu-suboption code="master.menu.inventor.toolkit.list-mine-toolkit" action="/inventor/toolkit/list-mine-toolkits"/>
			<acme:menu-suboption code="master.menu.inventor.xx1.list-mine" action="/inventor/xx1/list-mine"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.inventor" action="/authenticated/inventor/update" access="hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

