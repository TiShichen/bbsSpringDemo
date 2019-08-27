function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_area").val();
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function (response) {
            console.log(response);
        },
        dataType: "json"
    });
    console.log(questionId);        // = System.out.print()
    console.log(content);
}