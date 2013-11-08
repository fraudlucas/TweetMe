<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="br.tweetme.entities.User"%>
<%@page import="br.tweetme.entities.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
</head>
<body>
	<f:view>

		<%
			User u = (User) session.getAttribute("user");
				List<Post> posts = (List<Post>) u.getPosts();
				int qtdPosts = posts.size();
		%>

		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"><h:outputText
							value="#{msg.tweetme}" /></a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#"><h:outputText
									value="#{msg.home}" /></a></li>
						<li><a href="#contact"><h:outputText
									value="#{msg.following}" /></a></li>
						<li><a href="#about"><h:outputText
									value="#{msg.followers}" /></a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a class="" href="#"><h:outputText
									value="#{msg.logout}" /></a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>

		<div id="profile"
			class="jumbotron pos-adv width-profile height-profile">
			<h:graphicImage styleClass="img-circle img-profile"
				value="#{pageContext.request.contextPath}/imgs/eu.jpg"></h:graphicImage>

			<h:outputText value="#{user.getLogin()}" />
		</div>

		<div id="to-post"
			class="jumbotron pos-home-1 width-home height-home-2">
			<h:form>
				<div class="form-group">
					<h:inputTextarea styleClass="margin-t-b-0 text-font form-control"
						cols="88" rows="2" value="#{postBean.post}" />
				</div>
				<div class="form-group">
					<h:commandButton styleClass="btn btn-info align-right form-control"
						action="#{postBean.toPost}" value="#{msg.post}" />
				</div>
			</h:form>

		</div>


		<div id="post" class="jumbotron pos-home-2 width-home height-home">

			<%
				for (int i = qtdPosts - 1; i >= 0; i--) {
			%>
			<div id="post"
				class="jumbotron-home pos-home-el width-home-el height-home-el">
				<h:graphicImage styleClass="img-circle img-profile-2"
					value="#{pageContext.request.contextPath}/imgs/eu.jpg"></h:graphicImage>
				<h:outputText value="#{user.getName()}" />
				<h:outputText value="#{user.getLogin()}" />
				<%=posts.get(i).getText() %>
				[<%=posts.get(i).getDate() %>]
			</div>
			<%
				}
			%>
		</div>

	</f:view>
</body>
</html>