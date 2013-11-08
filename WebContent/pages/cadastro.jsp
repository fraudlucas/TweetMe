<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/imgs/favicon.png">
<title>TweetMe</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/bootstrap-theme.css">
<script src="${pageContext.request.contextPath}/scripts/bootstrap.js"></script>
<script
	src="${pageContext.request.contextPath}/scripts/jquery-1.10.2.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/style.css" />

<title>TweetMe</title>
</head>
<body>
	<f:view>
				<!-- Page header -->
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"><h:outputText value="#{msg.tweetme}" /></a>
				</div>
				<div class="navbar-collapse collapse">
					<h:form styleClass="navbar-form navbar-right">
						<div class="form-group">
							<h:inputText value="#{loginBean.username}"
								styleClass="form-control" />
						</div>
						<div class="form-group">
							<h:inputSecret value="#{loginBean.senha}"
								styleClass="form-control" />
						</div>
						<h:commandButton styleClass="btn btn-default"
							action="#{loginBean.signin}" value="#{msg.sign_in}" />
					</h:form>
				</div>
				<!--/.navbar-collapse -->
			</div>
		</div>

		<div id="signup" class="jumbotron pos-adv width-adv height-signup2">

			<h3><h:outputText value="#{msg.join_today}" /> </h3>
			<h:form styleClass="form-horizontal">
				<div class="form-group">
					<h:outputLabel value="#{msg.full_name}" />
					<h:inputText styleClass="form-control" id="inputName"
						value="#{signupBean.name }" />
				</div>
				<div class="form-group">
					<h:outputLabel value="#{msg.email_adress}" />
					<h:inputText styleClass="form-control" id="inputEmail"
						value="#{signupBean.email }" />
				</div>
				<div class="form-group">
					<h:outputLabel value="#{msg.create_password}" />
					<h:inputSecret styleClass="form-control" id="inputPassword"
						value="#{signupBean.password }" />
				</div>
				<div class="form-group">
					<h:outputLabel value="#{msg.username_choose}" />
					<h:inputText styleClass="form-control" id="inputUsername"
						value="#{signupBean.login }" />
				</div>
				<div class="form-group">
					<h:commandButton styleClass="btn btn-default"
						action="#{signupBean.signup}" value="#{msg.sign_up}" />
				</div>
			</h:form>
		</div>
	</f:view>
</body>
</html>