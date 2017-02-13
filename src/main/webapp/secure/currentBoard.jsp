<%@ page import="com.ironyard.data.ChatPermissions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<br/>
<style>
    .alert-message
    {
        margin: 20px 0;
        padding: 20px;
        border-left: 3px solid #eee;
    }
    .alert-message h4
    {
        margin-top: 0;
        margin-bottom: 5px;
    }
    .alert-message p:last-child
    {
        margin-bottom: 0;
    }
    .alert-message code
    {
        background-color: #fff;
        border-radius: 3px;
    }
    .alert-message-success
    {
        background-color: #F4FDF0;
        border-color: #3C763D;
    }
    .alert-message-success h4
    {
        color: #3C763D;
    }
    .alert-message-danger
    {
        background-color: #fdf7f7;
        border-color: #d9534f;
    }
    .alert-message-danger h4
    {
        color: #d9534f;
    }
    .alert-message-warning
    {
        background-color: #fcf8f2;
        border-color: #f0ad4e;
    }
    .alert-message-warning h4
    {
        color: #f0ad4e;
    }
    .alert-message-info
    {
        background-color: #f4f8fa;
        border-color: #5bc0de;
    }
    .alert-message-info h4
    {
        color: #5bc0de;
    }
    .alert-message-default
    {
        background-color: #EEE;
        border-color: #B4B4B4;
    }
    .alert-message-default h4
    {
        color: #000;
    }
    .alert-message-notice
    {
        background-color: #FCFCDD;
        border-color: #BDBD89;
    }
    .alert-message-notice h4
    {
        color: #444;
    }
    .clickable
    {
        cursor: pointer;
    }

    .clickable .glyphicon
    {
        background: rgba(0, 0, 0, 0.15);
        display: inline-block;
        padding: 6px 12px;
        border-radius: 4px
    }

    .panel-heading span
    {
        margin-top: -23px;
        font-size: 15px;
        margin-right: -9px;
    }
    a.clickable { color: inherit; }
    a.clickable:hover { text-decoration:none; }
</style>
<script>
    $('#newMsgForm').submit(function() { // catch the form's submit event
        $.ajax({ // create an AJAX call...
            data: $(this).serialize(), // get the form data
            type: $(this).attr('method'), // GET or POST
            url: $(this).attr('action'), // the file to call
            success: function(response) { // on success..
                $('#mainDiv').html(response); // update the DIV
            }
        });
        return false; // cancel original event to prevent form submitting
    });
</script>
<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">
            <c:out value="${selectedBoard.boardName}"/> board
        </h3>
        <span class="pull-right">
       <i></i>
        </span>
    </div>
    <div class="panel-body">
        <c:forEach items="${bMessages}" var="aMsg">
            <div class="col-sm-7 col-md-7">
                <div class="alert-message alert-message-info">
                    <h4>
                        <strong>Created by:</strong>
                        <c:out value="${user.username}"/>
                    </h4>
                    <p>
                        <c:out value="${aMsg.messageText}"/>
                    </p>
                       </div>
            </div>
        </c:forEach>
    </div>
    <c:if test="${permissions.contains('POST_MESSAGE')}">
        <div class="alert-message">
            <form id="newMsgForm" class="form-horizontal" action="/secure/messages/create" method="post">
                <div class="form-group">
                    <label class="control-label" for="messageText">Message Text:</label>
                    <div>
                        <textArea id="messageText" name="messageText"></textArea>
                    </div>
                </div>
                <input type="hidden" name="boardId"  value="<c:out default="0" value="${selectedBoard.ID}"/>" />
                <div class="form-group">
                    <div>
                        <button type="submit" class="btn btn-info">Post Message</button>
                    </div>
                </div>
            </form>
        </div>
    </c:if>
    </div>
</div>