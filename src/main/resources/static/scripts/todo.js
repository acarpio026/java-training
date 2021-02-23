$(document).ready(function () {

    /* LOAD DATA ON DOM READY */
    $('#todoModal').modal({ show: false })
    getTodoLIst();


    $('#chkIncludeDone').change(function () {
        getTodoLIst();
    });

    $('#contentWrapper').on('click', 'input[type="checkbox"].isDoneToggle', function (event) {

        var todoId = $(this).val();
        var row = $(this).closest('tr');
        var currentState = !($(this).prop('checked'));

        $('.todoList input:checkbox').attr("disabled", true);

        $.ajax({
            type: "PATCH",
            url: "/../api/todo/",
            data: serializeTodoFromDataTable(row, !currentState),
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {

                showSuccess('Updated successfully.');
                getTodoLIst();

                // $('.todoList input:checkbox').removeAttr("disabled");

                // if ($('#chkIncludeDone').prop('checked')) {

                //     if (currentState) {
                //         row.find('.icon').show();
                //         row.find('.form-group > p').removeClass('done');
                //     }
                //     else {
                //         row.find('.icon').hide();
                //         row.find('.form-group > p').addClass('done');
                //     }
                // }
                // else {
                //     getTodoLIst();
                // }

            },
            error: function (xhr, status, error) {
                showPermanentError(xhr.status + ' : ' + xhr.responseText);
                $('.todoList input:checkbox').removeAttr("disabled");
                event.preventDefault();
                event.stopPropagation();
            }
        });
    });



    /* 
        $('#contentWrapper').on('change', '.isDoneToggle input[type="checkbox"]', function (event) {
    
            var todoId = $(this).val();
            var row = $(this).closest('tr');
    
            var currentState = row.find('checkbox').prop('checked');
    
            $('.todoList input:checkbox').attr("disabled", true);
    
            $.ajax({
                type: "PATCH",
                url: "/../api/todo/",
                data: serializeTodoFromDataTable(row, !currentState),
                async: false,
                contentType: "application/json; charset=utf-8",
                success: function (result, status, xhr) {
    
                    showSuccess('Updated successfully.');
    
                    // $('.todoList input:checkbox').removeAttr("disabled");
    
                    // console.log(currentState);
                    // console.log(row.find('.icon'));
                    // console.log(row.find('.form-group > p'));
    
                    // if ($('#chkIncludeDone').prop('checked')) {
    
                    //     if (currentState) {
                    //         $(this).removeAttr("checked");
                    //         //row.find('.icon').css("display", "block");
                    //         row.find('.icon').show();
                    //         row.find('.form-group > p').removeClass('done');
                    //     }
                    //     else {
                    //         $(this).prop( "checked", true );
                    //         row.find('.icon').hide();
                    //         //row.find('.icon').css("display", "none");
                    //         row.find('.form-group > p').addClass('done');
                    //     }
                    // }
                    // else {
                    //     getTodoLIst();
                    // }
    
                },
                error: function (xhr, status, error) {
                    showPermanentError(xhr.status + ' : ' + xhr.responseText);
                    $('.todoList input:checkbox').removeAttr("disabled");
                    event.preventDefault();
                    event.stopPropagation();
                }
            });
    
            getTodoLIst();
        });
     */

    $('#contentWrapper').on('click', '.btnTodoDelete', function (event) {
        var row = $(this).closest('tr');

        $("#hTodoId").val(row.find('.isDoneToggle').val());
        $("#txtTodoDescription").val(row.find('p').text());

        $('#todoModal').modal('show');
    });



});


function getTodoLIst() {
    //showInfo('Loading data from server, please wait...');

    $.ajax({
        type: "GET",
        url: "/todo/list?includeDone=" + $('#chkIncludeDone').prop('checked'),
        async: false,
        success: function (response) {
            $('body').find('#contentWrapper').html(response);
            $('#tblTodo').DataTable({
                "paging": true,
                "ordering": false,
                "info": false
            });
            setTimeout(null, 2000);
            showSuccess('Loading data completed.');
        },
        error: function (xhr, status, error) {
            showPermanentError(xhr.status + ' : ' + xhr.responseText);
        }
    });
}

function serializeTodoFromDataTable(row, isDone) {
    var result = {
        todoId: parseInt(row.find('.isDoneToggle').val()),
        isDone: isDone,
        todoDescription: row.find('p').text(),
        modifiedOn: new Date($.now())
    };
    return JSON.stringify(result);
}

function todoItemDelete(itemId) {
    $.ajax({
        type: "DELETE",
        url: "/../api/todo/" + itemId,
        async: false,
        success: function (result, status, xhr) {
            showSuccess('Deleted successfully.');
            getTodoLIst();
        },
        error: function (xhr, status, error) {
            showPermanentError(xhr.status + ' : ' + xhr.responseText);
            $('.todoList input:checkbox').removeAttr("disabled");
        }
    });

}

function todoItemSave() {
    var todo = {
        todoId: parseInt($("#hTodoId").val()),
        isDone: false,
        todoDescription: $("#txtTodoDescription").val(),
        modifiedOn: new Date($.now())
    };

    $.ajax({
        type: (parseInt($("#hTodoId").val()) < 1) ? "POST" : "PATCH",
        url: "/../api/todo/",
        data: JSON.stringify(todo),
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (result, status, xhr) {

            showSuccess('Saved successfully.');
            getTodoLIst();
            $('#todoModal').modal('hide');
        },
        error: function (xhr, status, error) {
            showPermanentError(xhr.status + ' : ' + xhr.responseText);
            $('.todoList input:checkbox').removeAttr("disabled");
        }
    });

}


function newTodo() {
    $("#hTodoId").val("0");
    $("#txtTodoDescription").val("");

    $('#todoModal').modal('show');
}