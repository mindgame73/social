$(document).on('click','.ajax-post-create', function () {
    $('.add-post')[0].reset();
    $('#create-modal-post').modal();
});

$('#btn-create-post').click(function () {
    var data = {};
    data['title'] = $('#c_title').val();
    data['content'] = $('#c_content').val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/post/create",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 600000,
        success: function (p) {
            $('#create-modal-post').modal('hide');
            window.location.href = "/index"
        }
    });

});

$('.upvote').click(function () {
    var id = $(this).parents('div').attr('id');

    $.ajax({
        type: "GET",
        url: "/post/upvote",
        data: {post_id : id, upvote: true},
        success: function (p) {
            $('#'+id).children('.upvote').addClass('upvote-on');
        }
    });
});

$('.downvote').click(function () {
    var id = $(this).parents('div').attr('id');

    $.ajax({
        type: "GET",
        url: "/post/upvote",
        data: {post_id : id, upvote: false},
        success: function (p) {
            $('#'+id).children('.downvote').addClass('downvote-on');
        }
    });
});


