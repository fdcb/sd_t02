<!--
  Created by IntelliJ IDEA.
  User: Filipa
  Date: 5/26/2016
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Edit ClassesEntity</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Edit ClassesEntity</h1>
            <h:form>
                <h:panelGrid columns="2">
                                    <h:outputText value="classId:"/>
                                                                        <h:inputText value="#{classesEntity.entity.classId}" title="classId" />
                                                                                <h:outputText value="name:"/>
                                                                        <h:inputText value="#{classesEntity.entity.name}" title="name" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{classesEntity.save}" value="Save"/>
                <h:commandButton action="classesEntityList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
