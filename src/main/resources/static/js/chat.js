	$(document).ready(function(){
$('#action_menu_btn').click(function(){
	$('.action_menu').toggle();
});
	});

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