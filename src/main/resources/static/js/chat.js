	$(document).ready(function(){
		$('#action_menu_btn').click(function(){
			$('.action_menu').toggle();
		});

		$("#groupTags").hide();
		$('#admin').click(function(){
			$('#groupTags').show();
			$('#groupTags').css("margin", "auto");
		});
		$('#back').click(function(){
		 	$("#groupTags").hide();
		});
		
		$("#chat_info").hide();
		$('#chat_info_but').click(function(){
			$('#chat_info').show();
			$('#chat_info').css("margin", "auto");
		});
		$('#backChat').click(function(){
		 	$("#chat_info").hide();
		});
		
	});


	//Функция, которая получает id чата из левого меню и отображает чат справа
	//закреплена на блоке в списке слева
	function toChat(id){
	console.log(id);
	}


	//Функция, которая получает id юзера, чью страницу нужно вывести
	//Закреплена за именем пользователя на сообщении
	function toUser(id){
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "/getUser?id="+id, true);
		xhr.send();
		xhr.onload = function() {
			if (xhr.status === 200) {
				document.getElementById("userpage").innerHTML = xhr.response;
				$('#backUser').click(function(){
					document.getElementById("userpage").innerHTML = "";
				});
			} else {
				alert('Request failed. Returned status of ' + xhr.status);
			}
		};
		
	}

/////////////////////////////////////////////////////////////////

function getTagMsgs(){
	//https://learn.javascript.ru/xmlhttprequest
//XMLHttpRequest - встроенный в браузер объект, который даёт возможность делать HTTP-запросы к серверу без перезагрузки страницы
	var xhr = new XMLHttpRequest();
	//Инициализация. Только конфигурирует запрос
	//Так как GET запрос, параметры записаны в url
	xhr.open("GET", "/getTagMsgs", true);

	//метод устанавливает соединение и отсылает запрос к серверу
	//если вы хотите отправить что-то в качестве параметра сервлету, используйте метод send, иначе send null
	//параметр запроса на бэк
	xhr.send();


//Функция для проверки какого-либо ответа
// 200 = OK
	xhr.onload = function() {
		console.log(`Загружено: ${xhr.status} ${xhr.response}`);
		if (xhr.status === 200) {
			document.getElementById("msgs_body").innerHTML = xhr.response;

			$("#groupTags").hide();
			$('#admin').click(function(){
				$('#groupTags').show();
				$('#groupTags').css("margin", "auto");
			});
			$('#back').click(function(){
				$("#groupTags").hide();
			});

			$("#chat_info").hide();
			$('#chat_info_but').click(function(){
				$('#chat_info').show();
				$('#chat_info').css("margin", "auto");
			});
			$('#backChat').click(function(){
				$("#chat_info").hide();
			});
		} else {
			alert('Request failed. Returned status of ' + xhr.status);
		}
	};
}


function setCurrTag(tagid){
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/setCurrTag", true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send(encodeURI('tagid=' + tagid));

	xhr.onload = function() {
		if (xhr.status === 200) {
			getTagMsgs();
		} else {
			alert('Request failed. Returned status of ' + xhr.status);
		}
	};
	return false;
}

function getMsg(){
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/getMsg", true);
	xhr.send(new FormData(document.getElementById("inputForm")));
	xhr.onload = function() {
		console.log(`Загружено: ${xhr.status} ${xhr.response}`);
		if (xhr.status === 200) {
			document.getElementById("chat_body").innerHTML = xhr.response;
			document.getElementById("msg").value = "";
			
			$("#groupTags").hide();
			$('#admin').click(function(){
				$('#groupTags').show();
				$('#groupTags').css("margin", "auto");
			});
			$('#back').click(function(){
				$("#groupTags").hide();
			});			
			
			$("#chat_info").hide();
			$('#chat_info_but').click(function(){
				$('#chat_info').show();
				$('#chat_info').css("margin", "auto");
			});
			$('#backChat').click(function(){
				$("#chat_info").hide();
			});
		} else {
			alert('Request failed. Returned status of ' + xhr.status);
		}
	};
	return false;
}

function groupTags(){
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/groupTags", true);
	xhr.send(new FormData(document.getElementById("newTagForm")));
	xhr.onload = function() {
		console.log(`Загружено: ${xhr.status} ${xhr.response}`);
		if (xhr.status === 200) {
			document.getElementById("chat_body").innerHTML = xhr.response;

			$("#groupTags").hide();
			$('#admin').click(function(){
				$('#groupTags').show();
				$('#groupTags').css("margin", "auto");
			});
			$('#back').click(function(){
				$("#groupTags").hide();
			});

			$("#chat_info").hide();
			$('#chat_info_but').click(function(){
				$('#chat_info').show();
				$('#chat_info').css("margin", "auto");
			});
			$('#backChat').click(function(){
				$("#chat_info").hide();
			});
		} else {
			alert('Request failed. Returned status of ' + xhr.status);
		}
	};
	return false;
}

////////////////////////////////////////////////
function getChat(id){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "/chat?id="+id, true);
	xhr.send();
	xhr.onload = function() {
		console.log(`Загружено: ${xhr.status} ${xhr.response}`);
		if (xhr.status === 200) {
			document.getElementById("chat_body").innerHTML = xhr.response;
		} else {
			alert('Request failed. Returned status of ' + xhr.status);
		}
	};
}