var alertTimeout, delay = 5000;

function remove_alert() {
    clearTimeout(alertTimeout);
    alertTimeout = setTimeout(function () {
        $(".custom-alert").stop().fadeOut(function () {
            $(this).remove()
        })
    }, delay)
}

function custom_alert(a, b) {
    $(".custom-alert").remove();
    $alert = $("<div>" + a + "</div>");
    $alert.addClass("custom-alert");
    if (b == "success") {
        $alert.addClass("custom-alert-success")
    }
    if (b == "error") {
        $alert.addClass("custom-alert-warning")
    }
    $("body").append($alert);
    $alert.fadeIn(function () {
        remove_alert()
    })
};