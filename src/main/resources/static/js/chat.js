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
function someFunc(){

	//console.log('Функция вызвана');
	//https://learn.javascript.ru/xmlhttprequest
//XMLHttpRequest - встроенный в браузер объект, который даёт возможность делать HTTP-запросы к серверу без перезагрузки страницы
	var xhr = new XMLHttpRequest();
	let tagid=1;
//Инициализация. Только конфигурирует запрос
	xhr.open('GET', 'jsp/servletPage.jsp',true);

//Устанавливает заголовок запроса с именем name и значением value
//value - определяет правильную кодировку для переменных POST. https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

//Функция для проверки какого-либо ответа
// 200 = OK
	xhr.onload = function() {
//		console.log('Функция вызвана');
		if (xhr.status === 200) {
			//TODO:Что здесь за действие?
			//Обработка того, что пришло с бэка
			//jQuery эквивалетно - success
			//doSomeAction(xhr.responseText, param1);
			//success(xhr.responseText);
			//console.log(xhr.responseType);
			console.log(xhr.response);
			return false;
		} else {
			alert('Request failed. Returned status of ' + xhr.status);
		}
	};

//метод устанавливает соединение и отсылает запрос к серверу
//encodeURI - кодирует строку
//https://developer.mozilla.org/ru/docs/Web/JavaScript/Reference/Global_Objects/encodeURIComponent
//если вы хотите отправить что-то в качестве параметра сервлету, используйте метод send, иначе send null
//параметр запроса на бэк
	xhr.send(encodeURI('tagid=' + tagid));
}

//Функция, которая обрабатывает то, что пришло с бэка
//
// function success(lst){
//
// }