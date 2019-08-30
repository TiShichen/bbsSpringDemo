function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_area").val();
    var user = $("session_user").val();

    if (user == null) {
        alert("User haven't logged in, we do not allow users to comment without logging in, for nuclear security reason.");
        return;
    }

    if (!content) {
        alert("Comment content cannot be empty, say something my friend.");
        return;
    }

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
                window.location.reload();
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