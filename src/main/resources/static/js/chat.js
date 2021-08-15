	$(document).ready(function(){
$('#action_menu_btn').click(function(){
	$('.action_menu').toggle();
});

		$("#hide").hide();
		$('.add_btn').click(function(){
			$('#hide').show();
			$('#newTag').click(function(){
				$('.newTag').show();
			});
			$('.newTag').hide();
			$('#hide').css("margin", "auto");
		});

		$('#cancel').click(function(){
			$("#hide").hide();
		});

		$('#chatinform').hide();
		$('.chatbut').click(function(){
			$('#chatinform').show();
			$('#chatinform').css("margin", "auto");
		});
		$('#back').click(function(){
			$("#chatinform").hide();
		});

		$('#userpage').hide();

		$("#hide2").hide();
		$('#next').click(function(){
			$("#hide").hide();
			$("#hide2").show();
		});
	});

//TODO: AJAX
	//Функция, которая получает id чата из левого меню и отображает чат справа
	//закреплена на блоке в списке слева
	function toChat(id){
	console.log(id);
	}

	//TODO: AJAX
	//Функция, которая получает id юзера, чью страницу нужно вывести
	//Закреплена за именем пользователя на сообщении
	function toUser(id){
		console.log(id);
		//$('#userpage').show();
	}

	//TODO: AJAX
	//Функция, которая получает id чата, информацию о котором нужно вывести
	//закреплена на кнопке справа в шапке чата
	function aboutChat(id){

	}

/////////////////////////////////////////////////////////////////КОД ДАШИ
function getTagMsgs(tagid){
	//https://learn.javascript.ru/xmlhttprequest
//XMLHttpRequest - встроенный в браузер объект, который даёт возможность делать HTTP-запросы к серверу без перезагрузки страницы
	var xhr = new XMLHttpRequest();
	//Инициализация. Только конфигурирует запрос
	//Так как GET запрос, параметры записаны в url
	xhr.open("GET", "/getTagMsgs?tagid="+tagid, true);

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
		} else {
			alert('Request failed. Returned status of ' + xhr.status);
		}
	};
}

function getMsg(event){
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/getMsg", true);
	xhr.send(new FormData(event.target));
	xhr.onload = function() {
		console.log(`Загружено: ${xhr.status} ${xhr.response}`);
		if (xhr.status === 200) {
			document.getElementById("msgs_body").innerHTML = xhr.response;
			document.getElementById("msg").value = "";
		} else {
			alert('Request failed. Returned status of ' + xhr.status);
		}
	};
	event.preventDefault();
	return false;
}