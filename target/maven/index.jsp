<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>
<body>

    <%--<div ng-app="" ng-init="firstName='json'">--%>
        <%--<p>名字 : <input type="text" ng-model="name"></p>--%>
        <%--<h1>Hello {{name}}</h1>--%>
        <%--<p>姓名为 <span ng-bind="firstName"></span></p>--%>
        <%--<p>姓名为 <span>{{firstName}}</span></p>--%>
        <%--<p>姓名为 <span data-ng-bind="firstName"></span></p>--%>
        <%--<p>我的第一个表达式： {{ 5 + 5 }}</p>--%>
    <%--</div>--%>

    <%--<div ng-app="myApp" ng-controller="myCtrl">--%>

        <%--名: <input type="text" ng-model="firstName"><br>--%>
        <%--姓: <input type="text" ng-model="lastName"><br>--%>
        <%--<br>--%>
        <%--姓名: {{firstName + " " + lastName}}--%>

    <%--</div>--%>

    <%--<script>--%>
        <%--var app = angular.module('myApp', []);--%>
        <%--app.controller('myCtrl', function($scope) {--%>
            <%--$scope.firstName= "John";--%>
            <%--$scope.lastName= "Doe";--%>
        <%--});--%>
    <%--</script>--%>

    <div ng-app="" ng-init="names=[
        {name:'Jani',country:'Norway'},
        {name:'Hege',country:'Sweden'},
        {name:'Kai',country:'Denmark'}]">

        <p>循环对象：</p>
        <ul>
            <li ng-repeat="x    in names">
                {{ x.name + ', ' + x.country }}
            </li>
        </ul>

    </div>
</body>
</html>
