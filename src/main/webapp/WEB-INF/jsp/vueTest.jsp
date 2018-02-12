<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<script src="../js/vue.min.js"></script>
</head>

<body>
	<div id="app">
		<p>{{ message }}</p>
	</div>

	<div id="app-2">
		<span v-bind:title="message2"> 鼠标悬停显示 </span>
	</div>
	<div id="app-3">
		<p v-if="seen">你可以看到我
		<p />
	</div>
	<div id="app-4">
		<ol>
			<li v-for="todo in todos">{{todo.text}}</li>
		</ol>
	</div>
	<div id="app-5">
		<p>{{message5}}</p>
		<button v-on:click="reverseMessage">点击逆转消息</button>
	</div>
	<div id="app-6">
		<p>{{message6}}</p>
		<input v-model="message6" />
	</div>

	<div id="app-7">
		<ol>
			<todo-item v-for="item in groceryList" v-bind:todo="item"
				v-bind:key="item.id"></todo-item>
			<!-- todo-item 是定义的Vue组件，这个组件有todo属性，template模板 ，最终展示是模板-->
		</ol>
	</div>

	<div id="app-8">
		<a v-on:click="alertMsg">...</a>
		<span>{{now}}</span>
	</div>

	<script>
		var app8 = new Vue({
			el : "#app-8",
			methods : {
				alertMsg : function() {
					alert("更多信息请移至官网");
				}
			},
			computed : {
				now : function() {
					return Date.now()
				}
			}
		});

		var app = new Vue({
			el : '#app',
			data : {
				message : 'Hello Vue.js!'
			}
		});

		var app2 = new Vue({
			el : '#app-2',
			data : {
				message2 : '页面加载于' + new Date().toLocaleString()
			}
		});

		var app3 = new Vue({
			el : "#app-3",
			data : {
				seen : false
			}

		});

		var app4 = new Vue({
			el : "#app-4",
			data : {
				todos : [ {
					text : 'aa'
				}, {
					text : 'bb'
				}, {
					text : 'cc'
				} ]
			}
		});//在控制台里，输入 app4.todos.push({ text: '新项目' })，你会发现列表最后添加了一个新项目

		var app5 = new Vue({
			el : "#app-5",
			data : {
				message5 : "嗨！你好"
			},
			methods : {
				reverseMessage : function() {
					this.message5 = this.message5.split('').reverse().join('')
				}
			}
		});

		var app6 = new Vue({
			el : "#app-6",
			data : {
				message6 : "hello"
			}
		});

		Vue.component("todo-item", {
			props : [ 'todo' ],
			template : '<li>{{todo.text}}<li>'
		});

		var app7 = new Vue({
			el : "#app-7",
			data : {
				groceryList : [ {
					id : 0,
					text : '蔬菜'
				}, {
					id : 1,
					text : "水果"
				}, {
					id : 2,
					text : '主食'
				} ]
			}
		});
	</script>
</body>
</html>
