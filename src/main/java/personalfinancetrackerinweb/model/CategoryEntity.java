<?xml version='1.0' encoding='UTF-8' ?>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:p="http://primefaces.org/ui">
    <h:head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Expense</title>
        
        <style>
            .nav-links {
                display: inline-block;
                margin-right: 10px;

}
        </style>
        <h:outputStylesheet library="primefaces-aristo" name="theme.css" />
    </h:head>

    <h:body id="layoutBody">
        <p:layout fullPage="true">
            <p:layoutUnit position="north" size="60">
                <h:outputText value="Personal Finance Tracker" style="font-size: 24px; font-weight: bold; margin: 20px;" />
            </p:layoutUnit>
            <p:layoutUnit position="west" size="250">
                <p:panelMenu style="width: 100%">
                    <p:submenu label="Expense">
                        <p:menuitem value="Add Expense Source" outcome="addExpense.xhtml" />
                        <p:menuitem value="Delete Expense Source" outcome="deleteExpense.xhtml" />
                        <p:menuitem value="Update Expense Source" outcome="updateExpense.xhtml" />
                        <p:menuitem value="Expense List Report" outcome="findAllExpense.xhtml" />
                        <p:menuitem value="Find Expense Source By Id" outcome="findByIdExpense.xhtml" />
                    </p:submenu>
                </p:panelMenu>
            </p:layoutUnit>
            <p:layoutUnit position="center">
                <p:outputPanel>
                    <h2>Welcome to the Expense Page</h2>
                    <!-- Add your content here -->
                </p:outputPanel>
            </p:layoutUnit>
        </p:layout>
    </h:body>
</html>

package personalfinancetrackerinweb.model;

/**
 *
 * @author ishwar
 */
public class CategoryEntity {
    
}
