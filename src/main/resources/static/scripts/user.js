$(document).ready(function () {

     /*ON DOM READY */
     $('#userRegistration').modal({ show: false })


    $('html').on('click', '#btnRegister', function (event) {
        event.preventDefault();
        event.stopPropagation();

        $('#userRegistration').modal('show');        
    });



    $('html').on('click', '#btnSaveCustomer', function (event) { 

        var path = $('#frmRegistration').attr("action"); 
        var data = $('#frmRegistration').serializeJSON(); 
    
        $.ajax({
            type: (parseInt($("#hUserId").val()) < 1) ? "POST" : "PATCH",
            url: path,
            data: JSON.stringify(getModalValue()),
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
    
                showSuccess('Saved successfully.'); 
                $('#userRegistration').modal('hide');   
    
            },
            error: function (xhr, status, error) {
                showPermanentError(xhr.status + ' : ' + xhr.responseText);
                $('.todoList input:checkbox').removeAttr("disabled");
            }
        });
        
    });

});

function getModalValue(){
    var result = {
        "userId" : parseInt($("#hUserId").val()),
        "username" : $("#txtUsername").val(),
        "password" : $("#txtPassword").val(),
        "active" : $("#chkIsActive").prop("checked")
    }
    return result;
}