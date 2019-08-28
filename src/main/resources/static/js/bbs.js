function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_area").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                $("#comment_section").hide();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm("Press confirm to proceed to login, please allow pop-up." + "\n\n" + response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=30db3d189a78ee7c7558&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}