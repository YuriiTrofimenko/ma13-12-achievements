$(document).ready(function () {
    
    function populateTable(){
    	//Добавляем на место таблицы полосу бесконечного прогресса - пока будет ожидаться ответ сервера
    	$('div#achievements').html("<div class='progress'><div class='indeterminate'></div></div>");
        //url: "http://localhost:8080/WebApp1/FirstServlet?action=all-achievements",
        $.ajax({
            url: "/achievements?action=all-achievements",
            dataType: 'json',
            method : "post",
            cache : false
        }).done(function(response) {
            
            console.log(response);
            
            //var achievements = response.data;
            
            //Сформировали пустой шаблон таблицы
            var template = Hogan.compile(
                '<h4>All achievements:</h4>'
                + '<table class="table">'
                    + '<thead>'
                        + '<tr>'
                            + '<th>ID</th>'
                            + '<th>Title</th>'
                            + '<th>Content</th>'
                        + '</tr>'
                    + '</thead>'
                    + '<tbody>'
                        + '{{#data}}'
                        + '<tr>'
                        + '<th scope="row">{{id}}</th>'
                        + '<td>{{title}}</td>'
                        + '<td>{{content}}</td>'
                        + '</tr>'
                        + '{{/data}}'
                    + '</tbody>'
                + '</table>'
                );
            
            //Заполнили таблицу данными, полученными с сервера
            var table = template.render(response);
            
            //Поместили таблицу в элемент div с идентификатором achievements
            $('div#achievements').html(table);
        });
    }
    
    populateTable();
        
    $('#submit-button2').click(function (ev) {

        //Предотвращение отправки формы
        ev.preventDefault();
        //console.log("test");
        
        //Получаем значения из полей ввода
        var title = $('input#title').val();
        var content = $('textarea#content').val();
        
        //Отправка асинхронного запроса
        //url: "http://localhost:8080/WebApp1/FirstServlet?action=create"
        $.ajax({
            url: "/achievements?action=create"
                + "&title="
                + title
                + "&content="
                + content,
            dataType: 'json',
            method : "post",
            cache : false
        }).done(function(response) {
            
            console.log(response);
            populateTable();
        });
    });
});
