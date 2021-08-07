	$(document).ready(function(){
$('#action_menu_btn').click(function(){
	$('.action_menu').toggle();
});
	});

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