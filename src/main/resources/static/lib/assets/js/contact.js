function contact_state(a) {
    if (a == "disable") {
        $("#contact-btn").removeClass("btn-loading");
        $("#contact-btn").removeClass("active");
        $("#contact-btn").addClass("disabled");
        $("#contact-form .form-control").each(function () {
            $(this).addClass("disabled")
        })
    }
    if (a == "loading") {
        $("#contact-btn").addClass("btn-loading")
    }
}

$(function () {
    this.sended = false;
    var c = this;
    var a = $("#contact-form"), b = "Message Send", d = "Something wrong! Please try later";
    console.log(a);
    a.on("submit", function (e) {
        contact_state("loading");
        if (!c.sended) {
            $.ajax({
                url: "php/form.php", type: "POST", data: a.serialize(), success: function (g) {
                    var f = JSON.parse(g);
                    if (f.status == "success") {
                        custom_alert(b, "success");
                        contact_state("disable")
                    } else {
                        custom_alert(d, "error");
                        contact_state("disable")
                    }
                }, error: function (f) {
                    custom_alert(d, "error");
                    contact_state("disable")
                },
            });
            c.sended = true
        }
        e.preventDefault()
    })
});