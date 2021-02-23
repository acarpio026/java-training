$(document).ready(function () {

    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": true,
        "progressBar": true,
        "positionClass": "toast-top-right",
        "preventDuplicates": true,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": 0,
        "extendedTimeOut": 0,
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut",
        "tapToDismiss": false,
        "onclick": null
    };

    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };


});

var appTitle = "Java Todo Exercise"

var showPermanentError = function (message) {
    toastr.error(message, appTitle, { closeButton: true });
};

var showError = function (message) {
    toastr.error(message, appTitle, { timeOut: 15000, extendedTimeOut: 15000 });
};

var showWarning = function (message) {
    toastr.warning(message, appTitle, { timeOut: 15000, extendedTimeOut: 15000 });
};

var showInfo = function (message) {
    toastr.info(message, appTitle, { timeOut: 5000, extendedTimeOut: 5000 });
};

var showSuccess = function (message) {
    toastr.success(message, appTitle, { timeOut: 5000, extendedTimeOut: 5000 });
};


var disableForm = function () {
    $('body').find('form').each(function () {
        $(this).find('input').each(function () {
            $(this).prop('disabled', true);
        });
    });
};

var enableForm = function () {
    $('body').find('form').each(function () {
        $(this).find('input').each(function () {
            $(this).prop('disabled', false);
        });
    });
};

var getAjaxError = function (jqXHR, exception) {
    var msg = '';

    msg = jqXHR.status.toString() + ' : ' + jqXHR.responseText;

    return msg;
/* 
    if (jqXHR.status === 0) {
        msg = 'Not connect.\n Verify Network.';
    } else if (jqXHR.status == 404) {
        msg = 'Not found. [404]  ' + jqXHR.responseText;
    } else if (jqXHR.status == 500) {
        msg = 'Internal Server Error [500]. ' + jqXHR.responseText;
    } else if (jqXHR.status == 405) {
        msg = 'Method Not Allowed [405]. ' + jqXHR.responseText;
    } else if (jqXHR.status == 403) {
        alert("Session time out. Please log in again.");
        window.location = $('login').attr('href');
    } else if (exception === 'parsererror') {
        msg = 'Requested JSON parse failed.';
    } else if (exception === 'timeout') {
        msg = 'Time out error.';
    } else if (exception === 'abort') {
        msg = 'Ajax request aborted.';
    } else {
        msg = 'Uncaught Error.\n' + jqXHR.responseText;
    }
    return msg; */
}
