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
        <title>ClassesEntity List</title>
    </head>
    <body>
        <f:view>
            <h1>ClassesEntity List</h1>
            <h:form>
              <h:commandButton action="#{classesEntity.startCreate}" value="Create"/>

              <h:dataTable value='#{classesEntity.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
                                                       <h:column>
                      <f:facet name="header">
                      <h:outputText value="classId"/>
                      </f:facet>
                      <h:outputText value="#{item.classId}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="name"/>
                      </f:facet>
                      <h:outputText value="#{item.name}"/>
                  </h:column>
                                                   <h:column>
                      <h:commandButton value="View" action="#{classesEntity.startView}">
                          <f:param name="id" value="#{item.classId}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{classesEntity.startEdit}">
                          <f:param name="id" value="#{item.classId}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{classesEntity.delete}">
                          <f:param name="id" value="#{item.classId}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
