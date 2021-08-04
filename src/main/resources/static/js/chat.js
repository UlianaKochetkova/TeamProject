	$(document).ready(function(){
$('#action_menu_btn').click(function(){
	$('.action_menu').toggle();
});
	});

/////////////////////////////////////////////////////////////////КОД ДАШИ
function someFunc(){
	//https://learn.javascript.ru/xmlhttprequest
//XMLHttpRequest - встроенный в браузер объект, который даёт возможность делать HTTP-запросы к серверу без перезагрузки страницы
	var xhr = new XMLHttpRequest();
//Инициализация. Только конфигурирует запрос
	xhr.open('POST', 'js/data/servletPage.jsp',true);

//Устанавливает заголовок запроса с именем name и значением value
//value - определяет правильную кодировку для переменных POST. https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
//TODO: в чем смысл этого действия?
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

//Функция для проверки какого-либо ответа
// 200 = OK
	xhr.onload = function() {
		if (xhr.status === 200) {
			//TODO:Что здесь за действие?
			doSomeAction(xhr.responseText, param1);
			return false;
		} else {
			alert('Request failed. Returned status of ' + xhr.status);
		}
	};

//метод устанавливает соединение и отсылает запрос к серверу
//encodeURI - кодирует строку, ничего особенного, видимо
//https://developer.mozilla.org/ru/docs/Web/JavaScript/Reference/Global_Objects/encodeURIComponent
	xhr.send(encodeURI('param1=' + param1));
}